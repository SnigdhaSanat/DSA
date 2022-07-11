package HeapsAndMaps;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MagicianAndChocolates {

    public int nchoc(int A, int[] B) {
        //PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
        int mod=1000000007;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        int n=B.length;
        for(int i=0;i<n;i++){
            int elem=B[i];
            pq.add(elem);
        }//for

        int res=0;
        for(int i=0;i<A;i++){
            int max=pq.poll();
            int newElemTemp=(int)Math.floor(max/2);
            pq.add(newElemTemp);
            res=(res%mod+max%mod);
            int test1=1;
        }
        return res%mod;
    }//nchoc

    public static void main(String[] args){
        int A=10;
        int[] list= new int[] {2147483647, 2000000014, 2147483647};
        int res=new MagicianAndChocolates().nchoc(A,list);
        System.out.println(res);
    }//main
}//MagicianAndChocolates
