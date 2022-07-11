package Graph.CodingNinja;

import java.util.*;
//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79344/problem/555

public class Islands {
    public static int numConnected(int[][] edges, int v) {
        if(v==0){
            //If no edges, the rest of the algo will take care of it
            //But if there is no  vertex, do this
            return 0;
        }

        else if (v==1) {
            return 1;
        }

        else{
            //adjacent matrix is the edges which is passed as param. So no need to create it separately.
            int visitedCount=0;
            int connectedCount=0;
            int[] visited=new int[v];

            while(visitedCount<v){
                connectedCount+=1;

                int startingVertex=-1;
                //find the first unvisited vertex of the next or the first connected component
                for(int i=0;i<v;i++){
                    if(visited[i]==0){
                        startingVertex=i;
                        break;
                    }
                }//inner for

                //now explore the current connected component
                Queue<Integer> open=new LinkedList<>();
                Queue<Integer> closed=new LinkedList<>();

                open.add(startingVertex);
                visited[startingVertex]=1;
                visitedCount+=1;

                while(!open.isEmpty()){
                    int front=open.poll();

                    //front's adjacent vertices
                    for(int j=0;j<v;j++){
                        if(edges[front][j]==1 && visited[j]==0){
                            //if adj and unvisited
                            open.add(j);
                            visited[j]=1;
                            visitedCount+=1;
                        }//if
                    }//for

                    closed.add(front);
                }//inner while
            }//outer while

            return  connectedCount;
        }

    }//numConnected

    public static void main(String[] args) {
        int[][] edges=new int[][]{{5, 8},
                {0, 1},
                {0, 4},
                {1, 2},
                {2, 0},
                {2, 4},
                {3, 0},
                {3, 2},
                {4, 3}};
        int v=8;
        int res=numConnected(edges,v);
        System.out.println(res);
    }
}//Islands