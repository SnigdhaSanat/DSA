package DP.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9696/offering/73073/problem/845

/*fn(n,w). We can either include the nth item, in which case we will call fn(n-1,w-w[n])+val[n],
* or we can exclude nth item, in which case we will call fn(n-1,w). Find the maximum of the 2 */
public class Knapsnack {
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] memo=new int[n+1][maxWeight+1];

        //initialize 0th row and 0th column
        for(int j=0;j<=maxWeight;j++){
            memo[0][j]=0;
        }
        for(int i=0;i<=n;i++){
            memo[i][0]=0;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=maxWeight;j++){
                if(j-weight[i-1]>=0){
                    //check if w-w[n]>=0
                    //max of include and exclude
                    memo[i][j]=Math.max(memo[i-1][j-weight[i-1]]+value[i-1],memo[i-1][j]);//weight and value indices are [i-1], because we are taking 1 based index
                }
                else{
                    memo[i][j]=memo[i-1][j];
                }
            }//inner loop
        }//outer loop

        return memo[n][maxWeight];
    }//knapsack

    public static void main(String[] args) {
        int[] weight=new int[]{1, 2, 4, 5};
        int[] value=new int[]{ 5, 4, 8, 6};
        int n=weight.length;
        int maxWeight=5;

        int res=knapsack(weight,value,n,maxWeight);
        System.out.println(res);
    }//main
}
