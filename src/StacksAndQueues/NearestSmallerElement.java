package StacksAndQueues;

import java.util.Stack;

public class NearestSmallerElement {
    public int[] prevSmaller(int[] A) {
    /*2 stacks are maintained, idx and elt. As we iterate, top elements of both idx and elt are popped, as long as
    A[i]< elt.top().As you pop, the popped top elt's res[i] is updated as A[i], as A[i] is the nearest element to the
    left which is smaller than elt.top(). Finally elt and idx are pushed into the stacks.

    So each element is pushed and popped just once.

    Basically, as soon as an A[i] <top is found, start popping the elements. At any point of time, all elts in the stack
    are greater than their immediate bottom elements, which is why they were pushed into the stack at first place.

    IMP: In problems involving stacks, always be mindful of the direction to proceed, from 0 or from n-1. How to decide that?
    In stack, usually the incoming element, A[i] is the result of the element(s) in the stack which are popped out. Here since the result will be lying
    to the left of the element, that means the left element has to be the incoming element. So proceed from n-1 in this case. If the question required you
    to find something to the right, proceed from 0 in that case.*/
        int n=A.length;

        //initializing the res array
        int[] res=new int[n];
        for(int i=0;i<n;i++){
            res[i]=-1;
        }//for

        //finding the G[i] values:
        Stack<Integer> idxL=new Stack<Integer>();
        Stack<Integer> eltL=new Stack<Integer>();

        for(int i=n-1;i>=0;i--){
            while(!idxL.isEmpty() && A[i]<eltL.peek()){
                res[idxL.peek()]=A[i];
                idxL.pop();
                eltL.pop();
            }//while
            idxL.push(i);
            eltL.push(A[i]);
        }//for

        return res;
    }//prevSmaller

    public static void main(String[] args) {
        int[] a=new int[]{4, 5, 2, 10, 8};
        int[] res=new NearestSmallerElement().prevSmaller(a);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }
}//NearestSmallerElement
