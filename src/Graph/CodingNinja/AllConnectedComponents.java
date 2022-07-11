package Graph.CodingNinja;

import java.io.IOException;
import java.util.*;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79342/problem/1698
public class AllConnectedComponents {
    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();

        //adjacent matrix
        int[][] adjMat=new int[v][v];
        for(int i=0;i<e;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();

            adjMat[a][b]=1;
            adjMat[b][a]=1;
        }//for

        int visitedCount=0;
        int[] visited=new int[v];

        while(visitedCount<v){
            ArrayList<Integer> al=new ArrayList<Integer>();

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
                    if(adjMat[front][j]==1 && visited[j]==0){
                        //if adj and unvisited
                        open.add(j);
                        visited[j]=1;
                        visitedCount+=1;
                    }//if
                }//for

                closed.add(front);
                al.add(front);
            }//inner while

            Collections.sort(al);
            for(int idx=0;idx<al.size();idx++){
                System.out.print(al.get(idx)+" ");
            }
            System.out.println();
        }//outer while
    }//main
}//AllConnectedComponents
