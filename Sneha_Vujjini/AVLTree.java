package tree;

import java.util.ArrayList;
import java.util.Random;
/* Class AVLNode */

class AVLNode

{    

    AVLNode left, right;

    int data;

    int height;



    /* Constructor */

    public AVLNode()

    {

        left = null;

        right = null;

        data = 0;

        height = 0;

    }

    /* Constructor */

    public AVLNode(int n)

    {

        left = null;

        right = null;

        data = n;

        height = 0;

    }     

}



/* Class AVLTreeTest */

class AVLTreeTest

{

    private AVLNode root;     



    /* Constructor */

    public AVLTreeTest()

    {

        root = null;

    }

    /* Function to check if tree is empty */

    public boolean isEmpty()

    {

        return root == null;

    }

    /* Make the tree logically empty */

    public void makeEmpty()

    {

        root = null;

    }
    /* Function to insert data */

    public void insert(int data)

    {

        root = insert(data, root);

    }

    /* Function to get height of node */

    private int height(AVLNode t )

    {

        return t == null ? -1 : t.height;

    }

    /* Function to max of left/right node */

    private int max(int lhs, int rhs)

    {

        return lhs > rhs ? lhs : rhs;

    }

    /* Function to insert data recursively */

    private AVLNode insert(int x, AVLNode t)

    {

        if (t == null)

            t = new AVLNode(x);

        else if (x < t.data)

        {

            t.left = insert( x, t.left );

            if( height( t.left ) - height( t.right ) == 2 )

                if( x < t.left.data )

                    t = rotateWithLeftChild( t );

                else

                    t = doubleWithLeftChild( t );

        }

        else if( x > t.data )

        {

            t.right = insert( x, t.right );

            if( height( t.right ) - height( t.left ) == 2 )

                if( x > t.right.data)

                    t = rotateWithRightChild( t );

                else

                    t = doubleWithRightChild( t );

        }

        else

          ;  // Duplicate; do nothing

        t.height = max( height( t.left ), height( t.right ) ) + 1;

        return t;

    }
    
    public void delete(int k)
    {
        if (isEmpty())
            System.out.println("Tree Empty");
        else if (search(k) == false)
            System.out.println("Sorry "+ k +" is not present");
        else
        {
            root = delete(root, k);
            System.out.println(k+ " deleted from the tree");
        }
        
    }
 
   
	  
  private AVLNode delete(AVLNode root, int k)
    {
    	 AVLNode p,p2,n;
          if(root.data == k){
        	  AVLNode lt,rt;
        	  lt=root.left;
        	  rt=root.right;
        	  if (lt == null && rt == null)
                  return null;
        	  else if (lt == null)
              {
                  p = rt;
                  return p;
              }
        	  else
              {
                  p2 = rt;
                  p = rt;
                  while (p.left != null)
                      p = p.left;
                  p.left=lt;
                  return p2;
              }
          }
          if (k < root.data)
          {
              n = delete(root.left, k);
              root.left = n;
          }
          else
          {
              n = delete(root.right, k);
              root.right = n;             
          }
          return root;}
	

    /* Rotate binary tree node with left child */     

    private AVLNode rotateWithLeftChild(AVLNode k2)

    {

        AVLNode k1 = k2.left;

        k2.left = k1.right;

        k1.right = k2;

        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;

        k1.height = max( height( k1.left ), k2.height ) + 1;

        return k1;

    }



    /* Rotate binary tree node with right child */

    private AVLNode rotateWithRightChild(AVLNode k1)

    {

        AVLNode k2 = k1.right;

        k1.right = k2.left;

        k2.left = k1;

        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;

        k2.height = max( height( k2.right ), k1.height ) + 1;

        return k2;

    }

    /**

     * Double rotate binary tree node: first left child

     * with its right child; then node k3 with new left child */

    private AVLNode doubleWithLeftChild(AVLNode k3)

    {

        k3.left = rotateWithRightChild( k3.left );

        return rotateWithLeftChild( k3 );

    }

    /**

     * Double rotate binary tree node: first right child

     * with its left child; then node k1 with new right child */      

    private AVLNode doubleWithRightChild(AVLNode k1)

    {

        k1.right = rotateWithLeftChild( k1.right );

        return rotateWithRightChild( k1 );

    }    

    /* Functions to count number of nodes */

    public int countNodes()

    {

        return countNodes(root);

    }

    private int countNodes(AVLNode r)

    {

        if (r == null)

            return 0;

        else

        {

            int l = 1;

            l += countNodes(r.left);

            l += countNodes(r.right);

            return l;

        }

    }
    public void heights(){
      	 
    	int h = heights(root);
    	System.out.print(h);
     }
    public int heights(AVLNode n)
    {
       if (n == null)
           return 0;
       else
       {
           /* compute the depth of each subtree */
           int lDepth = height(n.left);
           int rDepth = height(n.right);
     
           /* use the larger one */
           if (lDepth > rDepth)
               return(lDepth+1);
           else 
           	return(rDepth+1);
       }
    } 

    /* Functions to search for an element */

    public boolean search(int val)

    {

        return search(root, val);

    }

    private boolean search(AVLNode r, int val)

    {

        boolean found = false;

        while ((r != null) && !found)

        {

            int rval = r.data;

            if (val < rval)

                r = r.left;

            else if (val > rval)

                r = r.right;

            else

            {

                found = true;

                break;

            }

            found = search(r, val);

        }

        return found;

    }

    /* Function for traversals */

    public void inorder()

    {

        inorder(root);

    }

    private void inorder(AVLNode r)

    {

        if (r != null)

        {

            inorder(r.left);

            System.out.println(r.data +" ");

            inorder(r.right);

        }

    }

    

    public void preorder()

    {

        preorder(root);

    }

    private void preorder(AVLNode r)

    {

        if (r != null)

        {

            System.out.println(r.data +" ");

            preorder(r.left);             

            preorder(r.right);

        }

    }

 

    public void postorder()

    {

        postorder(root);

    }

    private void postorder(AVLNode r)

    {

        if (r != null)

        {

            postorder(r.left);             

            postorder(r.right);

            System.out.println(r.data +" ");

        }

    }     

}

/* Class AVL Tree */

    public class AVLTree

    {

        public static void main(String[] args)

       {            

           

           /* Creating object of AVLTree */

           AVLTreeTest avlt = new AVLTreeTest(); 
           System.out.println("AVLTree Tree Test\n");
           
           /*  Perform tree operations  */
           
          // int[] inputSize = {100, 500, 1000, 10000}; 
           int[] inputSize = {10};
           Random rand = new Random();
           for(int n : inputSize){
               
           	System.out.println("Input Size : " + n);
           	
           	ArrayList<Integer> randNumbers = new ArrayList<>();
               
           	for(int i = 0; i < n; i++){
               	randNumbers.add(rand.nextInt(20000));
               }
               
               System.out.println("** Step-1 : Inserting n Elements **");
               
               for (int number : randNumbers){
      	              avlt.insert(number);
               }
               System.out.println("Preorder:");
               avlt.preorder();
               
               avlt.makeEmpty();
    
               System.out.println("** Step-2 : Performing 2n operations of insertion, deletion and search with probabilities 0.3, 0.2 and 0.5 **");
               
               int operationSize = 2*n;
               
               int operationCounter = 0;
	            System.out.println("\nHeight:");
	            avlt.heights();
               
               	/* First 30% Insertions */
               	for(int j = 0; j < (3*operationSize)/10; j++){
               		avlt.insert(rand.nextInt(20000));
               		operationCounter++;
            		if(operationCounter%5 == 0){
	            		System.out.println("\nHeight:");
	    	            avlt.heights();
	            	}
               	}
               	System.out.println("Preorder:");
                avlt.preorder();
                
               	/* Next 20% Deletions */
               	for(int j = (3*operationSize)/10; j < operationSize/2; j++){
               		avlt.delete(rand.nextInt(20000));
               		operationCounter++;
            		if(operationCounter%5 == 0){
	            		System.out.println("\nHeight:");
	    	            avlt.heights();
	            	}
               	}
               	
               	/* Next 50% Search Operations */
               	for(int j = operationSize/2; j < operationSize; j++){
               		int searchNumber = rand.nextInt(20000);
               		System.out.println("Element to be searched:" + searchNumber);
               		System.out.println("Search result : " +avlt.search(searchNumber));
               		operationCounter++;
            		if(operationCounter%5 == 0){
	            		System.out.println("\nHeight:");
	    	            avlt.heights();
	            	}
               	}
               	
               	
               	}
       }
}
           