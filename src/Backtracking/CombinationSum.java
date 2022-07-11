package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class CombinationSum {
    class Utility{
        Stack<Integer> stkElement;
        Stack<Integer> stkCount;
        ArrayList<ArrayList<Integer>> res;
    }//Utility

    public int[][] combinationSum(int[] ADup, int B) {
 /*Strategy: At each level, generate the multiples of A[level] starting from 0, and till multiple <=B. At any point,
 sum the result.
 Entry point: if the multiple <=B, recurse for the multiples of A[level]. Here, level=0. Push the element, and its
 count(multiplier) before recursion.
 Entry to subsequent levels: Same as entry point
 Return: Pop the element from the stack
 Base condition: if level[A.length-1] is reached, OR the exact sum B is reached. */
        //sort Array
        Arrays.sort(ADup);

        int[] A=getDistinct(ADup);//this is because ADup might contain duplicates

        Utility util=new Utility();
        util.stkElement= new Stack<Integer>();
        util.stkCount=new Stack<Integer>();
        util.res=new ArrayList<ArrayList<Integer>>();

        int level=0;
        int element=A[level];
        int multiplier=0;
        while((element*multiplier)<=B){
            int nextRoot=element*multiplier;
            //conditionally recurse
            util.stkElement.push(nextRoot);
            util.stkCount.push(multiplier);
            recurse(A,nextRoot, level,B,util);

            //after lateral iteration
            multiplier+=1;
        }//while

        //after recursion
        //Convert ArrayList into int[][]

        int row=util.res.size();
        int [][] result=new int[row][];

        for(int i=0;i<row;i++){
            ArrayList<Integer> alRow=util.res.get(i);
            int column=alRow.size();
            int[] temp=new int[column];
            for(int j=0;j<column;j++){
                temp[j]=alRow.get(j);
            }//inner for
            //now add temp to the row
            result[row-1-i]=temp;//to return the result int the order required
        }//outer for

        return result;

    }//combinationSum

    void recurse(int[] A, int currentRoot, int level, int B, Utility util ){
        int sum=getStackSum(util.stkElement);

        if(level==A.length-1 ||sum==B){
            //base condition
            if(sum==B){
                //if sum is equal to B, add to res
                ArrayList<Integer> al=new ArrayList<Integer>();
                Iterator<Integer> itr1=util.stkElement.iterator();
                Iterator<Integer> itr2=util.stkCount.iterator();

                while(itr1.hasNext() && itr2.hasNext()){
                    int element=itr1.next();
                    int count=itr2.next();
                    if(element!=0){
                        //avoiding adding 0 elements
                        int actualElement=element/count;
//this is because we need to add every element count number of times, and  as actualElement
                        for(int i=0;i<count;i++){
                            al.add(actualElement);
                        }  //inner for
                    }//if
                }//while
                util.res.add(al);
            }

            //pop stack
            util.stkElement.pop();
            util.stkCount.pop();
            return;
        }//base condition

        int nextLevel=level+1;
        int element=A[nextLevel];
        int multiplier=0;

        while((element*multiplier)<=B){
            //checking for the element itself
            int nextRoot=element*multiplier;
            int sum2=getStackSum(util.stkElement);

            if(sum2+nextRoot<=B){//checking for the sum after adding the element. This is not required in entry point
                //conditionally recurse
                util.stkElement.push(nextRoot);
                util.stkCount.push(multiplier);
                recurse(A,nextRoot, nextLevel,B,util);
            }

            //after returning from the lateral recursion,continue with next lateral iteration
            multiplier+=1;
        }//while

        //backtrack, pop stack
        util.stkElement.pop();
        util.stkCount.pop();
        return;
    }//recurse

    int getStackSum(Stack<Integer> s){
        Iterator<Integer> itr=s.iterator();
        int sum=0;
        while(itr.hasNext()){
            sum+=itr.next();
        }//while
        return sum;
    }//getStackSum

    int[] getDistinct(int[] A){
        ArrayList<Integer> distinctAl= new ArrayList<Integer>();
        distinctAl.add(A[0]);
        for(int i=1;i<A.length;i++){
            if(A[i]!=A[i-1]){
                //if not duplicate
                distinctAl.add(A[i]);
            }//if
        }//for

        int[] distinct=new int[distinctAl.size()];
        for(int i=0;i<distinctAl.size();i++){
            distinct[i]=distinctAl.get(i);
        }
        return  distinct;
    }//getDistinct

    public static void main(String[] args) {
        int[] A=new int[]{2,3,6,7};
        int B=7;
        int[][] res=new CombinationSum().combinationSum(A,B);

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
}//CombinationSum
