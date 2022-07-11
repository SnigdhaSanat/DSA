package DP.CodingNinja;

import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73046/problem/1705
public class MaximumSumRectangle {
/*Fix left and right, which will have m combinations each. So we will end up with total m*m combinations. In a fixed
* left and right, we now need to find the best top and bottom. Find the sum of each row from first to last row, within the fixed
* left and right boundaries. Take those sums as candidates for Kadane's algorithm, and find the best possible contiguous sub-array
* with max sum. This makes the complexity O(m*m*n), where last n comes from Kadane's algo. That is for each of the m*m combos of L and R
* Kadane's algo takes n time.
*
* Now how do you get the sum? When you iterate for left and right, add(when increasing right) or subtract(when increasing left) that COLUMN's
* elements to the Kadane algo's candidates. */

static int findMaxSum(int[][] arr){
    int rows=arr.length;
    int cols=arr[0].length;

    int maxArea=Integer.MIN_VALUE;
    int[] kadane=new int[rows];//initialize for the first time

    for(int left=0;left<cols;left++){
        for(int right=left;right<cols;right++){
            //fix left and right

            //initialize Kadane 1-D array: Add here
            for(int i=0;i<rows;i++){
                kadane[i]+=arr[i][right];
            }//for

            int area=findMaximumContiguousSum(kadane);
            if(area>maxArea){
                maxArea=area;
            }
        }//inner loop

        //Reinitialize Kadane 1-D array: Subtract here
        kadane=new int[rows];
    }//outer loop

    return maxArea;
}  //finMaxSum

static int findMaximumContiguousSum(int[] kadane){
    //Kadane's algo
    int len=kadane.length;
    int max=Integer.MIN_VALUE;
    int max_so_far=0;

    for(int i=0;i<len;i++){
        //this takes care of all negative elements as well
        max_so_far=Math.max(max_so_far+kadane[i],kadane[i]);
        if(max_so_far>max){
            max=max_so_far;
        }
    }//for
    return  max;
}

public static void main(String[] args) {
// Write your code here
//    4 4
//            -1 -2 -2 -2
//            -5 -4 -1 -9
//            -3 -2 -6 -3
//            -4 -3 -3 -2
//    Scanner sc=new Scanner(System.in);
//    int rows=sc.nextInt();
//    int cols=sc.nextInt();
//
//    int [][] arr=new int[rows][cols];
//    for(int i=0;i<rows;i++){
//        for(int  j=0;j<cols;j++){
//            arr[i][j]=sc.nextInt();
//        }//inner for
//    }//outer for

    int[][] arr={{-1, -2, -2, -2},{-5, -4 ,-1 ,-9},{ -3, -2, -6, -3},{ -4, -3, -3, -2}};
    int res=MaximumSumRectangle.findMaxSum(arr);
    System.out.println(res);

//    int[] kadane=new int[]{-2,-1,-6,-3};
//    int res=MaximumSumRectangle.findMaximumContiguousSum(kadane);
//    System.out.println(res);

}//main
}//MaximumSumRectangle
