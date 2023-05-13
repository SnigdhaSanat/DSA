package Graph.CodingNinja;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79336/problem/1695

class Utility{
    Stack<Integer> stk;
    int[] visited;
    boolean pathExists;
}

public class GetPathDFS {
    public static void main(String[] args) throws NumberFormatException, IOException {
/**IMP: As it is DFS, which means it traverses "greedily", it might NOT return the shortest path. Also, why don't we un-visit vertices as we pop? We pop because we need the path to be the exact one, which means the start to end sequence matters. But we keep a visited as such because we don't want to travel that again if that did not yield a result the last time it was visited. If it did, we would have broken the loop anyway. We un-visit in case of problems like CodingNinjas or ConnectingDots because there, if a vertex did not yield a result in a past iteration, it might still yield it in the current iteration, as its position in the word CODINGNINJA or in the colored sequence of dots matter, which might be different in the current iteration. But in this problem, we are concerned only about the destination rather than the vertices in its path or their placements.*/

        Scanner sc=new Scanner(System.in);
        int v=4;//sc.nextInt();
        int e=4;//sc.nextInt();


        //create the adjacency matrix
        int[] starts=new int[]{0,1,2,3};//new int[]{0,0,1,2};
        int[] ends=new int[]{1,2,3,0};//new int[]{1,2,4,5};

        int[][] adjMat=new int[v][v];
        for(int i=0;i<e;i++){
            int start=starts[i];//sc.nextInt();
            int end=ends[i];//sc.nextInt();

            adjMat[start][end]=1;
            adjMat[end][start]=1;
        }//for

        int v1=0;//sc.nextInt();
        int v2=3;//sc.nextInt();

        if(v1==v2){
            //if the starting and ending vertex is the same, return the vertex
        System.out.println(v1);
        }
        else{
            //DFS
            Utility util=new Utility();
            util.stk=new Stack<Integer>();
            util.visited=new int[v];
            util.pathExists=false;

            int startingVertex=v1;
            util.visited[startingVertex]=1;
            util.stk.add(startingVertex);

            DFSGetPath(adjMat,v,startingVertex,v2,util);

            //after recursion
            if(!util.pathExists){
                //if no path exists
                System.out.println(" ");
            }
            else{
                //as the question asks to print the path from end to start
                while (!util.stk.empty()){
                    System.out.print(util.stk.pop()+" ");
                }//while
            }//inner else
        }//else

    }//main

    static void DFSGetPath(int[][] adjMat, int v, int startingVertex, int end, Utility util){
        if(util.pathExists){
            //now that the path is found
            return;
        }
        for(int j=0;j<v;j++){
            if(util.pathExists){
                //now that the path is found
                return;
            }
            //get the vertices adjacent to startingVertex AND not visited
            if(adjMat[startingVertex][j]==1 && util.visited[j]==0){
                util.visited[j]=1;
                //add the vertex to the stack path
                util.stk.add(j);

                if(j==end){
                    //end vertex found, so update the pathExists, so that recursion is stopped
                    util.pathExists=true;
                    break;
                }//inner if

                //recurse, with j as the starting vertex
                DFSGetPath(adjMat,v,j,end,util);

                //returned from recursion. Continue with the next lateral iteration
            }//if
        }//for

        //pop out from the stack
        if (!util.pathExists) {
            util.stk.pop();
        }

        //This is where you arrive at, after the original vertex's adjacent vertices have been traversed, all of them.
        return;//return up the recursion stack;
    }//DFSGetPath
}//GetPathDFS
