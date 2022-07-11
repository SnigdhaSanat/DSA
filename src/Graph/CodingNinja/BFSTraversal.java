package Graph.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79330/problem/1699

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFSTraversal {
    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();

        if(v==0){
            System.out.println("");
        }
        else{
            //create the adjMat
            int[][] adjMat=new int[v][v];
            for(int i=0;i<e;i++){
                int start=sc.nextInt();
                int end=sc.nextInt();

                adjMat[start][end]=1;
                adjMat[end][start]=1;
            }//for

            //Now traverse in BFS way
            Queue<Integer> open=new LinkedList<Integer>();
            Queue<Integer> closed=new LinkedList<Integer>();

            int[] visited=new int[v];
            int visitedCount=0;

            int startingVertex=-1;

            while(visitedCount<v) {
                /*The outer loop is required to keep track of the next connected component */
                //go to the the start of the next(or the first) connected component
                for(int i=0;i<v;i++){
                    if(visited[i]==0){
                        startingVertex=i;
                        break;
                    }//if
                }//for

                open.add(startingVertex);//adding vertex 0
                visited[startingVertex] = 1;
                visitedCount += 1;

                while (!open.isEmpty()) {
                    /*Inner loop is to traverse within the connected component*/
                    //Add its adjacent vertices of the front of open queue to open list.
                    int front = open.poll();
                    for (int j = 0; j < v; j++) {
                        if (adjMat[front][j] == 1 && visited[j] == 0) {
                            //if it is adjacent AND not visited
                            open.add(j);
                            visited[j] = 1;
                            visitedCount += 1;
                        }//if
                    }//inner for loop

                    //Remove from the front of open, and add it to closed list.
                    closed.add(front);
                    System.out.print(front + " ");

                }//inner while
            }//outer while
        }//else
    }//main
    }//BFSTraversal
