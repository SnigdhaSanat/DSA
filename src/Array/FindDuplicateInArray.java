package Array;

import java.util.TreeSet;

public class FindDuplicateInArray {
    public int repeatedNumber(final int[] A) {
        int n= A.length;
        //Simply use a TreeSet for duplicate checks
        TreeSet<Integer> tree = new TreeSet<Integer>();

        int res=-1;

        for(int i=0;i<n;i++){

            if(tree.contains(A[i])){
                //check if already there=>repeatedNumber
                res=A[i];
                break;
            }
            else{
                //else add it
                tree.add(A[i]);
            }

        }//for

        return res;
    }

    public static void main(String[] args) {
        int[] A=new int[]{3,4 ,1, 4, 1};
        int res=new FindDuplicateInArray().repeatedNumber(A);
        System.out.println(res);
    }
}//FindDuplicateInArray
