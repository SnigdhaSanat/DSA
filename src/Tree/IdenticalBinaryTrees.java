package Tree;

class TreeNodeIdentical {
    int val;
    TreeNodeIdentical left;
    TreeNodeIdentical right;
    TreeNodeIdentical(int x) {
        val = x;
        left=null;
        right=null;
    }
}

public class IdenticalBinaryTrees {

public int isSameTree(TreeNodeIdentical A, TreeNodeIdentical B) {
    boolean result=false;

    if((A!=null && B==null) ||(A==null && B!=null)){
        //if only one of them exists, return false
        return 0;
    }
    else if(A==null && B==null){
        //if BOTH are null, return true
        return 1;
    }
    else{
        //if both exists, check if the subtrees rooted at A.left and A.right are mirrors of each other
        result=checkIfEqual(A,B);
    }

    return result?1:0;
}//isSameTree

boolean checkIfEqual(TreeNodeIdentical root1, TreeNodeIdentical root2){
/*Traverse both in INORDER. Why? It logs the exact positions, whether it is an RC or LC. Travel
BOTH in L N R(normal). At every point, check if current roots are same */

    //Either both root1 and root2 are null, or both non-null

    if(root1==null && root2==null)
    {
        return true;
    }


    if(root1.val!=root2.val){
        //if root values don't match, return false
        return false;
    }

    //Now the children
    if(root1.left!=null && root2.left==null ||root1.left==null && root2.left!=null ||
            root1.right!=null && root2.right==null ||root1.right==null && root2.right!=null){
        return false;
    }

    boolean checkLeft=true;
    boolean checkRight=true;


    checkLeft= checkIfEqual(root1.left, root2.left);

    if(!checkLeft){
        return false;
    }


    checkRight=checkIfEqual(root1.right,root2.right);


    if(!checkRight){
        return false;
    }

    return true;
}//checkIfMirror

    public static void main(String[] args) {
        TreeNodeIdentical root1=new TreeNodeIdentical(1);
        root1.left = new TreeNodeIdentical(2);
        root1.right = new TreeNodeIdentical(3);

        TreeNodeIdentical root2=new TreeNodeIdentical(1);
        root2.left = new TreeNodeIdentical(2);
        root2.right = new TreeNodeIdentical(5);

        int res=new IdenticalBinaryTrees().isSameTree(root1,root2);
        System.out.println(res);
    }//main

}//IdenticalBinaryTrees
