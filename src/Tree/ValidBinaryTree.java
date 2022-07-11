package Tree;

import java.util.ArrayList;

class TreeNodeValid {
    int val;
    TreeNodeValid left;
    TreeNodeValid right;
    TreeNodeValid(int x) {
        val = x;
        left=null;
        right=null;
    }
}

class Utillity{
    ArrayList<Integer> al;
}

public class ValidBinaryTree {


    public int isValidBST(TreeNodeValid A) {
        ///traverse in order, and put the nodes in the ArrayList


        boolean res=recurseInorder(A,null);

        //after recursion

        return res?1:0;

    }//isValidBST

    boolean recurseInorder(TreeNodeValid root, TreeNodeValid prev){
        boolean leftCheck=true;
        boolean rightCheck=true;

        //left
        if(root.left!=null){
            leftCheck=recurseInorder(root.left,prev);
        }
        if(!leftCheck)
        {
            return false;
        }

        //root

        if(prev!=null && prev.val>=root.val){
            return false;
        }

        prev=root;


        //right
        if(root.right!=null){
            rightCheck=recurseInorder(root.right,prev);
        }
        if(!rightCheck)
        {
            return false;
        }

        return true;
    }//recurseInorder

    public static void main(String[] args) {
        TreeNodeValid root=new TreeNodeValid(4);
        root.left=new TreeNodeValid(2);
        root.right=new TreeNodeValid(5);
        root.left.left=new TreeNodeValid(1);
        root.left.right=new TreeNodeValid(5);

        int res=new ValidBinaryTree().isValidBST(root);
        System.out.println(res);
    }
}//ValidBinaryTree
