package DP.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73041/problem/855
public class CoinChange {
    public static int countWaysToMakeChange(int denominations[], int value){
        /*If the question was about finding the min number of coins rather than total number of ways, it would have been same,
        * except that then equation would have been:

        int temp=(j-denominations[i-1])>=0?memo[i][j-denominations[i-1]]:0;
        memo[i][j]=min(temp+1,memo[i-1][j]);//Note: Plus 1 because we are adding current denominations i to the existing denominations
         */
        /* Eg: value=4 and denominations[]=1,2,3. So create the following table, where j:values, i:denominations

          0|1|2|3|4|
       {}|1|0|0|0|0| //with an empty array, except value=0, we can make all other values in 0 no. of ways
        1|1|1|1|1|1|
        2|1|1|2|2|3|
        3|1|1|2|3|4|
        //If value=0, no matter if the denominations array is empty or not, we can create the value in 1 number of ways
        * */
        int denomLen=denominations.length;

        //#rows is denomLen+1, to accommodate the empty array.
        //#cols is value +1, to accommodate 0 value
        int[][] memo=new int[denomLen+1][value+1];

        //initialize 1st col to 1, as explained above, and 1st row except 0 to 0
        for(int i=0;i<=denomLen;i++){
            memo[i][0]=1;
        }
        for(int j=1;j<=value;j++){
            memo[0][j]=0;
        }

        for(int i=1;i<=denomLen;i++){
            for(int j=1;j<=value;j++){

                //denominations[i-1] because we are artificially inserting an empty array/denomination at the top
                int temp=(j-denominations[i-1])>=0?memo[i][j-denominations[i-1]]:0;//to check out of bounds exception
                /*Either we take the denominations i, which case then it fits in value:(j-denominations[i-1]), which is the column/value. Row stays i because
                * we have an infinite supply of each denomination, and this is the case where we DO TAKE denominations i.
                *
                * Else we DO NOT TAKE denominations i, which means we have to consider row/denominations: i-1. Since this denominations do not contribute
                * to current value, so we have to take the value/j as j itself*/
                memo[i][j]=temp+memo[i-1][j];

            }//inner for
        }//outer for

        return memo[denomLen][value];

    }//countWaysToMakeChange

    public static void main(String[] args) {
        int[] denom=new int[]{1, 2, 3};
        int val=4;
        int res=CoinChange.countWaysToMakeChange(denom,val);
        System.out.println(res);
    }//main
}//CoinChange
