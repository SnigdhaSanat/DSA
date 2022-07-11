package Tree;

import java.math.BigInteger;

class TreeNodeSumRL {
    int val;
    TreeNodeSumRL left;
    TreeNodeSumRL right;
    TreeNodeSumRL(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeSumRootToLeaf

public class SumRootToLeafNumbers {

    class NodeUtility{
        String s;
        int sum;
    }

public int sumNumbers(TreeNodeSumRL A) {
    NodeUtility nodeUtility=new NodeUtility();
    nodeUtility.s="";
    nodeUtility.sum=0;

    recurseSum(A,nodeUtility);

    //after recursion
    int res=nodeUtility.sum;
    return res;
}//sumNumbers

void recurseSum( TreeNodeSumRL root, NodeUtility nodeUtility){
    //append root's val to the string
    String curr=String.valueOf(root.val);
    nodeUtility.s+=curr;

    //if root is leaf, add the sum
    if(root.left==null && root.right==null){
        BigInteger bigInteger=new BigInteger(nodeUtility.s);//String to BI
        BigInteger modu=bigInteger.mod(BigInteger.valueOf(1003));//Mod value, so <1003
        int num=modu.intValue();//BI to int, and also <1003

        //Current sum and num both will also be <1003. Also the result sum is 'mod'ed. So it is also <1003
        nodeUtility.sum=(nodeUtility.sum+num)%1003;
    }

    //recurse
    if(root.left!=null){
        recurseSum(root.left,nodeUtility);
    }
    if(root.right!=null){
        recurseSum(root.right,nodeUtility);
    }

    //remove the last char from the string
    String str=nodeUtility.s;
    int len=str.length();
    nodeUtility.s=str.substring(0,len-1);
    return;

}//recurseSum

    public static void main(String[] args) {
        TreeNodeSumRL root=new TreeNodeSumRL(1);
        root.left = new TreeNodeSumRL(2);
        root.right = new TreeNodeSumRL(3);
        root.left.left= new TreeNodeSumRL(4);
        root.left.right= new TreeNodeSumRL(5);
        root.right.left= new TreeNodeSumRL(6);
        root.right.right= new TreeNodeSumRL(7);
        root.left.left.left= new TreeNodeSumRL(8);

       int sum= new SumRootToLeafNumbers().sumNumbers(root);
       System.out.println(sum);
    }//main
}//SumRootToLeafNumbers
