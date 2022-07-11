package Array;

public class PerfectPeakOfArray {

    /*Idea: Get the max_so_far and min_from_here at every i. Then for any i, if max_so_far[i-1]<A[i] AND
    * A[i]<min_from_here[i+1], break and return 1 */
    public int perfectPeak(int[] A) {
        int n=A.length;
        int[] max_so_far=new int[n];
        int[] min_from_here=new int[n];

        //find the max so far
        int curr_max=Integer.MIN_VALUE  ;
        for(int i=0;i<n;i++){
            if(A[i]>curr_max)
            {
                curr_max=A[i];
            }
            max_so_far[i]=curr_max;
        }//for

        //find min_from_here
        int curr_min=Integer.MAX_VALUE;
        for(int i=n-1;i>=0;i--){
            if(A[i]<curr_min)
            {
                curr_min=A[i];
            }
            min_from_here[i]=curr_min;

        }//for

        int result=0;
        int i=1;
        while(i<n-1){
            if(max_so_far[i-1]<A[i] && A[i]< min_from_here[i+1]){
                //checking if A[i] is the peak
                result=1;
                break;
            }
            i+=1;

        }//for

        if(result==0 && i==n-1){
            //not found
            result=0;
        }
        return result;
    }//perfectPeak

    public static void main(String[] args) {
        int[] A=new int[]{5, 1, 4, 4};
        int res=new PerfectPeakOfArray().perfectPeak(A);
        System.out.println(res);
    }
}//PerfectPeakOfArray
