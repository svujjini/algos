package tree;

import java.util.ArrayList;
import java.util.Random;
/* Class BSTNode */
class BSTNode
{
    BSTNode left, right;
    int data;

    /* Constructor */
    public BSTNode()
    {
        left = null;
        right = null;
        data = 0;
    }
    /* Constructor */
    public BSTNode(int n)
    {
        left = null;
        right = null;
        data = n;
    }
    /* Function to set left node */
    public void setLeft(BSTNode n)
    {
        left = n;
    }
    /* Function to set right node */ 
    public void setRight(BSTNode n)
    {
        right = n;
    }
    /* Function to get left node */
    public BSTNode getLeft()
    {
        return left;
    }
    /* Function to get right node */
    public BSTNode getRight()
    {
        return right;
    }
    /* Function to set data to node */
    public void setData(int d)
    {
        data = d;
    }
    /* Function to get data from node */
    public int getData()
    {
        return data;
    }     
}


class BST
{
    private static final BSTNode Null = null;
	private BSTNode root;

    /* Constructor */
    public BST()
    {
        root = null;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root == null;
    }
    public void makeEmpty()

    {

        root = null;

    }
    /* Functions to insert data */
    public void insert(int data)
    {
        root = insert(root, data);
    }
    /* Function to insert data recursively */
    private BSTNode insert(BSTNode node, int data)
    {
        if (node == null)
            node = new BSTNode(data);
        else
        {
            if (data <= node.getData())
                node.left = insert(node.left, data);
            else
                node.right = insert(node.right, data);
        }
        return node;
    }
    /* Functions to delete data */
    public void delete(int k)
    {
        if (isEmpty())
            System.out.println("Tree Empty");
        else if (search(k) == false)
            System.out.println("\nSorry "+ k +" is not present");
        else
        {
            root = delete(root, k);
            System.out.println("\n" + k + " deleted from the tree");
        }
    }
    /* Functions to delete data recursively */
    private BSTNode delete(BSTNode root, int k)
    {
        BSTNode p, p2, n;
        if (root.getData() == k)
        {
            BSTNode lt, rt;
            lt = root.getLeft();
            rt = root.getRight();
            if (lt == null && rt == null)
                return null;
            else if (lt == null)
            {
                p = rt;
                return p;
            }
            else if (rt == null)
            {
                p = lt;
                return p;
            }
            else
            {
                p2 = rt;
                p = rt;
                while (p.getLeft() != null)
                    p = p.getLeft();
                p.setLeft(lt);
                return p2;
            }
        }
        if (k < root.getData())
        {
            n = delete(root.getLeft(), k);
            root.setLeft(n);
        }
        else
        {
            n = delete(root.getRight(), k);
            root.setRight(n);             
        }
        return root;
    }
    /* Functions to search for an element */
    public boolean search(int val)
    {
        return search(root, val);
    }
    /* Function to search for an element recursively */
    private boolean search(BSTNode r, int val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            int rval = r.getData();
            if (val < rval)
                r = r.getLeft();
            else if (val > rval)
                r = r.getRight();
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    /*Function to search height*/
    public void height(){
   	 
    	int h = height(root);
    	System.out.println(h);
     }
     public int height(BSTNode n)
     {
        if (n == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int leftDepth = height(n.getLeft());
            int rightDepth = height(n.getRight());
      
            /* use the larger one */
            if (leftDepth > rightDepth)
                return(leftDepth+1);
            else 
            	return(rightDepth+1);
        }
     } 
     /*Functions for traversals*/
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(BSTNode r)
     {
         if (r != null)
         {
             inorder(r.getLeft());
             System.out.println(r.getData() +" ");
             inorder(r.getRight());
         }
     }
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(BSTNode r)
     {
         if (r != null)
         {
             System.out.println(r.getData() +" ");
             preorder(r.getLeft());             
             preorder(r.getRight());
         }
     }
    
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(BSTNode r)
     {
         if (r != null)
         {
             postorder(r.getLeft());             
             postorder(r.getRight());
             System.out.println(r.getData() +" ");
         }
     }     
 }
 
 /* Class BinarySearchTree */

public class BinarySearchTree {
	 public static void main(String[] args)
	    {                 
	        /* Creating object of BST */
	        BST bst = new BST(); 
	        System.out.println("Binary Search Tree Test\n");          
	        
	        /*  Perform tree operations  */
	        
	        //int[] inputSize = {100, 500, 1000, 10000};
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
	   	              bst.insert(number);
	            }
	            System.out.println("\nPre order : ");
                bst.preorder();
                bst.makeEmpty();
	            
	           System.out.println("\n** Step-2 : Performing 2n operations of insertion, deletion and search with probabilities 0.3, 0.2 and 0.5 **\n");
	            
	            int operationSize = 2*n;
	           
	            int operationCounter = 0;
	            System.out.println("\nHeight:");
	            bst.height();
	           
                /* First 30% Insertions */
            	for(int j = 0; j < (3*operationSize)/10; j++){
            		bst.insert(rand.nextInt(20000));
            		operationCounter++;
            		if(operationCounter%5 == 0){
	            		System.out.println("\nHeight:");
	    	            bst.height();
	            	}
            	}
            	System.out.println("\nPre order : ");
                bst.preorder();	
                
            	/* Next 20% Deletions */
            	for(int j = (3*operationSize)/10; j < operationSize/2; j++){
            		bst.delete(rand.nextInt(20000));
            		operationCounter++;
            		if(operationCounter%5 == 0){
	            		System.out.println("\nHeight:");
	    	            bst.height();
	            	}
            	}
            	
            	/* Next 50% Search Operations */
            	for(int j = operationSize/2; j < operationSize; j++){
            		int searchNumber = rand.nextInt(20000);
            		System.out.println("\n Element to be searched:" + searchNumber);
            		System.out.println("Search result : " + bst.search(searchNumber));
            		operationCounter++;
            		if(operationCounter%5 == 0){
	            		System.out.println("\nHeight:");
	    	            bst.height();
	            	}
            	}	    	
	            
	        }

	    }
}
