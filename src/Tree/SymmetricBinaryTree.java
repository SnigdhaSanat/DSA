package Tree;

//  Definition for binary tree
  class TreeNodeSymm {
    int val;
    TreeNodeSymm left;
    TreeNodeSymm right;
    TreeNodeSymm(int x) {
       val = x;
       left=null;
       right=null;
      }
  }

public class SymmetricBinaryTree {

public int isSymmetric(TreeNodeSymm A) {
boolean result=false;
    if(A==null){
        //if A is null, return true
        return 1;
    }
    TreeNodeSymm left=A.left;
    TreeNodeSymm right=A.right;

    result=checkIfMirror(left,right);


   return result?1:0;
}//isSymmetric

boolean checkIfMirror(TreeNodeSymm root1, TreeNodeSymm root2){
        /**Traverse both in INORDER. Why? It logs the exact positions, whether it is an RC or LC.But travel
    one in L N R(normal), and other as R N L. At every point, check if current roots are same */

//Either both root1 and root2 are null, or both non-null

//If both roots are null, return true
    if(root1==null && root2==null){
        return true;
    }
    //if only one of them is null, return false
    else if ((root1!=null && root2==null)||(root1==null && root2!=null)) {
        return false;
    }

    else if(root1.val!=root2.val){
        //if root values don't match, return false
        return false;
    }

    //At this point, both root1 and root2 are non null and have the same value

    boolean checkLeft=true;
    boolean checkRight=true;

    //BOTH left and right are non-null, or both null
    checkLeft= checkIfMirror(root1.left, root2.right);
    if(!checkLeft){
        return false;
    }

    //BOTH left and right are non-null, or both null
    checkRight=checkIfMirror(root1.right,root2.left);
    if(!checkRight){
        return false;
    }

    //for other conditions return true
    return true;
}//checkIfMirror

public static void main(String[] args) {
    TreeNodeSymm root=new TreeNodeSymm(1);
    root.left = new TreeNodeSymm(2);
    root.right = new TreeNodeSymm(2);
    root.left.left = new TreeNodeSymm(3);
    root.left.right = new TreeNodeSymm(4);
    root.right.left = new TreeNodeSymm(4);
    root.right.right = new TreeNodeSymm(3);

    int res=new SymmetricBinaryTree().isSymmetric(root);
    System.out.println(res);
}//main
}//SymmetricBinaryTree
