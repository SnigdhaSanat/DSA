package Array;

import java.util.Arrays;

public class NobleInteger {
    //This problem is a little deceptive. It looks simple enough, until you realize that duplicate elements is the devil here
    public int solve(int[] A) {

        //Sort the array
        Arrays.sort(A);
        int n=A.length;

        //create the #elements greater than A[i] array
        int[] greater_than=new int[n];
        greater_than[n-1]=0;

        //current count of duplicate consecutive elements. Note that the 'master copy' is not counted, only duplicates are counted
        int dup_count=0;

        for(int i=n-2;i>=0;i--){

            int count=greater_than[i+1];//minimum value will be count

            if(A[i+1]>A[i]){
                count+=1+dup_count;//1 is for the master copy itself,and dup_count is for duplicates
                dup_count=0;//then reset it, as dup_count in the next iteration, if any will be for a[i], not a[i+1]
            }

            else{
                //case where the next element is same as the current element
                count=count;//unchanged
                //but these duplicate elements will contribute to the count of the next(actually prev, as we are traversing backward) greater_than count
                //of the next smallest element, so keep a track of it
                dup_count+=1;

            }

            greater_than[i]=count;

        }//for

        int res=-1;
        for(int i=0;i<n;i++){
            if(A[i]==greater_than[i]){
                res=1;
                return res;
            }

        }//for
        return res;

    }//solve

    public static void main(String[] args) {
        int[] A=new int[]{1, 1, 3, 3};
        int res=new NobleInteger().solve(A);
        System.out.println(res);
    }//main
}//NobleInteger
