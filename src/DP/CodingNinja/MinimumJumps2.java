package DP.CodingNinja;

public class MinimumJumps2 {
    //Coding Ninja: https://www.codingninjas.com/codestudio/problems/minimum-jumps_1062693?leftPanelTab=0

    /*Iterative or tabulation method, which is bottom up*/

    /*f(n)=min{f(0)...f(n-1)}+1, provided they are eligible. It is eligible if f(j)>= the gap,
     * which is (i-j).
     * Eg: to reach index n=5, find the minimum of all eligible values from index 0 to 4, and then add 1 to it.
     * It means we find the element which takes minimum jumps to reach that element, and then add 1 to it,
     * provided it is eligible, meaning 5 can be reached from it*/

    public static int minimumJumps2(int[] arr, int N) {
        int[] memo=new int[N];
        for(int i=0;i<N;i++){
            memo[i]=-1;
        }
        memo[0]=0;

        for(int i=1;i<N;i++){
            int min=Integer.MAX_VALUE;
            for(int j=0;j<i;j++){
                if(arr[j]>=(i-j) && memo[j]<min){
                    //eligible && less than current min
                    min=memo[j];
                }//if
            }//inner for

            if(min==Integer.MAX_VALUE){
                memo[i]=min;//so that it does not wrap over to a negative number
            }
            else{
                memo[i]=min+1;
            }
        }//outer for

        return memo[N-1]>=Integer.MAX_VALUE?-1:memo[N-1];
    }//minimumJumps

    public static void main(String[] args) {
        int[] arr={1,1,1,1};
        int len=arr.length;
        int res=new MinimumJumps2().minimumJumps2(arr,len);
        System.out.println(res);
    }
}//MinimumJumps2
