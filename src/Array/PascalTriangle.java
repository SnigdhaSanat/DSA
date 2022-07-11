package Array;

public class PascalTriangle {
    public int[][] solve(int k) {

// simple method of building ith row from (i-1)th row
        int[][] A = new int[k][];
        for(int i=0;i<k;i++){
            int[] row=new int[i+1];
            for(int j=0;j<=i;j++){
                if(i==0){
                    row[0]=1;
                }//if

                else if(i==1){
                    row[0]=1;
                    row[1]=1;
                }//if

                else{
                    if(j==0 || j==i)
                    {
                        row[j]=1;
                    }
                    else{
                        row[j]=A[i-1][j-1]+A[i-1][j];
                    }
                }//else

            }//inner for
            A[i]=row;
        }//outer for
        return A;
    }

    public static void main(String[] args) {
        int k=5;
        int[][] res=new PascalTriangle().solve(k);

        for(int i=0;i<res.length;i++){
            int[] row=res[i];
            for( int j=0;j<row.length;j++){
                System.out.print(row[j]+" ");
            }//inner loop
            System.out.println();
        }//outer loop
    }//main
}//PascalTriangle
