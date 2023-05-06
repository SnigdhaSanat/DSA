package Array;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;

public class FirstMissingInteger {
    public int firstMissingPositive(ArrayList<Integer> A) {

        int n=A.size();
/*IMP: The size of the array is n. So the first missing number can be 1. If all the first n numbers are present, then the first
    missing number will be n+1. So the first missing integer will lie in the range [1,n+1]*/

/*If space were not a constraint, you would have taken an additional array of size extra[n]. Iterate over A, and if the element belongs to
the range[1,n],place each element in element-1 position in the extra[n] array. And finally iterate over that array, to see if any
position in the extra array is empty. the first empty number is the answer. If all are filled, n+1 is the answer.

But now that WE NEED TO USE CONSTANT SPACE, we have to accommodate extra[n] somehow in the original array A itself. We iterate over A, and
for each element, if it belongs to [1,n], exchange its position with the  element in element-1 position.

After every exchange, we DO NOT increment i. we send the new element at i again to its rightful place. This continues, until at i, we have
the right element, or else an element which has no rightful place.

It might seem that while loop runs >n times. That is true. But since every element, if eligible, is sent to its rightful place just once,
and after that it is not uprooted from its right position, the exchanges happen at max for n number of times, if all the n elements are eligible,
or in fact <n times, if some elements are ineligible(negative numbers)

Then we iterate again, and the first place we see an out of place element, that is the answer*/

        int i=0;
        while(i<n){
            int element=A.get(i);
            int pos=element-1;//rightful place

            if(1<=element && element<=n && A.get(pos)!=element){
                //exchange it, if pos does not already contain the right element
                A.set(i,A.get(pos));
                A.set(pos,element);
            }//if
            else{
                i+=1;
            }

        }//while

        for(int indx=0;indx<n;indx++){
            if(A.get(indx)!=indx+1){
                return indx+1;
            }//if
        }//for loop

//if all elements are present, the missing element is n+1
        return n+1;
    }//firstMissingPositive

    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<Integer>(Arrays.asList(1,2,0));
        int res=new FirstMissingInteger().firstMissingPositive(al);
        System.out.println(res);
    }
}//FirstMissingInteger
