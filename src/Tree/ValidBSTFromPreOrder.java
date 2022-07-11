package Tree;

import java.util.Stack;

public class ValidBSTFromPreOrder {
    public int solve(int[] A) {
        /*How do we traverse pre order? Root->Left subtree-> Right subtree. Note that left subtree is traversed first.
        So in an array of elements, if current element is less than the prev element, append it as the left child of the previous element(push
        it to the stack).This is why the stack should be of decreasing order. As you push, you traverse along the chain of left children.

        If an element is greater than its prev element, then pop all the elements in the stack smaller than it. The last element to be popped, say x, is
        the LARGEST AMONG THE ONES SMALLER THAN THE CURRENT ELEMENT. Also the ones popped before x
        are smaller than x, which is why they were placed above x in the stack. Those make a chain of left children in the left subtree.

        The current element will be the right child of x.UPDATE ROOT AS X(actually keep updating as you pop). So any element beyond this we be in the right
        subtree of x.

        Above is the ideal situation. When is the given array an invalid one? At any point, now being in the right subtree of x(current root) if
        we have an element which is less than the current root.*/

        int n=A.length;
        Stack <Integer> stk=new Stack<Integer>();
        int root=Integer.MIN_VALUE;
        int res=1;

        //create the tree
        for(int i=0;i<n;i++){
            int elem=A[i];

/*IMP: If we are pushing into the stack, root is the parent of the biggest(top) element of the current left chain, which has
 the biggest element as the right child. So we are in the right subtree
of root. Also, root updates only when we find a bigger element, in which case we travel up the left chain to find the correct root and append
the element as its right child. Then again, for the rest of the elements, we create a left chain from that right child. So at any point,
root is either the parent of the top element of the left chain, if we are pushing, or if we are popping, it is the parent of the current element,
where the element is its right child. So in both of the cases, as we are in the right subtree, the elem has to be ideally > root. */
            if(elem<root){
                res=0;
                return res;
            }//if

            while(!stk.isEmpty() && stk.peek()<elem){
                //pop till there are smaller elements
                root=stk.peek();
                stk.pop();
            }//while

            //finally place the element(as the right child of the root)
            stk.push(elem);
        }//for

        return  res;
    }//solve

    public  static  void main(String[] args){
        int[] list=new int[]{7, 7, 10, 10, 9, 5, 2, 8};
        int result=new ValidBSTFromPreOrder().solve(list);
        System.out.println(result);
    }
}//ValidBSTFromPreOrder
