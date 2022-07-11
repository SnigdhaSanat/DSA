package DP.CodingNinja;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9696/offering/73075/problem/1714

/*For each party i, we can either include that party or exclude that. If we include, fn(i,budget)=
 fn(i-1,budget-budget of i)+fun of i. If we exclude, fn(i,budget)=fn(i-1,budget). And take the max of the two.
  In taking budget-budget i, if budget of i is greater than the total budget,m we take only the exclusion option.*/
public class Party {
//Test cases are failing from 2 to 6 in CodeNinja platform. But Test case 2 runs correctly in this IDE
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int budget = 1;
        int n = 1;

        try{
            while (budget != 0 && n != 0) {
                 budget = sc.nextInt();
                 n = sc.nextInt();

                if(budget == 0 && n == 0)
                    break;
                int[] fee = new int[n];
                int[] fun = new int[n];

                for (int i = 0; i < n; i++) {
                    fee[i] = sc.nextInt();
                    fun[i] = sc.nextInt();
                }

                int[][] memoFun = new int[n + 1][budget + 1];

                //initialize 0 budget column  and 0 parties row
                for (int j = 0; j <= budget; j++) {
                    memoFun[0][j] = 0;
                }
                for (int i = 0; i <= n; i++) {
                    memoFun[i][0] = 0;
                }

                //now create the dp array
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= budget; j++) {
                        if (j - fee[i - 1] >= 0 && memoFun[i - 1][j - fee[i - 1]] + fun[i - 1] >= memoFun[i - 1][j]) {
                            //include i
                            memoFun[i][j] = memoFun[i - 1][j - fee[i - 1]] + fun[i - 1];
                        } else {
                            //exclude i
                            memoFun[i][j] = memoFun[i - 1][j];
                        }
                    }//inner for
                }//outer for

                /*Now to find the cost, we do have the maximum fun value. So at the nth row, that is including all the parties,
                 * we will check the memo[][] from j=0 to budget. If any of the j<budget have the same value as memo[n][budget],
                 * we take that j value of budget as the course. That means we get the same fun at j<budget, as at j=budget.
                 * This is to optimize the cost. Of course we break the loop then*/

                int cost=0;
                for(int j=0;j<=budget;j++){
                    if(memoFun[n][j]==memoFun[n][budget]){
                        cost=j;
                        break;
                    }
                }
                System.out.println(cost+ " " + memoFun[n][budget]);

            }//while
        }
        catch(Exception e)
        {
            return;
        }

    }//main
}//Party
