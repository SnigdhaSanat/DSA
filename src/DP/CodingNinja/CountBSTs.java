package DP.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73052/problem/2198
public class CountBSTs {
    public static int countTrees(int numKeys) {
    int mod=1000000007;
    long[] memo= new long[numKeys+1];

    //base cases
        if(numKeys==0 || numKeys==1||numKeys==2){
            return numKeys;
        }

    memo[0]=0;//cushion
    memo[1]=1;
    memo[2]=2;
    /*Count=number of trees with n as root + number of trees with n as leaf+
    * number of trees with n in middle. As root and as leaf count will be same as fn(n-1). In each of those trees of n-1,
    * we can attach n as the root, with the whole subtree to its left. For leaf, we attach n as the right most node to each of
    * the n-1 subtrees. So they each count as memo(i-1).
    *
    * Now as n in the middle, since it is the biggest, and has to be in the middle, so all elements smaller than it will be its parent,
    * meaning it will be the right child of that subtree. For the rest of the elements after it, they will go into its left subtree.
    * IMP: ALL the elements of the parent subtree will be smaller than ALL the elements of its child subtree, which is why they are aligned such.
    *
    * ST1
    *  \
    *   n
    *   /
      ST2
    * How many arrangements of this one is possible? ST1 can have 1 to n-2 elements, and ST2 can have next n-2 to 1 elements correspondingly
    * So it will be fn(1)fn(n-2)+fn(2)fn(n-3)+.....+fn(n-1)fn(1).
    *
    * Time complexity: n*n, more precisely: n*(n-2)
    */

    for(int i=3;i<=numKeys;i++){
        long middle=0;

        //For f(5), NOT 4: do f(1)*f(3)+f(2)*f(2)+f(3)*f(1)
        //for f(6): do f(1)*f(4)+f(2)*f(3)+f(3)*f(2)+f(4)*f(1)

        for(int j=1;j<=i-2;j++){
            middle=(middle+(memo[j]*memo[i-1-j])%mod)%mod;
        }//inner for

        memo[i]=((memo[i-1]+memo[i-1])%mod+middle)%mod;//As root+ As leaf+ As middle
    }//outer for

    return (int)memo[numKeys];
    }//countTrees

    public static void main(String[] args) {
        int n=1;
        int res=CountBSTs.countTrees(n);
        System.out.println(res);
    }//main
}//CountBSTs
