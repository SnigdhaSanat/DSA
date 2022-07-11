package DP.CodingNinja;

public class LootHouses {
    public  int maxMoneyLooted(int[] houses) {
        /*Prob: https://www.codingninjas.com/codestudio/problems/loot-houses_630510
        Logic: What is the value of fn(n)? Note that consecutive houses are not allowed.
        * So either (n-1)th house will be looted, and nth will be left out, in which case answer will be fn(n-1).
        * Else (n)th house will be looted, and n-1 will be left out, in which case ans will be fn(n-2)+houses[n].
        * Find the max of the 2
       -----------------------------------------------------------------------------------------------------------------------------
        IMP: Some problems require you to solve only a constant number of sub problems, say i-1 and i-2, or say
        (i-1,j), (i,j-1), and (i-1,j-1) for (i,j). Other problems require you to solve for all j<i.
        How do you decide that?

        Check how n can be arrived at. If the question required min number of houses to loot
        covering nth house, or say some sort of max, where ANY subsequence might be an answer, then checking for all j<i is required.
        That is because i can be arrived from ANY j<i, with probably some eligibility criterion.

        But here, i can be arrived either from i-2, or from i-1 and leaving i. Jumping from ANY j<i does NOT make sense, because
        we want to loot MAX number of houses(max total loot amt). So here only a constant number of j<i has to be checked.
        ------------------------------------------------------------------------------------------------------------------------------*/
        int len=houses.length;
        int[] memo=new int[len];

        if(len==0){
            return 0;
        }
        else if(len==1){
            return houses[0];
        }
        else if(len==2){
            return Math.max(houses[0],houses[1]);
        }

        memo[0]=houses[0];
        memo[1]=Math.max(houses[0],houses[1]);

        for(int i=2;i<len;i++){
            memo[i]=Math.max(memo[i-1],memo[i-2]+houses[i]);
        }//for

        return memo[len-1];
    }//maxMoneyLooted

    public static void main(String[] args) {
        int [] arr=new int[]{5, 5, 10, 100, 10, 5};
        int result=new LootHouses().maxMoneyLooted(arr);
        System.out.println(result);
    }//main
}//LootHouses
