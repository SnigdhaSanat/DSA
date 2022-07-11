package Backtracking;

import java.util.*;

public class Permutations {
    /*Strategy: Recurse for every nConst digits. At every level, check if the nextDigit is contained in the set. If not,
    add it to the stack and the set, and recurse. Also decrement n by 1. Base condition: if n==1. At that point, take
    the whole set, add it to the result[][], and then pop it from the stack and set. We take a stack because of recursion
    sequence. And at every return point(backtrack), we pop the set and the stack.

    Basically in recursion, or specifically in backtracking, we take care of 4 things: starting of recursion, entry to
    every recursion level, exit from every level, and base condition. Since we often  test(NOT traverse) breadthwise
    to check whether or not to recurse, we need to take care to not confuse that with the return condition, which is done
    ONLY AFTER  we breadth First TEST(and maybe recurse) all those options*/

    class Result{
        Stack<Integer> stk=new Stack<Integer>();
        Set<Integer> hs = new HashSet<Integer>();
        ArrayList<ArrayList<Integer>> res= new ArrayList<ArrayList<Integer>> ();
    }

    public int[][] permute(int[] A) {
        int n=A.length;
        Result result=new Result();

        for(int i=0;i<n;i++){
            int nextDigit=A[i];//since this is the first level, we don't need an if condition

            result.hs.add(nextDigit);
            result.stk.push(nextDigit);
            recurse(A, nextDigit,n,n,result.stk,result.hs,result.res );
        }//for

        //after recursion is done
        int sze=result.res.size();
        int [][] resArr=new int[sze][n];
        for(int i=0;i<sze;i++){
            for(int j=0;j<n;j++){
                resArr[i][j]=result.res.get(i).get(j);
            }//for
        }//for
        return resArr;
    }//permute

    void recurse(int[] A,int digit,int nConst, int n,Stack<Integer> stk, Set<Integer> hs,ArrayList<ArrayList<Integer>>res){

        if(n==1){
            //base condition
            ArrayList<Integer> al=new ArrayList<Integer>();

            Iterator<Integer> itr = stk.iterator();
            //add the WHOLE SET
            while (itr.hasNext())
            {//this adds first to last, not last to first, even if it is a stack. We traverse here, not pop
                al.add(itr.next());
            }//while

            res.add(al);

            //pop only one level. Rest will be taken care of by respective levels
            int test=stk.pop();
            hs.remove(digit);

            return;
        }

        for(int i=0;i<nConst;i++){
            int nextDigit=A[i];

            if(!hs.contains(nextDigit)){
                hs.add(nextDigit);
                stk.push(nextDigit);

                recurse(A, nextDigit, nConst,n-1,stk,hs,res);
                //if i is between 0 and n, we DO NOT backtrack here. We go to the other lateral options, and only once we
                //are done with ALL of them, we backtrack.

                //Test line: This is where it arrives after recursion from previous level
            }//if

        }//for

        //backtrack
        stk.pop();
        hs.remove(digit);
        return;
    }//recurse

    public static void main(String[] args) {

        int[] A=new int[]{1,2,3};
        int[][] res=new Permutations().permute(A);

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
}//Permutations
