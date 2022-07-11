package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNodeReverseLevel {
    int val;
    TreeNodeReverseLevel left;
    TreeNodeReverseLevel right;
    TreeNodeReverseLevel(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeReverseLevel

public class ReverseLevelOrder {

class NodeZigZag{
    TreeNodeReverseLevel node;
    int level;
    NodeZigZag(TreeNodeReverseLevel n, int l){
        node=n;
        level=l;
    }
}//NodeZigZag

    public ArrayList<Integer> solve(TreeNodeReverseLevel A) {
        /*Traverse each horizontal level. Each iteration will traverse 1 level, which will be directed by the currLevel.
         * As a level progresses, put all the nodes of that level into the currLevelList. After the level ends(after each inner while),
         * put that currLevelList into res.*/

        Queue<NodeZigZag> levelList=new LinkedList<NodeZigZag>();
        ArrayList<ArrayList<Integer>> res= new ArrayList<ArrayList<Integer>>();

        TreeNodeReverseLevel curr=A;

        //adding the root
        int currLevel=0;
        levelList.add(new NodeZigZag(curr,currLevel));

        while(!levelList.isEmpty()){
            //inner while: Add children of all the nodes belonging to the current level
            //create a new currentLevelList to store elements belonging to the current level
            ArrayList<Integer> currLevelList=new ArrayList<Integer> ();

            while(!levelList.isEmpty() && levelList.peek().level==currLevel) {
                NodeZigZag front=levelList.peek();
                if(front.node.left!=null)
                {
                    levelList.add(new NodeZigZag(front.node.left,currLevel+1));
                }
                if(front.node.right!=null){
                    levelList.add(new NodeZigZag(front.node.right,currLevel+1));
                }
                NodeZigZag removed=levelList.remove();
                currLevelList.add(removed.node.val);
            }//inner while

            //add contents from the currentLevelList to res
            res.add(currLevelList);

            //update current level
            currLevel+=1;
        }//outer while


        //add res in reverse order into final result
        ArrayList<Integer> finalRes=new ArrayList<Integer>();

        int len=res.size();
        for(int i=len-1;i>=0;i--){
            ArrayList<Integer> level=res.get(i);
            for(int j=0;j<level.size();j++){
                finalRes.add(level.get(j));
            }//inner
        }//outer for

//        for(int i=0;i<finalRes.size();i++){
//            System.out.println(finalRes.get(i));
//        }
        return  finalRes;
    }//solve

    public static void main(String[] args) {
        TreeNodeReverseLevel root=new TreeNodeReverseLevel(1);
        root.left = new TreeNodeReverseLevel(2);
        root.right = new TreeNodeReverseLevel(3);
        root.left.left= new TreeNodeReverseLevel(4);
        root.left.right= new TreeNodeReverseLevel(5);
        root.right.left= new TreeNodeReverseLevel(6);
        root.right.right= new TreeNodeReverseLevel(7);
        root.left.left.left= new TreeNodeReverseLevel(8);


        new ReverseLevelOrder().solve(root);
    }//main

}//ReverseLevelOrder
