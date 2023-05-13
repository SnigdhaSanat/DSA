package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

class TreeNodeCousins {
    int val;
    TreeNodeCousins left;
    TreeNodeCousins right;
    TreeNodeCousins(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeCousins

/**Question requirement: 1) Siblings should not be considered as cousins. 2) Try to do it in single traversal. 3) Order DOES NOT matter in the output.*/

public class CousinsInBinaryTree {
    //Two nodes of a binary tree are cousins if they have the same depth with different parents.
    /**Insert all the nodes of the tree into a hashmap, with key as the level.
    * Take note of node B and its sibling, and their level. For that level, output all the nodes, except B and its sibling */

    class NodeUtility{
        HashMap<Integer, ArrayList<TreeNodeCousins>> hm;
        int levelOfB;
        TreeNodeCousins siblingOfB;
    }//NodeUtility


    public ArrayList<Integer> solve(TreeNodeCousins A, int B) {
        NodeUtility nodeUtility=new NodeUtility();
        nodeUtility.hm=new HashMap<Integer, ArrayList<TreeNodeCousins>>();
        nodeUtility.levelOfB=-1;
        nodeUtility.siblingOfB=null;

        levelWise(A,0,B,nodeUtility);

        //after recursion
        int level=nodeUtility.levelOfB;
        ArrayList<TreeNodeCousins> levelList=nodeUtility.hm.get(level);
        TreeNodeCousins sibling=nodeUtility.siblingOfB;

        ArrayList<Integer> res=new ArrayList<Integer>();
        for(int i=0;i<levelList.size();i++){
            TreeNodeCousins currNode=levelList.get(i);
            if(currNode.val!=B && (sibling==null || currNode!=sibling)){
                res.add(currNode.val);
            }
        }//for

        return res;
    }//solve

    void levelWise(TreeNodeCousins root, int level,int B, NodeUtility nodeUtility){
        //add into hashmap
        if(nodeUtility.hm.containsKey(level)){
            //if key(level) is  present
            ArrayList<TreeNodeCousins> al=nodeUtility.hm.get(level);
            al.add(root);
            nodeUtility.hm.put(level,al);
        }
        else{
            //if key(level) is not present
            ArrayList<TreeNodeCousins> al=new ArrayList<TreeNodeCousins>();
            al.add(root);
            nodeUtility.hm.put(level,al);
        }

        //check if root is B, and update levelOfB
        if(root.val==B){
          nodeUtility.levelOfB=level;
        }

        //get sibling of B
        if(root.left!=null && root.left.val==B){
            nodeUtility.siblingOfB=root.right!=null?root.right:null;
        }
        if(root.right!=null && root.right.val==B){
            nodeUtility.siblingOfB=root.left!=null?root.left:null;
        }


        //recurse
        if(root.left!=null){
            levelWise(root.left,level+1,B,nodeUtility );
        }

        if(root.right!=null){
            levelWise(root.right,level+1,B,nodeUtility );
        }

        return;
    }//levelWise


    public static void main(String[] args) {
        TreeNodeCousins root=new TreeNodeCousins(1);
        root.left = new TreeNodeCousins(2);
        root.right = new TreeNodeCousins(3);
        root.left.right= new TreeNodeCousins(4);
        root.right.right= new TreeNodeCousins(5);
        root.left.right.left=new TreeNodeCousins(6);
        root.left.right.right=new TreeNodeCousins(7);
        root.right.right.left=new TreeNodeCousins(8);
        root.right.right.right=new TreeNodeCousins(9);
        root.right.right.right.right=new TreeNodeCousins(10);


        ArrayList<Integer> result=new CousinsInBinaryTree().solve(root,5);
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }//main
}//CousinsInBinaryTree
