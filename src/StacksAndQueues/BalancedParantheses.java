package StacksAndQueues;

import java.util.Stack;

public class BalancedParantheses {
    public int solve(String A) {
/* If (, push it into the stack. If ), it DOES NOT go into the stack. Pop the top ( from the stack to pair with it.
If there is no ( for pairing up, meaning the stack is empty, return 0, as it is a case of excess ). Finally, at the
end of the string, if the stack is not empty, it is a case of excess (. Return 0 in this case as well. If the stack is empty
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
                    return 0;
                }
                s.pop();
            }
        }//for

        if(s.empty()){
            return 1;
        }
        return 0; //case of excess '('
    }//solve

    public static void main(String[] args) {
        String A = "(()())";
        int res=new BalancedParantheses().solve(A);
        System.out.println(res);
    }//main
}//BalancedParantheses
