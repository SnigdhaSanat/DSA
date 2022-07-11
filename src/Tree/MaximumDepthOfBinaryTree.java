package Tree;
//Definition for binary tree
class TreeNodeMaxDepth {
    int val;
    TreeNodeMaxDepth left;
    TreeNodeMaxDepth right;
    TreeNodeMaxDepth(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeMaxDepth

public class MaximumDepthOfBinaryTree {
/*Calculate recursively in in-order traversal. Recurse left, get LH, and then right, get RH. Get the max between them, and
propagate it up the tree to the root.*/

public int maxDepth(TreeNodeMaxDepth A) {
int res=checkMaxDepth(A);
return res;
}//maxDepth

int  checkMaxDepth(TreeNodeMaxDepth root){
    //Initialize. if left/right subtree is null, it stays as the initialized values
    int leftHt=0;
    int rightHt=0;

    //recurse left
    if(root.left!=null){
        leftHt=checkMaxDepth(root.left);
    }

    //recurse right
    if(root.right!=null){
        rightHt=checkMaxDepth(root.right);
    }

    int max=Math.max(leftHt,rightHt);
        return max+1;
}//checkBalanced

public static void main(String[] args) {
        TreeNodeMaxDepth root=new TreeNodeMaxDepth(1);
        root.left = new TreeNodeMaxDepth(2);
//        root.right = new TreeNodeMaxDepth(7);
//        root.left.left=new TreeNodeMaxDepth(2);
//        root.left.right= new TreeNodeMaxDepth(5);
//        root.right.left= new TreeNodeMaxDepth(8);
//        root.right.left.left= new TreeNodeMaxDepth(9);

        int height=new MaximumDepthOfBinaryTree().maxDepth(root);
        System.out.println(height);

    }//main
}//MaximumDepthOfBinaryTree
