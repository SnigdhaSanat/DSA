package Array;

import java.util.ArrayList;

public class FindPermutation {

    /*The question requires the solution to run in linear time and space.*/

    public ArrayList<Integer> findPerm(final String A, int B) {
        // Space Complexity: constant
        int n=A.length();

        //finding the range of max negative and positive numbers, based on I and D
        int min=0,max=0;
        for(int i=0;i<n;i++){
            //Time Complexity: linear
            if(A.charAt(i)=='I'){
                max+=1;
            }//if
            else{
                min-=1;
            }//else
        }//for

/*Say the range of min and max is -a to +b. We need to shift the range to the right by an offset, so that the result can be computed from
that. Eg: The range is -3 to 5. We need to shift it 1 to 9. So offset here is 4. We are choosing 1 based index instead of 0 based, so that
the numbers from the array list can be directly put in the res. */

        ArrayList<Integer> res=new ArrayList<Integer>();
/*Significance of offset. Offset is like some sort of center, which was originally 0, but changed to 'offset' after offsetting. If we start from offset,
* we start within 1 and the max value. That is why, we start with adding offset to the result, and initializing minDigit and maxDigit with (offset-1)
* and (offset+1) respectively*/
        int offset=1-min;//bring it to 1, not 0

        res.add(offset);

        int minDigit=offset-1;
        int maxDigit=offset+1;

        for(int i=0;i<n;i++){
            //Time Complexity: linear
            if(A.charAt(i)=='I')
            {
                res.add(maxDigit);
                maxDigit+=1;
            }//if
            else{
                res.add(minDigit);
                minDigit-=1;

            }//else

        }//for
        return res;
    }//findPerm

    public static void main(String[] args) {
        int n=3;
        String s="ID";
        ArrayList<Integer> res=new FindPermutation().findPerm(s,n);

        for(int i=0;i<res.size();i++){
            System.out.println(res.get(i));
        }//for

    }//main
}//FindPermutation
