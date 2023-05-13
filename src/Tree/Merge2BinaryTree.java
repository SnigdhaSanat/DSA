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

  /**Send a mergedRoot, as the root of the merged tree. At any point, if both are null, return. Else return a root made of the  non null root, or their sum if both are non-null. Recurse for left and right*/
public class Merge2BinaryTree {
    public TreeNodeMerge solve(TreeNodeMerge A, TreeNodeMerge B) {
    //initializing the root of result
    TreeNodeMerge mergedRoot=new TreeNodeMerge(-1);

    TreeNodeMerge resultRoot=traverseBoth(A, B,mergedRoot);
    return resultRoot;
    }//solve

public TreeNodeMerge traverseBoth(TreeNodeMerge root1, TreeNodeMerge root2, TreeNodeMerge resultRoot){
    //check current roots
    //if both null
    if(root1==null && root2==null){
        resultRoot= null;
        return resultRoot;
    }
    //if one of them is null

    if(root1!=null && root2==null){
        resultRoot= new TreeNodeMerge(root1.val);
    }
    if(root1==null && root2!=null){
        resultRoot= new TreeNodeMerge(root2.val);
    }

    //if both are non null
    if(root1!=null && root2!=null){
        resultRoot= new TreeNodeMerge(root1.val+root2.val);
    }

    //check left children
    TreeNodeMerge left1=root1!=null?root1.left:null;
    TreeNodeMerge left2=root2!=null?root2.left:null;
    resultRoot.left=traverseBoth(left1,left2,new TreeNodeMerge(-1));

    //check right children
    TreeNodeMerge right1=root1!=null?root1.right:null;
    TreeNodeMerge right2=root2!=null?root2.right:null;
    resultRoot.right=traverseBoth(right1,right2,new TreeNodeMerge(-1));
    //return current root
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
