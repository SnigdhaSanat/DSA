package DP.CodingNinja;

import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73051/problem/1708
public class VanyaAndGCD {
    /*Original idea:
    For every i, find 2 things, #subsequences ending at i with GCD=1, and  #subsequences ending at i with GCD NOT 1.
    * Base case for i=0 will be: if arr[0] is 1, GCD1 will be 1 and GCDNot1=0, else GCDNot1=1 and GCD1=0. Now for every i, iterate over j<i, IF
    a[j]<a[i]. If GCD(a[i],a[j])=1, then contribution to GDC1[i] from j will be GCD1[j]+GCDNot1[j], and GCDNot1[i]=0, as all of the subsequences
    formed by appending arr[i] to a sequence ending at arr[j] will have GCD=1.

    If GCD(a[i],a[j]) NOT=1, then contribution to GDC1[i] from j will be GCD1[j]. and GCDNot1[i]=GCDNot1[j]. To find GCD1 and GCDNot1 for i,
    sum over all the js. Finally, sum over all is for GCD1[i]/.

    Correct idea:
    The flaw with the above approach is that,  while it works for the case of GCD(a[i],a[j])=1, for GCD(a[i],a[j])NOT=1, it fails. Why?
    Take an example where a[j] =60 and a[i] =68. So their GCD is 4. Take all the subsequences ending at j, where GCD is NOT 1.
    Do all of them contribute to GCDNot1[i]? What about the GCDs 3, 5,6, 10? They are the factors of 60, but not 68. They go into the GCD1 for 68,
     rather than GCDNot1.

     IMP: Watch the hint video.
     So instead of taking a blanket bucket for GCDNot1, take it for all factors from 2 to 100. As A[i]<=100, GCD can go only till 100.*/
    public static void main(String[] args) {
        int modulo=1000000007;

        Scanner sc=new Scanner(System.in);

        //int n=sc.nextInt();

//        int[] arr=new int[n];
//        for(int i=0;i<n;i++){
//            arr[i]=sc.nextInt();
//        }//for

//        int[] arr=new int[]{90,64,31,17,27,65,57,33,41,34,75,19,7,2,76,44,74,29,22,81,33,46,72,37,54,74,16,2,69,8,70,82,87,80,11,82,
//                19,8,44,28,34,10,40,29,63,80,47,28,100,26,11,71,43,70,45,15,23,37,42,7,78,10,44,58,60,96,80,62,89,37,71,3,51,78,97,49,
//                5,50,93,76,100,40,92,78,52,44,11,36,2,40,82,75,55,55,48,79,66,3,66,89};

        int[] arr=new int[]{1,2,3};
        int n=arr.length;

        //memo[i][j] is the number of subsequences with GCD value i=GCD, ending at arr[j]
        int[][] memo=new int[101][n];

        //Base case is the sequence {arr[0]}. This has GCD=arr[0]
        memo[arr[0]][0]=1;//Means with a sequence of only arr[0], there is only 1 subseq ending at j=arr[0], and with i=GCD=itself. Rest all is 0 by default



        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){
                //i and j are the wrt the arr, not the memo

                if(arr[j]<arr[i]){
                    if(gcd(arr[i],arr[j])==1){
                    //if the GCD is 1, in this case memo[1][i] will be sum of memo[gcdVal][j], where gcd is from 1 to 100.
                    //This is because any subsequence formed by appending arr[i] to all the sequences ending at arr[j] will have gcd=1
                        int sum=0;
                        for(int k=0;k<=100;k++){
                            sum=(sum+memo[k][j]%modulo)%modulo;
                        }//for
                        memo[1][i]=(memo[1][i]+sum)%modulo;
                        //all the rest of the GCD rows will increment by 0;
                        //memo[2 to 100][i]= stays the same, do nothing
                    }//if
                    else{
                        //if gcd(arr[i],arr[j]) NOT =1
                        for(int k=0;k<=100;k++){
                            int gcdKAtJ=memo[k][j]%modulo;//#sub sequences with gcd=k, ending at j
                            /*Now all of those gcdAtJ number of subsequences(of course having GCD=k), when combined with arr[i],
                            * will add same gcdAtJ number of subsequences, but at k= GCD=gcd(k,arr[i])*/
                            int gcdVal=gcd(k,arr[i])%modulo;
                            memo[gcdVal][i]=(memo[gcdVal][i]+gcdKAtJ)%modulo;
                        }//for
                    }//else
                }//if element at j is smaller than the current element
            }//inner for

            //Incrementing by 1 for the subseq starting and ending at i, with GCD arr[i], as it could not have been covered by j
            memo[arr[i]][i]= (memo[arr[i]][i]+1)%modulo;

        }//outer for

        int sum=0;
        for(int j=0;j<n;j++){
            sum=(sum+memo[1][j])%modulo;//GCD=1
        }

        System.out.println(sum);

    }//main

    static int gcd(int a, int b){
        int smaller=a<=b?a:b;
        int larger=(a+b)-smaller;

        if(smaller==0){
            return 0;
        }
        int divisor=smaller;
        int dividend=larger;

        //int quotient=dividend/divisor;
        int remainder=dividend%divisor;

        while(remainder!=0){
            dividend=divisor;
            divisor=remainder;

            //quotient=dividend/divisor;
            remainder=dividend%divisor;
        }//while

        return divisor;
    }//gcd
}//Main
