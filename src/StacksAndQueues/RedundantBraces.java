package StacksAndQueues;

import java.util.Stack;

public class RedundantBraces {
    public int braces(String A) {

        int len=A.length();
        Stack<Character> stk=new Stack<Character>();
        for(int i=0;i<len;i++){
            char ch=A.charAt(i);
            if(ch==')'){
                //pop out till '(' is met
                int count=0;
                while(stk.peek()!='('){
                    count+=1;
                    stk.pop();
                }//while

                if(count==0 || count==1){
                    //0: this is the case where there is no char between '(' and ')'
                    //1: this is the case for single char expressions like (a).
                    //This is a case of redundant brackets
                    return 1;
                }

                //pop 1 more time for '('
                stk.pop();
            }
            else{
                //else simply push the char
                stk.push(ch);
            }
        }//for
        return 0;
    }//braces

    public static void main(String[] args) {
        String A="(a+(a+b))";
        int res=new RedundantBraces().braces(A);
        System.out.println(res);
    }
}//RedundantBraces
