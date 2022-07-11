package Backtracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class PalindromePartitioning {
    /*Strategy:For the given start to end, partition for (start,start), (start,start+1)...(start,end). Eg: for "aaabd",
partition for "a","aa","aaa","aaab","aaabd". For each of these lateral iterations, find the remaining. That is, for
the above 5, the corresponding ones will be "aabd", "abd", "bd", "d", "".  If the first part is a palindrome, push into stack.
For the second part, if it is non "", recurse. If empty "", it is a base condition
Entry point:
Each entry:

*/
    class Utility{
        Stack<String> stk;
        ArrayList<ArrayList<String>> res;
    }

    public ArrayList<ArrayList<String>> partition(String A) {
        Utility util=new Utility();
        util.stk=new Stack<String>();
        util.res=new ArrayList<ArrayList<String>> ();

        int n=A.length();

//entry point
        int start=0;
        int end=n-1;
        for(int currentEnd=start; currentEnd<=end;currentEnd++){
            String s=A.substring(start, currentEnd+1);
            if(isPalin(s)){
                util.stk.push(s);
                if(currentEnd<n-1){
                    String secondPart=A.substring(currentEnd+1,end+1);
                    recurse(A,n, secondPart,currentEnd+1, end,util);
                }
                else{
                    String secondPart="";
                    recurse(A,n, secondPart,currentEnd+1, end,util);
                }
            }//if
        }//for

        return util.res;

    }//partition

    void recurse(String A,int n,String str,int start, int end, Utility util){
        if(str.contentEquals("")){
            //base condition. If it wasn't a palindrome, isPalin(s) would have filtered it
            //take a snapshot
            Iterator itr=util.stk.iterator();

            ArrayList<String> temp=new ArrayList<String>();
            while(itr.hasNext()){
                temp.add(itr.next().toString());
            }//while

            util.res.add(temp);


            util.stk.pop();
            return;
        }//base condition

        for(int currentEnd=start; currentEnd<=end;currentEnd++){
            String s=A.substring(start, currentEnd+1);

            if(isPalin(s) ){
                util.stk.push(s);
                if(currentEnd<n-1){
                    String secondPart=A.substring(currentEnd+1,n);
                    recurse(A,n, secondPart,currentEnd+1, n-1,util);
                }
                else{
                    String secondPart="";
                    recurse(A,n, secondPart,currentEnd+1, end,util);
                }

            }//if palin

            //continue with next lateral iteration
        }//for

        util.stk.pop();
        return;

    }//recurse

    boolean isPalin(String s){
        boolean res=true;
        int n=s.length();
        int i=0;
        int j=n-1;
        while(j>=i){
            if(s.charAt(i)==s.charAt(j)){
                i+=1;
                j-=1;
            }//if
            else{
                res=false;
                break;
            }
        }//while

        return res;
    }

    public static void main(String[] args) {
        String A="aab";
        ArrayList<ArrayList<String>> res=new PalindromePartitioning().partition(A);
        for(int i=0;i<res.size();i++){
            ArrayList<String> row=res.get(i);
            for(int j=0;j<row.size();j++){
                System.out.print(row.get(j)+" ");
            }
            System.out.println();
        }
    }
}//PalindromePartitioning
