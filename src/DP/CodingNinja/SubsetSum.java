package DP.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9696/offering/73077/problem/1941

/*For fn(i,sum), either fn(i-1, sum-value of i) should exist, if we include ith element.
* If we exclude ith element, fn(i-1, sum) should exist*/
public class SubsetSum {
    static boolean isSubsetPresent(int[] arr, int n, int sum) {
        boolean[][] memo=new boolean[n+1][sum+1];

        //if n=0 result will be false
        for(int j=0;j<=sum;j++){
            memo[0][j]=false;
        }

        //if sum=0, we can make a 0 sum with an empty subset. memo[0][0] is overwritten as true by this loop
        for(int i=0;i<=n;i++){
            memo[i][0]=true;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=sum;j++){
                if(j-arr[i-1]>=0){
                    //include
                    //note that this is a subset, not the coin change problem with infinite coins of each denomination,
                    // hence it is (i-1) in both the cases
                    memo[i][j]=(memo[i-1][j-arr[i-1]])||(memo[i-1][j]);
                }
                else{
                    //exclude
                    memo[i][j]=memo[i-1][j];
                }
            }//inner loop
        }//outer loop
 return memo[n][sum];
    }//isSubsetPresent

    public static void main(String[] args) {
        int[] arr=new int[]{4, 2, 5, 6, 7};
        int n=arr.length;
        int sum=14;
        boolean res=isSubsetPresent(arr,  n,  sum);
        System.out.println(res);
    }
}//SubsetSum
