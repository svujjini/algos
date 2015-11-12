

import java.util.*;

public class QuickSortCRand {

   
    public static void main(String[] args) {
    	// TODO auto-generated method stub
	    Scanner scan = new Scanner( System.in );        
        System.out.println("Quick Sort Test\n");
        int n, i;

    	System.out.println("Enter number of integer elements");
        n = scan.nextInt();
        /** Create array of n elements **/
        int arr[] = new int[ n ];
        /** accept elements **/
        Random rand = new Random(); 
        System.out.println("\n Elements Before Sorting");
        for ( i=0;i < n;i++)
        {
       arr[i] = rand.nextInt(100000);
       System.out.print(arr[i]+" ");            
       }
        /* Call method sort */
        long startTime = System.currentTimeMillis();
        quickSort(arr,0,arr.length-1);
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

    public static void quickSort(int[] arr, int p, int r)
    {
        if(p<r)
        {
            int q=partition(arr,p,r);
            quickSort(arr,p,q);
            quickSort(arr,q+1,r);
        }
    }

    private static int partition(int[] arr, int p, int r) {

        int x = arr[p];
        int i = p-1 ;
        int j = r+1 ;

        while (true) {
            i++;
            while ( i< r && arr[i] < x)
                i++;
            j--;
            while (j>p && arr[j] > x)
                j--;

            if (i < j)
                swap(arr, i, j);
            else
                return j;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        // TODO auto-generated method stub
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

