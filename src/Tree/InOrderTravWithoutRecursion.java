package Tree;

import java.util.ArrayList;
import java.util.Stack;

class TreeNodeIn {
    int val;
    TreeNodeIn left;
    TreeNodeIn right;
    TreeNodeIn(int x) {
        val = x;
        left=null;
        right=null;
    }//ctor
}//TreeNodeIn
public class InOrderTravWithoutRecursion {
    public int[] inorderTraversal(TreeNodeIn A) {

  /**The "CLEANER" solution used for pre order will not work here, as that solution printed the root of every
  subtree whenever that was reached. That is what pre order requires, but cannot be done in inorder. Tweak the solution
  which I had originally submitted for pre order. Tweak is that instead of printing during push, print during pop.

  Push root into stack. Update current to left.
   Outer loop condition: While stack is not empty OR curr is not null.
   Keep pushing left children down the stack, till there are none.
   Then pop from the stack, print it, and then push its right child. If there is no right child,
   keep popping and printing till the popped element's right child exists. Set it as curr and travel down the left again */
        ArrayList<Integer> al=new ArrayList<Integer>();
        Stack<TreeNodeIn> stk=new Stack<TreeNodeIn>();

        TreeNodeIn curr=A;
        stk.push(curr);
        //al.add(curr.val);
        curr=curr.left;

/**When curr is null after an iteration, it means that the entire stack has been popped yet there is no RC. So it also implies that stack is also empty.
* So curr!=null condition is sufficient. But where it fails is if the tree has only one node. There, with curr=curr.left as empty, we still need to enter the outer
* while loop, since the element is yet to be popped(and printed). Hence the OR condition of !stk.isEmpty() is used. In pre-order,
* this is not required as the element is printed during push, not pop.*/
        while( curr!=null || !stk.isEmpty()){
            while(curr!=null){
                stk.push(curr);
                //al.add(curr.val);
                curr=curr.left;
            }//inner while

            TreeNodeIn top=stk.pop();
            al.add(top.val);

            while(!stk.isEmpty() && top.right==null){
                top=stk.pop();
                al.add(top.val);
            }//inner while

            curr=top.right;
        }//outer while

        int len=al.size();
        int [] res=new int[len];
        for(int i=0;i<len;i++){
            res[i]=al.get(i);
        }
        return res;
    }//inorderTraversal

    public static void main(String[] args) {
        TreeNodeIn root=new TreeNodeIn(3);
        //root.left = new TreeNodeIn(1);
//        root.right = new TreeNodeIn(3);
//        root.left.left=new TreeNodeIn(4);
//        root.left.left.right=new TreeNodeIn(7);
//        root.left.right=new TreeNodeIn(5);
//        root.left.right.left=new TreeNodeIn(8);
//        root.right.left=new TreeNodeIn(6);
//        root.right.left.left=new TreeNodeIn(9);
//        root.right.left.right=new TreeNodeIn(10);


        int[] result=new InOrderTravWithoutRecursion().inorderTraversal(root);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }//main
}//TreeNodeIn
