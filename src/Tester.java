import java.util.Arrays;

public class Tester {
    public static void main(String args[])
    {
        Object[] inOrderSequence = { 9, 5, 1, 7, 2, 12, 8, 4, 3, 11};
        Object[] postOrderSequence = { 9, 1, 2, 12, 7, 5, 3, 11, 4, 8 };

        System.out.println("Input inOrderSequence[] : " + Arrays.toString(inOrderSequence));
        System.out.println("Input postOrderSequence[] : " + Arrays.toString(postOrderSequence));
        System.out.println();

        BinaryTree mytree = BinaryTree.buildTree(inOrderSequence, postOrderSequence);
        System.out.print("Output inOrderTraversal() : ");
        mytree.inorderTraversal();
        System.out.print("Output postOrderTraversal() : ");
        mytree.postorderTraversal();
    }
}
