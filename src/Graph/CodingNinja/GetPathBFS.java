package Graph.CodingNinja;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79338/problem/1696
public class GetPathBFS {
    public static void main(String[] args) throws NumberFormatException, IOException {
        /*Exactly same as BFS. When the current vertex is v2, break the loop. Also keep a track of the path.
        Else return ""*/
        /*IMP: How does it ensure that the shortest path is returned?  Say for v1=0 and v2=4, we can go either by 0->4, or by 0->2->4. But since
        * we will mark 4 as visited and also input it in the hm when traversing the edge 0->4, when visiting vertex 2,vertices 0 and 4 will already be
        * marked as visited, so we won't even enter it in the hm. As the hm is used to return the shortest path, so it automatically takes care of this. */

        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();

        boolean pathExists=false;

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

        if(v1==v2){
            //if start and end is the same
            System.out.println(v1);
        }
        else{
            //Now traverse in BFS way
            Queue<Integer> open=new LinkedList<Integer>();
            Queue<Integer> closed=new LinkedList<Integer>();

            //IMP: As it is BFS, unlike DFS, the queues don't track the path. So creating an  hm to keep track of the path.
            HashMap<Integer, Integer> hm=new HashMap<Integer, Integer>();

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

                        //NOTE: key:end, value:start, as the question requires the reverse path
                        hm.put(j,front);

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

            if(pathExists){
                int nextPoint=v2;

                while(nextPoint!=v1){
                    System.out.print(nextPoint+" ");
                    nextPoint=hm.get(nextPoint);
                }//while

                System.out.print(nextPoint);
            }//inner if
            else{
                System.out.println(" ");
            }   //inner else
        }//outer else
    }//main
}//GetPathBFS
