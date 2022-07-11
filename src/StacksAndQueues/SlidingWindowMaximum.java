package StacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] slidingMaximum(final int[] A, int B) {
 /*Concept: We will be using a Deque. For the first B elements, simply compute the max and print it. Then from Bth(0-index)
 to the N-Bth index element, where N is size of A, we do the following: Move the sliding window to the right, effectively
 increasing the "Front" by 1, and removing the first. But before adding an element to the rear, we remove the useless
 elements from the Deque. What is a useless element? If an element in the dequeue is smaller than the element being added
 to the rear, it means it is not the max element in the current window any more. And also it will be removed by the sliding
 window before the rear element is removed. So it can never be the max element.So the element has no business any longer.
 So, before adding the rear element,remove all the "useless" elements from the deque. This also means the the max element
 in any window stays at the front(left)

 What would we do if we required the minimum element? The removing criteria, while adding a new element, would have been
 reversed. The min element in any window will still be at the front*/

//IMP: First or front is towards the beginning, and last or rear is towards the right

        /*We are adding indices instead of elements in deque, because this helps tracking the front and rear elements easier*/
        int N=A.length;
        int[] res=new int[N-B+1];//to hold the results

        //Use a Deque
        Deque<Integer> dq=new LinkedList<Integer>();

        //compute max among the first B elements
        int max=Integer.MIN_VALUE;

        //Just add the first B elements while maintaining the max at front. Don't remove anything
        for(int i=0;i<B;i++){
            if(A[i]>max){
                max=A[i];
            }//if

        while(!dq.isEmpty() && A[dq.peekLast()]<A[i]){
/* The same rule of polling the rear elements, if the current element is greater than them, applies for the first B
elements as well. This is to maintain the desired structure of peekFirst() ALWAYS being the max element in the current
window. After all, the first B elements will be used in the sliding windows starting from j>=1*/
            dq.removeLast();
        }//while
        dq.addLast(i);//finally add the ith index, NOT element

    }//for

        res[0]=max;//assign max to res[0]


        //now compute for the rest of the elements, where start of the sliding window is 1 to N-B
        for(int i=1;i<=(N-B);i++){
            //i is the starting of the sliding window
            int newElement=A[i+B-1];

            //delete the front element, basically move the sliding window's front
            if(!dq.isEmpty() && dq.peekFirst()<i){
                //why "if"? because it might have got deleted earlier because of being "useless"
                dq.removeFirst();
            }

            //add rear, after removing the "useless" elements
            while(!dq.isEmpty() && A[dq.peekLast()]<newElement){
                dq.removeLast();
            }//while

            dq.addLast(i+B-1); //add the index, not the element
            res[i]=A[dq.peekFirst()];//add it to the result array
        }//for

        return res;
    }//slidingMaximum

    public static void main(String[] args) {
        int[] A=new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int B=3;
        int[] res=new SlidingWindowMaximum().slidingMaximum(A,B);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }
}//SlidingWindowMaximum
