package Tree;

class TreeNodeLCA {
    int val;
    TreeNodeLCA left;
    TreeNodeLCA right;
    TreeNodeLCA(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeLCA

public class LeastCommonAncestor {
/**Question requirement:1) Duplicates don't exist. 2) One or both of the nodes may not exist 3) Parent pointer cannot be used, but
* other node members/functions can be added to node, or use a helper functions or extra memory */

    class Utility{
        int value;
    }
public int lca(TreeNodeLCA A, int B, int C) {
    Utility utility=new Utility();
    utility.value=-1;


    recurseLCA(A,B,C,utility);

    return utility.value;
}//lca

boolean recurseLCA(TreeNodeLCA root, int B, int C,Utility utility){
/**What if we find a node, but the other node exists in its left or right subtree? If we return from the node,
* we will miss the other one. So do a POST order traversal. */
    boolean left=false;
    boolean right=false;

    if(root.left!=null){
        left=recurseLCA(root.left, B,  C,utility);
    }

    if(root.right!=null){
        right=recurseLCA(root.right, B, C,utility);
    }

//Ordering is important. First compare the AND condition, then the OR condition. Else, OR condition will return true;
    if(left && right){
        //if one is in the left and the other is in the right subtree, that is our result
        utility.value=root.val;
        return true;
    }

    //The below 2 conditions are independent of each other.
    //First club them
    if((left|| right) && (root.val==B || root.val==C)){
        //if root itself is one of the value and the other value is in one of its subtrees
        //update the utility
        utility.value=root.val;
        return true;
    }

    if(left|| right){
        //if one of the 2 is in its left or right subtree,  propagate that up as true
        return true;
    }

    if(root.val==B || root.val==C){
        //if the root itself is B or C, return true
        if(B==C){
            //if the values of B and C are the same
            utility.value=root.val;
        }
        return true;
    }


    return false;
}//recurseLCA

public static void main(String[] args) {
    TreeNodeLCA root=new TreeNodeLCA(3);
    root.left=new TreeNodeLCA(5);
    root.right=new TreeNodeLCA(1);
    root.left.left=new TreeNodeLCA(6);
    root.left.right=new TreeNodeLCA(2);
    root.right.left=new TreeNodeLCA(0);
    root.right.right=new TreeNodeLCA(8);
    root.left.right.left=new TreeNodeLCA(7);
    root.left.right.right=new TreeNodeLCA(4);

    int val1=6;
    int val2=41;
    int result=new LeastCommonAncestor().lca(root,val1,val2);
    System.out.println(result);
}//main
}//LeastCommonAncestor
