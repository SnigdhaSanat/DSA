package Array;

public class Flip {

    public int[] flip(String A) {
        int n=A.length();
/*Since 0->1 and 1->0 in the sub-array, basically, we need to penalize 1s and incentivize 0s. So convert 1->-1 and 0 to 1. Then find
the maximum sum of the sub-array*/
        int[] B=new int[n];//equivalent int array
        int quickSumCheck=0;
        for(int i=0;i<n;i++){
            if(A.charAt(i)=='0')
            {
                B[i]=1;
            }
            else
            {
                B[i]=-1;
            }
            quickSumCheck+=B[i];
        }//for

        if(quickSumCheck==0-n){
            //if all values are 1
            int[] res= {};
            return res;
        }

        //find the maximum sum of the sub-array
        int sumCurr=0, maximum=-1;
        int left=0, right=0, leftRes=0, rightRes=0;

        //int idx=0;
        while(left<n && right<n){
            sumCurr+=B[right];
            if(sumCurr>maximum){
                //update the maximum
                maximum=sumCurr;
                leftRes=left;
                rightRes=right;
            }

            if(sumCurr<0){
                //if sumCurr hits a negative value, do it like you do for max contiguous array sum,reset
                left=right+1;
                right=left;
                sumCurr=0;
            }
            else {
                //if sumCurr is still positive, simply increment the right
                right+=1;
            }

        }//while

        //as the question requires a 1 based index
        int[] res=new int[]{leftRes+1,rightRes+1 };
        return res;
    }//flip

    public static void main(String[] args) {
        String A="0011001";
       int[] res= new Flip().flip(A);
       for(int i=0;i<res.length;i++){
           System.out.println(res[i]);
       }
    }
}//Flip
