package DP.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73052/problem/1440

import java.util.ArrayList;

public class MinimumNoOfChocolates {

    public static int getMin(int arr[], int N){
    /*Find the indices of the minima, and assign 1 to them.
    Next find all the maxima, and assign them 1+diff, where diff is the max of the 2 distances between its left and right minima.
    For others, if its left is smaller, assign 1+diff, where diff is its distance from its left minima. Else assign the corresponding right value.
    For the 1st and last, if it is less than its adjacent element, treat it as minima, else treat it like any other element.

    This method works, except the case where you have an input like 1 4 4 1. Use getMin2(), which works for this input, and overall is a cleaner solution*/

        int[] res=new int[N];

        ArrayList<Integer> minimaIndexAl= new ArrayList<Integer>();

        //check if 0th element is a minima
        if(arr[0]<arr[1]){
            res[0]=1;
            minimaIndexAl.add(0);
        }
        //check for the middle elements
        for(int i=1;i<N-1;i++){
            //except the first and last, check if the elements are minima
            //and if so, assign them 1
            if(arr[i-1]>arr[i] && arr[i]<arr[i+1]){
                res[i]=1;
                minimaIndexAl.add(i);
            }//if
        }//for
        //check if last element is a minima
        if(arr[N-2]>arr[N-1]){
            res[N-1]=1;
            minimaIndexAl.add(N-1);
        }

        //get right and left minima
        int [] leftMinimaIndex=new int[N];
        int [] rightMinimaIndex=new int[N];
        int len=minimaIndexAl.size();

        if(len==1){
            int elem=minimaIndexAl.get(0);
            for(int idx=0;idx<=elem;idx++){
                rightMinimaIndex[idx]=elem;
            }
            for(int idx=elem;idx<=N-1;idx++){
                leftMinimaIndex[idx]=elem;
            }
        }
        else{
            for(int i=0;i<len;i++){
                if(i==0){
                    int right=minimaIndexAl.get(i+1);
                    int elem=minimaIndexAl.get(i);
                    for(int idx=0;idx<=elem;idx++){
                        rightMinimaIndex[idx]=elem;
                    }
                    for(int idx=elem;idx<=right-1;idx++){
                        leftMinimaIndex[idx]=elem;
                    }

                }
                else if(i==len-1){
                    int left=minimaIndexAl.get(i-1);
                    int elem=minimaIndexAl.get(i);
                    for(int idx=left+1;idx<=elem;idx++){
                        rightMinimaIndex[idx]=elem;
                    }
                    for(int idx=elem;idx<=N-1;idx++){
                        leftMinimaIndex[idx]=elem;
                    }
                }
                else{
                    int left=minimaIndexAl.get(i-1);
                    int right=minimaIndexAl.get(i+1);
                    int elem=minimaIndexAl.get(i);
                    for(int idx=left+1;idx<=elem;idx++){
                        rightMinimaIndex[idx]=elem;
                    }
                    for(int idx=elem;idx<=right-1;idx++){
                        leftMinimaIndex[idx]=elem;
                    }
                }
            }//for
        }


        //find all the maxima and others
        for(int i=1;i<N-1;i++){
            //except the first and last, check if the elements are maxima
            //and if so, assign them res
            if(arr[i-1]<arr[i] && arr[i]>arr[i+1]){
                int leftDistToMinima=i-leftMinimaIndex[i];
                int rightDistToMinima=rightMinimaIndex[i]-i;
                res[i]=Math.max(leftDistToMinima,rightDistToMinima)+1;
            }//if

            else if(arr[i-1]<arr[i] && res[i]!=1 ){
                //if minima is towards left
                res[i]=(i-leftMinimaIndex[i])+1;
            }
            else if(arr[i-1]>arr[i] && res[i]!=1){
                //if minima is toward right
                res[i]=(rightMinimaIndex[i]-i)+1;
            }
        }//for

        //for 1st and last element
        if(arr[0]<arr[1]){
            res[0]=1;
        }
        else{
            //else minima is towards right
            res[0]=(rightMinimaIndex[0]-0)+1;
        }
        if(arr[N-2]>arr[N-1]){
            res[N-1]=1;
        }
        else{
            //else minima is towards left
            res[N-1]=((N-1)-leftMinimaIndex[N-1])+1;
        }

        int sum=0;
        for(int i=0;i<N;i++){
            sum+=res[i];
        }
        return sum;
    }//getMin

    public static int getMin2(int arr[], int N){
        /*From the left, assign memo[i-1]+1 if a[i] is greater than a[i-1]. If a[i] is
        * smaller OR EQUAL to a[i-1] assign it 1. This is because we need the min #chocolates. So if smaller, definitely assign
        * 1 as it satisfies the required condition. If equal, it is still allowed to have 1, as the question does not say that being equal
        * one of them should have more value. In fact it says that they can have different values.
        *
        * Now do the same from the right. Just check the memo[] values you assigned in the left to right sweep. If a[i]>a[i+1]
        * AND memo[i] is less than OR EQUAL to memo[i+1], make it memo[i]= memo[i+1]+1.
        * */

        int[] memo=new int[N];

        //left to right sweep
        memo[0]=1;
        //start from index=1
        for(int i=1;i<N;i++){
            if(arr[i]>arr[i-1]){
                memo[i]=memo[i-1]+1;
            }
            else
                memo[i]=1;
        }//for

        //right to left sweep. Start from second last
        for(int i=N-2;i>=0;i--){
            if(arr[i]>arr[i+1] && memo[i]<=memo[i+1]){
                memo[i]=memo[i+1]+1;
            }//if
        }//for

        int sum=0;
        for (int i=0;i<N;i++){
            sum+=memo[i];
        }//for

        return sum;
    }//getMin2

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,4 };
        int res=MinimumNoOfChocolates.getMin2(arr, arr.length);
        System.out.println(res);
    }//main
}//MinimumNoOfChocolates
