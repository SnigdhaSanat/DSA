package BinarySearch;

public class PaintersPartitionProblem {
    /**This problem is similar to the AllocateBooks problem. Here also, basically we need the minimum value of Max length allocated to individual
     * painters. Why min? Only then we can parallelize the work, and the overall time will be minimum, which is what the question requires*/
    public int paint(int A, int B, int[] C) {

        /**A:#painters  B: units of time to paint 1 unit of board. C: #boards*/
        int n=C.length;

        long lengthSum=0;
        for(int i=0;i<n;i++){
            lengthSum+=C[i];
        }//for

 /**We take left as 0, right as lengthSum, and then we iterate over the minimum time using binary search. Here, we can run
 out of boards for painters, but not the other way round; meaning a painter can be left out without work,
 but a board can't be left out without a painter*/

//As the values are large, take type 'long' for left, right and mid range. Convert into %10000003 later
        long left=0; long right=lengthSum;

        long res=-1;

        while(left<=right){
            long mid=(left+right)/2;

            if(isFeasible(C,n,A,B,mid)){

/*if isFeasible, this might be an answer. Store this in res. Still continue searching towards left for a lesser value.*/
                res=mid;
                right=mid-1;//to find even smaller time
            }
            else{
                left=mid+1;//false returned by isFeasible is always because the mid is too small
            }
        } //while

        /*res is the longest board assigned to an individual painter(which is what we minimized). As the work
        * was parallelized, this max individual length(res) took the longest time. So multiply it by B.*/

        return (int)(B*res%10000003)% 10000003;///because we need the time here, not the actual max length
    }//paint

    boolean isFeasible(int[] C, int n,int A, int B, long mid){
/**A:#painters  B: units of time to paint 1 unit of board. C: #boards*/
        int curSum=0;
        int noOfPainters=1;

        for(int i=0;i<n;i++){

            if(C[i]>mid)
            {//it is individually more than the limit
                return false;
            }//too less mid

            int possibleCandidate=curSum+C[i];
            if(possibleCandidate>mid){
                //time for the next painter
                noOfPainters+=1;

                //reset currSum
                curSum=C[i];

                if(noOfPainters>A)
                {
                    return false;//too less mid. If you had a greater value of mid, less painters would have been reqd
                }

            }
            else{
                curSum=possibleCandidate;
            }

        }//for
        return true;
    }//isFeasible

    public static void main(String[] args) {
        int A = 1;
        int B = 1000000;
        int[] C = new int[]{1000000, 1000000};
        int res= new PaintersPartitionProblem().paint(A,B,C);
        System.out.println(res);
    }//main
}//PaintersPartitionProblem
