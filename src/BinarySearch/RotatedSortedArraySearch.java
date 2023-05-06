package BinarySearch;

public class RotatedSortedArraySearch {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY

    public int search(final int[] a, int B) {
        //0 1 2 4 5 6 7  might become 4 5 6 7 0 1 2 because of rotation

        /*Duplicates need to be taken care of*/
        int n=a.length;

        //find the offset
        int offset=-1;

/*

Start:left=0;right=n-1

During: if a[mid-1]>a[mid](NOT >=(unless whole array is duplicate), as they are the end and starting of the array) return mid(short circuit).
If a[mid]>=a[right]:left=mid+1.
If a[0]>=a[mid]: right=mid-1

End: continue while left<right(Not equal).

Return: Since we are down to 2 elements, offset must be the "right"*/

        if(a[0]<a[n-1]){
            //NOT <=
            //0,1,2,3,4,5,6,7,8,9=> no offset
            /*offset is 0, basically no rotation. Else no matter whether the offset is at the mid, to its right, or its left,
            * a[0] will be the element after a[n-1], so a[0]>=a[n-1]*/
            offset=0;
        }

        else{
            //find the offset

            int left=0;
            int right=n-1;

            while(left<=right){

                int mid=(left+right)/2;

                if(a[mid-1]>a[mid]){
                    //there is no equal sign as we are comparing the min and max
                    //short circuit
                    offset= mid;
                    break;
                }

                //3,4,5,6,7,8,9,0,1,2=> a[mid]>=a[right], move to right(equal because of duplicates)
                else if(a[mid]>=a[right]){
                    left=mid+1;

                }

                //8,9,0,1,2,3,4,5,6,7=> a[left]>=a[mid], move to left(equal because of duplicates)
                else{
                    //a[left]>=a[mid]
                    right=mid-1;

                }
            }//while


            if(offset==-1){
                offset=right;
            }
        }//else

        //now do a simple binary search with the offset
        //IMP: calculate by ideals, only access through reals
        int left_ideal=0;
        int right_ideal=n-1;

        int res=-1;

        while(left_ideal<=right_ideal){
            //left_real=(left_idea+offset)%n
            //right_real=(right_ideal +offset)%n;

            int midSearchIdeal=(left_ideal+right_ideal)/2;

            int midSearchReal=(midSearchIdeal+offset)%n;

            if(a[midSearchReal]==B){
                res=midSearchReal;
                break;
            }
            else if(a[midSearchReal]<B){
                left_ideal=midSearchIdeal+1;
            }
            else{
                right_ideal=midSearchIdeal-1;
            }

        }//while

        return res;
    }//search

    public static void main(String[] args) {
        int[] A = new int[]{4, 5, 6, 7, 0, 1, 2, 3};
        int B = 4;
        int res=new RotatedSortedArraySearch().search(A,B);
        System.out.println(res);
    }//main
}//RotatedSortedArraySearch
