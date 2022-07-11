package Array;

import java.util.ArrayList;

public class MinimumLightsToActivate {
    public int solve(int[] A, int B)
    {
    /*Central idea: We create a range of left and right for the bulbs marked as 1. Then, we initialize nextPositionToCover as 0. We check
    * the left elements of the now created left and right lists, and find the rightmost bulb whose left range can cover the nextPositionToCover.
    * We check for the rightmost bulb because we need the minimum count. Then we derive that since its right range can light
    * up till right.get(idx-1), nextPositionToCover will be right.get(idx-1)+1, and also increase the count. There are 2 more checks.
    *
    * First  condition checks if the new right.get(idx-1)+1 is beyond the range. If so, we break the outer loop and return count.
    * This condition will be always arrived at if there is a valid return value.
    *
    * The other condition checks if (idx-1) covers the nextPositionToCover. IMP: nextPositionToCover to cover is the one which idx-1 could not
    * cover with its right range. So it was hoping to get covered with the idx's range in the next outer loop. If it would have got covered at
    * any point of the next outer loop's inner while loop, although updated idx in the inner loop will not cover it, idx-1 would have still
    * covered it. But in this case no idx covered it, hence no idx-1(after idx is incremented in the inner loop) will cover it.
    * Note that the inner loop never runs in this case. Thus this else condition is arrived at, and we update the count as -1 and break the
    * outer loop  */


        // create 2 arrays- left and right, which will contain the left and right ranges of each of the '1' bulbs
        int n=A.length;
        ArrayList<Integer> left=new ArrayList<Integer>();
        ArrayList<Integer> right=new ArrayList<Integer>();
        for(int i=0;i<n;i++)
        {
            if(A[i]==1){
                left.add(Math.max(i-B+1,0));
                right.add(Math.min(i+B-1,n-1));
            }//if
        }//for
        //done creating right and left arrays

        int count=0;
        int idx=0;//for left and right lists
        int sze=left.size();

        int nextPositionToCover=0;//next bulb position to cover
        while(idx<sze){
            //idx is the bulb through which we are trying to cover the one at "nextPositionToCover"
            while(idx<sze && left.get(idx)<=nextPositionToCover && nextPositionToCover<=right.get(idx)){
                //find the rightmost bulb that covers the nextPositionToCover
                idx+=1;
            }//inner while


            if(idx>0 && left.get(idx-1)<=nextPositionToCover && nextPositionToCover<=right.get(idx-1)){
                /*left.get(idx-1)<=nextPositionToCover && nextPositionToCover<=right.get(idx-1) condition checks if the last
                idx covered the nextPositionToCover. Else it goes to the "else" condition where -1 is returned

                (idx-1) is the rightmost bulb that covers the current nextPositionToCover through its left range.
                On its right, it covers till right.get(idx-1). So right.get(idx-1)+1 is the updated nextPositionToCover*/
                nextPositionToCover=right.get(idx-1)+1;
                count+=1;
            }
            else if(idx>0 && right.get(idx-1)+1>=sze){
                /*if you arrive at the last range. The next position to cover would have been right.get(idx-1)+1, but that is
                * >=size*/
                break;
            }
            else{
                //Case when for ANY one of the bulbs, we could not get the 'if' condition of
                //left.get(idx-1)<=nextPositionToCover && nextPositionToCover<=right.get(idx-1)
                //meaning we could not proceed at all, so the chain breaks
                count =-1;
                break;
            }

        }//outer while
        return count;
    }//solve

    public static void main(String[] args) {
        int[] A = new int[]{ 0, 0, 1, 1, 1, 0, 0, 1};
        int B = 3;
        int res=new MinimumLightsToActivate().solve(A,B);
        System.out.println(res);
    }//main
}//MinimumLightsToActivate
