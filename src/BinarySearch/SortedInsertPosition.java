package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;

public class SortedInsertPosition {
    public int searchInsert(ArrayList<Integer> a, int b) {
        int n=a.size();

/**
 start:left=0,right=n-1

 during: mid<b:left=mid+1, mid>b:right=mid-1, b==mid: Short circuit if b is present.
 But if b is not present, this condition will never be true. In that case we basically need the index of the last element smaller to b, and then add 1 to it. So for mid<b:left=mid+1, mid might be a valid value, so we store it.

 ends when:while left<=right

 Return what: If b is present, b==mid: Short circuit wil take care of it.

 Else we keep searching for #smaller elements, and so even if mid might be a valid value, we assign left=mid+1. We store this mid and return its +1.

 */

        int left=0;
        int right=n-1;

        int possibleValue=-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(a.get(mid)==b){
                //short circuiting
                return mid;
            }

            if (a.get(mid)>b){
                right=mid-1;

            }
            else{
                //a.get(mid)<b
                //mid might be a possible value, tht is the last index of a[i]<b
                possibleValue=mid;
                left=mid+1;

            }

        }//while
        return possibleValue+1;
    }//searchInsert

    public static void main(String[] args) {
        ArrayList<Integer> A=new  ArrayList<Integer>(Arrays.asList(1, 3, 5, 6));
        int B = 2;
        int res=new SortedInsertPosition().searchInsert(A,B);
        System.out.println(res);
    }//main
}//SortedInsertPosition
