package DP.CodingNinja;

public class MinimumJumps {
    //Coding Ninja: https://www.codingninjas.com/codestudio/problems/minimum-jumps_1062693?leftPanelTab=0

    /*Recursion-Memoization method, which is top down*/
    /*f(n)=min{f(0)...f(n-1)}+1, provided they are eligible. It is eligible if f(j)>= the gap,
    * which is (end-i).
    * Eg: to reach index n=5, find the minimum of all eligible values from index 0 to 4, and then add 1 to it.
    * It means we find the element which takes minimum jumps to reach that element, and then add 1 to it,
    * provided it is eligible, meaning 5 can be reached from it*/
    class Util{
        int[] memo;
    }
    public  int minimumJumps(int[] arr, int N) {
        Util util=new Util();
        util.memo=new int[N];

        for(int i=0;i<N;i++){
            util.memo[i]=-1;
        }

        int result=recurse(arr,  N-1,util);
        int res= result>=Integer.MAX_VALUE?-1:result;
        return res;
    }//minimumJumps

    int recurse(int[] arr, int end,Util util){
        if(end==0){
            return 0;
        }
        int min=Integer.MAX_VALUE;
        for(int i=end-1;i>=0;i--){
            if(arr[i]>=(end-i)){
                int res=-1;
                if(util.memo[i]!=-1){
                    res=util.memo[i];
                }
                else{
                    //if(util.memo[i]==-1)
                    res=recurse(arr,i,util);
                }
                if(res<min){
                    min=res;
                }
            }//outer if
        }//lateral for
        //end of lateral iteration. So return
        //if(min!=Integer.MAX_VALUE){
            int result=min+1;
        if(result<0) result=Integer.MAX_VALUE;//else it wraps over to negative value
            util.memo[end]=result;
            return result;
    }//recurse

    public static void main(String[] args) {
        int[] arr={1, 1, 1, 1};
        int len=arr.length;
        int res=new MinimumJumps().minimumJumps(arr,len);
        System.out.println(res);
    }
}//MinimumJumps
