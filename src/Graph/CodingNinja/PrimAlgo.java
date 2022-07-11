package Graph.CodingNinja;

import java.util.*;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10312/offering/79839/problem/1724

/*Each time find the minimum weight vertex, NOT edge, and update its adjacent weights*/
class PrimEdge {
    int v1;
    int v2;
    int weight;

    PrimEdge(int v1, int v2, int w){
        this.v1=v1;
        this.v2=v2;
        this.weight=w;
    }//PrimEdge

}//PrimEdge


public class PrimAlgo {
    static ArrayList<PrimEdge> Prim(int[][] adjMat, int v){
        //IMP: Here mark visited when  closed, not opened
        int[] visited=new int[v];
        int[] weights=new int[v];
        int[] parents=new int[v];

        //initialize the above. Assign weights to vertices, not edges
        for(int i=0;i<v;i++){
            visited[i]=0;
            weights[i]=Integer.MAX_VALUE;
            parents[i]=-1;
        }

        //As we want to start with 0, assign it the min weight
        weights[0]=0;

        int visitedCount=0;
        while(visitedCount<v){
            //run this for v number of vertices. Basically visit every vertex

            //pick the vertex with the lowest weight and which is unvisited
            int minVertex=-1;
            int minWeight=Integer.MAX_VALUE;
            for(int i=0;i<v;i++){
                if(visited[i]==0 && weights[i]<minWeight){
                minWeight=weights[i];
                minVertex=i;
                }
            }//inner for

            //update its visited status
            visited[minVertex]=1;
            visitedCount+=1;

            //check its unvisited neighbours, in that row of the matrix
            for(int j=0;j<v;j++){
                //if neighbour AND unvisited, AND provides a lesser weight than its previous
                /*Unlike Dijkstra, where weights[j] is updated to weights[minVertex]+adjMat[minVertex][j] conditionally, here weights[minVertex] is not counted.
                * Meaning at each point, min vertex is the source vertex. This is because we are looking for an MST here, not a path from a source vertex. */
                if(adjMat[minVertex][j]!=0 && visited[j]==0 && adjMat[minVertex][j]<weights[j]){
                    //update parent and weight
                    parents[j]=minVertex;
                    weights[j]=adjMat[minVertex][j];
                    int test=1;

                }//if
            }//for
        }//while

ArrayList<PrimEdge> primList=new ArrayList<PrimEdge>();
        for(int i=1;i<v;i++){
            //leave out 0, as its parent is -1. Anyway its co-vertex will include it as the parent
            int vertex=i;
            int parent=parents[i];
            int weight=weights[i];
            if(vertex<=parent){
                PrimEdge edge=new PrimEdge(vertex,parent,weight);
                primList.add(edge);
            }
            else{
                PrimEdge edge=new PrimEdge(parent,vertex,weight);
                primList.add(edge);
            }
        }//for

        return primList;
    }//Prim


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = 4;//sc.nextInt();
        int e = 4;//sc.nextInt();


//As according to the question both v and e are >=2, no need to handle for 0 count cases

        //create the adj matrix
        int[][] adj=new int[v][v];

        int[] sources=new int[]{0,1,1,2};
        int[] destinations=new int[]{1,2,3,3};
        int[] weights=new int[]{2,6,2,2};
        for(int i=0;i<e;i++){
            //for edge number of times
            int v1=sources[i];//sc.nextInt();
            int v2=destinations[i];//sc.nextInt();
            int w=weights[i];//sc.nextInt();

            adj[v1][v2]=w;
            adj[v2][v1]=w;
        }//for

        ArrayList<PrimEdge> res=Prim(adj,v);
        for(int i=0;i<res.size();i++){
            PrimEdge edge=res.get(i);
            System.out.println(edge.v1+" "+edge.v2+" "+edge.weight);
        }//for
    }//main
}//PrimAlgo
