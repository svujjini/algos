
import java.util.*;
public class ModifiedQuickSortRand {
    /** Quick Sort function **/
	public static int[] arr;
    public static void sort(int[] arr)
    {
        quickSort(arr, 0, arr.length - 1);
    }
    /** Quick sort function **/
    public  static void quickSort(int arr[], int low, int high) 
    {
        int i = low, j = high;
        int temp;
        int pivot = median(arr, i, j);
      
        /** partition **/
        while (i <= j) 
        {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) 
            {
                /** swap **/
               swap(arr,i,j);
               i++;
               j--;
            }
        }

        /** recursively sort lower half **/
        if (low < j)
            quickSort(arr, low, j);
        /** recursively sort upper half **/
        if (i < high)
            quickSort(arr, i, high);
    
    }
	public static int median(int arr[], int low, int high) 
	{
		// TODO Auto-generated method stub
		int center=(low+high)/2;
		
		//order low & center
		if(arr[center]<arr[low])
		swap(arr,low,center);
		//order low&high
		if(arr[low]>arr[high])
		swap(arr,low,high);
		//order center & high
		if(arr[center]>arr[high])
		swap(arr,center,high);

		swap(arr,center,high-1);//put pivot on high
		return arr[high-1];//return median value
		
	}

	
    
	 private static void swap(int[] a, int i, int j) {
	        // TODO Auto-generated method stub
	        int temp = a[i];
	        a[i] = a[j];
	        a[j] = temp;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Scanner scan = new Scanner( System.in );        
        System.out.println("Quick Sort Test\n");
        int n, i;
        /** Accept number of elements **/
        System.out.println("Enter number of integer elements");
        n = scan.nextInt();
        /** Create array of n elements **/
        int arr[] = new int[ n ];
        /** Accept elements **/
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

