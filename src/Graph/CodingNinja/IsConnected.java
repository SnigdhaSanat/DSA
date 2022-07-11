package Graph.CodingNinja;

import java.io.IOException;
import java.util.Scanner;

class Util {
    int[] visited;
    int visitedCount;
}

//https://classroom.codingninjas.com/app/classroom/me/567/content/10259/offering/79340/problem/1697

public class IsConnected {
    public static void main(String[] args) throws NumberFormatException, IOException {
    Scanner sc=new Scanner(System.in);
    int v=sc.nextInt();
    int e=sc.nextInt();

    if(v==0 || v==1){
        System.out.print("true");
    }
    else{
        //create the adj matrix
        int[][] adjMat=new int[v][v];
        for(int i=0;i<e;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();

            adjMat[a][b] =1;
            adjMat[b][a]=1;
        }//for

        Util utility=new Util();
        utility.visited=new int[v];
        utility.visitedCount=0;

        DFS(utility,0,v,adjMat);

        if(utility.visitedCount==v){
            System.out.print("true");
        }
        else{
            System.out.print("false");
        }
    }//outer else
    }//main

static void DFS(Util utility, int start,int v,int[][] adjMat){
    for(int j=0;j<v;j++){
        if(adjMat[start][j]==1 && utility.visited[j]==0){
            //if adjacent and not visited
            utility.visited[j]=1;
            utility.visitedCount+=1;
            DFS(utility,j,v,adjMat);

            int test=1;
            //returned from recursion. Continue with the next lateral iteration
        }//if
    }//for

    //This is where you arrive at, after the original vertex's adjacent vertices have been traversed, all of them.
    return;
}//DFS
}//IsConnected
