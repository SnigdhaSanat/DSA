package Tree;


class TreeNodeSortedArrayToBalanced {
    int val;
    TreeNodeSortedArrayToBalanced left;
    TreeNodeSortedArrayToBalanced right;
    TreeNodeSortedArrayToBalanced(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeSortedArrayToBalanced


public class SortedArrayToBalancedBST {
    /**Note that this is a sorted array. So its middle element(or one of the 2 in case of even #nodes) will be the root of the tree.
     * Now, split it into left half and right half, leaving out the index in the middle. Those will be the left and right subtrees.
     * Recursively call them, and assign them to (root.left) and (root.right). Return root. Do this recursively, meaning within each subtree,
     * find the middle, assign it to the root, and then call the left and right halves of arrayList and assign them to its left and right.
     * Return root from each level*/

    public TreeNodeSortedArrayToBalanced sortedArrayToBST(final int[] A) {
        int len=A.length;

        TreeNodeSortedArrayToBalanced resultRoot=recursivelyBuild(A,0,len-1);//pass the index, not size
        return resultRoot;
    }//sortedArrayToBST



    TreeNodeSortedArrayToBalanced recursivelyBuild(int[] A, int start, int end){
        if(A.length==1){
            TreeNodeSortedArrayToBalanced root=new TreeNodeSortedArrayToBalanced(A[start]);
            return root;
        }
        int rootIndex=(start+end)/2;

        TreeNodeSortedArrayToBalanced root=new TreeNodeSortedArrayToBalanced(A[rootIndex]);
        if(rootIndex!=start){
            root.left=recursivelyBuild(A,start,rootIndex-1);
        }
        else{
            root.left=null;
        }

        if(rootIndex!=end){
            root.right=recursivelyBuild(A,rootIndex+1,end);
        }
        else{
            root.right=null;
        }

        return  root;
    }//recursivelyBuild

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6};
        new SortedArrayToBalancedBST().sortedArrayToBST(arr);
    }//main
}//SortedArrayToBalancedBST
