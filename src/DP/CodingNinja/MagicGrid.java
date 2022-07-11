package DP.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73044/problem/1794
public class MagicGrid {
    public static int getMinimumStrength(int[][] grid) {
    //Given: A[1][1] = A[R][C] = 0

    /*To get the cost at [i][j] get the min of x and y, where x=(min health required at [i][j+1])-cost[i][j+1],
    * y=(min health required at [i+1][j])-cost[i+1][j]. Why subtract the cost? eg: If health is at right/down 3, we require (min health
    * at right/down) (-3) at the current row, as we can gain that 3 at the right/down cell. Similarly, if health at right/down is -3,
    * we require (min health right/down) -(-3) at the current row, as we can lose that 3 at the right/down cell.
    *
    * If this min(x,y) becomes <0, make it 1, since at no point it can hit 0.
    *
    * Min cost at [row-1][col-1] is 1, which is the base case. Along the last row, compare only with [i][j+1].
    * Along the last col, compare it with only [i+1][j]*/

    /*Note: Instead of taking the health cost of right/down at every iteration, we can also take the cost of current [i][j]*/
        int row=grid.length;
        int col=grid[0].length;

        int[][] memo=new int[row][col];

        //initialize bottom-right most cell
        memo[row-1][col-1]=1;

        //initialize last row
        for(int j=col-2; j>=0;j--){
            //memo[row-1][j]=memo[row-1][j+1]-grid[row-1][j+1];
            memo[row-1][j]=memo[row-1][j+1]-grid[row-1][j+1];
            if( memo[row-1][j]<=0){
                memo[row-1][j]=1;
            }
        }//for

        //initialize last col
        for(int i=row-2; i>=0;i--){
            //memo[i][col-1]=memo[i+1][col-1]-grid[i+1][col-1];
            memo[i][col-1]=memo[i+1][col-1]-grid[i+1][col-1];
            if( memo[i][col-1]<=0){
                memo[i][col-1]=1;
            }
        }//for

        //now fill for all the cells
        for(int i=row-2;i>=0;i--){
            for(int j=col-2;j>=0;j--){
//                int right=memo[i][j+1]-grid[i][j+1];
//                int down=memo[i+1][j]-grid[i+1][j];

                int right=memo[i][j+1]-grid[i][j+1];
                int down=memo[i+1][j]-grid[i+1][j];

                memo[i][j]=Math.min(right,down);
                if(memo[i][j]<=0){
                    memo[i][j]=1;
                }
            }//inner for
        }//outer for

        return memo[0][0];
    }//getMinimumStrength

    public static void main(String[] args) {
        int [][] arr=new int[][]{{0, -2, -3, 1},{-1, 4, 0, -2},{1, -2, -3, 0}};
        int res=MagicGrid.getMinimumStrength(arr);
        System.out.println(res);
    }//main
}//MagicGrid
