package Backtracking;

import java.math.BigInteger;

public class KthPermutationSequence {
    /*Use big integer.

Idea: Let's take an example of A=4, B=17. It means total permutations will be A!=24. We need to find the characters at every
index, index=0 to A-1.

We take a String res (effectively, an array of size A), and fill it as we go.
Now for B=17(say), to decide the position of each position starting from index 0, we do the following:

Initialize the group size as A!/A =24/4 =6 and factor as A-1. So we decide which group of size grp_size does B belong to. Also in that
group, what is its number, both using B and grp_size.

We do adjustments of both group and number though. If mod value is NOT 0, we increment group
by 1. Why?: 17/6=2. But 17 belongs to group 3. Also, if mod is 0, we convert number into  the group size. Eg: for B=18,
mod is 0, but it is the last number of its group.

Once we get the group, we get the (group)th natural number, which IS NOT MARKED. We then MARK THAT number. Thus we get the ith character of the res.

For each next iteration, the "universe" shrinks to size of previous group size, and so B becomes the previous 'number'.
Also, grp_size=grp_size/factor (factor was initialized to A-1), and decrement the factor by 1, and thus recurse.

Base condition: if factor becomes 0, we are then effectively down to grp_size=1, and have only last one unmarked number left.
Append that to the res*/

    class Result{
        int[] numArray=new int[100];
        String res;
    }
    public String getPermutation(int A, int B) {
        BigInteger factorial=getFactorial(A);
        BigInteger grp_size= factorial.divide(BigInteger.valueOf(A));
        int factor=A-1;
        Result result=new Result();
        result.numArray=new int[A];
        result.res="";
        recurse(A,BigInteger.valueOf(B),grp_size,BigInteger.valueOf(factor),result);

        return result.res;
    }//get getPermutation

    void recurse(int A, BigInteger B, BigInteger grp_size, BigInteger factor,Result result){

        if(factor.compareTo(BigInteger.valueOf(0))==0){
            //base condition

            for(int i=0;i<A;i++){
                if(result.numArray[i]==0){
                    //this will iterate only once, as ony unmarked element is left
                    result.res+=(String.valueOf(i+1));
                }//if
            }//for

            return;
        }//base condition

        BigInteger grp= B.divide(grp_size);
        BigInteger number=B.mod(grp_size);

        if(number.compareTo(BigInteger.valueOf(0))!=0){
            grp=grp.add(BigInteger.valueOf(1));
        }
        else{
            number=grp_size;
        }

        BigInteger count=BigInteger.valueOf(0);
        for(int i=0;i<A;i++){
            if(result.numArray[i]==0){
                //count of unmarked numbers
                count=count.add(BigInteger.valueOf(1));
            }//if
            if(count.compareTo(grp)==0){
                //(group)th natural number which is UNMARKED
                result.res+=String.valueOf(i+1);
                //mark it
                result.numArray[i]=1;
                break;
            }//if
        }//for


        grp_size=grp_size.divide(factor);
        factor=factor.subtract(BigInteger.valueOf(1));

        recurse(A,number,grp_size,factor,result);
        return;
    }//recurse

    BigInteger getFactorial(int A){
        BigInteger res=BigInteger.valueOf(1);
        for(int i=1;i<=A;i++){
            res=res.multiply(BigInteger.valueOf(i));
        }//for
        return res;
    }//factorial

    public static void main(String[] args) {
        int A=4;
        int B=17;
        String res=new KthPermutationSequence().getPermutation(A,B);
        System.out.println(res);
    }
}//KthPermutationSequence
