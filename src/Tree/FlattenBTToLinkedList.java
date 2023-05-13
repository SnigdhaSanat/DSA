package Tree;


class TreeNodeFlatten {
    int val;
    TreeNodeFlatten left;
    TreeNodeFlatten right;
    TreeNodeFlatten(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeFlatten

public class FlattenBTToLinkedList {
/*Question requirement: flatten it to a linked list IN-PLACE.*/

/**Think of the smallest example. How would you convert a binary tree of root 1, LC as 2 and RC as 3 into a flat linkedList?
* Move RC to temp, LC to RC and set LC as null. Then go to the lowest rightmost child of root, and assign temp into its
* lowest RC. Recurse this. */
    public TreeNodeFlatten flatten(TreeNodeFlatten a) {
        recurseFlatten(a);
        return a;
    }//flatten

    void recurseFlatten(TreeNodeFlatten root){
        //base condition
        if(root==null ||(root!=null && root.left==null && root.right==null)){
            //if root is null, or if it is a leaf node
            return;
        }//base condition

        if(root.left!=null){
            recurseFlatten(root.left);
        }

        if(root.right!=null){
            recurseFlatten(root.right);
        }

        //place LC into RC, and original RC into temp. IMP: Set LC as null.
        TreeNodeFlatten temp=root.right;
        root.right=root.left;
        root.left=null;

        //Then go to the lowest rightmost child of root, and assign temp into its lowest RC
        TreeNodeFlatten curr=root;
        TreeNodeFlatten prev=null;
        while (curr!=null){
            prev=curr;
            curr=curr.right;
        }
        prev.right=temp;
    }//recurseFlatten

    public static void main(String[] args) {
        TreeNodeFlatten root=new TreeNodeFlatten(1);
        root.left=new TreeNodeFlatten(2);
        root.right=new TreeNodeFlatten(5);
        root.left.left=new TreeNodeFlatten(3);
        root.left.right=new TreeNodeFlatten(4);
        root.right.right=new TreeNodeFlatten(6);

        new FlattenBTToLinkedList().flatten(root);
    }//main
}//FlattenBTToLinkedList
