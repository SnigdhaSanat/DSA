package Tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//Definition for binary tree with next pointer.
   class TreeLinkNode {
      int val;
      TreeLinkNode left, right, next;
      TreeLinkNode(int x)
      {
          val = x;
      }
  }//TreeLinkNode


/**Question requirement: You may only use constant extra space, so recursion can't be used*/
public class PopulateNextRightPointersTree {

public void connect(TreeLinkNode root) {
    /**The question demands that recursion should not be used. But since we have to visit every node we need something.How?
     * Start from the root. Connect its left child to its right child, and right child to 1st cousin. Also keep a pointer to the first node at next level,
     * so that you can arrive at that when the current level ends. Within a level, next pointers created while at the previous level
     * will guide you. So you can traverse without recursion. Basically this is a level order traversal. */

    TreeLinkNode firstChild=root;//root is the firstChild of an imaginary superRoot
    boolean shouldContinue=true;//true for first iteration
    TreeLinkNode curr=null;
    TreeLinkNode prevChild=null;
    TreeLinkNode currChild=null;

    while(shouldContinue){
        //new level
        curr=firstChild;

        firstChild=null;//meaning we at the starting of the level

        prevChild=null;
        currChild=null;

        while(curr!=null){
            if(curr.left!=null){
                currChild=curr.left;

                if(firstChild!=null){
                    //if it is not the first child, connect it to the previous pointer
                    prevChild.next=currChild;
                }

                if(firstChild==null){
                    //assign firstChild for the next level. Also there is no prevChild to connect to
                    firstChild=currChild;
                }
                prevChild=currChild;
            }

            if(curr.right!=null){
                currChild=curr.right;

                if(firstChild!=null){
                    //if it is not the first child, connect it to the previous pointer
                    prevChild.next=currChild;
                }

                if(firstChild==null){
                    //assign firstChild for the next level. Also there is no prevChild to connect to
                    firstChild=currChild;
                }

                prevChild=currChild;
            }

            curr=curr.next;//Already assigned at the previous level
        }//inner while

        if(prevChild!=null)
        {
            //the last child whose next pointer was not set
            prevChild.next=null;
        }
        if(prevChild==null){
            //this is the case when the last node is reached
            shouldContinue=false;
        }
    }//outer while

}//connect

public static void main(String[] args) {
    TreeLinkNode root=new TreeLinkNode(1);
    root.left = new TreeLinkNode(2);
    root.right = new TreeLinkNode(3);
    root.left.left= new TreeLinkNode(4);
    root.left.right= new TreeLinkNode(5);
    root.right.left= new TreeLinkNode(6);
    root.right.right= new TreeLinkNode(7);
    root.left.left.left= new TreeLinkNode(8);

    new PopulateNextRightPointersTree().connect(root);
}//main
}//PopulateNextRightPointersTree
