package DP.CodingNinja;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9696/offering/73071/problem/1713
public class ValikaVadhu {
    public static void main(String[] args) {

/*Approach: Get the longest common subsequence, AND STORE the subsequence. This takes n*n time. Then among the common subsequence,
take the top k chars with max ASCII value. This takes klogk time. This approach fails, because in taking the LENGTH, we didn't
consider the constituent letters. That means we wrongly focused on taking the longest subseq, when we only required one of len=k. In doing so,
we might have left out the higher ASCII value characters, which might have been outside the longest subseq.

Since here the ASCII value of the letters matter, so we will have to consider them too.
This means apart from the len, we also have to consider the ascii value. But note that we need to find the max ascii value, not len.
Len is already given. This means we don't have to evaluate len as an output. But since len=k is given as in input, it goes into the dimensions
of the DP memo. So we will take a 3D DP of s1.len*s2.len*k.
*
* if s1[i] !=s2[j], memo[i][j][k]=max(memo[i-1][j][k], memo[i][j-1][k]). But if they are equal, now since we are not optimizing len, rather we
need a fixed len, but need to optimize on ASCII value, which might be by leaving out the current two equal characters,
 we also take the i,j-1 and i-1,j values. That is, if s1[i] ==s2[j], memo[i][j][k]=max(memo[i-1][j][k], memo[i][j-1][k],memo[i-1][j-1][k-1]) */


        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        for(int index=0;index<t;index++){
            String s1=sc.next();
            String s2=sc.next();
            int k=sc.nextInt();

            int lcs=longestLCSWithKLen(s1, s2,k);
            System.out.println(lcs);
        }//for loop for test cases
    }//main

    static int longestLCSWithKLen(String A, String B,int KLen){
        int ALen=A.length();
        int BLen=B.length();

        if(ALen==0 || BLen==0){ //KLen>=1 according to question
            return 0;
        }

        //Take two arrays, one for holding the ascii values, other for holding the actual strings
        int memoValue[][][]=new int[ALen+1][BLen+1][KLen+1];//+1s are for the "cushions"
        String memoString[][][]=new String[ALen+1][BLen+1][KLen+1];//+1s are for the "cushions"

        //Initialize k=0 for all i and js
        for(int i=0;i<=ALen;i++){
            for(int j=0;j<=BLen;j++){
                memoValue[i][j][0]=0;
                memoString[i][j][0]="";
            }//inner loop
        }//outer loop

        /*Initialize 0th row and 0th col for every k*/
        for(int k=1;k<=KLen;k++ ){
            for(int j=0;j<=BLen;j++){
                memoValue[0][j][k]=0;
                memoString[0][j][k]="";
            }
            for(int i=0;i<=ALen;i++){
                memoValue[i][0][k]=0;
                memoString[i][0][k]="";
            }
        }//for loop for KLen

         /* Now iterate for i=1 to ALen, and j=1 to BLen */
        for(int k=1;k<=KLen;k++) {
            for(int i=1;i<=ALen;i++){
                for(int j=1;j<=BLen;j++){
                    //comparing indices: A[i-1] and B[i-1]. -1s are to remove the cushions,
                    //so that the actual chars are compared
                    if(A.charAt(i - 1) == B.charAt(j - 1)) {
                        int candidate1=memoValue[i-1][j][k];
                        int candidate2=memoValue[i][j-1][k];
                        int candidate3=memoValue[i-1][j-1][k-1]+(int)(A.charAt(i-1));


                        if(candidate1>=candidate2 && candidate1>=candidate3){
                            //if candidate1 is the largest
                            memoValue[i][j][k]=candidate1;
                            memoString[i][j][k]=memoString[i-1][j][k];
                        }
                        else if(candidate2>=candidate1 && candidate2>=candidate3){
                            //if candidate2 is the largest
                            memoValue[i][j][k]=candidate2;
                            memoString[i][j][k]=memoString[i][j-1][k];;
                        }
                        else{
                            //if candidate3 is the largest
                            memoValue[i][j][k]=candidate3;
                            memoString[i][j][k]=memoString[i-1][j-1][k-1]+A.charAt(i-1);
                        }
                    } //if
                    else {
                        //s1[i] !=s2[j], memoValue[i][j][k]=max(memoValue[i-1][j][k], memoValue[i][j-1][k])
                        int candidate1=memoValue[i-1][j][k];
                        int candidate2=memoValue[i][j-1][k];
                        if(candidate1>=candidate2){
                            memoValue[i][j][k]=candidate1;
                            memoString[i][j][k]=memoString[i-1][j][k];
                        }
                        else{
                            memoValue[i][j][k]=candidate2;
                            memoString[i][j][k]=memoString[i][j-1][k];;
                        }
                    }//else
            }// for loop for j
          }// for loop for i
        }//for loop for k

        /*Be mindful of the indices used during iteration, during comparison and during return.
         * "Cushion" changes those a bit*/
        return memoString[ALen][BLen][KLen].length()>=KLen? memoValue[ALen][BLen][KLen]:0;
    }//longestLCSWithKLen
}//ValikaVadhu
