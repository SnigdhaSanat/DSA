package DP.CodingNinja;

import DP.Stairs;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73040/problem/1541
public class StairCase {
    public static long staircase(int n) {
        /*The last jump to n will either be from n-1 or n-2 or n-3. So fn(A)=fn(n-1)+fn(n-2)+fn(n-3).
         * fn(0)=1, fn(1)=1 and fn(2)=2*/
        if(n==0){
            return 1;
        }
        if(n==1||n==2){
            return n;
        }
        long[] mem=new long[n+1];//take 1 extra, just to save the offset overhead
        mem[0]=1;
        mem[1]=1;
        mem[2]=2;
        for(int i=3;i<=n;i++){
            mem[i]=mem[i-1]+mem[i-2]+mem[i-3];
        }//for
        return mem[n];
    }//staircase

    public static void main(String[] args) {
        int n=4;
        long res= StairCase.staircase(n);
        System.out.println(res);
    }//main
}//StairCase
