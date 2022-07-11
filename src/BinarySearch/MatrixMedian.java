package BinarySearch;

public class MatrixMedian {
    public int findMedian(int[][] A) {
        int rows=A.length;
        int cols=A[0].length;
/*
IMP: Duplicates need to be considered. Also, median is about the distribution of numbers. For an array {1,2,3,4,10,100}, median will be far less than 50,
and for {1,60,90,92,99}, it will be far more than  50. this is what is used for the binary search.

The idea: Because each of the individual rows are sorted, we can find the overall min and max element in O(rows) time instead of O(rows*cols). That is the
only place where we can use the fact. That gives the intermediate  time complexity  to O(rows).

Now, we find the place where the median would have been, if the 2D array would have been fully sorted(expandable like a sorted 1D array). We basically
calculate the number of integers that will be smaller than the median. Then with min and max as left and right, we binary search upon mid, till we arrive
at the element where the number of elements SMALLER than it, is EQUAL OR SMALLER than the pre calculated smaller elements than the median. Why consider
smaller? Because in case of duplicate elements(Eg: 1,2,2,2,2,2,3,3,3,3,3),  2s will have a "smaller" count lesser than the required. Had we calculated
number of elements lesser OR EQUAL, then we would have considered where number of elements smaller or equal is equal or BIGGER than the reqd. Why?
See the duplicate array again for an answer.

start: left=min, right=max

during: Basically we find out if mid is the median, by comparing the numbers SMALLER to it with the required smaller numbers count, and keep adjusting.
smaller>reqd_smaller: right=mid-1. Why? The smaller obtained can be genuinely lesser(if there are duplicates as in the above example) than reqd, but not bigger.
smaller<=reqd_smaller: left=mid+1.
if equal, if the mid exists in the array, return that, basically short circuit.

end when:when  (left<=right)  is false

Return what:right. What happens in case of the last element? When left==right, we are already having a valid answer, but to get a better answer
, we assign left=mid+1. This is where the condition left<=right breaks. So what stays valid then? The prev iteration values
where left<=right. At the current iteration, this is returned by right, as left was modified.
*/

        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        /*if #elements in A are odd, say 9, then 9/2=4 is the # smaller elements <median. Of course it can be equal to the median if there are duplicates.
        * if #elements in A are even, say 8, then 8/2=4 is the # smaller elements <median. */
        int reqd_smaller=rows*cols/2;

//finding min and max of the entire 2D array A[][]
        for(int i=0;i<rows;i++){
            //as rows are sorted. Time complexity: O(row)=O(n)
            min=Math.min(min,A[i][0]);
            max=Math.max(max, A[i][cols-1]);
        }//for

        int left=min;
        int right=max;

        //Time complexity of while loop: log(max-min)*O(n*(log m))
        while(left<=right){
            int mid=(left+right)/2;

            int smaller=findSmallerNos(mid,A,rows,cols); //Time complexity O(n*(log m))

            if(smaller>reqd_smaller){
                //means the mid is too high
                right=mid-1;
            }
            else if(smaller<=reqd_smaller){
                //means the mid is too low
                left=mid+1;
            }
            else{
                //if smaller==reqd_smaller, short circuit
                    return mid;
            }//else

        }//while

        return right;

        //Overall time complexity: O(n)+ {log(max-min)*O(n*(log m))}
    }//findMedian

    static int findSmallerNos(int element, int[][] A, int rows, int cols)
    {
        /*Use the fact that the rows are sorted*/

        //finding the "strictly smaller than element" count in every row, and adding to the count
        //Time complexity O(n*(log m))
        int count=0;

        for(int i=0;i<rows;i++){
            //find #smaller(NOT equal) to mid at every row, and keep adding it to count
            int lo=0;
            int hi=cols-1;

            while(lo<=hi){
                //no short circuiting
                int mid=lo+(hi-lo)/2;
                if(A[i][mid]>=element){
                    hi=mid-1;

                }
                else{
                    lo=mid+1;
                }
            }//while

            int resRow=hi+1;//or lo as we need the count
            count+=resRow;

        }//for

        return count;
    }//findSmallerNos



    public static void main(String[] args) {
        int[][]A=new int[][]{
                {1, 3, 5},
                {2, 6, 9},
                {3, 6, 9}
        };

        int res=new MatrixMedian().findMedian(A);
        System.out.println(res);

    }//main
}//MatrixMedian
