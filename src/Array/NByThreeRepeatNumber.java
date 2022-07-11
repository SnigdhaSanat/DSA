package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NByThreeRepeatNumber {
    public int repeatedNumber(final List<Integer> a) {

//Question requirement: linear time and constant additional space.
/* Required: Any integer which occurs more than floor(n/3). This is a variant of Moore's Voting algorithm.

So, imagine an array of dimensions(floor(n/3)*3). So since the total number of elements will be floor(n/3)*3, total elements in this imaginary
array will still be <=n.

Central idea: The number of COMPLETE rows(of distinct elements) that can be created with n elements is floor(n/3). Anything remaining beyond that will
only fill up an additional partial row(either 1 or 2 elements). That being taken care of, the idea is to fit the elements inside the (floor(n/3)*3) box.
Anything that remains after that(after we keep eliminating the distinct element rows) is an eligible candidate for the required answer.

We will try to distribute duplicate elements across the floor(n/3) rows. If an element is still
left after this, this gives us our CANDIDATE, NOT RESULT. We will iterate again to check if the candidate qualifies for being the final result.

We will keep creating 3 element rows, of distinct elements of course. Once a row is created we cross those elements off. The remainder elements,
or any of those elements, is our CANDIDATE. */

        int n=a.size();

        int[] element=new int[]{-1,-1};
        int[] count=new int[2];
        for(int i=0;i<n;i++){

            if(a.get(i)==element[0]){
                //duplicate elements, so increment the respective count
                count[0]+=1;
            }
            else if(a.get(i)==element[1]){
                //duplicate elements, so increment the respective count
                count[1]+=1;
            }
            else if(element[0]==-1){
                //elements of the new row are being still added
                element[0]=a.get(i);
                count[0]=1;

            }
            else if(element[1]==-1){
                //elements of the new row are being still added
                element[1]=a.get(i);
                count[1]=1;

            }

            else{
        /*the current element is different from the other 2 elements, so this completes the row. This means decrement count from the 2 elements,
         Also ignore the count of the third element, as this is a case of row completion for ALL the 3 elements.HENCE OVERALL WE NEED TO MAINTAIN
         AN EXTRA SPACE OF 2, NOT 3*/
                count[0]-=1;
                count[1]-=1;
                if(count[0]==0){
                    element[0]=-1;
                }
                if(count[1]==0){
                    element[1]=-1;
                }
            }//else
        }//for

        //Now, check the candidates for actual eligibility, if their counts actually cross n/3. Return any or the first one
        int count1=0, count2=0;

        for(int i=0;i<n;i++){
            if(a.get(i)==element[0]){
                count1+=1;
            }
            if(a.get(i)==element[1]){
                count2+=1;
            }
        }//for

        if(count1>n/3){
            return element[0];
        }
        if(count2>n/3){
            return element[1];
        }
        return -1;

    }//repeated repeatedNumber

    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<Integer>(Arrays.asList(7,1,2,7,3,4,7,5,6,7,8,9,7,10));
        int res=new NByThreeRepeatNumber().repeatedNumber(al);
        System.out.println(res);
    }
}//NByThreeRepeatNumber
