package BinarySearch;

public class AllocateBooks {
    public int books(int[] A, int B)
    {
        int n=A.length;
        if(B>n){
            //if number of students is more than number of books
            return -1;
        }

        int sum=0;
        for(int i=0;i<n;i++ )
        {
            //sum of all the pages
            sum+=A[i];
        }


/*So the number lies between 0 and total pages, which is the sum. We will do binary search over this range to find the minimum possible
MAXIMUM number of pages allocated to a student*/
        int left=0;
        int right=sum;
        int lastPossibleValue=-1;

        while(left<=right){
/*Every mid is the maximum number of pages allocated. This value has  to be minimized. If isPossible
returns false, at every false return you will see that the mid value is too low. Hence search for the right half in this
case. Else, right=mid-1. No short circuiting here.

How mid or result will end up being an actual sum? Because even it is not, subsequent iteration will adjust it to a
lower actual value, or a higher actual*/

            int mid=(left+right)/2;//mid: max number of pages

            if(isPossible(A,n,B,mid)){
                /*neither any A[i] exceeded mid(max number of pages), nor we ran out of students. As the question requires the minimum mid(max number of
                 pages allocated to a student), decrease the mid. This is an optimization. */
                lastPossibleValue=mid;
                right=mid-1;
            }

            else{
                /*if isPossible returns false, it means mid is too less, either because one or more A[i] is >mid, or because we ran out of students with
                * books still left. So increase mid. This is the case where mid value not valid.*/
                left=mid+1;
            }

        }//while

        return lastPossibleValue;
    }//books

    boolean isPossible(int[] A, int n, int B,int mid){
        //to check if possible. Mid is the max pages a student can read. Now iterate over the book loop

        int studentCount=1;//starting with the first student. This is a 1 based index tracker of the current student

        //currSum of the count of pages for the current student
        int currSum=0;

        for(int i=0;i<n;i++){
            if(A[i]>mid){
//a book individually has more pages than mid(the max no. of pages a student can read). It means mid value is too less
                return false;
            }

            int possiblePagesForCurrStudent=currSum+A[i];

            if(possiblePagesForCurrStudent>mid){
                //Page quota up for the current student.So assign it to the next student
                studentCount+=1;

                //reset currSum
                currSum=0;
                currSum=A[i];//and start a count freshly upon the next student

                if(studentCount>B){
//this means we are out of students, which means we have books left, which again means that mid value is too less
                    return false;
                }//inner if
            }//if

            else{
                //the generic flow, add it to the pages of the current student
                currSum=possiblePagesForCurrStudent;
            }

        }//for

        return true;

    }//isPossible

    public static void main(String[] args) {
        int[] A = new int[]{12, 34, 67, 90};
        int B = 2;
        int res=new AllocateBooks().books(A,B);
        System.out.println(res);
    }//main
}//AllocateBooks
