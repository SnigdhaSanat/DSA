package StacksAndQueues;

import java.util.Stack;

public  class MinStack {

    static class Util
    {
        static int[] stk;
        static Stack<Integer> minStack;
        int top;

    }

    //Requirement: Constant time
    /**Basically, minStack will be a stack tapering towards the top. A new element will be pushed there only if it
    is less than the minStack's top, or if minStack is empty. So, its top will always be the min element of the original
    stack. When popped, if the popped element of the original array is the min element, then it is popped from the minStack
    too. So min element is popped. Else, minStack is not popped.

    IMP: Take the case of 7,5,8,3,10. Min stack, from the bottom, will be: 7,5,3. Does not having 8 and 10 in the minStack
    makes a difference? No. Because at no point of time, they will be min elements, and hence they are not required in
    the minStack. After all, minStack is required only to accomplish getMin() in constant time. Rest of the operations are constant anyway.
    So alter the minStack conservatively, while the stk(original stack) is changed always*/

    public void push(int x, Util util) {
        util.stk[++util.top]=x;

        //if min stack is empty, or current x is less than minStack's top, push it
        if(util.minStack.isEmpty()||x<util.minStack.peek()){
            util.minStack.push(x);
        }//if

    }//push

    public void pop(Util util) {
        if(util.top!=-1){
            //pop only if non empty

            if(util.stk[util.top]==util.minStack.peek()){
                //pop minStack if the top of the original array(stack) is equal to minStack's top. This is the case
                //where the min is removed
                util.minStack.pop();
            }//inner if

            --util.top;
        }//outer if
    }//pop

    public int top(Util util) {
        if(util.top==-1){
            //return -1 if empty
            return -1;
        }
        //simply return the top of the original array
        return util.stk[util.top];
    }//top

    public int getMin(Util util) {
        if(util.top==-1){
            //return -1 if empty
            return -1;
        }
        //return minStack's top
        return util.minStack.peek();
    }//getMin

    public static void main(String[] args) {
        int res=-1;

        Util util=new Util();
        util.stk= new int[2000001];
        util.minStack=new Stack<Integer>();
        util.top=-1;

        new MinStack().push(7,util);
        new MinStack().push(5,util);
        new MinStack().push(8,util);

        res=new MinStack().getMin(util);
        System.out.println("Min:"+ res);

        new MinStack().push(3,util);
        new MinStack().push(10,util);

        res=new MinStack().getMin(util);
        System.out.println("Min:"+res);

        new MinStack().pop(util);

        res=new MinStack().top(util);
        System.out.println("Top:"+res);
    }//main
}//MinStack
