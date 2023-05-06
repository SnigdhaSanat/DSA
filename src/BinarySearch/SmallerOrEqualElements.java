package BinarySearch;

public class SmallerOrEqualElements {
    public int solve(int[] A, int B) {
/*Things to decide in a binary search:

Conditions for shifting the right or left at every iteration: In this case, to take care of duplicates, if mid<=B, left=mid+1

When to break the iteration:l<=r. When we are dealing with duplicates, it is best to break when l<=r, instead of l<r.
IMP: As a thumb rule, for inclusive bounds(when r=arr.len-1), take l<=r. And for exclusive bounds(when r=arr.len) take l<r.
Reference: https://stackoverflow.com/questions/35256433/binary-search-terminating-condition

What to return: This might be tricky.By doing a simple dry run, in this case it turns out to be r+1
What happens in case of the last element? When A[mid]==B, we are already having a valid answer, but to get a better answer
, we assign left=mid+1. This is where the condition left<=right breaks. So what stays valid then? The prev iteration values
where left<=right. At the current iteration, this is returned by right+1(or left), as left was modified. Why +1 to right?
right is the last B element of the 0 based index array, while the question needs the count.

You need to find the last of the duplicate(if any) of B
*/

        int left=0;
        int right=A.length-1;

        int possibleResult=-1;

        while(left<=right){
            int mid=(left+right)/2;

            //Cannot do short circuiting even if mid is a match, as we need the last element
            if(A[mid]<=B){
                possibleResult=mid;
                left=mid+1;

            }
            else{
                right=mid-1;
            }

        }//while

        return possibleResult+1;

    }//solve

    public static void main(String[] args) {
        int[] A=new int[]{1, 3, 4, 4, 6, 6, 6, 6, 6, 7};
        int B=6;
        int res=new SmallerOrEqualElements().solve(A,B);
        System.out.println(res);
    }//main
}//SmallerOrEqualElements
