package Array;

public class SpiralOrderMatrixII {
    public int[][] generateMatrix(int A) {
        int n=A;
        int ring_limit;
        if(n<2){
            ring_limit=0;
            int[][] arr = {{1}};
            return arr;
        }//if
        else{
            ring_limit=(int)Math.ceil((double)n/2-1);

            int[][] sq_matrix=new int[n][n];
            int value=1;//value to put in the square matrix positions
            int i=0,j=0;//i is curr row, j is curr column

            for(int k=0;k<=ring_limit;k++){
                if(k==n-1-k){
                    //the last ring or single point square for odd numbers
                    i=k;j=k;
                    sq_matrix[i][j]=value;
                }

                //top row------
                i=k;
                for(j=k;j<n-1-k;j++){
                    sq_matrix[i][j]=value;
                    value+=1;
                }

                //right column------
                j=n-1-k;
                for(i=k;i<n-1-k;i++){
                    sq_matrix[i][j]=value;
                    value+=1;
                }


                //down row------
                i=n-1-k;
                for(j=n-1-k;j>k;j--){
                    sq_matrix[i][j]=value;
                    value+=1;

                }

                //left column------
                j=k;
                for(i=n-1-k;i>k;i--){
                    sq_matrix[i][j]=value;
                    value+=1;
                }

            }// for loop

            return sq_matrix;
        }//else
    }//generateMatrix

    public static void main(String[] args) {
        int A=3;
        int[][] res=new SpiralOrderMatrixII().generateMatrix(A);
        for(int i=0;i<res.length;i++){
            int[] row=res[i];
            for( int j=0;j<row.length;j++){
                System.out.print(row[j]+" ");
            }//inner loop
            System.out.println();
        }//outer loop
    }
}//SpiralOrderMatrixII
