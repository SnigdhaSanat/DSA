package Greedy.CodingNinja;

import java.util.Arrays;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9347/offering/69381/problem/1028
public class MinAbsDiffInArray {
    /*Greedy technique in general involves finding the criteria to be greedy on, and then sorting the input on its basis.
    * Unlike DP, which has overlapping sub-problems, Greedy has optimal subproblems,meaning each subproblem is optimum*/
    public static int minAbsoluteDifference(int input[]) {
//Criteria is the absolute difference between its next smaller and next greater element
        int n=input.length;
        //sort it
        Arrays.sort(input);

        //now find the min diff
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n-1;i++){
            int diff=Math.abs(input[i]-input[i+1]);
            if(diff<min){
                min=diff;
            }//if
        }//for
return min;
    }//minAbsoluteDifference

    public static void main(String[] args) {
        int[] arr=new int[]{2,9,0,4,5};
        int res=minAbsoluteDifference(arr);
        System.out.println(res);
    }
}//MinAbsDiffInArray
