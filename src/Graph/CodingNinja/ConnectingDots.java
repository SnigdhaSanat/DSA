package Graph.CodingNinja;

class UtilityConnectingDots{
    int[][] visited;
    boolean pathExists;
    char colorLetter;
    int cycleLength;
}

//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79344/problem/529
public class ConnectingDots {
    static int solve(String[] board , int n, int m)
    {
        /**Use DFS. Take the matrix as the input "board". In the original DFS, we used the adjacency matrix, and travelled only if
         * there is an edge there. Here, we check for all the 4 neighbouring cells, and proceed only if a same colored letter is present in one of them*/
        UtilityConnectingDots utilityConnectingDots=new UtilityConnectingDots();

        //convert the String[] into a char[][]
        char[][] charGraph=new char[n][m];
        for(int i=0;i<n;i++){
            String rowString=board[i];
            for(int j=0;j<m;j++){
                charGraph[i][j]=rowString.charAt(j);
            }//inner for
        }//outer for


        //continue the loop till the path is not yet found
        utilityConnectingDots.visited=new int[n][m];
        utilityConnectingDots.pathExists=false;
        utilityConnectingDots.cycleLength=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!utilityConnectingDots.pathExists){
                    utilityConnectingDots.colorLetter=charGraph[i][j];

                    /*resetting the visited array will only give you TLE. Also it is redundant as the recursion un-visits the array on every  return*/
                    //utilityConnectingDots.visited=new int[n][m];

                    //set the first point as visited, and increment cycleLength
                    utilityConnectingDots.visited[i][j]=1;
                    utilityConnectingDots.cycleLength+=1;

                    //call DFS. Note that while standing at the current point, we look for the next char
                    DFSGetPath(utilityConnectingDots,charGraph,n,m,i,j);
                }//if
            }//inner for loop
        }//outer for loop

        int res=utilityConnectingDots.pathExists?1:0;
        return res;
    }//solve

    static void DFSGetPath(UtilityConnectingDots util, char[][] charGraph,int n, int m, int currentRow, int currentCol){
        //IMP: while standing at current row and column, search for the next wordIndex.
        // Also mark the visited of the next point before actually starting the DFS and stepping into it

        //if any of the 4 adjacent points is visited(implying having the same color) AND cycleLength>=4, set pathExists as true;
        if(((currentCol-1>=0 && util.visited[currentRow][currentCol-1]==1)
        || (currentRow-1>=0 && util.visited[currentRow-1][currentCol]==1)
        ||(currentCol+1<m && util.visited[currentRow][currentCol+1]==1)
        || (currentRow+1<n && util.visited[currentRow+1][currentCol]==1) )
                && util.cycleLength>=4 && charGraph[currentRow][currentCol-1]==util.colorLetter ){
            util.pathExists=true;
            return;
        }

        if(util.pathExists){
            //now that the path is found
            return;
        }


        /**check all the 4 neighbours. This is the equivalent of the for loop in the original DFS
        In all the 4 directions, check if path is not yet found, if the next step is within limits,
        is it the same color we are looking for and if it is still unvisited*/

        //left
        if(!util.pathExists && currentCol-1>=0 && charGraph[currentRow][currentCol-1]==util.colorLetter
                && util.visited[currentRow][currentCol-1]==0){
            util.visited[currentRow][currentCol-1]=1;
            util.cycleLength+=1;

            DFSGetPath(util,charGraph,n,m,currentRow,currentCol-1);
            //returned from recursion. Continue with the next direction
            /*Note that this is the route where path is not yet found. Else pathExists will be true
             * and it would have returned from the top of this function*/
        }

        //top
        if(!util.pathExists && currentRow-1>=0 && charGraph[currentRow-1][currentCol]==util.colorLetter
                && util.visited[currentRow-1][currentCol]==0){
            util.visited[currentRow-1][currentCol]=1;
            util.cycleLength+=1;

            DFSGetPath(util,charGraph,n,m,currentRow-1,currentCol);
            //returned from recursion. Continue with the next direction
        }
        //right
        if(!util.pathExists && currentCol+1<m && charGraph[currentRow][currentCol+1]==util.colorLetter
                && util.visited[currentRow][currentCol+1]==0){
            util.visited[currentRow][currentCol+1]=1;
            util.cycleLength+=1;

            DFSGetPath(util,charGraph,n,m,currentRow,currentCol+1);
            //returned from recursion. Continue with the next direction
        }
        //bottom
        if(!util.pathExists && currentRow+1<n && charGraph[currentRow+1][currentCol]==util.colorLetter &&
                util.visited[currentRow+1][currentCol]==0){
            util.visited[currentRow+1][currentCol]=1;
            util.cycleLength+=1;

            DFSGetPath(util,charGraph,n,m,currentRow+1,currentCol);
            //returned from recursion. Continue with the next direction
        }

        /**since you are going back the path, set [currentRow][currentCol] as unvisited, , and decrement the cycleLength,so that in the same recursion stack,
        the current row and column can be arrived at from a different path*/
        util.visited[currentRow][currentCol]=0;
        util.cycleLength-=1;
        return;//return up the recursion stack;
    }//DFSGetPath

    public static void main(String[] args) {
        int n=2;
        int m=2;
        String[] board=new String[n];

        board[0]="AA";
        board[1]="BB";

        int result=solve(board , n,  m);
        System.out.print(result);
    }
}//ConnectingDots
