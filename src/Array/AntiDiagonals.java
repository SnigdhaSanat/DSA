package Array;

public class AntiDiagonals {
    public int[][] diagonal(int[][] A) {
        int n=A.length;
        int[][] res=new int[2*n-1][];
        int i_start=0,j_start=0;
        int row_length=1;
        int res_idx=0;//row of res arr

        while(i_start<n && j_start<n){
            int i_curr=i_start, j_curr=j_start;
            int[] row_array=new int[row_length];
            int row_idx=0;//idx of row_array

            while(j_curr>=0 && i_curr<n)
            {
                row_array[row_idx]=A[i_curr][j_curr];
                i_curr+=1;
                j_curr-=1;
                row_idx+=1;
            }//inner loop ends=>row ends

            //put the row_array into res
            res[res_idx]=row_array;
            res_idx+=1;

            if(j_start<n-1){
                j_start+=1;
                row_length+=1;
            }
            else{
                i_start+=1;
                row_length-=1;
            }
        }//outer loop
        return res;
    }

    public static void main(String[] args) {
        int[][] param=new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        int[][] res=new AntiDiagonals().diagonal(param);

        for(int i=0;i<res.length;i++){
            int[] row=res[i];
            for( int j=0;j<row.length;j++){
                System.out.print(row[j]+" ");
            }//inner loop
            System.out.println();
        }//outer loop
    }
}//AntiDiagonals
