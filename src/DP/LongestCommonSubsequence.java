package DP;

import java.util.ArrayList;
import java.util.HashMap;

public class LongestCommonSubsequence {
/*Had it been a continuous sequence, we could have used KMP-LPS:
     https://www.geeksforgeeks.org/check-string-substring-another/,
     https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/

  as was used to solve the problem: https://www.interviewbit.com/problems/implement-strstr/

 The current  problem is to solve non-contiguous sequence, which is why DP kicks in*/

    public int solve(String A, String B) {
/*Create a 2D array of A:abbcdgf and B:bbadcgf. Add a layer of 'cushion',
so that 1st row and columns can be filled. So strings become: A:"0abbcdgf" and B:"0bbadcgf"

    0|a|b|b|c|d|g|f|
  0|0|0|0|0|0|0|0|0|
* b|0| | | | | | | |
* b|0| | | | | | | |
* a|0| | | | | | | |
* d|0| | | | | | | |
* c|0| | | | | | | |
* g|0| | | | | | | |
* f|0| | | | | | | |
*
Now keep on filling the values, row wise and then col wise. For any i and j,
if A[i] and B[j] are NOT same, it means they do not contribute to the result. So take the max from res[i-1,j] and
res[i,j-1]. But if they are same, then you can't do this, as that will lead to a suboptimal answer. A[i] and B[j]
has to be contributors, So add 1 to res[i-1,j-1]. Note that i and j are the ENDINGS NOT the startings in the ITERATIVE solution.

In the RECURSIVE solution, we call for fn(i,j), (i+1), and (j+1). In the recursive case, i and j are the STARTINGS.
*/

        int Alen=A.length();
        int BLen=B.length();

        if(Alen==0 || BLen==0){
            return 0;
        }

        int res[][]=new int[Alen+1][BLen+1];//+1s are for the "cushions"

        /*Automatically all are initialized to 0. 0th row and col are filled.
        * So iterate for i=1 to ALen, and j=1 to BLen */

        for(int i=1;i<=Alen;i++){
            for(int j=1;j<=BLen;j++){
                if(A.charAt(i-1)==B.charAt(j-1)){
                    //comparing indices: A[i-1] and B[i-1]. -1s are to remove the cushions,
                    //so that the actual chars are compared
                    res[i][j]=res[i-1][j-1]+1;
                }

                else{
                    res[i][j]=Math.max(res[i-1][j],res[i][j-1]);
                }
            }//inner for
        }//outer for

        /*Be mindful of the indices used during iteration, during comparison and during return.
        * "Cushion" changes those a bit*/
        return res[Alen][BLen];
    }//solve

    public static void main(String[] args) {
        String A="abbcdgf";
        String B="bbadcgf";
        int result=new LongestCommonSubsequence().solve(A,B);
        System.out.println(result);
    }
}//LongestCommonSubsequence
