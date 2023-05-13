package Tree;

import java.util.ArrayList;
import java.util.Collections;

class TreeNodeDiagonal {
    int val;
    TreeNodeDiagonal left;
    TreeNodeDiagonal right;
    TreeNodeDiagonal(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeDiagonal

public class DiagonalTraversal {

class NodeDiag implements Comparable<NodeDiag>{
int val;
int diagLevel;
int index;
    NodeDiag(int v, int l, int idx){
    val=v;
    diagLevel=l;
    index=idx;
}//ctor

@Override
public int compareTo(NodeDiag n) {
    if(this.diagLevel>n.diagLevel){
        //ascending order of diagonal level
        return 1;
    }
    else if(this.diagLevel<n.diagLevel){
        //ascending order of diagonal level
        return -1;
    }
    else //if(this.diagLevel==n.diagLevel )
    {
        //if diagLevel level is same, preserve the original index order
        if(this.index>n.index){
            return 1;
        }
        else if(this.index<n.index){
            return -1;
        }
        return 0;
    }
}
}//NodeDiag

class ListClassDiag {
    ArrayList<NodeDiag> al;
    int index;
}//ListClassDiag

    //Question requirement: within the same diagonal, preserve the pre-order

public ArrayList<Integer> solve(TreeNodeDiagonal A) {
    /**Recursively traverse in pre order . For the left child, the diagonal value will be +1 of that of root. For
    * right child it will be the same. Then sort according to diagonal value, and with indices within them*/
    ListClassDiag listClassDiag=new ListClassDiag();
    listClassDiag.al=new ArrayList<NodeDiag>();
    listClassDiag.index=-1;

    //starting the recursion
    recurseDiagonal(A,0,listClassDiag);

    //after recursion
    Collections.sort(listClassDiag.al);

    ArrayList<Integer> res=new ArrayList<Integer>();
    for(int i=0;i<listClassDiag.al.size();i++){
        res.add(listClassDiag.al.get(i).val);
        //System.out.println(listClassDiag.al.get(i).val);
    }
    return  res;

}//solve

void recurseDiagonal(TreeNodeDiagonal root, int diagLevel, ListClassDiag listClassDiag) {
    /*For left child, increase the diagLevel by 1, Keep it same for right child*/

    //put the root into the list first, as it is pre-order
    listClassDiag.index += 1;
    listClassDiag.al.add(new NodeDiag(root.val, diagLevel, listClassDiag.index));

    //recurse left
    if (root.left != null) {
        recurseDiagonal(root.left, diagLevel + 1, listClassDiag);
    }

    //recurse right
    if (root.right != null) {
        recurseDiagonal(root.right, diagLevel, listClassDiag);
    }
}//recurseDiagonal

public static void main(String[] args) {
    TreeNodeDiagonal root=new TreeNodeDiagonal(1);
    root.left = new TreeNodeDiagonal(4);
    root.right = new TreeNodeDiagonal(2);
    root.left.left=new TreeNodeDiagonal(8);
    root.left.right= new TreeNodeDiagonal(5);
    root.right.right= new TreeNodeDiagonal(3);
    root.left.right.left= new TreeNodeDiagonal(9);
    root.left.right.right= new TreeNodeDiagonal(7);
    root.right.right.left= new TreeNodeDiagonal(6);

    new DiagonalTraversal().solve(root);
}//main
}//DiagonalTraversal
