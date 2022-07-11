package Tree;

import java.util.ArrayList;

public class TwoSumBinaryTree {
class TreeNode {
    int val;
    TwoSumBinaryTree.TreeNode left;
    TwoSumBinaryTree.TreeNode right;
    TreeNode(int x) {
        val = x;
        left=null;
        right=null;
    }
}//TreeNode

class List{
        ArrayList<Integer> al;
    }

public int t2Sum(TreeNode A, int B) {
    //Traverse the array inorder, and make a list of it. Then compare using th e2 pointer approach
    List list=new List();
    list.al= new ArrayList<Integer>();

    //check if root is null, return -1.
    if(A==null){
        return 0;
    }

    checkSum( A,  B, list);
    int len=list.al.size();
    int[] arr=new int[len];

    //copying al to arr
    for(int i=0;i<len;i++){
        arr[i]=list.al.get(i);
    }

    int i=0;
    int j=len-1;

  int res=0;
    while(j>i){
        if(arr[i]+arr[j]<B){
            i+=1;
        }
        else if(arr[i]+arr[j]>B){
            j-=1;
        }

        else{
            res=1;
            break;
        }
    }//while

    return res;
    }//t2Sum

void checkSum(TwoSumBinaryTree.TreeNode root, int k, List list){
        //Traverse inorder and add the values inorder to the list
        if(root.left!=null){
            checkSum(root.left,k,list);
        }

        list.al.add(root.val);

        if(root.right!=null){
           checkSum(root.right,k,list);
        }

        return;

    }//checkSum

TwoSumBinaryTree.TreeNode insert(TwoSumBinaryTree.TreeNode root, int x){
    if(root==null){
        root=new TwoSumBinaryTree.TreeNode(x);
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
        int[] val=new int[]{10,9,20};
        TwoSumBinaryTree.TreeNode root=null;

        for(int i=0;i<val.length;i++){
            root=new TwoSumBinaryTree().insert(root, val[i]);
        }//for
    int B=40;
    int res=new TwoSumBinaryTree().t2Sum(root, B);
    System.out.println(res);

    }//main
}//TwoSumBinaryTree
