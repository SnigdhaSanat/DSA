package Array;

public class MaximumSumSquareSubMatrix {
    public int solve(int[][] A, int b) {
        int n=A[0].length;
/* Find the sum of the first square:0,0 to b,b. Then along the row, keep on adding the right b length column and subtracting the left b length
column to the sum at every iteration. Basically move the b square towards right. Complete the row. Then, move the square a row down, and repeat.
At the starting of every row, calculate the left most square sum.*/

        int maxSum=Integer.MIN_VALUE;
        int sum=0;
        for(int i=0;i<b;i++){
            for(int j=0;j<b;j++){
                sum+=A[i][j];
            }//inner for
        }//outer for
        int leftMostSum=sum;

        maxSum=Math.max(maxSum,sum);

        int b_top=0;
        int b_bottom=b-1;
        while(b_bottom<n)
        {
            int b_left=0;
            int b_right=b;

            //sum for the leftmost square of the row
            if(b_top>0){
                int addBottom = 0;
                int subtractTop = 0;
                for (int j = 0; j < b; j++) {
                    addBottom += A[b_bottom][j];
                }

                for (int j = 0; j < b; j++) {
                    subtractTop += A[b_top-1][j];
                }

                sum = leftMostSum + addBottom - subtractTop;
                leftMostSum = sum;
                maxSum=Math.max(maxSum,sum);
            }

            while(b_right<n)
            {
                int add=0;
                for(int i=b_top;i<=b_bottom;i++){
                    add+=A[i][b_right];
                }

                int subtract=0;
                for(int i=b_top;i<=b_bottom;i++){
                    subtract+=A[i][b_left];
                }
                sum=sum+add-subtract;
                maxSum=Math.max(maxSum,sum);

                b_right+=1;
                b_left+=1;
            }//inner while

            b_top+=1;
            b_bottom+=1;

        }//outer while

        return maxSum;

    }//solve

    public static void main(String[] args) {
        int[][] a=new int[][]{
            {1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2},
            {3, 8, 6, 7, 3},
            {4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5}
        };
        int b=3;

        int res=new MaximumSumSquareSubMatrix().solve(a,b);
        System.out.println(res);
    }
}//MaximumSumSquareSubMatrix
