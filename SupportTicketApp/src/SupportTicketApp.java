
class Ticket {
    String id;
    String name;
    String issue;

    Ticket(String id, String name, String issue) {
        this.id = id;
        this.name = name;
        this.issue = issue;
    }
}


class Node {
    Ticket data;
    Node next;

    Node(Ticket data) {
        this.data = data;
        this.next = null;
    }
}


// The queue. "front" is the next ticket to resolve, "rear" is the newest ticket.
//   front                      rear
//     v                          v
//   [T001] --> [T002] --> [T003] --> null
class TicketQueue {
    private Node front = null;
    private Node rear = null;
    private int size = 0;

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    // ENQUEUE: add a ticket at the back
    void enqueue(Ticket t) {
        Node newNode = new Node(t);
        if (rear == null) {          // queue was empty
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;     // old last node points to the new node
            rear = newNode;          // new node is now the last
        }
        size++;
    }

    // DEQUEUE: remove and return the front ticket (null if empty)
    Ticket dequeue() {
        if (front == null) {
            return null;
        }
        Ticket t = front.data;
        front = front.next;          // move front to the second node
        if (front == null) {         // queue is now empty
            rear = null;
        }
        size--;
        return t;
    }

    // Get the ticket at a position (0 = front)
    Ticket get(int position) {
        Node current = front;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }

    // LINEAR SEARCH: check each node from the front until the ID matches.
    // Returns the position (0 = front) or -1 if not found.
    int search(String id) {
        Node current = front;
        int position = 0;
        while (current != null) {
            if (current.data.id.equalsIgnoreCase(id)) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }
}


// The window (the only JFrame)
public class SupportTicketApp extends javax.swing.JFrame {

    TicketQueue queue = new TicketQueue();

    javax.swing.JTextField idField = new javax.swing.JTextField();
    javax.swing.JTextField nameField = new javax.swing.JTextField();
    javax.swing.JTextField issueField = new javax.swing.JTextField();
    javax.swing.JTextField searchField = new javax.swing.JTextField();

    // Table with read-only cells
    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new String[]{"Position", "Ticket ID", "Name", "Issue"}, 0) {
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    javax.swing.JTable table = new javax.swing.JTable(model);

    javax.swing.JLabel message = new javax.swing.JLabel("Register a ticket to begin.");

    public SupportTicketApp() {
        setTitle("Support Ticket Queue");
        setSize(600, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);  // we place every component by hand: x, y, width, height

        addLabel("Ticket ID:", 20, 20);
        addLabel("Name:", 20, 55);
        addLabel("Issue:", 20, 90);
        addLabel("Search ID:", 20, 165);

        idField.setBounds(130, 20, 200, 25);
        nameField.setBounds(130, 55, 200, 25);
        issueField.setBounds(130, 90, 200, 25);
        searchField.setBounds(130, 165, 200, 25);

        javax.swing.JButton registerButton = new javax.swing.JButton("Register Ticket");
        javax.swing.JButton resolveButton = new javax.swing.JButton("Resolve Next Ticket");
        javax.swing.JButton searchButton = new javax.swing.JButton("Search");
        registerButton.setBounds(130, 125, 200, 25);
        resolveButton.setBounds(350, 125, 200, 25);
        searchButton.setBounds(350, 165, 100, 25);

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(table);
        scroll.setBounds(20, 205, 545, 180);
        message.setBounds(20, 395, 545, 25);

        add(idField);
        add(nameField);
        add(issueField);
        add(searchField);
        add(registerButton);
        add(resolveButton);
        add(searchButton);
        add(scroll);
        add(message);

        // Connect each button to its method
        registerButton.addActionListener(e -> registerTicket());
        resolveButton.addActionListener(e -> resolveNextTicket());
        searchButton.addActionListener(e -> searchTicket());
    }

    void addLabel(String text, int x, int y) {
        javax.swing.JLabel label = new javax.swing.JLabel(text);
        label.setBounds(x, y, 100, 25);
        add(label);
    }

    // Rebuild the table from the queue (front to back)
    void refreshTable() {
        model.setRowCount(0);
        for (int i = 0; i < queue.size(); i++) {
            Ticket t = queue.get(i);
            String position = String.valueOf(i + 1);
            if (i == 0) {
                position = "1 (next)";
            }
            model.addRow(new Object[]{position, t.id, t.name, t.issue});
        }
    }

    // REGISTER: validate, then enqueue
    void registerTicket() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String issue = issueField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || issue.isEmpty()) {
            message.setText("Please fill in all fields.");
            return;
        }
        if (queue.search(id) != -1) {
            message.setText("Ticket ID " + id + " already exists.");
            return;
        }

        queue.enqueue(new Ticket(id, name, issue));
        refreshTable();
        idField.setText("");
        nameField.setText("");
        issueField.setText("");
        message.setText("Ticket " + id + " registered. Pending: " + queue.size());
    }

    // RESOLVE: dequeue the front ticket
    void resolveNextTicket() {
        if (queue.isEmpty()) {
            message.setText("No pending tickets to resolve.");
            return;
        }
        Ticket t = queue.dequeue();
        refreshTable();
        message.setText("Resolved " + t.id + " (" + t.name + "). Pending: " + queue.size());
    }

    // SEARCH: linear search through the stored tickets
    void searchTicket() {
        String id = searchField.getText().trim();
        if (id.isEmpty()) {
            message.setText("Type a ticket ID to search.");
            return;
        }

        int position = queue.search(id);
        if (position == -1) {
            table.clearSelection();
            message.setText("NOT FOUND: no pending ticket with ID " + id);
        } else {
            Ticket t = queue.get(position);
            table.setRowSelectionInterval(position, position);  // highlight the row
            message.setText("FOUND: " + t.id + " | " + t.name + " | " + t.issue
                    + " (position " + (position + 1) + ")");
        }
    }

    public static void main(String[] args) {
        new SupportTicketApp().setVisible(true);
    }
}