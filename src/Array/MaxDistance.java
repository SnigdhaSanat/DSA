package Array;

import java.util.ArrayList;
import java.util.Collections;

public class MaxDistance {
    class Item implements Comparable<Item> {
        int index;
        int element;
        Item(int i, int e){
            index=i;
            element=e;
        }

        @Override
        public int compareTo(Item item) {
            if(this.element>item.element){
                return 1;
            }
            else if(this.element<item.element){
                return  -1;
            }
            else{
                return 0;
            }
        }
    }//Item

    public int maximumGap(final int[] A) {

        /* We will sort the given array, and the index array along with it. Once sorted, we are interested only in the index array,
        which is rearranged(not sorted) according to the elements. This index array, for any i and j, satisfies A[i] <= A[j], and
        of course j>i.So for any i, j, with A[i] <= A[j] condition now taken care of, where j>i, we only need to find the max (j-i).
        For this, create 2 arrays: min_so_far and max_from_here. At any iteration, max gap will be the gap between min element at
        that point i, and max from i+1 to array size*/
        int n=A.length;

        if(n==1) return 0;

        ArrayList<Item> al=new ArrayList<Item>();

        for (int i=0;i<n;i++){
            al.add(new Item(i,A[i]));
        }

        Collections.sort(al);

        int[] min_so_far=new int[n];
        int[] max_from_here=new int[n];


        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            min=Math.min(min,al.get(i).index);
            min_so_far[i]=min;

        }

        int max=Integer.MIN_VALUE;
        for(int i=n-1;i>=0;i--){
            max=Math.max(max,al.get(i).index);
            max_from_here[i]=max;
        }

        int maxGap=Integer.MIN_VALUE;

        for(int i=0;i<n-1;i++){
            int gap=max_from_here[i+1]- min_so_far[i];
            maxGap=Math.max(maxGap,gap);

        }//for

        return Math.max(0,maxGap);

    }//maximumGap

    public static void main(String[] args) {
        int[] A = {3, 5, 4, 2};
        int res=new MaxDistance().maximumGap(A);
        System.out.println(res);
    }
}//MaxDistance
