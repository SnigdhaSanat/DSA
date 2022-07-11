package Graph.CodingNinja;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79334/problem/1693
public class HasPath {
    public static void main(String[] args) throws NumberFormatException, IOException {
        /*Exactly same as BFS. When the current vertex is v2, break the loops and return true. Else return false*/
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();

        boolean pathExists=false;

        if(v==0){
            System.out.println(pathExists);
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

            int v1=sc.nextInt();
            int v2=sc.nextInt();

            //Now traverse in BFS way
            Queue<Integer> open=new LinkedList<Integer>();
            Queue<Integer> closed=new LinkedList<Integer>();

            int[] visited=new int[v];

            int startingVertex=v1;

            open.add(startingVertex);
            visited[startingVertex] = 1;

            while (!open.isEmpty()) {
                //Add its adjacent vertices of the front of open queue to open list.
                int front = open.poll();
                for (int j = 0; j < v; j++) {
                    if (adjMat[front][j] == 1 && visited[j] == 0) {
                        //if j is adjacent AND not visited
                        open.add(j);
                        visited[j] = 1;
                        if(j==v2){
                            pathExists=true;
                            break;
                        }//inner if
                    }//outer if
                }//for loop

                if(pathExists){
                    break;
                }
                //Remove from the front of open, and add it to closed list.
                closed.add(front);
                }// while

            System.out.println(pathExists);
        }//else

    }//main
}//HasPath
