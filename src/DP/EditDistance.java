package DP;

import java.util.HashSet;

public class EditDistance {
/*4 things we need for DP:
1. Recursion logic
2. Cushion required?: Yes
3. Sequence of recursion
4. Return*/
public int minDistance(String A, String B) {

/* A: bbbaabaa B:aababbabb
    0|b|b|b|a|a|b|a|a|
  0|0|1|2|3|4|5|6|7|8|
* a|1| | | | | | | | |
* a|2| | | | | | | | |
* b|3| | | | | | | | |
* a|4| | | | | | | | |
* b|5| | | | | | | | |
* b|6| | | | | | | | |
* a|7| | | | | | | | |
* b|8| | | | | | | | |
* b|9| | | | | | | | |

 Cushions are so that A can be converted into 0 and 0 can be converted into B.

 Recursion logic:Note that res[i][j] means converting A(0,j) to B(0,i), meaning ENDING in i and j.

 Let us start with the first condition. If A[i]=B[j], res[i][j] =res[i-1][j-1], since we don't have to do anything to
 convert A[i] into B[j].What if If A[i]!=B[j]?  Eg: convert "axj" to "byp". We can convert either
 s1 to s2 or vice versa. Both will yield the same result.
 Take the min of the following. Each has 1 as op count:
  1. REPLACE "j" by "p". So effectively then convert "ax" to "by". res[i][j] =(res[i-1][j-1])+1, 1 is for replacing.
  2. Convert "axj" to "by" and ADD "p". res[i][j] =(res[i][j-1])+1, 1 is for adding.
  3. DELETE "j" and then convert "ax" to "byp". res[i][j] =(res[i-1][j])+1, 1 is for deleting.

  Ref videos:
  Tushar Roy: https://www.youtube.com/watch?v=We3YDTzNXEk&t=482s
  GKCS: https://www.youtube.com/watch?v=XJ6e4BQYJ24
  Coding Ninja: https://classroom.codingninjas.com/app/classroom/me/567/content/9696/offering/73070/problem/858
   */

    int ALen=A.length();
    int BLen=B.length();
    int[][] res=new int [BLen+1][ALen+1];

    //initialize first row and first col
    for(int j=0;j<ALen+1;j++){
        res[0][j]=j;
    }
    for(int i=0;i<BLen+1;i++){
        res[i][0]=i;
    }

    for(int i=1;i<=BLen;i++){
        for(int j=1;j<=ALen;j++){
            //Note the indices. -1 because they are cushioned
            if(B.charAt(i-1)==A.charAt(j-1)){
                res[i][j]=res[i-1][j-1];
            }
            else{
                res[i][j]=Math.min(Math.min(res[i-1][j-1],res[i-1][j]),res[i][j-1])+1;
            }
        }//inner loop
    }//outer loop

    return res[BLen][ALen];
}//minDistance

    public static void main(String[] args) {
        String A="bbbaabaa";
        String B="aababbabb";
        int result=new EditDistance().minDistance(A,B);
        System.out.println(result);
    }
}//EditDistance