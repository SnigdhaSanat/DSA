package DP.CodingNinja;

import java.util.HashMap;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73051/problem/1711
public class RoyAndCoinBoxes {

    public static void main(String[] args) {
   /*Steps:
   * 1. From the queries, create an arr where arr1[i] = coins at box i
   * 2. Convert it onto an array or a hashmap, where arr2[i] = number of boxes with #coins i
   * 3. Now create the "atleast" array, where fn(i)=fn(i+1)+arr2[i], that is, #boxes with ATLEAST i coins=
   * #boxes with ATLEAST (i+1) coins plus number of boxes with exactly i coins
   * 4. Using the "atleast" array, answer the queries*/

        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();

        /*Step 1. Naive way is for each l and r, we update the l to r indexes by 1. Complexity:O(m*n)
        Efficient way:
        * We use 2 arrays, start and end. start[i] contains the number of times ith box was the "L",
          and end[i] contains the number of times ith box was the "R". Complexity till here: O(m*2)=O(m)

          Now to find coins in box: 1, boxesToCoins[1] start[1](does not matter if it ends in 1)
           To find #coins in box:2, boxesToCoins[2]= boxesToCoins[1]+start[2]-end[1], and so on. Complexity of this step
           :O(n).
           So overall complexity is O(m+n), compared to O(m*n)*/

        int[] boxesToCoins=new int[n+1];//use 1 based index

        int[] start=new int[n+1];//use 1 based index
        int[] end=new int[n+1];//use 1 based index

        for(int i=0;i<m;i++){
            int l=sc.nextInt();
            int r=sc.nextInt();

            start[l]+=1;
            end[r]+=1;
        }//for

        //now fill in the boxesToCoins[]
        for(int i=1;i<=n;i++){
            if(i==1){
                boxesToCoins[1]=start[1];
            }
            else{
                boxesToCoins[i]=boxesToCoins[i-1]+start[i]-end[i-1];
            }
        }//for

        //Step 2: Convert it into a CoinsToBoxes array
        HashMap<Integer,Integer> coinsToBoxes= new  HashMap<Integer,Integer>();
        int maxCoinsInSingleBox=Integer.MIN_VALUE;
        for(int i=1; i<=n;i++){
            int coinsCount=boxesToCoins[i];
            if(coinsCount>maxCoinsInSingleBox){
                maxCoinsInSingleBox=coinsCount;
            }
            if(!coinsToBoxes.containsKey(coinsCount)){
                coinsToBoxes.put(coinsCount,1);
            }
            else{
                int boxes=coinsToBoxes.get(coinsCount);
                coinsToBoxes.put(coinsCount,boxes+1);
            }
        }//for

        //Step 3: Create the "atleast" array
        int[] atleast=new int[maxCoinsInSingleBox+1]; //1 based index
        for(int i=maxCoinsInSingleBox;i>=0;i--){

            int boxesWithICoins=coinsToBoxes.containsKey(i)?coinsToBoxes.get(i):0;

            if(i==maxCoinsInSingleBox){
                atleast[i]=boxesWithICoins;
            }
            else if(i==0){
                atleast[i]=n;// ALL boxes contain atleast 0 coins
            }
            else{
                atleast[i]=atleast[i+1]+boxesWithICoins;
            }
        }//for

        //Step 4: Now answer the queries
        int q=sc.nextInt();
        for(int i=0;i<q;i++ )
        {
            int query=sc.nextInt();
            if(query>maxCoinsInSingleBox){
                System.out.println(0);
            }
            else{
                System.out.println(atleast[query]);
                }
        }//for

    }//main


}//RoyAndCoinBoxes
