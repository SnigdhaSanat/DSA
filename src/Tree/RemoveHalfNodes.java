package Tree;

//Definition for binary tree
class TreeNodeRemove {
    int val;
    TreeNodeRemove left;
    TreeNodeRemove right;
    TreeNodeRemove(int x) {
        val = x;
        left=null;
        right=null;
    }
}//class TreeNode

public class RemoveHalfNodes {
public TreeNodeRemove solve(TreeNodeRemove A) {
    /**Recurse through the tree. If leaf node(no children), leave as it is
    * If only one side is null, take the only child, and make it right child of its grand parent, if the half node was a right child, else left*/
    TreeNodeRemove root=removeHalf(A);
    return root;
}//solve

TreeNodeRemove removeHalf(TreeNodeRemove root){
    //recurse
    if(root.left!=null){
        //return left subtree(modified/unmodified) and assign to root's left
        root.left=removeHalf(root.left);
    }

    if(root.right!=null){
        //return right subtree(modified/unmodified) and assign to root's right
        root.right=removeHalf(root.right);
    }

    //after recurs-ing both left and right
    if(root.left==null && root.right==null){
        //if leaf, return as it is
        return root;
    }
    else if(root.left!=null && root.right!=null){
        //if both children are present, return as it is
        return root;
    }

    else
        // if(root.left==null || root.left==null),
    {
        //If it is a half node, return the only child
        if(root.right!=null)
        {
            return root.right;
        }
        else {
            return root.left;
        }
    }//else if
}//removeHalf

public static void main(String[] args) {
    TreeNodeRemove root=new TreeNodeRemove(1);
    root.left = new TreeNodeRemove(2);
    root.right = new TreeNodeRemove(3);
    root.left.left = new TreeNodeRemove(4);

    TreeNodeRemove resultRoot=new RemoveHalfNodes().removeHalf(root);

}//main
}//RemoveHalfNodes
