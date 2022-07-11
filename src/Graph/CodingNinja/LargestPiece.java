package Graph.CodingNinja;

class UtilityLP{
    int[][] visited;

    /*this has to be above the stack. Within the recursion stack, even if you are travelling back and forth, this stays as it is.
    //It only increases, never decreases within a recursion stack. Reason being within a connected component, we keep on adding to the size
    within the stack, AND also add with ALL the 4 directions. Plus we never reset the visited array within the stack*/
    int currMaxCount;
}


public class LargestPiece {
    /*In a for loop, find out all the points which has 1 and unvisited. Each of them serve as the starting point of a connected piece of 1.
    * Mark a connected piece as visited, and DO NOT reset it at the lateral iterations(Reason explained below). Compare and update the maxCount if applicable,
    * at the end of each  lateral iteration.
    *
    * With a DFS recursion stack,, traverse in the 4 directions, if there is a 1 and unvisited. Return when there is no more adjacent
    * unvisited 1. The currentMaxCount WITHIN a recursion stack(connected component) can only increase, as they are all connected.*/
    public static int dfs(String[] edge, int n) {

        //convert String[] into char[][]
        char[][] cake=new char[n][n];
        for(int i=0;i<n;i++){
            String rowString=edge[i];
            for(int j=0;j<n;j++){
                cake[i][j]=rowString.charAt(j);
            }
        }//outer for

        UtilityLP utilityLP=new UtilityLP();
        utilityLP.visited=new int[n][n];
        utilityLP.currMaxCount=0;
        
        int maxCount=0;

        //iterate for the unvisited 1s
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                //lateral iteration
                //reset currMaxCount for each connected component
                utilityLP.currMaxCount=0;

                /*What will happen if we reset it? Within a connected component, we will end up traversing it from different starting points,
                * since the visited array is reset at every lateral iteration. This will lead to a TLE. We don't have to reset the visited array, as among
                * disconnected components, a new component will always be unvisited. This is also the reason why we update currMaxCount only at every lateral iteration.*/
                //utilityLP.visited=new int[n][n];

                if(utilityLP.visited[i][j]==0 && cake[i][j]=='1'){
                    //if unvisited AND 1
                    //mark it as visited
                   
                    utilityLP.visited[i][j]=1;
                    utilityLP.currMaxCount+=1;
                    
                    //Note the we are looking for connected components, not cycles
                    DFSTraversal(utilityLP,cake,n,n,i,j);
                    
                    //update maxCount if applicable
                    maxCount=Math.max(maxCount,utilityLP.currMaxCount);
                }//if
            }//inner for
        }//outer for
        
        return maxCount;
    }//dfs

    static void DFSTraversal(UtilityLP util,char[][] cake,int n, int m, int currentRow, int currentCol){
        //IMP: while standing at current row and column, search for the next 1.
        // Also mark the neighbouring cell as visited and update the currMaxCount of the next point before actually starting the DFS and stepping into it

        //check all the 4 neighbours. This is the equivalent of the for loop in the original DFS
        //In all the ALL 4 directions, check if the next step is within limits,
        //is it a 1 and if it is still unvisited

        //left
        if(currentCol-1>=0 && cake[currentRow][currentCol-1]=='1'
                && util.visited[currentRow][currentCol-1]==0){
            util.visited[currentRow][currentCol-1]=1;
            util.currMaxCount+=1;

            DFSTraversal(util,cake,n,m,currentRow,currentCol-1);
            //returned from recursion. Continue with the next direction, as we want the largest piece
        }

        //top
        if(currentRow-1>=0 && cake[currentRow-1][currentCol]=='1'
                && util.visited[currentRow-1][currentCol]==0){
            util.visited[currentRow-1][currentCol]=1;
            util.currMaxCount+=1;

            DFSTraversal(util,cake,n,m,currentRow-1,currentCol);
            //returned from recursion. Continue with the next direction, as we want the largest piece
        }
        
        //right
        if( currentCol+1<m && cake[currentRow][currentCol+1]=='1'
                && util.visited[currentRow][currentCol+1]==0){
            util.visited[currentRow][currentCol+1]=1;
            util.currMaxCount+=1;

            DFSTraversal(util,cake,n,m,currentRow,currentCol+1);
            //returned from recursion. Continue with the next direction, as we want the largest piece
        }
        //bottom
        if(currentRow+1<n && cake[currentRow+1][currentCol]=='1' 
                && util.visited[currentRow+1][currentCol]==0){
            util.visited[currentRow+1][currentCol]=1;
            util.currMaxCount+=1;

            DFSTraversal(util,cake,n,m,currentRow+1,currentCol);
            //returned from recursion. Continue with the next direction, as we want the largest piece
        }

        /*even if you are going back the path, you DO NOT have to reset the visited[] and the currMaxCount. Reason being within
        a connected component, if a cell is marked as visited and it stays as visited for rest of the DFS within the connected component
        traversal. As currentMaxCount keeps on adding, we don't miss out that cell
        */

        //return when there is no more '1' cell to visit
        return;//return up the recursion stack;
    }//DFSTraversal


    public static void main(String[] args) {
        int n=3;
        String[] edge=new String[3];
        edge[0]="100";
        edge[1]="010";
        edge[2]="010";

        int res=dfs(edge,n);
        System.out.print(res);
    }    
}//LargestPiece
