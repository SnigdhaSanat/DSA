package Tree;

class TreeNodeInvertBT {
    int val;
    TreeNodeInvertBT left;
    TreeNodeInvertBT right;
    TreeNodeInvertBT(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeLCA


public class InvertTheBinaryTree {

public TreeNodeInvertBT invertTree(TreeNodeInvertBT A) {
    TreeNodeInvertBT res=recursivelyInvert(A);
    return res;
}//invertTree

TreeNodeInvertBT recursivelyInvert(TreeNodeInvertBT root){
    //Exchange can be done either before or after recursing
    //Exchange left and right children at the root.
    // This covers the case of both being non-nulls, both being null, or only of them being null
    TreeNodeInvertBT temp=root.left;
    root.left=root.right;
    root.right=temp;

    //recurse left and right
    if(root.left!=null){
        recursivelyInvert(root.left);
    }

    if(root.right!=null){
        recursivelyInvert(root.right);
    }

//    //Exchange left and right children at the root.
//    // This covers the case of both being non-nulls, both being null, or only of them being null
//    TreeNodeInvertBT temp=root.left;
//    root.left=root.right;
//    root.right=temp;

    return  root;
}//recursivelyInvert


public static void main(String[] args) {
    TreeNodeInvertBT root=new TreeNodeInvertBT(1);
    root.right=new TreeNodeInvertBT(2);

//    root.right=new TreeNodeInvertBT(3);
//    root.left.left=new TreeNodeInvertBT(4);
//    root.left.right=new TreeNodeInvertBT(5);
//    root.right.left=new TreeNodeInvertBT(6);
//    root.right.right=new TreeNodeInvertBT(7);

new InvertTheBinaryTree().invertTree(root);

}//main
}//InvertTheBinaryTree
