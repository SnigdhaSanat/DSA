package Greedy.CodingNinja;

import java.util.Arrays;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9347/offering/69395/problem/3003
public class ProblemDiscussion {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();

        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }//for

        /* Sort the array first. Then we need to find the min possible difference between the min and the max elements after EACH a[i](+or-)k.
         * Initialize the diff with diff of (a[0]+k) and (a[n-1]-k)(Swap if required). Then for i=1 to i=n-2, check for each a[i]+k
         * and a[i]-k. If either of them belongs to the gap of currMin and currMax, keep it as such. Else,
         * out of a[i]+k and a[i]-k, take the one which increases the max or reduces the min less, that is, the one which increases the
         * gap minimally. */

        //Sort the array
        Arrays.sort(arr);

        int min=arr[0]+k;
        int max=arr[n-1]-k;
        if(min>max){
            //swap if required
            int temp=min;
            min=max;
            max=temp;
        }

        //Note that the gap can stay the same or increase, but it cannot be reduced
        for(int i=1;i<n-1;i++){
            int afterAdding=arr[i]+k;
            int afterSubtracting=arr[i]-k;
            if(min<=afterAdding && afterAdding<=max){
                //gap stays the same, so continue
                /*you can change the arr[i] to arr[i]+k, but since we only need the diff, it won't make a diff*/
                continue;
            }
            else if(min<=afterSubtracting && afterSubtracting<=max){
                //gap stays the same, so continue
                /*you can change the arr[i] to arr[i]+k, but since we only need the diff, it won't make a diff*/
                continue;
            }
            else{
                //choose the one which increases gap by minimum
                int gap1=afterAdding-min;
                int gap2=max-afterSubtracting;
                if(gap1<=gap2){
                    max=afterAdding;
                }
                else{
                    min=afterSubtracting;
                }
            }//else
        }//for

        System.out.println(max-min);
    }//main
}//ProblemDiscussion
