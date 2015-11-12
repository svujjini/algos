
import java.util.*;
public class HeapsortRand {
    private static int N;
    /* Sort Function */
    public static void sort(int arr[])
    {       
        heapify(arr);        
        for (int i = N; i > 0; i--)
        {
            swap(arr,0, i);
            N = N-1;
            maxheap(arr, 0);
        }
    }     
    /* Function to build a heap */   
    public static void heapify(int arr[])
    {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);        
    }
    /* Function to swap largest element in heap */        
    public static void maxheap(int arr[], int i)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])        
            max = right;
        if (max != i)
        {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }    
    /* Function to swap two numbers in an array */
    public static void swap(int arr[], int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp; 
    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scan = new Scanner( System.in );        
	        System.out.println("Heap Sort Test\n");
	        int n, i;    
	        /* Accept number of elements */
	        System.out.println("Enter number of integer elements");
	        n = scan.nextInt();    
	        /* Make array of n elements */
	        int arr[] = new int[ n ];
	        /* Accept elements */
	        
	        Random rand = new Random(); 
	        System.out.println("\n Elements Before Sorting");
	        for ( i=0;i < n;i++)
	        {
	       arr[i] = rand.nextInt(100000);
	       System.out.print(arr[i]+" ");            
	       }
	        /* Call method sort */
	        long startTime = System.currentTimeMillis();
	        sort(arr);
	        long endTime = System.currentTimeMillis();
	        /* Print sorted Array */
	        System.out.println("\nElements after sorting ");
	        System.out.println("Start Time : " + startTime);
	        System.out.println("End Time : " + endTime);
	        System.out.println("Total execution time : "+ (endTime - startTime));
	        for (i = 0; i < n; i++)
	            System.out.print(arr[i]+" ");            
	        System.out.println();            


	}

}

