package StacksAndQueues;

import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] A) {
    /*For each A[i], get the first INDEX(NOT ELEMENT) to its right and to its left, whose A[j] is lesser than A[i].
    For A[i]'s height, the rectangle will be limited by these 2 boundaries. The difference between the 2 will give you the
    base, and A[i] will be the height of the rectangle. Repeat the same for each A[i], and return the max*/

        int N=A.length;

        int[] right=new int[N];//to hold the right boundaries, that is, first element less than A[i]
        //initialize right
        for(int i=0;i<N;i++){
            //by default, limit should be the (last index+1), which is the size of the array
            right[i]=N;
        }//for

        int[] left=new int[N];//to hold the left boundaries, that is, first element less than A[i]
        //initialize left
        for(int i=0;i<N;i++){
            //by default, limit should be (first index -1), which is -1
            left[i]=-1;
        }//for

        //diff arrays to hold the differences, which are bases of rectangles
        int[] rightDiff=new int[N];
        int[] leftDiff=new int[N];

        //finding the right boundaries
        Stack<Integer> idxR=new Stack<Integer>();
        Stack<Integer> eltR=new Stack<Integer>();

        for(int i=0;i<N;i++){
            while(!idxR.isEmpty() && A[i]<eltR.peek()){
                right[idxR.peek()]=i;
                idxR.pop();
                eltR.pop();
            }//while
            idxR.push(i);
            eltR.push(A[i]);
        }//for
        //diff on the right, excluding i
        for(int i=0;i<N;i++){
            rightDiff[i]=right[i]-i-1;
        }

        //finding the left boundaries
        Stack<Integer> idxL=new Stack<Integer>();
        Stack<Integer> eltL=new Stack<Integer>();

        for(int i=N-1;i>=0;i--){
            while(!idxL.isEmpty() && A[i]<eltL.peek()){
                left[idxL.peek()]=i;
                idxL.pop();
                eltL.pop();
            }//while
            idxL.push(i);
            eltL.push(A[i]);
        }//for
        //diff on the left, excluding i
        for(int i=0;i<N;i++){
            leftDiff[i]=i-left[i]-1;
        }

//calculating the  reactangle areas for each i, and the max
        int max=Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            //+1 to include i
            int breadth=leftDiff[i]+rightDiff[i]+1;
            int area=breadth*A[i];//area=breadth*height of A[i]
            if(area>max){
                max=area;
            }
        }//for

        return max;
    }//largestRectangleArea

    public static void main(String[] args) {
        int[] A=new int[]{2, 1, 5, 6, 2, 3};
        int res=new LargestRectangleInHistogram().largestRectangleArea(A);
        System.out.println(res);
    }
}//LargestRectangleInHistogram
