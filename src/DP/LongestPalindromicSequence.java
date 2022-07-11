package DP;

public class LongestPalindromicSequence {
public int solve(String A) {
    /*4 things we need for DP:
    1. Recursion logic
    2. Cushion required? No
    3. Sequence of recursion
    4. Return*/

    int len=A.length();
    int[][] res=new int[len][len]; //'0' Cushion not required

    /*Recursion sequence: Start with (0,0), (1,1)....(len,len). Then (0,1),(1,2)..(len-2, len-1)...then (0,2), (1,3)...(len-3, len-1) and so on. */
    //Recursion logic
    int diff=0;
    for(diff=0;diff<len;diff++){
        for(int i=0;i<len;i++){
            int j=i+diff;
            if(j<len)
            {
                //System.out.println("i:"+i+" j:"+j);
                if(i==j){
                    //across the diagonal
                    res[i][j]=1;
                }
                else{
                    if(A.charAt(i)==A.charAt(j)){
                        res[i][j]=res[i+1][j-1]+2;
                    }
                    else{
                        res[i][j]=Math.max(res[i+1][j], res[i][j-1]);
                    }
                }//outer else
            }//if j<len
        }//inner for
    }//outer for
    //return
    return res[0][len-1];
}//solve

    public static void main(String[] args) {
    String s="bebeeed";
        int result=new LongestPalindromicSequence().solve(s);
        System.out.println(result);
    }//main
}//LongestPalindromicSequence
