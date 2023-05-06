package DP.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73052/problem/1442

public class MinimumCount {

public static int minCount(int n) {
    /*Base case: For 0 and 1 it is 1. For 2 onwards, check for every perfect square less than it. Eg: For 10,
    * check for 1 ,4 and 9. Then find memo[10-1]=memo[9] ,memo[10-4]=memo[6] and memo[10-9]=memo[1]. It means 10 can be
    * expressed as either memo[1]+memo[9] or memo[4]+memo[6] or memo[9]+memo[1]. For the perfect squares themselves, memo[]=1.
    * Find the min of all and assign it to memo[n].
    *
    * If n itself is a perfect square, assign memo[n]=1.*/
    int[] memo=new int[n+1];
    memo[0]=1;
    memo[1]=1;

    for(int i=2;i<=n;i++){
        //inner iteration. Iterate for each of the square roots
        int j=1;
        int min=Integer.MAX_VALUE;
        while(j*j<=i){
            if(j*j==i){
                //if i is a perfect square
                memo[i]=1;
            }
            else{
                int square=j*j;
                int candidate=memo[i-square];
                if(candidate<min){
                    min=candidate;
                }//min
            }

            j+=1;
        }//while

        if(memo[i]==0){
            /*if i was a perfect square,it was already assigned in the condition if(j*j==i). So memo[i]!=0
            Do NOT assign in this case.

            So this if is the case where memo[i] is still unassigned*/
            memo[i]=1+min;//1 is to account for memo[perfect square]
        }
    }//outer for

    return memo[n];
}//minCount

public static void main(String[] args) {
    int n=6;
    int res=MinimumCount.minCount(n);
    System.out.println(res);
}//main
}//MinimumCount
