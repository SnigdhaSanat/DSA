package Array;

public class RepeatAndMissingNumberArray {
    public int[] repeatedNumber(final int[] A) {
        int n=A.length;
//find the missing number first
//-------------------------
        int missing=-1;

//Question requirement: Linear runtime complexity, and without using extra memory
/*If space were not a constraint, you would have taken an additional array of size extra[n]. Iterate over A, and if the element belongs to
the range[1,n],place each element in element-1 position in the extra[n] array. And finally iterate over that array, to see if any
position in the extra array is empty. the first empty number is the answer. If all are filled, n+1 is the answer.

But now that we need to use constant space, we have to accommodate extra[n] somehow in the original array A itself. We iterate over A, and
for each element, if it belongs to [1,n], exchange its position with the  element in element-1 position.

After every exchange, we DO NOT increment i. we send the new element at i again to its rightful place. This continues, until at i, we have
the right element, or else an element which has no rightful place

It might seem that while loop runs >n times. That is true. But since every element, if eligible, is sent to its rightful place just once,
and after that it is not uprooted from its right position, the exchanges happen at max for n number of times, if all the n elements are eligible,
or in fact <n times, if some elements are ineligible(negative numbers)

Then we iterate again, and the first place we see an out of place element, that is the answer*/

        int i=0;
        while(i<n){
            int element=A[i];
            int pos=element-1;//rightful place

            if(1<=element && element<=n && A[pos]!=element){
                //exchange it, if pos does not already contain the right element
                A[i]=A[pos];
                A[pos]=element;
            }//if
            else{
                i+=1;
            }

        }//while

        int indx;
        for( indx=0;indx<n;indx++){
            if(A[indx]!=indx+1){
                missing= indx+1;
                break;
            }//if
        }//for loop

//if all elements are present, the missing element is n+1
        if(indx==n){
            missing= n+1;
        }

//------------now the repeated number
        int sum=0;
        for(int j=0;j<n;j++){
            sum+=A[j];
        }//for

        /*sum=n(n+1)/2+repeatedNumber-missing, or repeated =sum-n(n+1)/2+missing*/
        int idealSum=-1;
        if(n%2==0){
            //if n is even
            int intermediate=n/2;
            idealSum=intermediate*(n+1);//n(n+1)/2

        }//if
        else{
            //if n is odd
            int intermediate=(n+1)/2;
            idealSum=intermediate*n;//n(n+1)/2

        }//else
        int repeated=sum-idealSum+missing;

        int[] res=new int[]{repeated,missing};

        return res;

    }//repeatedNumber

    public static void main(String[] args) {
        int[] A=new int[]{3 ,1 ,2, 5, 3};
        int[] res=new RepeatAndMissingNumberArray().repeatedNumber(A);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }
}//RepeatAndMissingNumberArray
