package BinarySearch;

public class SearchForARange {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int[] searchRange(final int[] A, int B) {
        int n=A.length;

        /**
         * algorithm's runtime complexity must be in the order of O(log n).
         */

        //for starting
    /**
    start: left=0,right=n-1
    during:b<=mid(no short circuiting): right=mid-1 else,left=mid+1
    end when:left<=right
Return what:return the possible res
    */
        int left=0,right=n-1;
        int possibleStaring=-1;

        while(left<=right){
            int mid=left+(right-left)/2;
            if(B<=A[mid]){
                possibleStaring=mid;
                right=mid-1;
            }
            else{
                left=mid+1;
            }

        }//while

        //A[possibleStaring]==B check is important
        int starting=(possibleStaring>=0 && possibleStaring<n && A[possibleStaring]==B)?left:-1;


    //for ending
    /*
    start: left=0,right=n-1
    during:(no short circuiting): if B>=A[mid] left=mid+1; else right=mid-1;
    end when:left<=right
    return what: Similar logic as applied for the starting element, so right
    */

        left=0;right=n-1;
        int possibleEnding=-1;

        while(left<=right){
            int mid=(left+right)/2;
            if(B>=A[mid]){
                possibleEnding=mid;
                left=mid+1;
            }
            else{
                right=mid-1;
            }

        }//while

        //A[possibleEnding]==B check is important
        int ending=(possibleEnding>=0 && possibleEnding<n && A[possibleEnding]==B)?right:-1;

        int[] res=new int[]{starting,ending};
        return res;

    }//searchRange

    public static void main(String[] args) {
        int[] A=new int[]{4, 7, 7, 7, 8, 10, 10};
        int B=3;

        int[] res=new SearchForARange().searchRange(A,B);

        for(int i=0;i<2;i++){
            System.out.println(res[i]);
        }//for
    }//main
}//SearchForARange
