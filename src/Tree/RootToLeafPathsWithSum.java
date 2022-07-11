package Tree;


import java.util.ArrayList;

class TreeNodeRootToLeafPaths {
    int val;
    TreeNodeRootToLeafPaths left;
    TreeNodeRootToLeafPaths right;
    TreeNodeRootToLeafPaths(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeRootToLeafPaths


public class RootToLeafPathsWithSum {
class NodeUtility{
    int s;
    ArrayList<Integer> currPath;
    ArrayList<ArrayList<Integer>> resList;
}
public ArrayList<ArrayList<Integer>> pathSum(TreeNodeRootToLeafPaths A, int B) {
    NodeUtility nodeUtility=new NodeUtility();
    nodeUtility.s=0;
    nodeUtility.currPath=new ArrayList<Integer>();
    nodeUtility.resList=new ArrayList<ArrayList<Integer>>();

    recurseSum(A,B,nodeUtility);

    //after recursion
    return nodeUtility.resList;
}//pathSum

    void recurseSum( TreeNodeRootToLeafPaths root,int B, NodeUtility nodeUtility){
        //add root's val to the sum and currPath
        nodeUtility.s+=root.val;
        nodeUtility.currPath.add(root.val);

        //if root is leaf, add the sum
        if(root.left==null && root.right==null){
            int sum=(nodeUtility.s);
            if(sum==B){
                //if the sum equals B, TAKE A SNAPSHOT OF the currPath, and add it to the res
                //Else, temp only stores a ref to the currPath, which keeps on changing,
                //and hence resList content will also keep on changing, as it will only be storing a ref
                ArrayList<Integer> temp=new ArrayList<>(nodeUtility.currPath);
                nodeUtility.resList.add(temp);
            }
        }//if

        //recurse
        if( root.left!=null){
            recurseSum(root.left,B,nodeUtility);
        }
        if( root.right!=null){
            recurseSum(root.right,B,nodeUtility);
        }

        //deduct the curr root's value from the sum and currPath
        nodeUtility.s-=root.val;
        int len=nodeUtility.currPath.size();
        nodeUtility.currPath.remove(len-1);
        return;

    }//recurseSum

    public static void main(String[] args) {
        TreeNodeRootToLeafPaths root=new TreeNodeRootToLeafPaths(5);
        root.left = new TreeNodeRootToLeafPaths(4);
        root.right = new TreeNodeRootToLeafPaths(8);
        root.left.left= new TreeNodeRootToLeafPaths(11);
        root.right.left= new TreeNodeRootToLeafPaths(13);
        root.right.right= new TreeNodeRootToLeafPaths(4);
        root.left.left.left= new TreeNodeRootToLeafPaths(7);
        root.left.left.right= new TreeNodeRootToLeafPaths(2);
        root.right.right.left= new TreeNodeRootToLeafPaths(5);
        root.right.right.right= new TreeNodeRootToLeafPaths(1);

        int B=22;

        ArrayList<ArrayList<Integer>> res= new RootToLeafPathsWithSum().pathSum(root,B);

    }//main
}//RootToLeafPathsWithSum
