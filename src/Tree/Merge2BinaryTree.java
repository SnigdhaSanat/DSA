package Tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//Definition for binary tree
  class TreeNodeMerge {
      int val;
    TreeNodeMerge left;
    TreeNodeMerge right;
    TreeNodeMerge(int x) {
       val = x;
       left=null;
       right=null;
      }
  }

public class Merge2BinaryTree {
      /*Traverse both the trees SIMULTANEOUSLY, and recurse as root1 and root2. If both are non null, traverse for left1, left2, right1 and right, which can be
      * null or non null. If both of them are null, don't traverse further. If only one of them is non null, still traverse for BOTH, but for the null node,
      * put root for next recursions as null, both for its left and right recursion. For the non null node, its left and right child can be null or non null.
      * So put its values accordingly. Effectively, it recurses for both the current roots, although one of the roots is  null. ONLY if both the roots are null,
      * no recursion happens.
      *
      * As for nodes, initialize each node as left or right child, with val as -1. When arriving at it as the root in the next recursion, update its value
      * as the sum. When returning, if the left or right child of a parent as value as -1, turn that node into null. */
    public TreeNodeMerge solve(TreeNodeMerge A, TreeNodeMerge B) {
    //initializing the root of result
    TreeNodeMerge mergedRoot=new TreeNodeMerge(-1);

    TreeNodeMerge resultRoot=traverseBoth(A, B,mergedRoot);
    return resultRoot;
    }//solve

public TreeNodeMerge traverseBoth(TreeNodeMerge root1, TreeNodeMerge root2, TreeNodeMerge resultRoot){
    if(root1==null && root2==null){
    //if both are null,don't recurse, and no need to assign value to root

    }

    else{
        //atleast 1 of them are non null, add the 2 values and add the sum node in Result Tree
        int node1=root1!=null?root1.val:0;
        int node2=root2!=null?root2.val:0;
        int sum=node1+node2;

        //assign value to the result root
        resultRoot.val=sum;


        //initializing the next roots, and recurse left for BOTH
        TreeNodeMerge left1=root1!=null && root1.left!=null?root1.left:null;
        TreeNodeMerge left2=root2!=null && root2.left!=null?root2.left:null;

        resultRoot.left=new TreeNodeMerge(-1);
        traverseBoth(left1,left2,resultRoot.left);

        //removing  null node
        if(resultRoot.left.val==-1){
            resultRoot.left=null;
        }

        //initializing the next roots, and recurse right for BOTH
        TreeNodeMerge right1=root1!=null && root1.right!=null?root1.right:null;
        TreeNodeMerge right2=root2!=null && root2.right!=null?root2.right:null;

        resultRoot.right=new TreeNodeMerge(-1);
        traverseBoth(right1,right2,resultRoot.right);

        //removing  null node
        if(resultRoot.right.val==-1){
            resultRoot.right=null;
        }
    }

    return resultRoot;
}//traverseBoth

public static void main(String[] args) {
    TreeNodeMerge root1=new TreeNodeMerge(2);
    root1.left = new TreeNodeMerge(1);
    root1.right = new TreeNodeMerge(4);
    root1.left.left = new TreeNodeMerge(5);

    TreeNodeMerge root2=new TreeNodeMerge(3);
    root2.left=new TreeNodeMerge(6);
    root2.right=new TreeNodeMerge(1);
    root2.left.right=new TreeNodeMerge(2);
    root2.right.right=new TreeNodeMerge(7);
    root2.left.right.left=new TreeNodeMerge(8);
    root2.left.right.right=new TreeNodeMerge(9);

    TreeNodeMerge root=new Merge2BinaryTree().solve(root1,root2);

}//main
}//Merge2BinaryTree
