package StacksAndQueues;

import java.util.Stack;

public class EvaluateExpression {
    public int evalRPN(String[] A) {
        int n=A.length;
        Stack<Integer> stk=new Stack<Integer>();

        for(int i=0;i<n;i++){

            String s=A[i];
            if(s.contentEquals("+") || s.contentEquals("-") || s.contentEquals("*") || s.contentEquals("/")){
                //if it is a operator, pop the first 2 elements. Be mindful of the order of the first and second operand.
                //Then evaluate the expression and push it back to the stack
                int second=stk.pop();
                int first=stk.pop();

                int val=0;
                switch(s){
                    case "+":
                        val=first+second;
                        break;

                    case "-":
                        val=first-second;
                        break;

                    case "*":
                        val=first*second;
                        break;

                    case "/":
                        val=first/second;
                        break;

                    default:
                        val=0;
                }//switch
                stk.push(val);
            }
            else{
                //if operand, simply push into the stack
                stk.push(Integer.parseInt(s));
            }
        }//for

        /*Once the last expression is evaluated and pushed into the stack, that is the result. So pop it*/
        return stk.pop();
    }//evalRPN

    public static void main(String[] args) {
        String[] A=new String[]{"4", "13", "5", "/", "+"};

        int res=new EvaluateExpression().evalRPN(A);
        System.out.println(res);
    }
}//EvaluateExpression
