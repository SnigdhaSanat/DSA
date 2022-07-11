package BinarySearch;

public class SquareRootOfInteger {
    public int sqrt(int A) {
/*
start:left=1, right=A
during:if mid*mid>A, right=mid-1. If <A, left=mid +1. If =A, short circuit
end:keep going till while(left<=right).
Return: right.  What happens in case of the last element? When mid*mid<A, we are already having a  valid(floor of the square root) answer, but to get a better answer
, we assign left=mid+1. This is where the condition left<=right breaks. So what stays valid then? The prev iteration values
where left<=right. At the current iteration, this is returned by right, as left was modified. As the question requires the floor element,
hence "right" is valid
*/
        if(A==0||A==1){
            return A;
        }

        long left=1;
        long right=A;

        while(left<=right){

            long mid=left+(right-left)/2;

            if((mid*mid)>A){
                right=mid-1;
            }
            else if((mid*mid)<A){
                left=mid+1;
            }
            else{
                //short circuit
                return (int)mid;
            }

        }//while
//The ans may either be left or right
        return (int)right;
    }//sqrt

    public static void main(String[] args) {
        int A=15;
        int res=new SquareRootOfInteger().sqrt(A);
        System.out.println(res);
    }
}//SquareRootOfInteger
