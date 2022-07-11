package DP;

public class RepeatingSubsequence {
    public int anytwo(String A) {
    /*Looks like "repeating" here means repeating ONCE. That reduces it into a longest common subsequence problem,
    * with added condition of matching as i!=j, apart from A[i]=B[j]*/
        /*Create a 2D array of A:aabebcdd and A:aabebcdd. Add a layer of 'cushion',
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
if A[i] and A[j] are NOT same, it means they do not contribute to the result. So take the max from res[i-1,j] ans
res[i,j-1]. But if they are same AND i!=j, then you can't do this, as that will lead to a suboptimal answer. A[i] and A[j]
has to be contributors, So add 1 to res[i-1,j-1]*/

        int Alen=A.length();
        int res[][]=new int[Alen+1][Alen+1];//+1s are for the "cushions"

        /*Automatically all are initialized to 0. 0th row and col are filled.
         * So iterate for i=1 to ALen, and j=1 to BLen */

        for(int i=1;i<=Alen;i++){
            for(int j=1;j<=Alen;j++){
                if(A.charAt(i-1)==A.charAt(j-1) && i!=j){
                    //comparing indices: A[i-1] and B[i-1]. -1s are to remove the cushions,
                    //so that the actual chars are compared
                    res[i][j]=res[i-1][j-1]+1;
                }

                else{
                    //either not equal, or equal but i=j
                    //res[i][j]=Math.max(Math.max(res[i-1][j],res[i][j-1]),res[i-1][j-1]);
                    res[i][j]=Math.max(res[i-1][j],res[i][j-1]);
                }
            }//inner for
        }//outer for

        /*Be mindful of the indices used during iteration, during comparison and during return.
         * "Cushion" changes those a bit*/
        return res[Alen][Alen]>=2?1:0;
    }//any two

    public static void main(String[] args) {
        String s="aabebcdd";
        int res=new RepeatingSubsequence().anytwo(s);
        System.out.println(res);
    }
}//RepeatingSubsequence
