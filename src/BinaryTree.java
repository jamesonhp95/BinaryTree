import java.util.ArrayList;

public class BinaryTree {
    private Node root;

    private class Node
    {
        private Object data;
        private Node right;
        private Node left;

        Node(Object d)
        {
            this.data = d;
            this.right = null;
            this.left = null;
        }
    }

    public BinaryTree()
    {
        this.root = null;
    }

    /**
        This is the method that is called statically to return a BinaryTree. This method
        creates a new BinaryTree and then calls the helper method to start building it from the
        inOrderSequence and postOrderSequences passed in.
     */
    static BinaryTree buildTree(Object inOrderSequence[], Object postOrderSequence[])
    {
        BinaryTree retTree = new BinaryTree();
        retTree.buildTreeHelper(inOrderSequence, postOrderSequence);
        return retTree;
    }

    /**
        This method merely starts the process of the buildTreeRecursion. We set the root to be equal to the node
        that is returned from buildTreeRecursion(...)
     */
    private void buildTreeHelper(Object inOrderSequence[], Object postOrderSequence[])
    {
        this.root = buildTreeRecursion(inOrderSequence, postOrderSequence);
    }

    /**
        This method has the base case if the length of the inOrderSequence or the postOrderSequence length equals 0, it returns null.
        Otherwise it gets the data from the last index of the postOrderSequence (Since we know thats the root), and constructs the node
        for this new root. Then it checks the data against the index data of the inOrderSequence until it finds a match and then
        copies the arrays into new arrays based on the index, grabbing all the left objects from the index into the new
        inOrderSequenceLeft[], and all of the right objects from the index that matches in the inOrderSequenceRight[]. Then it
        grabs the first x-1 objects in the postOrderSequence[] where x = the index where the root was found in the inOrderSequence[].
        This will grab the left subtree of the postOrderSequence. Then we grab the next values up to the length of the postOrderSequence
        -1 for the right subtree of the postOrderSequence.
        Then it sends these in pairs of inOrderSequenceLeft, postOrderSequenceLeft & inOrderSequenceRight, postOrderSequenceRight recursively
        and assigns root.left to be equal to the return root node of the left subtree, and root.right to be the return root node of the right subtree.
        Finally it returns the root node to the entire BinaryTree.
     */
    private Node buildTreeRecursion(Object inOrderSequence[], Object postOrderSequence[])
    {
        if(inOrderSequence.length == 0 || postOrderSequence.length == 0) { return null; }
        Object rData = postOrderSequence[postOrderSequence.length-1];
        Node root = new Node(rData);

        Object indexData = null;
        int x = 0;
        while(rData != indexData)
        {
            indexData = inOrderSequence[x];
            x++;
        }

        Object inOrderSequenceLeft[] = new Object[x-1];
        Object inOrderSequenceRight[] = new Object[inOrderSequence.length - x];
        Object postOrderSequenceLeft[] = new Object[x-1];
        Object postOrderSequenceRight[] = new Object[postOrderSequence.length - x];

        System.arraycopy(inOrderSequence, 0, inOrderSequenceLeft, 0, x-1);
        System.arraycopy(inOrderSequence, x, inOrderSequenceRight, 0, inOrderSequence.length - x);
        System.arraycopy(postOrderSequence, 0, postOrderSequenceLeft, 0, x-1);
        System.arraycopy(postOrderSequence, x-1, postOrderSequenceRight, 0, postOrderSequence.length - x);

        root.left = buildTreeRecursion(inOrderSequenceLeft, postOrderSequenceLeft);
        root.right = buildTreeRecursion(inOrderSequenceRight, postOrderSequenceRight);
        return root;
    }

    /**
        postorderTraversal sets up an arraylist for the printPostorder to use and set up the proper
        order of what should be printed.
     */
    public void postorderTraversal()
    {
        ArrayList<Object> toPrint = new ArrayList();
        printPostorder(root, toPrint);
        System.out.println(toPrint);
    }
    /**
        printPostorder has the base case where if the node is null, it returns, otherwise it recursively
        sends node.left to printPostorder, and node.right to printPostoder. After these have finished, the node
        data is added to the arraylist that will print the postorder of the tree.
     */
    private void printPostorder(Node node, ArrayList<Object> aList)
    {
        if(node == null) { return; }
        printPostorder(node.left, aList);
        printPostorder(node.right, aList);
        aList.add(node.data);
    }

    /**
        inorderTraversal sets up the arraylist for printing, and passes the root node as well as the arraylist
        to the printInorderTraversal method.
     */
    public void inorderTraversal()
    {
        ArrayList<Object> toPrint = new ArrayList();
        printInorderTraversal(root, toPrint);
        System.out.println(toPrint);
    }

    /**
        printInorderTraversal has the base case where if the node is null it returns, otherwise it visits the left subtree
        and then after completely visiting the left subtree, the current nodes data is added to the arraylist. Afterwards,
        the right subtree is sent to printInorderTraversal.
     */
    private void printInorderTraversal(Node node ,ArrayList<Object> aList)
    {
        if(node == null) { return; }
        printInorderTraversal(node.left, aList);
        aList.add(node.data);
        printInorderTraversal(node.right, aList);
    }
}
