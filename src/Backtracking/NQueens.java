package Backtracking;

import java.util.*;

public class NQueens {
    class Utility{
        Stack<Integer> stk;
        Set<Integer> hs;
        Set<Integer> rightDiag;
        Set<Integer> leftDiag;
        ArrayList<ArrayList<Integer>> res;
    }
    /*Strategy: While recursion for every row, push not just the column, but also the leftDiag and rightDiag value. The SUM
    of row and col along a right diagonal stays const, while the DIFF of row and col along a left diagonal stays constant. Push
    only if the column is not present in Set hs, AND the sum is not present in set rightDiag, AND diff is not present in
    set leftDiag. Base condition is when level=num*/
    public ArrayList<ArrayList<String>> solveNQueens(int n) {
        int level=1;
        Utility util=new Utility();
        util.stk=new Stack<Integer>();
        util.hs=new HashSet<Integer>();
        util.rightDiag=new HashSet<Integer>();
        util.leftDiag=new HashSet<Integer>();
        util.res= new ArrayList<ArrayList<Integer>>();

        //entry point
        for(int i=1;i<=n;i++){
            if(!util.hs.contains(i)){
                //if the DFS trail does not contain i
                util.hs.add(i);
                util.stk.push(i);
                int row=1;
                int column=i;
                int rd=row+column;//sum
                int ld=(row-column);//diff
                util.rightDiag.add(rd);
                util.leftDiag.add(ld);
                recurse(level,i, n,util);
            }//if
        }//for

        //after recursion
        //converting int to string
        ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();

        int len=util.res.size();

        for(int i=len-1;i>=0;i--){
            ArrayList<Integer> intRow= util.res.get(i);
            ArrayList<String> strRow= new ArrayList<String>();
            for(int j=0;j<n;j++){
                int val= intRow.get(j);
                String strVal="";
                for (int k=1;k<=n;k++){
                    if(k==val){
                        strVal+="Q";
                    }
                    else{
                        strVal+=".";
                    }
                }//inner inner for
                strRow.add(strVal);
            }//inner for
            result.add(strRow);
        }//outer

        return result;
    }//solveNQueens

    void recurse(int level,int currRoot, int num,Utility util){

        int rootRow=level;
        int rootColumn=currRoot;
        int rootRd=rootRow+rootColumn;//sum
        int rootLd=rootRow-rootColumn;//diff

        if(level==num){
            //base condition
            //get a snapshot of the stack
            ArrayList<Integer> temp=new ArrayList<Integer>();
            Iterator<Integer> itr=util.stk.iterator();
            while(itr.hasNext()){
                temp.add(itr.next());
            }//while

            util.res.add(temp);

            //pop
            util.stk.pop();
            util.hs.remove(currRoot);
            util.rightDiag.remove(rootRd);
            util.leftDiag.remove(rootLd);
            return;

        }//base condition

        for(int i=1;i<=num;i++){
            int row=level+1;
            int column=i;
            int rd=row+column;//sum
            int ld=row-column;//diff
            if(!util.hs.contains(i) && !util.rightDiag.contains(rd) && !util.leftDiag.contains(ld)){
                //if the DFS trail does not contain i AND i is not +1 or -1 to num
                util.hs.add(i);
                util.stk.push(i);
                util.rightDiag.add(rd);
                util.leftDiag.add(ld);
                recurse(level+1,i, num,util);
            }//if
        }//for

        //backtrack
        util.stk.pop();
        util.hs.remove(currRoot);
        util.rightDiag.remove(rootRd);
        util.leftDiag.remove(rootLd);
        return;
    }//recurse

    public static void main(String[] args) {
        int n=4;
        ArrayList<ArrayList<String>> res=new NQueens().solveNQueens(n);
        for(int i=0;i<res.size();i++){
            ArrayList<String> row=res.get(i);
            for(int j=0;j<row.size();j++){
                System.out.print(row.get(j)+" |");
            }
            System.out.println();
        }
    }//main
}//NQueens
