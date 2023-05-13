package Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

class TreeNodePost {
    int val;
    TreeNodePost left;
    TreeNodePost right;
    TreeNodePost(int x) {
        val = x;
        left=null;
        right=null;
    }//ctor
}//TreeNodePost
public class PostOrderTravWithoutRecursion {
    public int[] postOrderTraversal(TreeNodePost A) {

  /**The "CLEANER" solution used for pre order will not work here, as that solution printed the root of every
  subtree whenever that was reached. That is what pre order requires, but cannot be done in post order. Tweak the solution
  which I had originally submitted for pre order. Tweak is that instead of printing during push, print during pop.
  This was also done in inorder without recursion. The difference with inorder here is that, while pushing an element's
  right child DO NOT pop it, as it will get printed AFTER the right subtree.

  Push root into stack. Update current to left.
   Outer loop condition: While stack is not empty OR curr is not null.
   Keep pushing left children down the stack, till there are none.
   Then PEEK, NOT POP from the stack, and then set curr as its right child, and SET RIGHT CHILD TO NULL, and travel down curr's left again.
   If there is no right child, keep popping and printing till the popped element's right child exists. Set it as curr and travel down curr's left again.

    IMP: Now when the un-popped but peeked element will be encountered again after traversing its right subtree, having set its right child to null, it will
    be popped and printed*/
        ArrayList<Integer> al=new ArrayList<Integer>();
        Stack<TreeNodePost> stk=new Stack<TreeNodePost>();

        TreeNodePost curr=A;
        stk.push(curr);

        curr=curr.left;

        while(!stk.isEmpty() || curr!=null){
            while(curr!=null){
                stk.push(curr);
                curr=curr.left;
            }//inner while

            TreeNodePost top=stk.peek();//DO NOT POP
            //al.add(top.val);
            while(!stk.isEmpty() && stk.peek().right==null){
                top=stk.pop();
                al.add(top.val);
            }//inner while

            curr=top.right;
            top.right=null;
        }//outer while

        int len=al.size();
        int [] res=new int[len];
        for(int i=0;i<len;i++){
            res[i]=al.get(i);
        }
        return res;
    }//postOrderTraversal

    public static void main(String[] args) {
        TreeNodePost root=new TreeNodePost(1);
        root.left = new TreeNodePost(2);
        root.right = new TreeNodePost(3);
        root.left.left=new TreeNodePost(4);
        root.left.left.right=new TreeNodePost(7);
        root.left.right=new TreeNodePost(5);
        root.left.right.left=new TreeNodePost(8);
        root.right.left=new TreeNodePost(6);
        root.right.left.left=new TreeNodePost(9);
        root.right.left.right=new TreeNodePost(10);

        int[] result=new PostOrderTravWithoutRecursion().postOrderTraversal(root);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }//main
}//PostOrderTravWithoutRecursion
