package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class Subset {
    class Utility{
        Stack<Integer> stk;//The kind of DS you take here is important
        ArrayList<ArrayList<Integer>> res;
    }//class

    public int[][] subsets(int[] A) {
    /*Strategy:
    Entry point: Iterate over all the A elements laterally, push them into the stack, and recurse over them.
    Every level entry: Same as entry point, except that, before recursion, check if the next root is greater than the
    current root, which is a condition of the question: "Elements in a subset must be in non-descending order". Also take
    a snapshot of the stack after pushing and before recursion, and add that to the result
    Every level backtrack: pop from the stack
    Base condition: if the level is A.length*/
        int n=A.length;
        //sort the list first
        Arrays.sort(A);

        Utility util=new Utility();
        util.stk=new Stack<Integer>();
        util.res=new ArrayList<ArrayList<Integer>>();

        //entry point of recursion
        for(int i=0;i<n;i++){
            util.stk.push(A[i]);

//IMP: take a snapshot of stk, into a NEW(else res points to stk, and updates the previous row) one, and add it to res
            ArrayList<Integer>tempAl=new ArrayList<Integer>();
            Iterator<Integer> itr=util.stk.iterator() ;
            while(itr.hasNext()) {
                tempAl.add(itr.next());
            }
            util.res.add(tempAl);
            recurse(A, A[i],n,util);
        }//for

        //after recursion
        //Convert ArrayList into int[][]

        int row=util.res.size();
        int [][] result=new int[row+1][];
        //adding the empty Set to the result
        result[0]=new int[]{};
        for(int i=0;i<row;i++){
            ArrayList<Integer> alRow=util.res.get(i);
            int column=alRow.size();
            int[] temp=new int[column];
            for(int j=0;j<column;j++){
                temp[j]=alRow.get(j);
            }//inner for
            //now add temp to the row
            result[i+1]=temp;
        }//outer for

        return result;
    }//subsets

    void recurse(int[] A, int currRoot, int n, Utility util){

        if(n==1){
            //base condition
            util.stk.pop();
            return;
        }//if

        //lateral iteration, and conditional entry to next level
        for(int i=0;i<A.length;i++){
            int element=A[i];

            if(element>currRoot){//if condition to take care of non descending condition
                util.stk.add(element);
                //take a snapshot of stk, into a NEW(else res points to stk, and updates the previous row) one, and add it to res
                ArrayList<Integer>tempAl=new ArrayList<Integer>();
                Iterator<Integer> itr=util.stk.iterator() ;
                while(itr.hasNext()) {
                    tempAl.add(itr.next());
                }
                util.res.add(tempAl);
                recurse(A,element,n-1,util);

                //after return from recursion, continue with next lateral recursion
            }//if
        }//for

        //backtrack from this level
        util.stk.pop();
        return;
    }//recurse

    public static void main(String[] args) {
        int[] a=new int[]{1,2,3};
        int[][] res=new Subset().subsets(a);
        for(int i=0;i<res.length;i++){
            int[] row=res[i];
            System.out.print("[");
            for(int j=0;j<row.length;j++){
                System.out.print(res[i][j]+" ");
            }
            System.out.print("]");
            System.out.println();
        }
    }
}//Subset
