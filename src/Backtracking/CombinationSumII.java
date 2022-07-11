package Backtracking;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class CombinationSumII {
    class Utility{
        Stack<Integer> stkElement;
        Stack<Integer> stkCount;
        ArrayList<ArrayList<Integer>> res;
    }//Utility

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> Al, int B) {
/*Strategy: Sort it. Then generate a dup array containing count of each element. Then make the array distinct.
At each level, generate multiples from 0 to dup[level]. At lateral iteration point,sum the result. In this question,
the multiplier will be limited by dup[level] instead of by element*multiplier<=B.

 Entry point: if the multiplier <=dup[level], recurse. Here, level=0. Push the element, and its count(multiplier)
 before recursion.
 Entry to subsequent levels: Same as entry point
 Return: Pop the element from the stack
 Base condition: if level[A.length-1] is reached, OR the exact sum B is reached. */
        //sort Array
        int sze=Al.size();

        //Convert arraylist Al into ADup[]
        int[] ADup=new int[sze];
        for(int i=0;i<sze;i++){
            ADup[i]=Al.get(i);
        }

        Arrays.sort(ADup);//sort

        int[] dup=getDup(ADup);//get the dup array
        int[] A=getDistinct(ADup);//this is because ADup might contain duplicates


        Utility util=new Utility();
        util.stkElement= new Stack<Integer>();
        util.stkCount=new Stack<Integer>();
        util.res=new ArrayList<ArrayList<Integer>>();

        int level=0;
        int element=A[level];
        int multiplier=0;
        while(multiplier<=dup[level]){
            int nextRoot=element*multiplier;
            //conditionally recurse
            util.stkElement.push(nextRoot);
            util.stkCount.push(multiplier);
            recurse(A,dup,nextRoot, level,B,util);

            //after lateral iteration
            multiplier+=1;
        }//while

        return util.res;
    }//combinationSum

    void recurse(int[] A, int[] dup,int currentRoot, int level, int B, Utility util ){
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

        while(multiplier<=dup[nextLevel]){
            //checking for the element itself
            int nextRoot=element*multiplier;
            int sum2=getStackSum(util.stkElement);

            if(sum2+nextRoot<=B){//checking for the sum after adding the element. This is not required in entry point
                //conditionally recurse
                util.stkElement.push(nextRoot);
                util.stkCount.push(multiplier);
                recurse(A,dup,nextRoot, nextLevel,B,util);
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

    int[] getDup(int[] A){
        //generating dup array
        ArrayList<Integer> temp=new ArrayList<Integer>();
        int n=A.length;

        int i=0;
        int j=i+1;
        int count=1;
        while(i<n){
            while(j<n && A[i]==A[j]){
                j+=1;
                count+=1;
            }//inner while
            temp.add(count);
            count=1;
            i=j;
            j+=1;
        }//outer while

        int syz=temp.size();
        int[] dup=new int[syz];
        for(int idx=0;idx<syz;idx++){
            dup[idx]=temp.get(idx);
        }
        return dup;
    }//getDup

    public static void main(String[] args) {
        ArrayList al=new ArrayList(Arrays.asList(10,1,2,7,6,1,5));
        int B=8;
        ArrayList<ArrayList> res=new CombinationSumII().combinationSum(al,B);

        for(int i=0;i<res.size();i++){
            ArrayList row=res.get(i);
            System.out.print("[");
            for(int j=0;j<row.size();j++){
                System.out.print(row.get(j)+" ");
            }
            System.out.print("]");
            System.out.println();
        }
    }
}//CombinationSumII
