package Tree;


import java.util.ArrayList;

//Definition for binary tree
class TreeNodePath {
int val;
    TreeNodePath left;
    TreeNodePath right;
    TreeNodePath(int x) {
       val = x;
       left=null;
       right=null;
  }
}//TreeNodePath

public class PathToGivenNode {
    class Path{
        ArrayList<Integer> trail;
    }
public int[] solve(TreeNodePath A, int B) {
    /**Recursively check for the value. At any level,if root is null, return false.
    If it is the value, add that value in the global arraylist and return true.
    * Else return true or false based on left subtree OR right subtree.
    If any subtree returns true, add current root to the trail
    * */
    Path path=new Path();
    path.trail=new ArrayList<Integer>();

    checkPath(path,A,B);

    //after recursion
    int len=path.trail.size();
    int[] res=new int[len];

    //reverse the order
    for(int i=0;i<len;i++){
        res[i]=path.trail.get(len-1-i);
    }
    return  res;
}//solve

boolean checkPath(Path path, TreeNodePath root, int B){
    if(root==null){
        //if null, return false
        return false;
    }
    else if(root.val==B){
        //if root itself is the value, return true.
        //Add it to the trail. Don't check further
        path.trail.add(root.val);
        return true;
    }

    //recurse left, then right
    boolean leftCheck=false;
    boolean rightCheck=false;
    if(root.left!=null){
        leftCheck=checkPath(path,root.left,B);
    }
    if(root.right!=null){
        rightCheck=checkPath(path,root.right,B);
    }

    boolean isPath=leftCheck||rightCheck;
    if(isPath){
        //if any of left OR right returns true, add root to the trail
        path.trail.add(root.val);
    }
    return isPath;
}//checkPath

    public static void main(String[] args) {
        TreeNodePath root=new TreeNodePath(1);
        root.left = new TreeNodePath(2);
        root.right = new TreeNodePath(3);
        root.left.left = new TreeNodePath(4);
        root.left.right = new TreeNodePath(5);
        root.right.left = new TreeNodePath(6);
        root.right.right = new TreeNodePath(7);

        int B=4;
        int[] result=new PathToGivenNode().solve(root,4);

        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }//main

}//PathToGivenNode
