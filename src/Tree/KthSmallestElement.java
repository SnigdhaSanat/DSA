package Tree;

public class KthSmallestElement {
// Definition for binary tree

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) {
   val = x;
   left=null;
   right=null;
  }
}//TreeNode

class Counter{
    int count=0;
}

public int kthSmallest(TreeNode A, int B) {
    Counter counter=new Counter();
    counter.count=0;

    //check if root is null, return -1.
    if(A==null){
        return -1;
    }

    int res=checkKthSmallest( A,  B, counter);
    return res;
}//kthSmallest

int checkKthSmallest(TreeNode root, int k,Counter counter){
    //Traverse inorder
    int leftVal=-1;
    if(root.left!=null){
        leftVal=checkKthSmallest(root.left,k,counter);
    }

    if(leftVal!=-1){
        //if left hit exactly k, else -1 will be returned, which will be propagated up the tree
        return leftVal;
    }

    counter.count+=1;
    if(counter.count==k){
        //this will happen only if counter at root hits exactly k, else -1
        //It returns its own value
        return root.val;
    }

    int rightVal=-1;
    if(root.right!=null){
        rightVal=checkKthSmallest(root.right,k,counter);
    }
    if(rightVal!=-1){
        //if right hit exactly k, else -1 will be returned, which will be propagated up the tree
        return rightVal;
    }

    //if k is not hit
    return -1;
}//checkKthSmallest

TreeNode insert(TreeNode root, int x){
if(root==null){
    root=new TreeNode(x);
}

//comparing at each of the recursion levels
else if (x<root.val){
//when returned, this will be appended at left
    root.left=insert(root.left,x);
}
//when returned, this will be appended at right
else if (x>root.val){
    root.right=insert(root.right,x);
}

//returning root, at each of the recursion level
return root;
}//insert


public static void main(String[] args){
int[] val=new int[]{2,1,3,};
TreeNode root=null;

for(int i=0;i<val.length;i++){
         root=new KthSmallestElement().insert(root, val[i]);
    }//for

int k=2;
int res=new KthSmallestElement().kthSmallest(root,k);

    System.out.println(res);
}//main

}//KthSmallestElement
