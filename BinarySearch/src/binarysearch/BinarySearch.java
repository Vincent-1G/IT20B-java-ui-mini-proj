package binarysearch;

public class BinarySearch {

    public static int search(int[] arr,int target){
        
        int left = 0;
        int right = arr.length -1;
        
        while(left <= right){
            int mid = (left + right)/2;
            
            if(arr[mid] == target){
                return mid;
            }
            else if(arr[mid] < target){
                left = mid + 1;
            }else{
         right = mid -1;
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
