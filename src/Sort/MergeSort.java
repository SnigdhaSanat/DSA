package Sort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
void sortArray(int[] arr, int lo, int hi){
    if(lo<hi){
        /*Why the comparison  of lo and hi is < and not <=? This is because, if we have lo==hi in the current iteration,
        * it means that this chunk of elements(or rather th single element) is sorted already, and hence ready to be merged.*/

        int mid=lo+(hi-lo)/2;

        sortArray(arr, lo, mid);
        sortArray(arr,mid+1, hi);

        merge(arr, lo, mid, hi);
    }//if

}//sortArray

void merge(int[] arr, int lo, int mid, int hi){
    //merge the 2 halves
    int leftLen=mid-lo+1;
    int rightLen=hi-mid;

    //create 2 temp arrays first
    int[] leftTemp=new int[leftLen];//from lo to mid
    int[] rightTemp=new int[rightLen];
    
    //transfer the elements into the temp arrays
    //IMP: maintain the offsets lo and (mid+1)
    for(int i=0;i<leftLen;i++){
        leftTemp[i]=arr[i+lo];
    }//for

    for(int i=0;i<rightLen;i++){
        rightTemp[i]=arr[i+mid+1];
    }//for

    //Now merge and put back into arr

    int index=lo;//IMP: index is initialized to be lo
    int leftIndex=0;
    int rightIndex=0;

    while(leftIndex<leftLen && rightIndex<rightLen){
        if(leftTemp[leftIndex]<=rightTemp[rightIndex]){
            arr[index]=leftTemp[leftIndex];
            leftIndex+=1;
        }
        else{
            arr[index]=rightTemp[rightIndex];
            rightIndex+=1;
        }
        index+=1;
    }//while

    while(leftIndex<leftLen){
        //if leftTemp elements are left
        arr[index]=leftTemp[leftIndex];
        index+=1;
        leftIndex+=1;
    }

    while(rightIndex<rightLen){
        //if leftTemp elements are left
        arr[index]=rightTemp[rightIndex];
        index+=1;
        rightIndex+=1;
    }

    }//merge
//-------------------------------------------------------

    public static  void main(String args[])
    {
        int[] arr=new int[]{15, 11, 17, 20, 13};
        int n=arr.length;

        new MergeSort().sortArray(arr,0,n-1);

        System.out.println("Sorted index:");
        for(int i=0;i<n;i++){
            System.out.println(arr[i]);
        }

    }//main

}//MergeSort
