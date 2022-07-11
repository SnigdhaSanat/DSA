package Backtracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Combinations {
    /*Strategy:
Entry point: Iterate for i=1 to n. Push into stack.
Level wise entry: iterate for currentRoot+1 to n. Push into stack.
Level wise exit: pop from stack
Base condition: if level==B. Take a snap shot of stack, push into the res. Then pop the stack and return
*/
    class Utility{
        Stack<Integer> stk;
        ArrayList<ArrayList<Integer>> res;
    }//Utility

    public int[][] combine(int n, int B) {
        if(n==B){
            int[][] A=new int[1][n];
            int[] temp=new int[n];
            for(int i=0;i<n;i++){
                temp[i]=i+1;
            }
            A[0]=temp;
            return A;
        }

        Utility util=new Utility();
        util.stk=new Stack<Integer>();
        util.res= new ArrayList<ArrayList<Integer>>();

        //entry point
        for(int i=1;i<=n;i++){
            util.stk.push(i);
            recurse(i,1,n,B,util);
        }//for

        //after recursion
        int len=util.res.size();
        int result[][]=new int[len][B];

        //convert al into result[][]
        for(int i=0;i<len;i++){
            ArrayList<Integer> alRow=util.res.get(i);
            int[] row=new int[B];
            for(int j=0;j<B;j++){
                row[j]=alRow.get(j);
            }//inner for
            result[i]=row;
        }//outer for

        return result;
    }//combine

    void recurse(int currentRoot, int level,int n, int B,Utility util){
        if(level==B){
            //base condition

            //take a snap shot of the Stack
            ArrayList<Integer> temp=new ArrayList<Integer>();
            Iterator<Integer> itr=util.stk.iterator();
            while(itr.hasNext()){
                temp.add(itr.next());
            }//while
            util.res.add(temp);

            //then pop from the stack
            util.stk.pop();
            return;
        }//base condition

        for(int i=currentRoot+1;i<=n;i++){
            //push into the stack
            util.stk.push(i);
            recurse(i,level+1,n,B,util);
//complete the lateral recursion
        }//for

        //backtrack
        util.stk.pop();
        return;
    }//recurse

    public static void main(String[] args) {
        int n=4;
        int k=2;
        int [][] res=new Combinations().combine(n,k);
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
}//Combinations
