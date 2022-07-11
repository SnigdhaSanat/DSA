package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;

public class SortedInsertPosition {
    public int searchInsert(ArrayList<Integer> a, int b) {
        int n=a.size();
    /*
    start:left=0,right=n-1

    during: b<=mid: right=mid-1, b>mid: left=mid+1 //short circuiting is optional here, as even if we don't short circuit, we would break the loop
    eventually after narrowing down left AND right to that index

    ends when:while left<=right
    returns: It will break the loop and return when A[mid] is an exact match. But if it comes to left<=right, and loop ends, return left

    Return what:left. What happens in case of the last element? When left==right, we are already having a valid answer(if at all the element is present in the list),
    but to get a better answer, we assign right=mid-1. If the element is not present at all, then also, left would be the return value, as current A[left]
    is the element just bigger than B.

    This is where the condition left<=right breaks. So what stays valid then? The prev iteration values
where left<=right. At the current iteration, this is returned by left, as right was modified. Note that we need the index here, not the count.So
no modification.
    */

        int left=0;
        int right=n-1;

        while(left<=right){
            int mid=left+(right-left)/2;

//            if(a.get(mid)==b){
//                //short circuiting is optional here
//                return mid;
//            }

            if (b<=a.get(mid)){
                right=mid-1;
            }
            else{
                left=mid+1;
            }

        }//while
        return left;
    }//searchInsert

    public static void main(String[] args) {
        ArrayList<Integer> A=new  ArrayList<Integer>(Arrays.asList(1, 3, 5, 6));
        int B = 2;
        int res=new SortedInsertPosition().searchInsert(A,B);
        System.out.println(res);
    }//main
}//SortedInsertPosition
