package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class LetterPhone {
    /*Strategy: Create a hm, consisting of numbers as keys and letters as values
Entry point: Get the hm value for (index)th char of A, index=0. If 0th index is 2, push "abc". Now laterally recurse for
them.
Entry to next level: Same as entry point. Keep increasing index for every level.
Exit to prev  level;: Pop
Base condition: If index=n-1. Take a snapshot of stack, and push to res array Pop from stack and return*/
    class Utility{
        Stack<String> stk;
        ArrayList<String> al;
    }
    public String[] letterCombinations(String A) {

        int n=A.length();
        Utility util=new Utility();
        util.stk=new Stack<String>();
        util.al=new ArrayList<String>();

//creating the hashmap
        HashMap<Character,String> hm=new HashMap<Character, String>();
        hm.put('0',"0");
        hm.put('1',"1");
        hm.put('2',"abc");
        hm.put('3',"def");
        hm.put('4',"ghi");
        hm.put('5',"jkl");
        hm.put('6',"mno");
        hm.put('7',"pqrs");
        hm.put('8',"tuv");
        hm.put('9',"wxyz");

//entry point
        int index=0;
        char numCh=A.charAt(index);
        String letters=hm.get(numCh);
        for(int i=0;i<letters.length();i++){
            char ch=letters.charAt(i);
            util.stk.push(Character.toString(ch));
            recurse(A,n,hm,0,util);
        }//for


//after recursion
//converting  al into []
        int len=util.al.size();
        String[] result=new String[len];
        for(int i=0;i<len;i++)
        {
            result[i]=util.al.get(i);
        }//for

        return result;
    }//letterCombinations

    void recurse(String A,int n,HashMap<Character, String> hm, int index,Utility util){
        if(index==n-1){
//base condition
//take a snapshot of stack, and put into ArrayList
            Iterator itr=util.stk.iterator();
            String str= "";

            while(itr.hasNext()){
                str+=itr.next();
            }//while

            util.al.add(str);

//pop from the stack
            util.stk.pop();
            return;

        }//base condition


        int nextIndex=index+1;


        char numCh=A.charAt(nextIndex);
        String letters=hm.get(numCh);

        for(int i=0;i<letters.length();i++){
            char ch=letters.charAt(i);
            util.stk.push(Character.toString(ch));
            recurse(A,n,hm,nextIndex,util);

            //continue with the next lateral iteration
        }//for

//backtrack
        util.stk.pop();
        return;

    }//recurse

    public static void main(String[] args) {
        String A="23";
        String[] res=new LetterPhone().letterCombinations(A);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }
}//LetterPhone
