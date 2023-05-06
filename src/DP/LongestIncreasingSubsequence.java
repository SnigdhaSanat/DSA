package DP;

public class LongestIncreasingSubsequence {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int lis(final int[] A) {
        /*NOTE: here ALL the smaller sub problems have to be calculated, not just n-1 or n-2. So DP can have
        a n*n complexity too, which often happens in the case of string problems.

        Brute complexity: For each element, we can either take or not take it, so we have 2^n choices of subsequences.
        Then for each of those 2^n subsequences, you would iterate to check if that is a strictly increasing subsequence,
        and if so, update the res globalMax. So complexity will be n*(2^n).

        fn(i) = max length among all fn(j), where fn(j) can be fn(0) ....fn(i-1),
        AND also A[j]<A[i]. Add 1 to that.
        If there is no smaller index satisfying this condition then assign 1, as we will take the globalMax anyway*/


        int len=A.length;

        if(len==1){
            //return 1 if A has only one element
            return 1;
        }

        int[] memo=new int[len];

        memo[0]=1;//for the first element
        int globalMax=Integer.MIN_VALUE;

        for(int i=1;i<len;i++){
            int max=Integer.MIN_VALUE;
            int index=-1;//default

            for(int j=0;j<=i-1;j++){
                if(A[j]<A[i] && memo[j]>max){
                    max=memo[j];
                    index=j;
                }//if
            }//inner for

            if(index==-1){
                memo[i]=1;
            }
            else{
                memo[i]=memo[index]+1;
            }
            //update globalMax
            if(memo[i]>globalMax){
                globalMax=memo[i];
            }
        }//outer for

        //we return the max, NOT memo[len-1]
        return globalMax;
    }//lis

    public static void main(String[] args) {
        int[] A = {14, 24, 18, 46, 55, 53, 82, 18, 101, 20, 78, 35, 68, 9, 16, 93, 101, 85, 81, 28, 78 };
        int res=new LongestIncreasingSubsequence().lis(A);
        System.out.println(res);
    }//main
}//LongestIncreasingSubsequence
