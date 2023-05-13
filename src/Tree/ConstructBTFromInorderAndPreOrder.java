package Tree;

import java.util.ArrayList;
import java.util.HashMap;

class TreeNodeFromInAndPre {
    int val;
    TreeNodeFromInAndPre left;
    TreeNodeFromInAndPre right;

    TreeNodeFromInAndPre(int x) {
        val = x;
        left = null;
        right = null;
    }//TreeNodeFromInAndPre
}


public class ConstructBTFromInorderAndPreOrder {
/** This is almost like the "SortedArrayToBalancedBST" question, where you create a balanced BST out of the
given inorder traversal of BST. Except that here it need not be balanced, and root is directed by the given pre order.

Note that there is a pre order list and an inorder list. How do you find the root element? The pre order list has roots
of the subsequent recursions in order(because that is what a preorder is). Use this fact:

As the nodes are unique according to the condition, so create a hashmap, with ELEMENTS of the INORDER list as hm's INDEX, and its indices as values.
Then maintain a universal counter, and increment it in every recursion. Its current value will always point to the current root in the PRE order list.
With the help of the hashmap, get its index in the inorder list. So you have the index in the INORDER list at every recursion.

 Now, split it into left half and right half, leaving out the index in the middle. Those will be the left and right subtrees.
 Recursively call them, and assign them to (root.left) and (root.right). Return root. Do this recursively, meaning within each subtree,
 find the root with the help of the process explained above, assign it to the root, and then call the left and right halves of
 arrayList and assign them to its left and right. Return root from each level*/

    class Utility{
        HashMap<Integer,Integer> hm;
        int counter;
    }

    public TreeNodeFromInAndPre buildTree(ArrayList<Integer> preList, ArrayList<Integer> inList) {
        int len=preList.size();//or inList's size

        Utility utility=new Utility();
        utility.hm=new  HashMap<Integer,Integer>();
        utility.counter=0;

        //as the question states that the nodes are unique, store the keys as nodes, and their positions
        //in the INORDER list as values
        for(int i=0;i<len;i++){
            utility.hm.put(inList.get(i),i);
        }

        TreeNodeFromInAndPre resultRoot=recursivelyBuildTree(preList,inList,0,len-1,utility);//pass the index, not size

        return resultRoot;
    }//buildTree

    TreeNodeFromInAndPre recursivelyBuildTree(ArrayList<Integer> preList,ArrayList<Integer> inList,int start,int end,Utility utility){
        if(start==end){
            int rootVal=preList.get(utility.counter);
            utility.counter+=1;

            //get the index of rootValue in inorder
            TreeNodeFromInAndPre root=new TreeNodeFromInAndPre(rootVal);
            return root;
        }

        int rootVal=preList.get(utility.counter);
        utility.counter+=1;

        //get the index of rootValue in inorder
        TreeNodeFromInAndPre root=new TreeNodeFromInAndPre(rootVal);


        //now split it
        int currRootIndex=utility.hm.get(rootVal);
        if(currRootIndex!=start){
            root.left=recursivelyBuildTree(preList,inList,start,currRootIndex-1,utility);
        }
        else{
            root.left=null;
        }


        if(currRootIndex!=end){
            root.right=recursivelyBuildTree(preList,inList,currRootIndex+1,end,utility);
        }
        else{
            root.right=null;
        }

        return  root;
    }//recursivelyBuild


    public static void main(String[] args) {
        ArrayList<Integer> alPre=new ArrayList<Integer>();
        alPre.add(50);
        alPre.add(30);
        alPre.add(10);
        alPre.add(40);
        alPre.add(80);
        alPre.add(60);

        ArrayList<Integer> alIn=new ArrayList<Integer>();
        alIn.add(10);
        alIn.add(30);
        alIn.add(40);
        alIn.add(50);
        alIn.add(60);
        alIn.add(80);

        new ConstructBTFromInorderAndPreOrder().buildTree(alPre,alIn);

    }//main
}//ConstructBTFromInorderAndPreOrder
