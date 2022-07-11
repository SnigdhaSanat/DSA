package DP.CodingNinja;

import java.text.DecimalFormat;
import java.util.Scanner;

// https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73051/problem/1709
public class HasanAndTrip {

/*Not that this is a sequence problem. So any point can be arrived at from any point behind it. So for any
* i, all j<i have to be considered.
* Start from 0, as that is a question requirement. So i=1 will consider max from j=0 to j=0, meaning i=0 to i=1 step is considered,
* and is the answer for i=1. i=2 will consider from j=1(which is in turn step from i=0 to 1 ) to j=0. So every j is actually linked to 0th step.
* We arrive at the (n-1)th, and return memo[n-1], since the question requires us to finish at n-1(and start at 0).
*
* So for any i and j, we take the candidate as memo[j]+ health[i]-distance(i to j), that is the jump from i to j.We take the max
* over all js, and assign that to memo[i].
*
* Note: If the question did NOT require us to go till n, we would have taken the max of all the memo[] and returned that max.
*
* And if the question did NOT require us to start from 0, the base condition would have been different. In the max operation,
* we would have also included the candidate of starting from itself. That is, memo[0] will be max between happy[0] and skipping i=0(=0).
* memo[1] will be max between jumping from 0, AND STARTING FROM 1 itself, memo[2] would have included the case of starting from 2, apart from its
* sub problems of js, all of which include the case of starting from 0 and starting from j, and so on.
*  */
    public static void main(String[] args) {
        DecimalFormat dec = new DecimalFormat("#0.000000");
        //Scanner sc= new Scanner(System.in);

        //int n=sc.nextInt();
        int n=3;

        int[] xArr=new int[n];
        int[] yArr=new int[n];
        int[] happyArr=new int[n];
//
//        for(int i=0;i<n;i++){
//            xArr[i]=sc.nextInt();
//            yArr[i]=sc.nextInt();
//            happyArr[i]=sc.nextInt();
//        }

        xArr=new int[]{0,3,6};
        yArr=new int[]{0,1,0};
        happyArr=new int[]{1,1,9};

        double memo[]=new double[n];
        memo[0]=Double.parseDouble(dec.format(happyArr[0]));

        for(int i=1;i<n;i++){
            double max=Integer.MIN_VALUE;
            for(int j=i-1;j>=0;j--){
                double xDist=Math.abs(xArr[j]-xArr[i]);
                double yDist=Math.abs(yArr[j]-yArr[i]);
                //distance from i to j
                double distance=Math.sqrt(xDist*xDist+yDist*yDist);
                double candidate=memo[j]+happyArr[i]-distance;
                if(candidate>max){
                    max=candidate;
                    memo[i]=candidate;
                }
            }//inner for
        }//outer for

        //System.out.println(String.format("%.6f", memo[n-1]));
        System.out.println(dec.format(memo[n-1]));
    }//main
}//HasanAndTrip
