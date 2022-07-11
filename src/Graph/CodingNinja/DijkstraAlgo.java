package Graph.CodingNinja;

import java.util.*;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10312/offering/79844/problem/1725
/*At each step, select the vertex u having the lowest distance AND un-traversed, and then modify(relax) the edges from it, if cost[u]+distance(u,v)<cost[v ]. But
* in case of a graph having negative edge(s), this condition of not traversing already selected vertices gives wrong answer*/

/*There is another algo called Bellman Ford(https://www.youtube.com/watch?v=FtN3BYH2Zes), where you traverse the edges rather than the vertices.
This is a DP problem and not a greedy like Dijkstra. There you will travel all the e edges, either v-1 number of times, or till the modification
  of edges stops completely at a pass, whichever is smaller. However, if there is a cycle AND its total weight is negative, then this fails. Reason being in
  a cycle whose total weight is negative, the total cost will keep reducing infinitely with every round. So Bellman Ford algo in this case will not freeze even after
  v-1 passes. It will continue till infinity. */
class DijEdge {
    int v1;
    int v2;
    int weight;

    DijEdge(int v1, int v2, int w){
        this.v1=v1;
        this.v2=v2;
        this.weight=w;
    }//DijEdge

}//DijEdge


public class DijkstraAlgo {
    static int[] Dij(int[][] adjMat, int v){
        //IMP: Here visited means closed, not opened
        int[] visited=new int[v];
        int[] distance=new int[v];

        //initialize the above
        for(int i=0;i<v;i++){
            visited[i]=0;
            distance[i]=Integer.MAX_VALUE;
        }
        distance[0]=0;

        int visitedCount=0;
        while(visitedCount<v){

            //pick the vertex with the lowest distance and which is unvisited
            int minVertex=-1;
            int minDistance=Integer.MAX_VALUE;
            for(int i=0;i<v;i++){
                if(visited[i]==0 && distance[i]<minDistance){
                    minDistance=distance[i];
                    minVertex=i;
                }
            }//inner for


            //update its visited status
            visited[minVertex]=1;
            visitedCount+=1;

            //check its unvisited neighbours, in that row of the matrix
            for(int j=0;j<v;j++){
                //if neighbour AND unvisited, AND provides a lesser sum of distance than its previous
                if(adjMat[minVertex][j]!=0 && visited[j]==0 && minDistance+adjMat[minVertex][j]<distance[j]){
                    //update parent and weight
                    distance[j]=minDistance+adjMat[minVertex][j];

                }//if
            }//for
        }//while


        return distance;
    }//Dij

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

//As according to the question both v and e are >=2, no need to handle for 0 count cases

        //create the adj matrix
        int[][] adj=new int[v][v];

        for(int i=0;i<e;i++){
            //for edge number of times
            int v1=sc.nextInt();
            int v2=sc.nextInt();
            int w=sc.nextInt();

            adj[v1][v2]=w;
            adj[v2][v1]=w;
        }//for

        int[] res=Dij(adj,v);

        for(int i=0;i<res.length;i++){
            System.out.println(i+" "+res[i]);
        }//for
    }//main
}//DijkstraAlgo
