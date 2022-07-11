package Backtracking;

import java.util.ArrayList;

public class GrayCode {
    class Sequence{
        int num;
        ArrayList<Integer> res;
    }//class

    public ArrayList<Integer> grayCode(int n) {
/*The baseline for recursion is: Find the entry point, entry to each subsequent recursion level, lateral iteration in each
level, back to each previous recursion level, and finally base condition. But for the ones expecting a particular
SEQUENCE,  this has to be taken care of separately.

In this case of gray code too, we need to not only generate the numbers, but in SEQUENCE.

Strategy: Refer to https://www.eetimes.com/gray-code-fundamentals-part-2/#
Recursively mirror and invert for each level(bit). Example, for n=4, starting with 0000, first mirror and invert
bit position 0(from right). So 0000 will become 0001. Now for bit position 1 from right, we have 0000 and 0001. mirror and
invert:
0000
0001
````````Mirror for bit position=1
0011
0010

Do this recursively at every level. How is this projected into a code? The tree will be till level n(4 here). So at every
level, before we go to the right recursion, we compute the mirror at that level, for that level. Recursively, we compute
this for every left-right recursion junction for every level. We store that at a var, say num. When we reach the leaf node, we
add current num value to the res ArrayList. Every num ever made at any level is "projected" at the left->left--->left  node. Mathematically
also, a n level binary tree has 2^n leaf nodes, which is the number of nums expected]

IMP: As we backtrack, the num RETAINS its value we made at the last left leaf*/

        Sequence seq=new Sequence();
        seq.num=0;
        seq.res=new ArrayList<Integer>();
        recurse(n,seq);//entry point

//after recursion, return
        return seq.res;

    }//grayCode

    void recurse(int level, Sequence seq){

        if(level==0){
//base condition
//leaf node, so add num to the result
            seq.res.add(seq.num);
            return;
        }

//recurse left
        recurse(level-1, seq);

//compute the mirror. This in a way is like lateral iterations we do
//We XOR it. Note that XORing with 0 retains the bit, and 1 inverses the bit
//So we right shift binary 1 to the position we want to invert the bit at
//Effectively, this is the mirror at that level. However, the bit value is n-1

        seq.num=seq.num^(1<<(level-1));

//recurse right
        recurse(level-1,seq);

//arrives at this position after recursion
        return;
    }

    public static void main(String[] args) {
        int n=3;
        ArrayList<Integer> res=new GrayCode().grayCode(n);
        for(int i=0;i<res.size();i++){
            System.out.println(res.get(i));
        }
    }
}//GrayCode
