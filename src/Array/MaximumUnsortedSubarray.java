package Array;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumUnsortedSubarray {
    public int[] subUnsort(int[] A) {

        /*Simply create a copy of sorted array, then compare it with the unsorted one, and check the min and max indices where elements
        if the sorted and the original array mismatch*/
        int n=A.length;
        int[] ACopy=new int[n];

        for(int i=0;i<n;i++){
            ACopy[i]=A[i];
        }//for

        Arrays.sort(ACopy);

        int startIndex=-1;
        int endIndex=-1;
        for(int i=0;i<n;i++){
            if(A[i]!=ACopy[i]){
                //if mismatch happens with the sorted array
                if(startIndex==-1)
                {
                    // if this is the first index
                    startIndex=i;
                }
                endIndex=i;
            }//if
        }//for

        if(startIndex==-1){
            //and endIndex will also be -1
            return new int[]{-1};

        }

        return new int[]{startIndex,endIndex};
    }//subUnsort

    public static void main(String[] args) {
       int[] A = {1, 3, 2, 4, 5};
       int[] res=new MaximumUnsortedSubarray().subUnsort(A);
       for(int i=0;i<res.length;i++){
           System.out.println(res[i]);
       }
    }
}//MaximumUnsortedSubarray
