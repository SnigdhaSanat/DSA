package Tree;

//Definition for binary tree
class TreeNodeMinDepth {
    int val;
    TreeNodeMinDepth left;
    TreeNodeMinDepth right;
    TreeNodeMinDepth(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeMinDepth


public class MinDepthOfBinaryTree {

public int minDepth(TreeNodeMinDepth A) {
/*Calculate recursively in in-order traversal. Recurse left, get LH, and then right, get RH. Get the min between them, and
propagate it up the tree to the root.*/

    int res=checkMinDepth(A);
    return res;
}//minDepth


int  checkMinDepth(TreeNodeMinDepth root){
    /*Unlike max depth problem, for min depth cases have to be handled separately for if both RC and LC are null,
    or one of them is null, or both exists Why? Finding max depth is relatively straight forward: find the max of the LC and RCs. For min depth,
    if both are non null child, it is same, that is, find the min depth of the two. But if one of the children is null, then you have to take the
    NON-NULL child, NOT the NULL child.*/

    //Initialize
    int leftHt=0;
    int rightHt=0;

    //recurse left
    if(root.left!=null){
        leftHt=checkMinDepth(root.left);
    }

    //recurse right
    if(root.right!=null){
        rightHt=checkMinDepth(root.right);
    }

    if(root.left!=null && root.right!=null)
    {
        //if both are non null, return the min of the two
        int min=Math.min(leftHt,rightHt);
        return min+1;
    }
    else if(root.left==null && root.right==null){
        //if both are null
        return 1;
    }
    else{
        //if only one of them is null
        if(root.left!=null){
            int min=leftHt;
            return min+1;
        }
        else{
            int min=rightHt;
            return min+1;
        }
    }
}//checkMinDepth

public static void main(String[] args) {
    TreeNodeMinDepth root=new TreeNodeMinDepth(1);
    root.left = new TreeNodeMinDepth(2);

    int height=new MinDepthOfBinaryTree().minDepth(root);
    System.out.println(height);

}//main
}//MinDepthOfBinaryTree
