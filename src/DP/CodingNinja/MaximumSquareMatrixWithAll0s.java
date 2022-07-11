package DP.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73052/problem/1436
public class MaximumSquareMatrixWithAll0s {
    /*Fix left and right, which will have n combinations each. So we will end up with total n*n combinations. In a fixed
     * left and right, we now need to find the grid with a top and bottom diff of >=R-L, with ALL 0s in between.
     *
     * How? Find the number of 1s in of each row from first to last row, within the fixed left and right boundaries. Find the max contiguous 0 sum.
     * If that max len is >=R-L and >max, update the max. Return max.
     *
     * This makes the complexity O(n*n*n), where last n comes from this algo. That is for each of the n*n combos of L and R, this
     * algo takes n time.
     *
     * Now how do you get the sum? When you iterate for left and right, add(when increasing right) or subtract(when increasing left) that COLUMN's
     * elements(1s) to the algo's candidates. */

    public static int findMaxSquareWithAllZeros(int[][] input){
        int rows=input.length;
        if(rows==0){
            return 0;
        }
        int cols=input[0].length;

        int[] utilArray=new int[rows];
        int finalMax=0;

        for(int left=0;left<cols;left++){
            for(int right=left;right<cols;right++){

                //update the utilArray:ADD
                for(int i=0;i<rows;i++){
                    utilArray[i]+=input[i][right];
                }//for

                //now find the max len of continuous 0s
                int maxLen=0;
                for(int i=0;i<rows;i++){
                    if(utilArray[i]==0){
                        maxLen+=1;
                        //maxLen has to match the (right-left+1) exactly
                        if(maxLen==(right-left+1) && maxLen>finalMax){
                            finalMax=maxLen;
                        }//if
                    }//if
                    else{
                        //else reset to 0
                        maxLen=0;
                    }
                }//for

            }//inner loop

            //Reinitialize the utilArray:SUBTRACT
            utilArray=new int[rows];

        }//outer loop

        return finalMax;
    }//findMaxSquareWithAllZeros

    /*A more efficient approach is below. For explanation, check out the hint video in the given problem link*/
    public static int findMaxSquareWithAllZeros2(int[][] input) {
        int rows=input.length;
        if(rows==0){
            return 0;
        }//if
        int cols=input[0].length;

        int[][] memo=new int[rows][cols];

        //fill the 1st row
        for(int j=0;j<cols;j++){
            //Note: assign 1 if input[0][j]==0
            memo[0][j]=input[0][j]==0?1:0;
        }

        //fill the 1st col
        for(int i=0;i<rows;i++){
            //Note: assign 1 if input[i][0]==0
            memo[i][0]=input[i][0]==0?1:0;
        }

        int max=1;
        for(int i=1;i<rows;i++){
            for(int j=1;j<cols;j++){
                if(input[i][j]==1){
                    memo[i][j]=0;
                }
                else{
                    int min=Math.min(Math.min(memo[i-1][j], memo[i][j-1]),memo[i-1][j-1]);
                    memo[i][j]=min+1;
                }
                if(memo[i][j]>max){
                    //update max
                    max=memo[i][j];
                }
            }//inner for
        }//outer for

        return max;
    }//findMaxSquareWithAllZeros2

    public static void main(String[] args) {
        int[][] input=new int[][]{{0,0}};
        int res=MaximumSquareMatrixWithAll0s.findMaxSquareWithAllZeros2(input);
        System.out.println(res);
    }//main
}//MaximumSquareMatrixWithAll0s
