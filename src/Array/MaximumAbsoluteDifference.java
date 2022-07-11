package Array;

public class MaximumAbsoluteDifference {
    public int maxArr(int[] A) {
/* We need: |A[i] - A[j]| + |i - j|, which with all the +/- extended into 4 cases, boils down into:
max diff of (A[i]+i) and (A[j]+j);  and max diff of (A[i]-i) and (A[j]-j), and finally max between the two
*/
        int n=A.length;
        int minAdd=Integer.MAX_VALUE;
        int maxAdd=Integer.MIN_VALUE;
        int minDiff=Integer.MAX_VALUE;
        int maxDiff=Integer.MIN_VALUE;
        int currAdd;
        int currDiff;
        for(int i=0;i<n;i++){

            currAdd=A[i]+i;
            maxAdd=Math.max(maxAdd, currAdd);
            minAdd=Math.min(minAdd, currAdd);

            currDiff=A[i]-i;
            maxDiff=Math.max(maxDiff, currDiff);
            minDiff=Math.min(minDiff, currDiff);
        }//for loop

        /*Now there will be a max and min of A[i]+i, and max and min of A[i]-i. Find the range of both, and return the greater
        * of the 2*/
        return Math.max(maxAdd-minAdd,maxDiff-minDiff);
    }//maxArr

    public static void main(String[] args) {
        int[] A=new int[]{1, 3, -1};
        int res=new MaximumAbsoluteDifference().maxArr(A);
        System.out.println(res);
    }
}//MaximumAbsoluteDifference
