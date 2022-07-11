package Tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNodeRightView {
    int val;
    TreeNodeRightView left;
    TreeNodeRightView right;
    TreeNodeRightView(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeRightView


public class RightViewOfBinaryTree {

class NodeRight{
    TreeNodeRightView node;
    int level;
    NodeRight(TreeNodeRightView n, int l){
        node=n;
        level=l;
    }
}//NodeRight


public ArrayList<Integer> solve(TreeNodeRightView A) {
    /*Rightmost node of each level is the right view for that level.
    Traverse each horizontal level. Each iteration will traverse 1 level, which will be directed by the currLevel.
    As a level ends, put the last node of that level into the res.*/

    Queue<NodeRight> levelList=new LinkedList<NodeRight>();
    ArrayList<Integer> res= new ArrayList<Integer>();

    TreeNodeRightView curr=A;
    //adding the root
    levelList.add(new NodeRight(curr,0));
    int currLevel=0;
    //res.add(curr.val);

    while(!levelList.isEmpty()){
        //inner while: Add children of all the nodes belonging to the current level
        NodeRight lastNode=null;
       while(!levelList.isEmpty() && levelList.peek().level==currLevel) {
           NodeRight front=levelList.peek();
           if(front.node.left!=null)
           {
               levelList.add(new NodeRight(front.node.left,currLevel+1));
           }
           if(front.node.right!=null){
               levelList.add(new NodeRight(front.node.right,currLevel+1));
           }

           lastNode=levelList.remove();
        }//inner while

       //add the last node to res
       res.add(lastNode.node.val);
        currLevel+=1;
    }//outer while

    for(int i=0;i<res.size();i++){
        System.out.println(res.get(i));
    }
    return  res;
}//solve


public static void main(String[] args) {
    TreeNodeRightView root=new TreeNodeRightView(1);
    root.left = new TreeNodeRightView(2);
    root.right = new TreeNodeRightView(3);
    //root.left.left=new TreeNodeRightView(4);
    root.left.right= new TreeNodeRightView(4);
    root.left.right.right= new TreeNodeRightView(5);

    new RightViewOfBinaryTree().solve(root);
}//main
}//RightViewOfBinaryTree
