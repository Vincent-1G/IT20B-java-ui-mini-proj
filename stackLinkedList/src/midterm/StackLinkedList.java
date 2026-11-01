package midterm;

public class StackLinkedList {
    
    
public void display() {
    
    if (isEmpty()) {
        System.out.println("Stack is empty.");
        return;
    }
    
    Node current = top;
    
    System.out.println("Stack elements:");
    
    while (current != null) {
        System.out.println(current.data);
        current = current.next;
        }
    }
    
    
public boolean isEmpty() {
    return top == null;
}
    
   
public int peek() {
    
    if (isEmpty()) {
        System.out.println("Stack is empty.");
        return -1;
    }
    
    return top.data;
}
    
    
public int pop() {
    
    if (isEmpty()) {
        System.out.println("Stack Underflow! Stack is empty.");
        return -1;
    }
    
    int value = top.data;
    
    
    top = top.next;
    
    return value;
}
    
    public void push(int value) {
    
   
    Node newNode = new Node(value);
    
   
    newNode.next = top;
    
    
    top = newNode;
    
    System.out.println(value + " pushed into the stack.");
}

    
    private class Node {

        int data;
        Node next;

        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    
    private Node top;

    
    public StackLinkedList() {
        top = null;
    }

    
    public static void main(String[] args) {

        
        StackLinkedList stack = new StackLinkedList();

        
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        System.out.println();

        
        stack.display();

       
        System.out.println("Top element: " + stack.peek());

        
        System.out.println("Removed: " + stack.pop());

       
        System.out.println("Top element: " + stack.peek());

       
        stack.display();
    }
}