package Sort;

public class QuickSort {
/*Time complexity: T(n)=T(k)+T(n-1-k)+O(n), where k is the pivot, and o(n) is for the partition.

Worst case happens  when the pivot element is the largest element. So in this case of choosing the last element as pivot, this
will happen if the array is in an ascending order. In this case, we don't really partition into 2 halves.
As k=n-1, so T(n)=T(k)+T(n-1-k)+O(n)=T(n-1)+T(0)+O(n)=t(n-1)+o(n). So the recursion will go on depth n, since as only progress 1 step at a time.
This makes the time complexity n*(n)= n^2.

Best case happens when the pivot element is the middle element. So we partition the array into 2 almost equal halves.
T(n)=T(n/2)+T(n/2)+O(n)=2T(n/2)+O(n). So the recursion will go till an approximate depth of log n, which makes ime complexity nlog(n).

Almost best case: Although here it wil not be a n/2+ n/2 split, but something like n/3+2n/3, the average depth of the recursion will still be logn,
 although the exact depth of the greater side will be logn to the base 3/2. So the time complexity remains nlogn.*/
    void swap(int[] A, int i,int j){
        int temp=A[i];
        A[i]=A[j];
        A[j]=temp;
    }//swap

    int partition(int[] A, int lo, int hi){
        int pivotElement=A[hi];
        int i=lo-1;//i is the current lowest point

        for(int j=lo;j<=hi-1;j++){
            //j is the iterator, which runs from lo to hi-1
            if(A[j]<pivotElement){
                //aligning the elements on the both ends of the pivot. So move A[j] to extreme left
                i+=1;
                swap(A, i,j);//swap j with the current lowest point
            }//if
        }//for

        //finally swap the pivotElement to its correct place
        swap(A, i+1, hi);
        return i+1;
    }//partition

    void sort(int[] A, int lo, int hi){
        if(lo<hi){
            /*Why the comparison  of lo and hi is < and not <=? This is because, if we have lo==hi in the current iteration,
            * it means that in the previous iteration, it was placed in the correct position wrt the pivot, be it to the right or
            * the to left of the pivot. So it AND the pivot are sorted. So that single element does not have to be sorted anymore. */
            int pivotIndex=partition(A, lo, hi);
            sort(A,lo,pivotIndex-1);
            sort(A, pivotIndex+1, hi);
        }
    }//sort

    public static void main(String[] args) {

        int[] A=new int[]{10, 9, 8, 7, 5, 1};
        new QuickSort().sort(A,0,A.length-1);

        for(int i=0;i<A.length;i++){
            System.out.println(A[i]);
        }
    }//main
}//QuickSort
