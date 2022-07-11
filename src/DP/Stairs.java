package DP;

public class Stairs {
    public int climbStairs(int A) {
/*The last jump to A will either be from A-1 or A-2. So fn(A)=fn(A-1)+fn(A-2).
* fn(1)=1 and fn(2)=2*/
        if(A==1||A==2){
            return A;
        }
        int[] mem=new int[A+1];//take 1 extra, just to save the offset overhead
        mem[0]=0;
        mem[1]=1;
        mem[2]=2;
        for(int i=3;i<=A;i++){
            mem[i]=mem[i-1]+mem[i-2];
        }//for
        return mem[A];
    }//climbStairs

    public static void main(String[] args) {
        int A=4;
        int res=new Stairs().climbStairs(A);
        System.out.println(res);
    }//main
}//Stairs
