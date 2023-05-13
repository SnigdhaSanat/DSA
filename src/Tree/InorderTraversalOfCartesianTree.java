package Tree;


import java.util.ArrayList;

class TreeNodeCartesian {
    int val;
    TreeNodeCartesian left;
    TreeNodeCartesian right;
    TreeNodeCartesian(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNodeCartesian


public class InorderTraversalOfCartesianTree {
    /** Input: Inorder of a HEAP, not BST, so it is not sorted. Required output: Heap
    Note that this is a heap tree. So find the biggest element and its index. That will be the root of the heap tree.
    * Now, split it into left half and right half, leaving out the index in the middle. Those will be the left and right subtrees.
    * Recursively call them, and assign them to (root.left) and (root.right). Return root. Do this recursively, meaning within each subtree,
    * find the biggest number, assign it to the root, and then call the left and right halves of arrayList and assign them to its left and right.
    * Return root from each level*/

    public TreeNodeCartesian buildTree(ArrayList<Integer> A) {
        int len=A.size();
        TreeNodeCartesian resultRoot=recursivelyBuild(A,0,len-1);//pass the index, not size
        return resultRoot;
    }//buildTree

    TreeNodeCartesian recursivelyBuild(ArrayList<Integer> A,int start,int end){
        if(A.size()==1){
            TreeNodeCartesian root=new TreeNodeCartesian(A.get(start));
            return root;
        }

        int biggestIndex=findBiggestIndex(A,start,end);
        TreeNodeCartesian root=new TreeNodeCartesian(A.get(biggestIndex));

        if(biggestIndex!=start){
            root.left=recursivelyBuild(A,start,biggestIndex-1);
        }
        else{
            root.left=null;
        }

        if(biggestIndex!=end){
            root.right=recursivelyBuild(A,biggestIndex+1,end);
        }
        else{
            root.right=null;
        }

        return root;
    }//recursivelyBuild


    int findBiggestIndex(ArrayList<Integer> subList, int start, int end){
        int biggest= Integer.MIN_VALUE;
        int index=-1;
        for(int i=start;i<=end;i++){
            if(subList.get(i)>biggest){
                biggest=subList.get(i);
                index=i;
            }//if
        }//for
        return  index;
    }//findBiggestIndex

    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<Integer>();
        al.add(15);
        al.add(20);
        al.add(17);
        al.add(30);
        al.add(7);
        al.add(10);
        al.add(5);
        al.add(3);
        new InorderTraversalOfCartesianTree().buildTree(al);
    }//main
}//InorderTraversalOfCartesianTree
