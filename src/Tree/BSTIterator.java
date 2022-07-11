package Tree;

import java.util.Stack;

public class BSTIterator {


class TreeNode {
    int val;
    BSTIterator.TreeNode left;
    BSTIterator.TreeNode right;
    TreeNode(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNode

    /*Question requirement: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.*/

 Stack<TreeNode> stk;

public void pushStk(TreeNode root){
    //This is called during ctor, and also at every next()

    /*Note that this is not a push to the tree , rather it pushes the param to the stack. Then updates current to the left
    * most child of its left chain, which is basically its successor in the in order traversal. This ensures that the successor, which is also
    * the next smallest element, is always at the top of the stack.*/

    stk.push(root);
    TreeNode curr=root;

    while(curr.left!=null){
        stk.push(curr.left);
        curr=curr.left;
    }//while
}

public BSTIterator(TreeNode root) {
    stk=new Stack<TreeNode>();
    if(root!=null){
        pushStk(root);
    }
}

public BSTIterator() {
    stk=new Stack<TreeNode>();
 }

/** @return whether we have a next smallest number */
public boolean hasNext() {
    return  !stk.isEmpty();
}//hasNext

/** @return the next smallest number */
public int next() {
    TreeNode top=stk.peek();//to be returned
    stk.pop();

    //make the successor(next smallest element) ready
    if(top.right!=null){
        pushStk(top.right);
    }

    return top.val;
}//next

 BSTIterator.TreeNode insert(BSTIterator.TreeNode root, int x){
    if(root==null){
        root=new BSTIterator.TreeNode(x);
    }
//comparing at each of the recursion levels
    else if (x<root.val){
//when returned, this will be appended at left
        root.left=insert(root.left,x);
    }
//when returned, this will be appended at right
    else if (x>root.val){
        root.right=insert(root.right,x);
    }

//returning root, at each of the recursion level
    return root;
}//insert

public static void main(String[] args){
    int[] val=new int[]{4,30,20,10,25,35,33,40};

    //insert
    BSTIterator.TreeNode root=null;
    for(int i=0;i<val.length;i++)
    {
        root=new BSTIterator().insert(root,val[i]);
    }

    //this will call pushStk() and initialize the stack
    BSTIterator i = new BSTIterator(root);

    //every next() will call pushStk() and update the stack
    while (i.hasNext()) {
     System.out.println(i.next());
 }//while

}//main
}//BSTIterator

