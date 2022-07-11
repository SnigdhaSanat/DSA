package Array;

public class MaxSumContiguousSubarray {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int maxSubArray(final int[] A) {

    /* Central idea: Initiate start and end indices as 0, and curr_sum as 0,  and a max with min int value.

    From the left, add arr[i] to the max_so_far_sum. Update max_so_far_sum if required. As and when you encounter a negative
    element, reset start to i+1. As any positive single element will be better off.

    If all the values are negative, the largest number (least negative) element will be the  max sum  */
        int n=A.length;

        //Start and end are only used for demos when the questions requires the start and end indices of the max sum.
        int start=0;
        int end=0;
        int curr_sum=0;
        int max_so_far_sum= -1000;

        for(int i=0;i<n;i++)
        {
            //curr_sum+=A[i];
            if(curr_sum+A[i]<0)
            {
                start=i+1;
                end=i+1;
                /*This part holds special significance for an all negative array. For an array with a mix of positives and negatives,
                * obviously we don't have to add the current negative element into curr_sum, and max_so_far_sum> A[i], so max_so_far_sum
                * stays unchanged. Basically we can keep this if condition empty.
                * But for an all negative element,for every iteration we will hit this if condition, and then A[i] might turn out to be better
                * (less negative) than max_so_far_sum. Hence this check.*/
                curr_sum=0;
                max_so_far_sum=Math.max(max_so_far_sum, A[i]);
            }
            else
            {
                end+=1;
                curr_sum+=A[i];
                max_so_far_sum=Math.max(max_so_far_sum, curr_sum);
            }

        }//for loop
        return max_so_far_sum;
    }//maxSubArray

    public static void main(String[] args) {
        int[] A= new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res=new MaxSumContiguousSubarray().maxSubArray(A);
        System.out.println(res);
    }
}
