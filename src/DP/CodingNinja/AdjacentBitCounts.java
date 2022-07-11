package DP.CodingNinja;

import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73051/problem/1712
public class AdjacentBitCounts {
/*Given values are n and k. To convert it into sub-problems,  we will have to call some functions where n-1 and k-1 is used. To be able to use a
sub-problem, let us say prepending a 0 or a 1 to a string (n-1), we will have to know whether we are prepending 0 or 1, AND whether the (n-1) string
starts with 0 or 1. So function will be fn(n,k,starts with0Or1). Note that the fn() gives us the number of strings of len n and AdjBC=k.

If n string starts with 0, n-1 string will need to have k, in both startsWith cases. If n string starts with 1, n-1 string will have
to have only k-1, if n-1 starts with 1. Because only then both 1s will add up.  But if it starts with 0, n's 1 will not be able to contribute,
so n-1 will have to have k.
fn(n,k)=fn(n,k,0)+fn(n,k,1)={fn(n-1,k,0)+fn(n-1,k,1)}+{fn(n-1,k,0)+fn(n-1,k-1,1)}

Base cases:
n=0, for all k, and both startsWith values, res will be 0.
(n=1,k=0, startsWith)=1, both for startsWith=0 or 1
(n=1,k=1, startsWith)=0. No matter what it starts with, for n=1,k=1, adjBC will be 0. So (1,1,0)=(1,1,1)=0. */

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int modulo=1000000007;

        int count=sc.nextInt();

        int[] dataSetArray=new int[count];
        int[] nArray=new int[count];
        int[] kArray=new int[count];

        for(int i=0;i<count;i++){
            dataSetArray[i]=sc.nextInt();
            nArray[i]=sc.nextInt();
            kArray[i]=sc.nextInt();
        }//for

        //computing
        for(int index=0;index<count;index++){
            //use a 3d memo, where rows(i) will have n, columns(j) will have k. The third dimension will contain the
            //startWith values of either 0 or  1.

            int n=nArray[index];
            int k=kArray[index];

            int[][][] memo=new int[n+1][k+1][2];

            //initializing Base cases
            //assigning n=0 values, each for both startsWith values
            //irrespective of requirement k, n=0 bit size will only give 0
            for(int j=0;j<=k;j++){
                memo[0][j][0]=0;
                memo[0][j][1]=0;
            }
            //assigning n=1 values, each for both startsWith values
            for(int j=0;j<=k;j++){
                if(j==0){
                    //if expectation is 0
                    memo[1][j][0]=1;
                    memo[1][j][1]=1;
                }
                else{
                    //if expectation is >0
                    memo[1][j][0]=0;
                    memo[1][j][1]=0;
                }
            }
//----------------------------------------------------------------------
            //fn(n,k,0)={fn(n-1,k,0)+fn(n-1,k,1)}
            //fn(n,k,1)={fn(n-1,k,0)+fn(n-1,k-1,1)}

            for(int i=2;i<=n;i++){
                    for(int j=0;j<=k;j++){
                    //For startsWith=0
                        memo[i][j][0]=i>0?(memo[i-1][j][0]+memo[i-1][j][1])%modulo:0;

                    //For startsWith=1
                        if(i>0 && j>0){
                            memo[i][j][1]=(memo[i-1][j][0]+memo[i-1][j-1][1])%modulo;
                        }
                        else if(i>0 && j<=0){
                            //base case of i==0
                            memo[i][j][1]=memo[i-1][j][0];
                        }
                        else{
                            //base case of j==0
                            memo[i][j][1]=0;
                        }

                }//inner for
            }//outer for

            int res=(memo[n][k][0]+memo[n][k][1])%modulo;
            System.out.println(index+1+" "+res);

        }//for loop for the test cases

    }//main

}//AdjacentBitCounts
