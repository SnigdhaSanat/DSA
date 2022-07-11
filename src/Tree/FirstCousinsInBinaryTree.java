package Tree;

import java.util.ArrayList;

class TreeNodeFirstCousins {
    int val;
    TreeNodeFirstCousins left;
    TreeNodeFirstCousins right;
    TreeNodeFirstCousins(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeFirstCousins

public class FirstCousinsInBinaryTree {
class NodeUtility{
    TreeNodeFirstCousins uncleNode;
}//NodeUtility


public ArrayList<Integer> solve(TreeNodeFirstCousins A, int B) {

    NodeUtility nodeUtility=new NodeUtility();
    nodeUtility.uncleNode=null;

    getGrandParent(A,B,nodeUtility);

    //after recursion
    ArrayList<Integer> res=new ArrayList<Integer>();
    //unless uncle node is null, return its children as b's first cousins
    if(nodeUtility.uncleNode==null){
        return res;
    }
    else{
        TreeNodeFirstCousins uncle=nodeUtility.uncleNode;
        if(uncle.left!=null){
            res.add(uncle.left.val);
        }
        if(uncle.right!=null) {
            res.add(uncle.right.val);
        }//else
    }

    return res;
}//solve

boolean getGrandParent(TreeNodeFirstCousins root, int B, NodeUtility nodeUtility){
   //check if left child or right child of the root is B, so that root is the parent
    //no need to recurse further then. Basically, return true from B's parent
    if(root.left!=null && root.left.val==B){
        return true;
    }
    else if(root.right!=null && root.right.val==B){
        return true;
    }

    boolean isLeftChildParentOfB=false;
    boolean isRightChildParentOfB=false;

    //recurse
    if(root.left!=null){
       isLeftChildParentOfB=getGrandParent(root.left,B,nodeUtility);
    }

    if(root.right!=null){
        isRightChildParentOfB=getGrandParent(root.right,B,nodeUtility);
    }

    //If the left child of root has B as its RC or LC(true was returned), root's right child is the uncle of B
    if(isLeftChildParentOfB){
        nodeUtility.uncleNode=root.right;
    }
    //If the right child of root has B as its RC or LC(true was returned), root's left child is the uncle of B
    else if(isRightChildParentOfB){
        nodeUtility.uncleNode=root.left;
    }
    return false;
}

public static void main(String[] args) {
    TreeNodeFirstCousins root=new TreeNodeFirstCousins(1);
    root.left = new TreeNodeFirstCousins(2);
    root.right = new TreeNodeFirstCousins(3);
    root.left.left= new TreeNodeFirstCousins(4);
    root.left.right= new TreeNodeFirstCousins(5);
    root.right.left= new TreeNodeFirstCousins(6);
    root.right.right= new TreeNodeFirstCousins(7);

    ArrayList<Integer> result=new FirstCousinsInBinaryTree().solve(root,5);
    for(int i=0;i<result.size();i++){
        System.out.println(result.get(i));
    }
}//main
}//CousinsInBinaryTree
