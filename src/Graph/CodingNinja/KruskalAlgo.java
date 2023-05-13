package Graph.CodingNinja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/10312/offering/79835/problem/1723

/**Find the minimum weight edge, but the one that does not form a cycle. Repeat v-1 times*/
class Edge implements Comparable<Edge>{
    int v1;
    int v2;
    int weight;

    Edge(int v1, int v2, int w){
        this.v1=v1;
        this.v2=v2;
        this.weight=w;
    }//Edge

    @Override
    public int compareTo(Edge e) {
        if(this.weight>e.weight){
            return 1;
        }
        else if(this.weight<e.weight){
            return -1;
        }
        else{
            return 0;
        }
    }//compareTo
}//Edge

public class KruskalAlgo {
    static ArrayList<Edge> Kruskal(ArrayList<Edge> edges, int v){

        //sort according to weights
        Collections.sort(edges);

        //create a parent array
        int[] parents=new int[v];//for all the v vertices

        //initialize the parent array of all the vertices as the vertices themselves
        for(int i=0;i<v;i++){
            parents[i]=i;
        }//for


        //for v-1 times(count of MST), pick a non cyclic edge from the input, and add it to the output MSTEdges
        ArrayList<Edge> MSTEdges=new ArrayList<Edge>();

        int edgesCount=0;
        int i=0;

        while(edgesCount<v-1){
            //run this for v-1 number of edges
            Edge edge=edges.get(i);
            int v1=edge.v1;
            int v2=edge.v2;

            //check if it is a cycle. Find the TOP parents, not immediate parents
            int v1Temp=v1;
            int v2Temp=v2;
            int p1=parents[v1Temp];
            int p2=parents[v2Temp];

            while(v1Temp!=p1){
                v1Temp=p1;
                p1=parents[v1Temp];
            }//while

            while(v2Temp!=p2){
                v2Temp=p2;
                p2=parents[v2Temp];
            }//while

            //if it is a cycle, skip it
            if(p1==p2){
                i+=1;////it is not possible that we run out of edges. MST will have edges <=e
                continue;
            }

            //if not a cycle, pick it and modify its top parent.
            else{
                /*Any one of the 2 can be modified, but for uniformity, modify the larger one to have parent as the smaller vertex*/
                parents[p2]=p1;

                //adjust, so that v1<=v2, as the question requires to do so
                if(edge.v2<edge.v1){
                    int temp=edge.v1;
                    edge.v1=edge.v2;
                    edge.v2=temp;
                }
                MSTEdges.add(edge);

                edgesCount+=1;
                i+=1;
            }

        }//while


        return  MSTEdges;
    }//Kruskal

    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        int v = 4;
        int e = 4;

   ArrayList<Edge> edges=new ArrayList<Edge>();

//As according to the question both v and e are >=2, no need to handle for 0 count cases

        //for edge number of times

        Edge edge1=new Edge(0, 1, 3);
        Edge edge2=new Edge(0 ,3, 5);
        Edge edge3=new Edge(1,2,1);
        Edge edge4=new Edge(2,3,8);
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);


       ArrayList<Edge> res= Kruskal(edges,v);

    for(int i=0;i<res.size();i++){
        Edge edge=res.get(i);
        System.out.println(edge.v1+" "+edge.v2+" "+edge.weight);
    }//for
    }//main
}//KruskalAlgo
