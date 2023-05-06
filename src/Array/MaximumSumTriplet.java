package Array;

import java.util.TreeSet;

public class MaximumSumTriplet {
    public int solve(int[] A) {
        int n=A.length;

        /*The idea is to find, for each index, max element to its right which is greater than it, and the max element to its left
        * which is smaller than it. Then while calculating the sum of each index, check if the maxToRightArray>A[i]*/

        //for each element i, right max can be obtained through a maxToRightArray array, where maxToRightArray[i] is the max element in (i+1)
        // to (n-1). This needs extra effort, as you later need to check if the maxToRightArray[i]>A[i]
        int[] maxToRightArray=new int[n];

        //initializing
        maxToRightArray[n-1]=A[n-1];
        int curr_max=A[n-1];

        for(int i=n-1;i>0;i--){
            curr_max=Math.max(curr_max,A[i]);
            maxToRightArray[i-1]=curr_max;
        }//for loop

        //for each element, sort the left  sub-arrays using a TreeSet
        TreeSet<Integer> ts=new TreeSet<Integer>();
        ts.add(0);
        ts.add(A[0]);

        int[] maxToLeftArray=new int[n];
        maxToLeftArray[0]=A[0];
        for(int i=1;i<n;i++)
        {
            ts.add(A[i]);
            maxToLeftArray[i]=ts.lower(A[i]);

        }//for loop

        int maxSum=0;
        for(int i=1;i<n-1;i++)
        { if(maxToRightArray[i]>A[i])
        {
            //This check is important
            maxSum=Math.max(maxSum,A[i]+maxToRightArray[i]+maxToLeftArray[i]);}
            //if
        }//for

        return maxSum;
    }//solve()

    public static void main(String[] args) {
        int[] A=new int[]{1, 2, 3};
        int res=new MaximumSumTriplet().solve(A);
        System.out.println(res);
    }
}//MaximumSumTriplet
