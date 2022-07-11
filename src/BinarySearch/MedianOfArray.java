package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedianOfArray {
    /*
Central idea: Take the smaller of the 2 arrays as A, and the other as B. Partition both the arrays into aLeft, aRight and
bLeft and bRight. 2 conditions that need to be met:

1st condition: If sum of elements of both the arrays are even,
then ALeft+BLeft elements should be equal to the number of elements in aRight and BRight combined. If sum of elements in
both the arrays are odd, then the extra element will be contained in left half. You can choose right half as well and then
tweak the logic accordingly.

2nd condition: The ALeft's last element should be <=BRight's first element(It is already lesser
than ARight's first element). And  BLeft's last element<= ARight's first element
*/

    /*How it works if A is too small?? In that case, aPartition will be m, and b will adjust accordingly
     */
    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        int m=a.size();
        int n=b.size();
        //ensure a is the smaller element, as we need time complexity O(log(min (m,n)))
        if(n<m){
            // return is important, else it will proceed with the rest with A array as larger
            return findMedianSortedArrays(b,a);
        }

/*now we binary search over the smaller array a. Every iteration, according to mid(which is a's partition), we fix B's partition(as the overall
elements in the right is already fixed:(m+n+1)/2), so that condition 1
is met. Then we check for condition 2.

Start:left:0, right:m Please note that IT IS NOT m-1.This is because m can have (m+1) partitions
During:if
End:
*/

        int left=0;
        int right=m;//NOT m-1


    /*we keep (m+n+1)/2 elements on the left in total. '+1' is to adjust for the odd case mentioned in Condition 1. That means:
    if there are total 5 elements vs 6 elements in both arrays combined, in both the cases we keep (m+n+1)/2=3 elements on
    left partitions combined.*/

        while(left<=right){
            int aPartition=(left+right)/2;//basically how many elements on the right, or where array a has to be partitioned. Ranges from 0 to m.

            //aPartition+bPartition together has to be (m+n+1)/2
            int bPartition=(m+n+1)/2-aPartition;//This satisfies condition 1

            int aLeft=(aPartition==0)?Integer.MIN_VALUE:a.get(aPartition-1);//if 0 elements on left
            int aRight=(aPartition==m)?Integer.MAX_VALUE:a.get(aPartition);//if 0 elements on right
            int bLeft=(bPartition==0)?Integer.MIN_VALUE:b.get(bPartition-1);//if 0 elements on left
            int bRight=(bPartition==n)?Integer.MAX_VALUE:b.get(bPartition);//if 0 elements on right

            //Now check the 2nd condition
            if(aLeft<=bRight && bLeft<=aRight){
                //found, so short circuit and return

                if((m+n)%2==0){
                    //if total elements is even, return avg(max(lefts), min(rights))
                    return (double)(Math.max(aLeft,bLeft)+Math.min(aRight,bRight))/2;

                }
                else
                    //if odd, as stated in condition 1, the extra element is on the left, so return max(lefts)
                    return (double)Math.max(aLeft,bLeft);
            }

            else if(aLeft>bRight){
                //a value is more=> move aPartition to left(and bPartition to right)
                right=aPartition-1;
            }
            else {
                //aLeft<=bRight, but bLeft>aRight
                //a value is less =>move to right
                left=aPartition+1;

            }

        }//while
        return -1;


    }//findMedianSortedArrays

    public static void main(String[] args) {
        ArrayList<Integer> A=new ArrayList<Integer>(Arrays.asList(1, 4, 5)) ;
        ArrayList<Integer> B=new ArrayList<Integer>(Arrays.asList(2,3)) ;
        double res=new MedianOfArray().findMedianSortedArrays(A,B);
        System.out.println(res);
    }
}//MedianOfArray
