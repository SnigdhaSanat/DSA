package Tree;

import java.math.BigInteger;

class TreeNodePathSum {
    int val;
    TreeNodePathSum left;
    TreeNodePathSum right;
    TreeNodePathSum(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeSumRootToLeaf

public class PathSum {
class NodeUtility{
    int s;
    boolean exists;
}

public int hasPathSum(TreeNodePathSum A, int B) {
    NodeUtility nodeUtility=new NodeUtility();
    nodeUtility.s=0;
    nodeUtility.exists=false;

    recurseSum(A,B,nodeUtility);

    //after recursion
    boolean res=nodeUtility.exists;
    return res?1:0;
}//hasPathSum

void recurseSum( TreeNodePathSum root,int B, NodeUtility nodeUtility){
    //if path found, no need to recurse further
//    if(nodeUtility.exists){
//        return;
//    }

    //add root's val to the sum
    nodeUtility.s+=root.val;

    //if root is leaf, add the sum
    if(root.left==null && root.right==null){
        int sum=(nodeUtility.s);
        if(sum==B){
            nodeUtility.exists=true;
        }
    }//if

    //recurse
    if( root.left!=null){
        recurseSum(root.left,B,nodeUtility);
    }
    if( root.right!=null){
        recurseSum(root.right,B,nodeUtility);
    }

    //deduct the curr root's value from the sum
    nodeUtility.s-=root.val;
    return;

}//recurseSum

    public static void main(String[] args) {
        TreeNodePathSum root=new TreeNodePathSum(5);
        root.left = new TreeNodePathSum(4);
        root.right = new TreeNodePathSum(8);
        root.left.left= new TreeNodePathSum(11);
        root.right.left= new TreeNodePathSum(13);
        root.right.right= new TreeNodePathSum(4);
        root.left.left.left= new TreeNodePathSum(7);
        root.left.left.right= new TreeNodePathSum(2);
        root.right.right.right= new TreeNodePathSum(1);

        int B=22;

        int res= new PathSum().hasPathSum(root,B);
        System.out.println(res);
    }//main
}//PathSum
