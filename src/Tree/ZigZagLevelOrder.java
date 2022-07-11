package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNodeZigzagLevel {
    int val;
    TreeNodeZigzagLevel left;
    TreeNodeZigzagLevel right;
    TreeNodeZigzagLevel(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeZigzagLevel

public class ZigZagLevelOrder {
    class NodeZigZag{
        TreeNodeZigzagLevel node;
        int level;
        NodeZigZag(TreeNodeZigzagLevel n, int l){
            node=n;
            level=l;
        }
    }//NodeZigZag

    public ArrayList<ArrayList<Integer>> solve(TreeNodeZigzagLevel A) {
        /*Traverse each horizontal level. Each iteration will traverse 1 level, which will be directed by the currLevel.
         * As a level progresses, put all the nodes of that level into the currLevelList. After the level ends(after each inner while),
         * put that currLevelList into temp, in ascending or descending order, based on the current direction. Then add temp
         * to the res.*/
        Queue<NodeZigZag> levelList=new LinkedList<NodeZigZag>();
        ArrayList<ArrayList<Integer>> res= new ArrayList<ArrayList<Integer>>();

        TreeNodeZigzagLevel curr=A;

        //adding the root
        int currLevel=0;
        levelList.add(new NodeZigZag(curr,currLevel));
        boolean leftToRight=true;

        while(!levelList.isEmpty()){
            //inner while: Add children of all the nodes belonging to the current level
            //list to store elements belonging to the current level
            //create a new currentLevelList
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

            //add contents from the currentLevelList to res, based on direction
            ArrayList<Integer> temp=new ArrayList<Integer>();
            if(leftToRight){
                for(int i=0;i<currLevelList.size();i++){
                    temp.add(currLevelList.get(i));
                }//for
            }
            else{
                for(int i=currLevelList.size()-1;i>=0;i--){
                    temp.add(currLevelList.get(i));
                }//for
            }
            //add the temp to res
            res.add(temp);

            //update direction and the currentLevelList
            leftToRight=!leftToRight;

            //update current level
            currLevel+=1;
        }//outer while

        for(int i=0;i<res.size();i++){
            ArrayList<Integer> al=res.get(i);
            System.out.println("Next level");
            for(int j=0;j<al.size();j++){
                System.out.println(al.get(j));
            }
        }
        return  res;
    }//solve

public static void main(String[] args) {
    TreeNodeZigzagLevel root=new TreeNodeZigzagLevel(1);
    root.left = new TreeNodeZigzagLevel(2);
    root.right = new TreeNodeZigzagLevel(3);
    root.left.left= new TreeNodeZigzagLevel(4);
    root.left.right= new TreeNodeZigzagLevel(5);
    root.right.left= new TreeNodeZigzagLevel(6);
    root.right.right= new TreeNodeZigzagLevel(7);
    root.left.left.left= new TreeNodeZigzagLevel(8);


    new ZigZagLevelOrder().solve(root);
}//main
}//ZigZagLevelOrder
