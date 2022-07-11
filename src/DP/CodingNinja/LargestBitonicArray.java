package DP.CodingNinja;
//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73038/problem/1543

public class LargestBitonicArray {
    public static int longestBitonicSubarray(int[] arr){
        /*This is same as the LIS. The diff is that since it is bitonic, you will find the LIS from both the ends.
        * Then create  a sum of both, and return the max.*/
    int len=arr.length;
    int[] incrFromStart=new int[len];
    int[] incrFromEnd=new int[len];

    //initializing the arrays
    for(int i=0;i<len;i++){
        incrFromStart[i]=1;
        incrFromEnd[i]=1;
    }//for

//Longest increasing subsequence from start
    for(int i=1;i<len;i++){
        int maxLen1= -1;
        for(int j=0;j<i;j++){
            if(arr[j]<arr[i] && incrFromStart[j]>maxLen1 ){
            //only if arr[j] is STRICTLY less than arr[i] and its incrFromStart is more than the maxLen1
                maxLen1=incrFromStart[j];
            }//if
        }//inner for
        if(maxLen1==-1){
            incrFromStart[i]=1;
        }
        else{
            incrFromStart[i]=maxLen1+1;
        }
    }//outer for

//Longest increasing subsequence from end
    for(int i=len-2;i>=0;i--){
        int maxLen2= -1;
        for(int j=len-1;j>i;j--){
           if(arr[j]<arr[i] && incrFromEnd[j]>maxLen2){
           //only if arr[j] is STRICTLY less than arr[i] and it is more than the maxLen2
               maxLen2=incrFromEnd[j];
           }//if
        }//inner for
        if(maxLen2==-1){
            incrFromEnd[i]=1;
        }
        else{
            incrFromEnd[i]=maxLen2+1;
        }
    }//outer for

    int max= Integer.MIN_VALUE;
    for(int i=0;i<len;i++){
        int sum=incrFromStart[i]+incrFromEnd[i]-1;//-1 because we are calculating at index i twice
        if(sum>max){
            max=sum;
        }
    }//for

    return max;
    }//longestBitonicSubarray

    public static void main(String[] args) {
        int[] arr=new int[]{6, 17, 12, 30, 7, 5, 4};
        int res= LargestBitonicArray.longestBitonicSubarray(arr);
        System.out.println(res);
    }//main
}//LargestBitonicArray
