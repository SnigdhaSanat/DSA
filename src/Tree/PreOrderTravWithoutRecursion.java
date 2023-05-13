package Tree;


import java.util.ArrayList;
import java.util.Stack;

class TreeNodePre {
 int val;
 TreeNodePre left;
 TreeNodePre right;
 TreeNodePre(int x) {
  val = x;
  left=null;
  right=null;
 }//ctor
}//TreeNodePre

public class PreOrderTravWithoutRecursion {

 public int[] preorderTraversal1(TreeNodePre A) {

  /**Push root into stack, and print it. Update current to left,
   Outer loop condition: curr is not null.
   Keep pushing left children down the stack and keep printing them, till there are none.
   Then pop from the stack, and push its right child. If there is no right child, keep popping till the
   popped element's right child exists. Set it as curr and travel down the left again */

  ArrayList<Integer> al=new ArrayList<Integer>();
  Stack<TreeNodePre> stk=new Stack<TreeNodePre>();

  TreeNodePre curr=A;
  stk.push(curr);
  al.add(curr.val);
  curr=curr.left;

  while(curr!=null){

   while(curr!=null){
    stk.push(curr);
    al.add(curr.val);
    curr=curr.left;
   }//inner while

   TreeNodePre top=stk.pop();
   while(!stk.isEmpty() && top.right==null){
     top=stk.pop();
   }//inner while

   curr=top.right;
  }//outer while

  int len=al.size();
  int [] res=new int[len];
  for(int i=0;i<len;i++){
   res[i]=al.get(i);
  }
  return res;
 }//preorderTraversal

 public int[] preorderTraversal2(TreeNodePre A) {
  /*An alternative and CLEANER approach is this. Convert the recursion into a stack, and each of its steps into a stack op.
  * Pre order recurse is print, recurse left and then recurse right. So push root to the stack. For print, pop an element from the stack and display it.
  * Reverse the recursion orders, so that push the popped element's right child first, then left child, so that they are popped and printed in correct order
  * Understand that those pushed elements will be printed when popped, as we are talking od PRE order traversal*/
  ArrayList<Integer> al=new ArrayList<Integer>();
  Stack<TreeNodePre> stk=new Stack<TreeNodePre>();

  stk.push(A);

  while(!stk.isEmpty()){
   TreeNodePre top=stk.pop();
   al.add(top.val);
   if(top.right!=null){
    stk.push(top.right);
   }
   if(top.left!=null){
    stk.push(top.left);
   }

  }//while

  //after traversing
  int len=al.size();
  int [] res=new int[len];
  for(int i=0;i<len;i++){
   res[i]=al.get(i);
  }
  return res;
 }//preorderTraversal2


 public static void main(String[] args) {
  TreeNodePre root=new TreeNodePre(1);
//  root.left = new TreeNodePre(2);
//  root.right = new TreeNodePre(3);
//  root.left.left=new TreeNodePre(4);
//  root.left.left.right=new TreeNodePre(7);
//  root.left.right=new TreeNodePre(5);
//  root.left.right.left=new TreeNodePre(8);
//  root.right.left=new TreeNodePre(6);
//  root.right.left.left=new TreeNodePre(9);
//  root.right.left.right=new TreeNodePre(10);

  //int[] result=new PreOrderTravWithoutRecursion().preorderTraversal1(root);
  int[] result=new PreOrderTravWithoutRecursion().preorderTraversal1(root);
  for(int i=0;i<result.length;i++){
   System.out.println(result[i]);
  }
 }//main

}//PreOrderTravWithoutRecursion
