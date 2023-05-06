package Array;

public class MaxMin {
    public int solve(int[] A) {
        int max=-1000000000;
        int min=1000000000;
        int size=A.length;
        int sum=0;
        for(int i=0;i<size;i++)
        {
            if(A[i]<min)
            {
                min=A[i];
            }
            if(A[i]>max){
                max=A[i];
            }
        }
        sum=min+max;
        return sum;
    }//solve

    public static void main(String[] args) {
        int[] A=new int[]{1, 3, 4, 1};
        int res=new MaxMin().solve(A);
        System.out.println(res);

    }//main
}//MinMax
