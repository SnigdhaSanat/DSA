package BinarySearch;

public class MatrixSearchMatrixSearch {

    /*This is a simple binary search. As the 2d array is sorted, copy it into a 1D array*/
    public int searchMatrix(int[][] A, int B) {
        /*
        Time complexity: log(mn)
        Space complexity: n*m
        * */

        int n=A.length;
        int m=A[0].length;

        int[] arr=new int[n*m];
        int arrSize=n*m;
        int index=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[index++]=A[i][j];
            }//inner for
        }//outer for

    /*
    Start:l=0,r=arrSize-1
    During:mid=b(short circuit), mid>b,mid<b
    End:l<=r
    Return:Else return 0
    */
        int res=search(arr,0,arrSize-1,B);
        return res;
    }//searchMatrix


    public int searchMatrix2(int[][] A, int B) {
        /*
        Time complexity: log(m)
        Space complexity: no extra space
        * */
    int n=A.length;
    int m=A[0].length;

    //find the correct row
       boolean found=false;
       int currRow=0;

        while(currRow<n && !found){
            if(A[currRow][0]<=B && B<=A[currRow][m-1]){
                found=true;
                break;
            }
            currRow+=1;
        }//while

        if(!found){
            //does not belong to any row
            return 0;
        }

//binary search in currRow
        /*
    Start:l=0,r=m-1
    During:mid=b(short circuit), mid>b,mid<b
    End:l<=r
    Return:Else return 0
    */
        int res=search(A[currRow],0,m-1,B);

    return res;
    }//searchMatrix



    static int search(int[] arr, int left,int right,int B){
        while(left<=right){
            int mid=left+(right-left)/2;

            if(arr[mid]==B){
                //short circuit
                return 1;

            }
            if(B<arr[mid]){
                right=mid-1;
            }//if

            else{
                left=mid+1;
            }//else

        }//while

        return 0;
    }//search

    public static void main(String[] args) {
        int[][]A=new int[][]{
                {3},
                {29},
                {36},
                {63},
                {67},
                {72},
                {74},
                {78},
                {85},
        };

        int B=41;
        int res=new MatrixSearchMatrixSearch().searchMatrix2(A,B);
        System.out.println(res);
    }//main
}//MatrixSearchMatrixSearch
