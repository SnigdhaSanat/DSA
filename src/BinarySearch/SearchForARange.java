package BinarySearch;

public class SearchForARange {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int[] searchRange(final int[] A, int B) {
        int n=A.length;

    //for starting
    /*
    start: left=0,right=n-1
    during:b<=mid(no short circuiting): right=mid-1 else,left=mid+1
    end when:left<=right
Return what:left. What happens in case of the last element? When left==right, we are already having a valid answer, but to get a better answer
, we assign right=mid-1. This is where the condition left<=right breaks. So what stays valid then? The prev iteration values
where left<=right. At the current iteration, this is returned by left, as right was modified. Note that we need the index here, not the count.So
no modification
    */
        int left=0,right=n-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(B<=A[mid]){
                right=mid-1;
            }
            else{
                left=mid+1;
            }

        }//while

        //A[left]==B check is important
        int starting=(left>=0 && left<n && A[left]==B)?left:-1;


    //for ending
    /*
    start: left=0,right=n-1
    during:(no short circuiting): if B>=A[mid] left=mid+1; else right=mid-1;
    end when:left<=right
    return what: Similar logic as applied for the starting element, so right
    */

        left=0;right=n-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(B>=A[mid]){
                left=mid+1;
            }
            else{
                right=mid-1;
            }

        }//while

        //A[right]==B check is important
        int ending=(right>=0 && right<n && A[right]==B)?right:-1;

        int[] res=new int[]{starting,ending};
        return res;

    }//searchRange

    public static void main(String[] args) {
        int[] A=new int[]{5, 7, 7, 8, 8, 10};
        int B=8;

        int[] res=new SearchForARange().searchRange(A,B);

        for(int i=0;i<2;i++){
            System.out.println(res[i]);
        }//for
    }//main
}//SearchForARange
