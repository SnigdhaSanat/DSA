package Array;

import java.util.Arrays;

public class MaximumConsecutiveGap {
    /*Sorting will take the time complexity to O(nlogn). We need to do it in linear time. Use bucketing.
    * #intervals will be (length-1). Create interval length.
    * Eg:
    * For [3,6,9,1,18] will be:
    * 1    -------2,3,4,---    -----6---9------      --------------     --------------   18.
    *
    * This works because the intervalRanges are based on equal gaps. So the gap BETWEEN max of previous and min of the next bucket
    * at any point will be higher than the gap of elements WITHIN the buckets. So this is what we use to calculate the max gap*/
    public int maximumGap(final int[] A) {
        int n=A.length;
        if(n<2){
            return 0;
        }
        if(n==2){
            return Math.abs(A[0]-A[1]);
        }

        //Find the min and max elements
        int minElement= Integer.MAX_VALUE;
        int maxElement=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            //Time complexity: O(n)
            minElement=Math.min(minElement,A[i]);
            maxElement=Math.max(maxElement,A[i]);
        }
        if(minElement==maxElement){
            return 0;
        }

        int intervalCount=n-1;

        //Take the ceil value
        int intervalRange=(int)(Math.ceil((maxElement-minElement+1.0)/intervalCount));

        //Now do the bucketing. Also track the min and max for each bucket. Leave out the min and max element of the input array though

        int[] minInTheBucket=new int[intervalCount];
        int[] maxInTheBucket=new int[intervalCount];

        //Initialize
        for(int i=0;i<intervalCount;i++){
            minInTheBucket[i]=Integer.MAX_VALUE;
            maxInTheBucket[i]=Integer.MIN_VALUE;
        }

        //Bucketing
        for(int i=0;i<n;i++){
            //Time complexity: O(n)
            int bucket=(A[i]-minElement)/intervalRange;//will be the floor value by default
            minInTheBucket[bucket]=Math.min(minInTheBucket[bucket],A[i]);
            maxInTheBucket[bucket]=Math.max(maxInTheBucket[bucket],A[i]);

        }//for

        int prevMax=maxInTheBucket[0];

        int currGap;
        int maxGap=Integer.MIN_VALUE;

        //Find the max gap. Leave out empty buckets
        for(int i=1;i<intervalCount;i++){
            //Time complexity: O(n-1)
            if(maxInTheBucket[i]==Integer.MIN_VALUE){
                continue;
            }
            currGap=minInTheBucket[i]-prevMax;
            maxGap=Math.max(maxGap, currGap);

            prevMax=maxInTheBucket[i];
        }

        return maxGap;
    } //maximumGap

    public static void main(String[] args) {
        int[] A=new int[]{100, 100, 100, 100, 100};
        int res=new MaximumConsecutiveGap().maximumGap(A);
        System.out.println(res);
    }
}//MaximumConsecutiveGap
