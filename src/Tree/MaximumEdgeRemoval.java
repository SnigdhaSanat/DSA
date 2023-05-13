package Tree;

import java.util.ArrayList;
/**Each row of the 2 column B matrix denotes an edge. As it is a tree, so B will have A-1 rows or edges, where A is the number of nodes.*/
class Visited{
    boolean visit[];
    int res;
    ArrayList<ArrayList<Integer>> matrix;
}
public class MaximumEdgeRemoval {

    public int solve(int A, int[][] B) {
// Create a visited array for DFS and make all nodes unvisited in starting
//A=#nodes. #Edges:A-1
    Visited visited=new Visited();
    visited.visit=new boolean[A+1];

//mark all as unvisited
    for (int i = 0; i <=A; i++)
    {
//As we want a 1 based matrix, take matrix size as A+1
        visited.visit[i] = false;
    }

//make the adjacency matrix
    visited.matrix=new ArrayList<ArrayList<Integer>>();

//As we want a 1 based matrix, take matrix size as A+1
    for(int i=0;i<=A;i++){
        visited.matrix.add(new ArrayList<Integer>());
    }

    for(int i=0;i<A-1;i++)
    {
        //iterate for number of edges, A-1
        int v1=B[i][0];
        int v2=B[i][1];
        visited.matrix.get(v1).add (v2);
        visited.matrix.get(v2).add (v1);
    }

    visited.res = 0;

    // calling the dfs from node-0
    dfs(1, visited);

    /**The final res from dfs, even if it is an even number is not counted. Why? This is because we either add subtreeNodeCount to the currComponentNodeCount in case of odd case, and in the even case, instead of adding subtreeNodeCount to the currComponentNodeCount, we add 1 to the global result. The thing that we don't add subtreeNodeCount to the currComponentNodeCount in case of even result means that subtreeNodeCount is disconnected from the currComponentNodeCount, or effectively the subtree is disconnected from its root. Even if the top root has an even root, it has nothing to disconnect from. So it adds nothing to the #edges removed. */
    return visited.res;
    }//solve

    // Utility method to do DFS of the graph and count edge
// deletion for even forest
    int dfs( int u, Visited visited)
    {
        //mark as true;
        visited.visit[u] = true;
        int currComponentNodeCount = 0;

        // iterate over all neighbor of node u
        for (int i = 0; i < visited.matrix.get(u).size(); i++)
        {
            int v = visited.matrix.get(u).get(i);
            if (!visited.visit[v])
            {//if v is unvisited

                // Count the number of nodes in a subtree
                int subtreeNodeCount = dfs(v, visited);

/** if returned node count is even, disconnect. You don't have to disconnect really, just increment res. It is still a disconnect because in this case it does not add to the currComponentNodeCount. Only visited.res is incremented
                */
                if (subtreeNodeCount % 2 == 0)
                    visited.res++;

                    // else add subtree nodes in current component
                else
                    currComponentNodeCount += subtreeNodeCount;
            }//if
        }//for

        // number of nodes in current component and one for
        // current node
        return (currComponentNodeCount + 1);
    }//dfs

public static void main(String[] args) {
    int A = 6;
    int[][] B = new int[][]{{1, 2},{1, 3},{1, 4},{3, 5},{4, 6}};
    int res=new MaximumEdgeRemoval().solve(A,B);
    System.out.println(res);
}//main

}//MaximumEdgeRemoval

