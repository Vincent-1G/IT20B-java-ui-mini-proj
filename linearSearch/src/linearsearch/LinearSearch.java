package linearsearch;

public class LinearSearch {

    public static int search(int[] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            
            if(arr[i] == target){
                 return i;
         }
      }
         return -1;
    }

    
    public static void main(String[] args) {
        // TODO code application logic here
        int arr[] = {1,2,3,4,5};
        
        search(arr,4);
        
        int result = search(arr, 4);
        System.out.println(result);
        
        if (result >= 0) {
            System.out.println("Found at index: " + result + " = " + arr[result]);
        }else{
            System.out.println("Element not found");
        }
    }
    
}
