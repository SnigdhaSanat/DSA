package Array;

import java.util.Arrays;

public class WaveArray {
    /*Question requirement: If there are multiple answers possible, return the one that's lexicographically smallest.
    So sort the array, and then start from the second element. 2nd, 1st, 4th, 3rd, 6th and so on. If it is odd size array, take
    care of the last remaining number*/

    public int[] wave(int[] A) {
        int n=A.length;
        Arrays.sort(A);

        int[] waveArr=new int[n];

        int indexOfSecondSmallest=1;//index of next crest
        int indexOfSmallest=0;//index of next trough

        int i=0;

        while(indexOfSecondSmallest<n){
            waveArr[i]=A[indexOfSecondSmallest];
            i+=1;
            waveArr[i]=A[indexOfSmallest];
            i+=1;

            //update both variables
            indexOfSmallest=indexOfSecondSmallest+1;
            indexOfSecondSmallest=indexOfSmallest+1;
        }//while

        if(n%2!=0){
            //in case the size of array is odd, you need to fill in the last remaining element
            waveArr[i]=A[indexOfSmallest];
        }
        return waveArr;
    }//wave

    public static void main(String[] args) {
        int[] A=new int[] {1, 2};
        int[] res=new WaveArray().wave(A);

        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }//for
    }//main
}//WaveArray
