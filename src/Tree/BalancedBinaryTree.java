package Tree;

//Definition for binary tree


class TreeNodeBalanced {
    int val;
    TreeNodeBalanced left;
    TreeNodeBalanced right;
    TreeNodeBalanced(int x) {
        val = x;
        left=null;
        right=null;
    }
}

public class BalancedBinaryTree {

public int isBalanced(TreeNodeBalanced A) {
    /*Calculate recursively in in order traversal. Recurse left, get LH, and then right, get RH. If (RH-LH) abs diff is >1, return false,
    propagate it up the tree to the root. Else, return the larger of the two heights*/
    int res=checkBalanced(A);
    if(res==-1){
        return 0;
    }
    else{
        return 1;
    }
}//isBalanced

int  checkBalanced(TreeNodeBalanced root){
    //Initialize. if left/right subtree is null, it stays as the initialized values
    int leftHt=0;
    int rightHt=0;

    //recurse left
    if(root.left!=null){
        leftHt=checkBalanced(root.left);
    }

    if(leftHt==-1){
        //propagate it up the tree till root
        return -1;
    }


    //recurse right
    if(root.right!=null){
        rightHt=checkBalanced(root.right);
    }

    if(rightHt==-1){
        //propagate it up the tree till root
        return -1;
    }

    int diff=Math.abs(leftHt-rightHt);
    if(diff<=1){
        //if balanced, return the max+1 of the two
        int max= leftHt>=rightHt?leftHt:rightHt;
        return max+1;
    }
    else{
        return -1;
    }

    }//checkBalanced

    public static void main(String[] args) {
        TreeNodeBalanced root=new TreeNodeBalanced(1);
        root.left=new TreeNodeBalanced(2);
        root.left.left=new TreeNodeBalanced(3);

        int res=new BalancedBinaryTree().isBalanced(root);
        System.out.println(res);
    }//main
}//BalancedBinaryTree
