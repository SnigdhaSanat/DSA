package Tree;

import java.util.*;

//Definition for binary tree
  class TreeNodeVertical {
    int val;
    TreeNodeVertical left;
    TreeNodeVertical right;
    TreeNodeVertical(int x) {
       val = x;
       left=null;
       right=null;
      }
  }//TreeNodeVertical



public class VerticalOrderTraversalOfBinaryTree {

public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNodeVertical A) {
/*Of course you can do a preorder traversal, and then take care of things like top-to-bottom sequence of the uncle nodes which are the RC of the
grand-parent(In pre order, the uncle node will be traversed after the current node, while in level order it is reverse). But the best way is to traverse
in a level order fashion itself, to keep things simple, and automatically take care of the question requirement. 

Keep a queue, one of the value and the other of the corresponding vertical level. LC will be (vertical level-1), while RC will be (vertical level+1).
Then while removing the elements from the queue, insert them in the appropriate TreeMap, with key as the vertical level, and value as the list of nodes in that
vertical level. Finally, convert the TreeMap into an ArrayList<ArrayList<Integer>>*/

    //Empty tree
    if(A==null)
    {
    return new ArrayList<ArrayList<Integer>>();

    }
    //Initialize hashmap
    TreeMap<Integer, ArrayList<Integer>> hm=new TreeMap<Integer, ArrayList<Integer>>();

    //initialize the queues
    Queue<TreeNodeVertical> nodeQueue=new LinkedList<TreeNodeVertical>();
    Queue<Integer> vertLevelQueue=new LinkedList<Integer>();
    
    nodeQueue.add(A);
    vertLevelQueue.add(0);
    
    //Now level order traversal
    while(!nodeQueue.isEmpty()){
        //and also till the vertLevelQueue is not empty
        
        //pop the front elements, and insert their RCs and LCs
        TreeNodeVertical currentNode=nodeQueue.poll();
        int currentVertLevel=vertLevelQueue.poll();

        //add to the queues
        if(currentNode.left!=null){
            nodeQueue.add(currentNode.left);
            vertLevelQueue.add(currentVertLevel-1);
        }
        
        if(currentNode.right!=null){
            nodeQueue.add(currentNode.right);
            vertLevelQueue.add(currentVertLevel+1);
        }

        //add the pop elements to the hm
        if(hm.containsKey(currentVertLevel)){
            //if the key is already present
            ArrayList<Integer> al=hm.get(currentVertLevel);
            //add the VALUE, NOT NODE
            al.add(currentNode.val);
            hm.put(currentVertLevel, al);
        }//if
        else{
            //if the key is NOT already present
            ArrayList<Integer> al=new ArrayList<Integer>();
            //add the VALUE, NOT NODE
            al.add(currentNode.val);
            hm.put(currentVertLevel, al);
        }//else

    }//while

    //Convert the hm into an ArrayList<ArrayList<Integer>>
    ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();

    for(Map.Entry<Integer, ArrayList<Integer>> element: hm.entrySet()){
        //get the VALUE of element, which is an ArrayList<Integer>
        ArrayList<Integer> row=element.getValue();

        //add a row to res
        res.add(row);
    }//for

return res;
}//verticalOrderTraversal


public static void main(String[] args) {
//    TreeNodeVertical root=new TreeNodeVertical(6);
//    root.left = new TreeNodeVertical(3);
//    root.right = new TreeNodeVertical(7);
//    root.left.left=new TreeNodeVertical(2);
//    root.left.right= new TreeNodeVertical(5);
//    root.right.left= new TreeNodeVertical(8);
//    root.right.right= new TreeNodeVertical(9);

    TreeNodeVertical root=new TreeNodeVertical(460);
    root.right=new TreeNodeVertical(4698);
    root.right.left=new TreeNodeVertical(4421);
    root.right.right=new TreeNodeVertical(7515);
    root.right.left.right=new TreeNodeVertical(3096);
    root.right.left.right.right=new TreeNodeVertical(3006);
    root.right.right.left=new TreeNodeVertical(434);
    root.right.right.left.right=new TreeNodeVertical(6693);

new VerticalOrderTraversalOfBinaryTree().verticalOrderTraversal(root);

}//main
}//VerticalOrderTraversalOfBinaryTree
