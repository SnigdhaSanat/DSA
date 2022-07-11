package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class SubsetsII {
    class Utility{
        Stack<Integer> stk;//The kind of DS you take here is important
        ArrayList<ArrayList<Integer>> res;
    }//class

    public int[][] subsetsWithDup(int[] A) {
    /*This is similar to the usual subset generation. But since it is a case of duplicates, you need to take care of 2 things:

    First: while LATERALLY
    iterating, if the current root element is DIFFERENT than the iterated element and the iterated element is non-first of a duplicate series, skip the
    iteration. This is because the subtree resulting from this element was already covered by the first element of the duplicate series.

    If the current root element is SAME as the iterated element and the iterated element is non-first of a duplicate series, then DON'T skip
    only if it is the immediate next duplicate. Eg: 1,3,3,3,5. So for the first 3 as the root element, iterate only for the 2nd 3. Skip for the rest 3s.
    as that will lead to duplication in the set. For the entry point though, it is considered as the case where current root element is DIFFERENT than the
    iterated element

    Second: During recursion, consider if it is greater than the current root, OR if they are duplicates, if the number comes AFTER the current root.  */

        int n=A.length;
        //sort the list first
        if(n==0){
            int[][] emptyRes=new int[][]{{}};
            return emptyRes;
        }
        //sort the array
        Arrays.sort(A);

        //generating dup array
        int[] dup=new int[n];
        dup[0]=1;
        int count=1;
        for(int i=1;i<n;i++){
            if(A[i]==A[i-1]){
                //if duplicate
                count+=1;
                dup[i]=count;
            }//if
            else{
                count=1;
                dup[i]=count;
            }//else
        }//for
//-------------------------------------
        Utility util=new Utility();
        util.stk=new Stack<Integer>();
        util.res=new ArrayList<ArrayList<Integer>>();

        //entry point of recursion
        for(int i=0;i<n;i++){
            if(dup[i]>1){
                //for lateral iteration, consider only distinct elements. If non distinct, skip. This is due to consideration #1
                continue;
            }
            else{
                util.stk.push(A[i]);
                //IMP: take a snapshot of stk, into a NEW one, else res points(refers) to stk, and updates the previous rows during subsequent iterations
                ArrayList<Integer>tempAl=new ArrayList<Integer>();
                Iterator<Integer> itr=util.stk.iterator() ;
                while(itr.hasNext()) {
                    tempAl.add(itr.next());
                }
                util.res.add(tempAl);
                recurse(A, dup, A[i],dup[i],n,util);
            }//else

        }//for

        //after recursion
        //Convert ArrayList into int[][]

        int row=util.res.size();
        int [][] result=new int[row+1][];

        //adding the empty Set to the result as the first element
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
    }//subsetsWithDup

    void recurse(int[] A, int[] dup,int currRoot, int currRootDupVal, int n, Utility util){

        if(n==1){
            //base condition
            util.stk.pop();
            return;
        }//if

        //lateral iteration, and conditional entry to next level

        for(int i=0;i<A.length;i++){

            int element=A[i];
            if((currRoot!=element && dup[i]>1 ) ||(currRoot==element &&(currRootDupVal+1)!=dup[i])){
                //for lateral iteration, consider only distinct elements. If non distinct, skip
                continue;
            }
            else{
                //condition for recursion
                if(element>currRoot ||(element==currRoot && dup[i]>currRootDupVal))
                {//if condition to take care of non descending condition
                    util.stk.add(element);
                    //take a snapshot of stk, into a NEW(else res points to stk, and updates the previous row) one, and add it to res
                    ArrayList<Integer>tempAl=new ArrayList<Integer>();
                    Iterator<Integer> itr=util.stk.iterator() ;
                    while(itr.hasNext()) {
                        tempAl.add(itr.next());
                    }
                    util.res.add(tempAl);
                    recurse(A,dup,element,dup[i],n-1,util);

                    //after return from recursion, continue with next lateral recursion
                }//if
            }//else

        }//for

        //backtrack from this level
        util.stk.pop();
        return;

    }//recurse

    public static void main(String[] args) {
        int[] A=new int[]{1,2,2};
        int[][] res=new SubsetsII().subsetsWithDup(A);
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
}//SubsetsII
