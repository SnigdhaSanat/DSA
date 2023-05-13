package Graph.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79344/problem/528
class UtilityCodingNinja{
    int[][] visited;
    boolean pathExists;
    String word;//the word to search for: CODINGNINJA
}


public class CodingNinjas {
    static int solve(String[] Graph , int n, int m)
    {

        /**Solve the LargestPiece question before this question*/

/**Use DFS. Take the matrix as the input "Graph". In the original DFS, we used the adjacency matrix, and travelled only if
* there is an edge there. Here, we check for all the 8 neighbouring cells, and proceed only if the required character is present in one of them*/
        UtilityCodingNinja utilityCodingNinja=new UtilityCodingNinja();
        utilityCodingNinja.pathExists=false;
        utilityCodingNinja.word="CODINGNINJA";//this stays constant
        utilityCodingNinja.visited=new int[n][m];

        //convert the String[] into a char[][]
        char[][] charGraph=new char[n][m];
        for(int i=0;i<n;i++){
            String rowString=Graph[i];
            for(int j=0;j<m;j++){
                charGraph[i][j]=rowString.charAt(j);
            }//inner for
        }//outer for


        //find all the Cs
        //continue the loop till more Cs exist AND the path is not yet found
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(charGraph[i][j]=='C' && !utilityCodingNinja.pathExists){
        /** resetting the visited array.This is NOT required, as anyway we mark the cells as unvisited when we return from recursion. So basically
        * only those cells are marked visited which we have traversed in the current recursion stack. So if we arrive at the 2nd or higher lateral iteration, then          all the cells visited during DFS recursions of the previous traversals will be marked unvisited. Rather this will give a TLE*/
                    //utilityCodingNinja.visited=new int[n][m];

                    /** set the first point as visited. */
                    utilityCodingNinja.visited[i][j]=1;

                    //call DFS. Note that while standing at the current point, we look for the next char
                    DFSGetPath(utilityCodingNinja,charGraph,n,m,i,j,1);
                }
            }//inner for loop
        }//outer for loop

        int res=utilityCodingNinja.pathExists?1:0;
        return res;

    }//solve

    static void DFSGetPath(UtilityCodingNinja util, char[][] charGraph,int n, int m, int currentRow, int currentCol, int wordIndex){
        //IMP: while standing at current row and column, search for the next wordIndex.
        // Also mark the visited of the next point before actually starting the DFS and stepping into it

        //if the next point to visit is the last word, set pathExists as true;
        if(wordIndex==util.word.length()){
            /**We step into the currentRow and currCol because it passed the condition in the previous recursion. And we have no more to search*/
            util.pathExists=true;
            return;
        }

        if(util.pathExists){
            //now that the path is found
            return;
        }


        /**check all the 8 neighbours. This is the equivalent of the for loop in the original DFS
        In all the 8 directions, check if path is not yet found, if the next step is within limits,
        is it the char we are looking for and if it is still unvisited*/

        //left
        if(!util.pathExists && currentCol-1>=0 && charGraph[currentRow][currentCol-1]==util.word.charAt(wordIndex)
        && util.visited[currentRow][currentCol-1]==0){
            util.visited[currentRow][currentCol-1]=1;

            DFSGetPath(util,charGraph,n,m,currentRow,currentCol-1,wordIndex+1);
            //returned from recursion. Continue with the next direction
            /*IMP: Note that this is the route where path is not yet found. Else pathExists will be true
            * and it would have returned from the top of this function, where util.pathExists=true */
        }
        //top
        if(!util.pathExists && currentRow-1>=0 && charGraph[currentRow-1][currentCol]==util.word.charAt(wordIndex)
        && util.visited[currentRow-1][currentCol]==0){
            util.visited[currentRow-1][currentCol]=1;

            DFSGetPath(util,charGraph,n,m,currentRow-1,currentCol,wordIndex+1);
            //returned from recursion. Continue with the next direction
        }
        //right
        if(!util.pathExists && currentCol+1<m && charGraph[currentRow][currentCol+1]==util.word.charAt(wordIndex)
        && util.visited[currentRow][currentCol+1]==0){
            util.visited[currentRow][currentCol+1]=1;

            DFSGetPath(util,charGraph,n,m,currentRow,currentCol+1,wordIndex+1);
            //returned from recursion. Continue with the next direction
        }
        //bottom
        if(!util.pathExists && currentRow+1<n && charGraph[currentRow+1][currentCol]==util.word.charAt(wordIndex) &&
                util.visited[currentRow+1][currentCol]==0){
            util.visited[currentRow+1][currentCol]=1;

            DFSGetPath(util,charGraph,n,m,currentRow+1,currentCol,wordIndex+1);
            //returned from recursion. Continue with the next direction
        }
        //left-top
        if(!util.pathExists && currentCol-1>=0 && currentRow-1>=0 && charGraph[currentRow-1][currentCol-1]==util.word.charAt(wordIndex) &&
                util.visited[currentRow-1][currentCol-1]==0){
            util.visited[currentRow-1][currentCol-1]=1;

            DFSGetPath(util,charGraph,n,m,currentRow-1,currentCol-1,wordIndex+1);
            //returned from recursion. Continue with the next direction
        }
        //top-right
        if(!util.pathExists && currentRow-1>=0 && currentCol+1<m && charGraph[currentRow-1][currentCol+1]==util.word.charAt(wordIndex) &&
                util.visited[currentRow-1][currentCol+1]==0){
            util.visited[currentRow-1][currentCol+1]=1;

            DFSGetPath(util,charGraph,n,m,currentRow-1,currentCol+1,wordIndex+1);
            //returned from recursion. Continue with the next direction
        }
        //right-bottom
        if(!util.pathExists && currentCol+1<m && currentRow+1<n && charGraph[currentRow+1][currentCol+1]==util.word.charAt(wordIndex) &&
                util.visited[currentRow+1][currentCol+1]==0){
            util.visited[currentRow+1][currentCol+1]=1;

            DFSGetPath(util,charGraph,n,m,currentRow+1,currentCol+1,wordIndex+1);
            //returned from recursion. Continue with the next direction
        }
        //bottom-left
        if(!util.pathExists && currentRow+1<n && currentCol-1>=0 && charGraph[currentRow+1][currentCol-1]==util.word.charAt(wordIndex) &&
                util.visited[currentRow+1][currentCol-1]==0){
            util.visited[currentRow+1][currentCol-1]=1;

            DFSGetPath(util,charGraph,n,m,currentRow+1,currentCol-1,wordIndex+1);
            //returned from recursion. Also the end of directions(lateral iterations)
        }

        /**since you are going back the path, set [currentRow][currentCol] as unvisited, so that in the same recursion stack,
        the current row and column can be arrived at from a different path*/
        util.visited[currentRow][currentCol]=0;
        return;//return up the recursion stack;
    }//DFSGetPath

    public static void main(String[] args) {
        int n=4;
        int m=4;
        String[] Graph=new String[n];

        Graph[0]="CANI";
        Graph[1]="OOJN";
        Graph[2]="DDIG";
        Graph[3]="JNIN";

        int result=solve(Graph , n,  m);
        System.out.print(result);
    }
}//CodingNinjas
