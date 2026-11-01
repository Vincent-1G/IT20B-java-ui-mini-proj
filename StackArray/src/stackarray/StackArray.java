package stackarray;

public class StackArray {

    private int[] stack;
    private int top;
    private int capacity;
      
    public void display(){
        if (isEmpty()){
            System.out.println("Stack is Empty.");
            return;
        }
    }
    
    public boolean isFull(){
        return top == capacity -1;
    }
    
      public boolean isEmpty(){
        return top == -1;
      }
      
      public int peek(){
         if(isEmpty()){
             System.out.println("Stack is empty. ");
             return -1;
         }
         return stack[top];
      }
      
      
      
      
      
        public int pop(){
         if(isEmpty()){
             System.out.println("Stack Overflow Stack is full!");
             return -1;
         }
         int value = stack[top];
         top--;
         
         return value;

    }
    
     public void push(int value){
         if(isFull()){
             System.out.println(" Stack Overflow Stack is full! ");
             return;

        }
         top++;
         stack[top]= value;
         System.out.println(value + " Push into the stack! ");
     }
    
    public StackArray(int capacity){
        this.capacity = capacity;
        stack = new int[capacity];
        top = -1;
    }
    public static void main(String[] args) {
      StackArray stack =  new StackArray(5);
      
      stack.push(10);
      stack.push(20);
      stack.push(30);
      stack.push(40);
    
      
        System.out.println();
        stack.display();
        
        
        
        System.out.println("Top element: " +  stack.peek());
        System.out.println();
        
        System.out.println("Removed: " + stack.pop());
        System.out.println();
    
        System.out.println("Top element: " +  stack.peek());
        System.out.println();
        
        stack.display();
        
    }
    
}
