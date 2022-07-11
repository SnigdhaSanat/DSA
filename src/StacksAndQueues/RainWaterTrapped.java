package StacksAndQueues;

import java.util.ArrayList;
import java.util.Stack;

public class RainWaterTrapped {
    public int trap(final int[] A) {
/*IMP: Intuitively, this is an array question. While both stack and array take linear TIME, array takes constant SPACE,
while the stack solution takes linear SPACE. For more detail check out https://www.geeksforgeeks.org/trapping-rain-water/,
 Method 5 (Two Pointer Approach) for an array solution*/

/*Central idea: Note that this requires you to find the nearest higher bars both of right and left of a[i]. Start from 0 and move
towards n-1. Build a tapering stack. For each iteration, check if the current height is greater than the peek. If so, current element
becomes the right boundary. Note this height and pop it. The new peek will be its left boundary. Find the distance between the
left and right boundaries. The height will be the diff of: min of the two boundaries and its own height.

IMP: Note that for explanation 1 in the question. as index 4 has a height of 1, the water be be calculated taking 1 as the bottom most level.
The water trapped at level 0 will be accounted for while calculating at index 5.*/



int n=A.length;

//create stack of index, NOT element

Stack<Integer> stk=new Stack<Integer>();

int res=0;

for(int i=0;i<n;i++) {

    while(!stk.empty() && A[i]>A[stk.peek()]){
        //meaning it is the right boundary
        int right=i;

        //note the height of the peek and then pop it
        int currHeight=A[stk.peek()];
        stk.pop();

        //find the left boundary, which will be the next peek, as the stack is tapering
        if(stk.empty()){
            break;
        }
        int left=stk.peek();

        //exclude the left and right
        int distance=right-left-1;//indices
        int height=Math.min(A[right], A[left])-currHeight;//actual heights
        int area=distance*height;

        res+=area;
    }//while

    stk.push(i);
}//for

//push the current index
return res;

    }//trap

    public static void main(String[] args) {
        int[] A=new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int res=new RainWaterTrapped().trap(A);
        System.out.println(res);
    }
}//RainWaterTrapped
