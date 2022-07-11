package DP.CodingNinja;

import java.util.ArrayList;
import java.util.Arrays;

public class Boredom {
    //https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73052/problem/554

    public int solve(int n,int A[])
    {
        //1<=N<=10^5//size
        //1<=A[i]<=1000//elements

        //Given: The element to be picked is also removed from the list.

    //Create a frequency array, so that freq[i] contains the frequency of element i.
        int[] freq=new int[1001];//so that index=1000 is present. According to the question, 1<=A[i]<=1000
        for(int i=0;i<n;i++){
            //update frequency at the A[i]'th index. Let index 0 stay 0
            int elem=A[i];
            freq[elem]+=1;
        }//for

        //Sizes have to be 1001 and not n, as A[i] can go upto 1000
        int[] take=new int[1001];
        int[] skip=new int[1001];

        //Base cases.
        take[0]=0;
        skip[0]=0;
        take[1]=1*freq[1];//i=1
        skip[1]=0;

        /*Either take i, in which case you will skip i-1 and add value of i to it,
        or skip i, and take the max of taking and skipping i-1*/
        for(int i=2;i<=1000;i++){
            take[i]=skip[i-1]+(i*freq[i]);
            skip[i]=Math.max(take[i-1], skip[i-1]);//Since you can either take or skip i-1, so find their max
        }//for

        return  Math.max(take[1000],skip[1000]);
    }//solve

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,1,3,2,2,2,2,3};
        int res=new  Boredom().solve(arr.length, arr);
        System.out.println(res);
    }//main
}//Boredom
