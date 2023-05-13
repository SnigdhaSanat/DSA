package Tree;


import java.util.ArrayList;

// Definition for binary tree
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) {
    val = x;
    left=null;
    right=null;
  }
}//TreeNode

/*A solution using O(n) SPACE is pretty straight forward. Could you devise a constant SPACE solution?*/
public class RecoverBST {
    class NodeVal{
        TreeNode prevVal;
        ArrayList<Integer> wrongValues;
    }

    /**Idea is to traverse the binary tree, at each point keeping track of the previous element. At the point where
    the prev and curr(root) node is out of order, add that to an arrayList. Finally return from that arrayList. Also, as
    demanded by the question, this takes a constant space, of prevVal(1) and wrongValues(2 or 4) of the class NodeVal*/

    public int[] recoverTree(TreeNode A) {
        NodeVal nodeVal=new NodeVal();
        nodeVal.prevVal =null;
        nodeVal.wrongValues=new ArrayList<Integer>();

        checkWrongElements(A,nodeVal);

        /**The swapped nodes can either be adjacent or non adjacent. Eg: For, 1,2,3,4,5,6,7,8
        * Non adjacent swapping: 1,2,6,4,5,3,7,8. Here, 6 and 4, and then 5 and 3 are out of order. So if the arraylist is of size 4, return 4th and 1st element.
        * Adjacent swapping: 1,2,4,3,5,6,7,8. Here 4 and 3 are out of order. So if arraylist is of size 2, return 1st and 2nd  */

        int len=nodeVal.wrongValues.size();
        if(len==0){
            System.out.println("This is a correct BST already");
            return new int[]{};
        }
        int[] res=new int[2];

        if(len==4){
            res[0]=nodeVal.wrongValues.get(3);
            res[1]=nodeVal.wrongValues.get(0);
        }
        else{
            res[0]=nodeVal.wrongValues.get(1);
            res[1]=nodeVal.wrongValues.get(0);
        }

        return  res;
    }//recoverTree

    void checkWrongElements(TreeNode root,NodeVal nodeVal){
        if(root.left!=null){
            checkWrongElements(root.left,nodeVal);
        }//if

        //compare with prev with curr
        TreeNode curr=root;
        if(nodeVal.prevVal!=null && nodeVal.prevVal.val>curr.val){
            nodeVal.wrongValues.add(nodeVal.prevVal.val);
            nodeVal.wrongValues.add(curr.val);
        }

        //update the prev with curr
        nodeVal.prevVal=root;

        if(root.right!=null){
            checkWrongElements(root.right,nodeVal);
        }//if
    }//checkWrongElements


    public  static void main(String[] args){


        //construct the tree
        TreeNode root=null;

        root=new TreeNode(2);
        root.left=new TreeNode(1);
        root.right=new TreeNode(3);



        int[] res=new RecoverBST().recoverTree(root);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }

    }//main
}//RecoverBST
