package Array;

public class KthRowOfPascalsTriangle {
    public int[] getRow(int k) {
// simple method of building ith row from (i-1)th row
        k=k+1;//since k given is 0 base
        int [][] A=new int[k][k];

        for(int i=0;i<k;i++){
            for(int j=0;j<=i;j++){

                if(i==0){
                    A[0][0]=1;
                }//if

                else if(i==1){
                    A[1][0]=1;
                    A[1][1]=1;
                }//if

                else{
                    if(j==0 || j==i)
                    {
                        A[i][j]=1;
                    }
                    else{
                        A[i][j]=A[i-1][j-1]+A[i-1][j];
                    }
                }//else

            }//inner for
        }//outer for
        int[]res=new int[k];
        for(int i=0;i<k;i++){
            res[i]=A[k-1][i];
        }//for
        return res;
    }//getRow

    public static void main(String[] args) {
        int k=3;
        int[] res=new KthRowOfPascalsTriangle().getRow(k);

        for(int i=0;i<res.length;i++){
            System.out.print(res[i]+" ");
        }
    }//main
}//KthRowOfPascalsTriangle
