package Array;

import java.util.Arrays;

public class NextPermutation {
    /*The question requires the replacement to be in-place, and not allocate extra memory.*/
    public int[] nextPermutation(int[] A) {

        /* Eg: 0,2,4,3,1
        The idea is simple. From the last index, find the subarray which is ascending from THE LAST, which  might only be the last index
        (4,3,1 in the example).Now, outside that subarray, pick the immediate previous element(2 in example). Now, find  the element
        from the subarray which is its successor.Swap that element with the successor element from the array
        (3 from the subarray is 2's successor, so swap 2 and 3). (In the example, we now have: 0,3,4,2,1) Now with the new subarray(4,2,1),
        simply reverse it in order to sort it.

        Why this works: The descending subarray you get is the highest permutation for that index. For the next permutation, you need to
        move index to the left, and find the successor of the previous element for that index.
        Previous element will fit inside the decreasing array range, else the decreasing array would have continued. To find its
        successor means, in the entire permutation sequence, we move the index further to the left by 1, with 'previous' replaced
        by 'previous's' successor. And every time we move the index to left, with a successor, we start with an ascending array */

        int n=A.length;

        if(n==1){
            return A;
        }
        int indxDesc=n-1;
        int nextToDesc=indxDesc-1;

        //finding the decreasing array limits
        while(nextToDesc>=0 && A[nextToDesc]>=A[indxDesc]){//equal for duplicate array
            indxDesc-=1;
            nextToDesc-=1;
        }

        if(nextToDesc<0 ){
            //if it is the highest Permutation, return the lowest number as stated in the question
            Arrays.sort(A);
            return A;
        }

        //Find the successor of A[nextToDesc]
        int indexSuccessor=n-1;
        while(A[indexSuccessor]<=A[nextToDesc]){
            /*we need A[indexSuccessor]>A[nextToDesc]. As the sub array is ascending from the last, we can traverse serially*/
            indexSuccessor-=1;
        }

        //Swap
        int temp=A[nextToDesc];
        A[nextToDesc]=A[indexSuccessor];
        A[indexSuccessor]=temp;

        // As the swap happened with its successor, the subarray from the last will still be ascending

        //Sort the remaining array, effectively reverse it
        int i=indxDesc; int j=n-1;
        while(j>i){
            int temp2=A[i];
            A[i]=A[j];
            A[j]=temp2;
            i+=1;
            j-=1;

        }

        return A;
    }//nextPermutation

    public static void main(String[] args) {
        int[] A=new int[]{1, 2, 3};
        int[] res=new NextPermutation().nextPermutation(A);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }//main
}//NextPermutation
