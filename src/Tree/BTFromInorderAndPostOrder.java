package Tree;

import java.util.ArrayList;
import java.util.HashMap;

class TreeNodeFromInAndPost {
    int val;
    TreeNodeFromInAndPost left;
    TreeNodeFromInAndPost right;

    TreeNodeFromInAndPost(int x) {
        val = x;
        left = null;
        right = null;
    }//TreeNodeFromInAndPost
}//TreeNodeFromInAndPost

public class BTFromInorderAndPostOrder {
/**This is almost like the "SortedArrayToBalancedBST" question, where you create a balanced BST out of the
given inorder traversal of BST. Except that here it need not be balanced, and root is directed by the given pre order.

Note that there is a POST(not pre) order list and an inorder list. How do you find the root element? The post order list has roots
of the subsequent recursions in REVERSE order. IF YOU TRAVERSE THE IN ORDER IN NODE RIGHT LEFT ORDER(because that is what a post
order is). Use this fact:

As the nodes are unique according to the condition, so create a hashmap, with ELEMENTS of the INORDER list as hm's INDEX, and its indices as values.
THEN Maintain a universal counter, and DECREMENT it in every recursion. Its current value will always point to the current root in the post order list.
With the help of the hashmap, get its index in the inorder list. So you have the index in the INORDER list at every recursion.

 Now, split it into left half and right half, leaving out the index in the middle. Those will be the left and right subtrees.
 Recursively call them, and assign them to (root.RIGHT) and  THEN (root.LEFT). Return root. Do this recursively, meaning within each subtree,
 find the root with the help of the process explained above, assign it to the root, and then call the RIGHT AND THEN LEFT halves of
 arrayList and assign them to its right and left. Return root from each level.

 To understand why we proceed in decreasing order, AND right to left, draw out a tree and do a dry run*/

class Utility{
    HashMap<Integer,Integer> hm;
    int counter;
}

    public TreeNodeFromInAndPost buildTree(ArrayList<Integer> inList, ArrayList<Integer> postList) {
        int len=postList.size();//or inList's size

        Utility utility=new Utility();
        utility.hm=new  HashMap<Integer,Integer>();
        utility.counter=len-1;

        //as the question states that the nodes are unique, store the keys as nodes, and their positions
        //in the INORDER list as values
        for(int i=0;i<len;i++){
            utility.hm.put(inList.get(i),i);
        }

        TreeNodeFromInAndPost resultRoot=recursivelyBuildTree(postList,inList,0,len-1,utility);//pass the index, not size

        return resultRoot;
    }//buildTree

    TreeNodeFromInAndPost recursivelyBuildTree(ArrayList<Integer> postList, ArrayList<Integer> inList, int start, int end, Utility utility){
        if(start==end){
            int rootVal=postList.get(utility.counter);//value of root, which inList[rootIndex]

            //get the index of rootValue in inorder
            TreeNodeFromInAndPost root=new TreeNodeFromInAndPost(rootVal);
            utility.counter-=1;

            return root;
        }

        int rootVal=postList.get(utility.counter);
        TreeNodeFromInAndPost root=new TreeNodeFromInAndPost(rootVal);
        utility.counter-=1;

        //get the index of rootValue in inorder
        int currRootIndex=utility.hm.get(rootVal);

        //Note that right is called first
        if(currRootIndex!=end){
            root.right=recursivelyBuildTree(postList,inList,currRootIndex+1,end,utility);
        }
        else{
            root.right=null;
        }

        if(currRootIndex!=start){
            root.left=recursivelyBuildTree(postList,inList,start,currRootIndex-1,utility);
        }
        else{
            root.left=null;
        }

        return  root;
    }//recursivelyBuild

    public static void main(String[] args) {
        ArrayList<Integer> alPost=new ArrayList<Integer>();

        alPost.add(10);
        alPost.add(40);
        alPost.add(30);

//        alPost.add(10);
//        alPost.add(40);
//        alPost.add(30);
//        alPost.add(60);
//        alPost.add(80);
//        alPost.add(50);

        ArrayList<Integer> alIn=new ArrayList<Integer>();

        alIn.add(10);
        alIn.add(30);
        alIn.add(40);

//        alIn.add(10);
//        alIn.add(30);
//        alIn.add(40);
//        alIn.add(50);
//        alIn.add(60);
//        alIn.add(80);

        new BTFromInorderAndPostOrder().buildTree(alPost,alIn);

    }//main
}//BTFromInorderAndPostOrder
