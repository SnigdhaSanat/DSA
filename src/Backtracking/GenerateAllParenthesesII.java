package Backtracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class GenerateAllParenthesesII {
    class Utility{
        Stack<String> stk;
        ArrayList<String> res;
    }
    //Question requirement: Make sure the returned list of strings are sorted.
    /*Strategy: At every level, generate ( and ) laterally. Recurse for each of them. Base condition is when level=2n.*/
    public ArrayList<String> generateParenthesis(int n) {
        Utility util=new Utility();
        util.stk=new Stack<String>();
        util.res=new ArrayList<String>();

        int levelTotal=2*n;
        int level=1;

        //entry point
        for(int i=0;i<2;i++){
            String s="";
            if(i==0){
                s="(";
            }
            else{
                s=")";
            }
            util.stk.push(s);
            recurse(level,s,levelTotal,util);
        }

        //after recursion
        return util.res;
    }//generateParenthesis

    void recurse(int level,String s, int levelTotal,Utility util){
        if(level==levelTotal){
            //base condition

            //check if balanced
            ArrayList<String> temp=new ArrayList<String>();
            String str="";
            Iterator<String> itr=util.stk.iterator();
            while(itr.hasNext()){
                String strTemp=itr.next();
                temp.add(strTemp);
                str+=strTemp;
            }//while
            boolean isBalanced=isBalancedParan(str);

            if(isBalanced){
                util.res.add(str);
            }

            //pop
            util.stk.pop();
            return;


        }//base condition

        for(int i=0;i<2;i++){
            String st="";
            if(i==0){
                st="(";
            }
            else{
                st=")";
            }
            util.stk.push(st);
            recurse(level+1,st,levelTotal,util);
        }//lateral iteration

        util.stk.pop();
        return;

    }//recurse

    public boolean isBalancedParan(String A) {
/* If (, push it into the stack. If ), it DOES NOT go into the stack. Pop the top ( from the stack to pair with it.
If there is no ( for pairing up, meaning the stack is empty, return 0, as it is a case of excess ). Finally, at the
end of the string, if the stack is not empty, itis a case of excess (. Return 0 in this case as well. If the stack is empty
at the end of the string, return 1.*/

        Stack<String> s=new Stack<String>();

        int len=A.length();
        for(int i=0;i<len;i++){
            char ch=A.charAt(i);
            if(ch=='('){
                s.push(Character.toString(ch));
            }
            else{
                if(s.empty()){
                    //case of excess ')'
                    return false;
                }
                s.pop();
            }
        }//for

        if(s.empty()){
            return true;
        }
        return false; //case of excess '('
    }//isbalancedParan

    public static void main(String[] args) {
        int n=3;
        ArrayList<String> res=new GenerateAllParenthesesII().generateParenthesis(n);
        for(int i=0;i<res.size();i++){
            System.out.println(res.get(i));
        }
    }
}//GenerateAllParenthesesII
