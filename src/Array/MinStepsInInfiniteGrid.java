package Array;

public class MinStepsInInfiniteGrid {
    public int coverPoints(int[] A, int[] B) {
        int n=A.length;
        int sum=0;
        int step;
        for (int i=1;i<n;i++)
        {
            //for each step, the value is the larger of x[i]-x[i-1] vs y[i]-y[i-1], the smaller will be automatically covered
            //with the larger diff. If diagonal movement wasn't allowed, you would take sum instead of the max of the diffs
            step=Math.max(Math.abs(A[i]-A[i-1]), Math.abs(B[i]-B[i-1])) ;
            sum+=step;
        }//for loop
        return sum;
    }//coverPoints

    public static void main(String[] args) {
        int[] A=new int[]{0, 1, 1};
        int[] B = new int[] {0, 1, 2};
        int res=new MinStepsInInfiniteGrid().coverPoints(A,B);
        System.out.println(res);
    }//solve
}//MinStepsInInfiniteGrid
