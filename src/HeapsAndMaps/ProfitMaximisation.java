package HeapsAndMaps;

import java.util.Arrays;
import java.util.TreeMap;

public class ProfitMaximisation {
    public int solve(int[] A, int B) {
        int res=0;
        int n=A.length;
        //Putting the values in the TreeMap,taking care of duplicates
        TreeMap<Integer,Integer> tm=new TreeMap<Integer,Integer>();
        for(int i=0;i<n;i++) {
            int elem = A[i];
            if (tm.containsKey(elem)) {
                int value = tm.get(elem);
                tm.put(elem, value + 1);
            } else {
                tm.put(elem, 1);
            }
        }

        for(int i=0;i<B;i++){
            //Removing the max value,taking care of duplicates
            int maxKey=tm.lastEntry().getKey();
            int maxValue=tm.lastEntry().getValue();

            if(maxValue==1){
                //if it is a NON DUPLICATE
                tm.remove(maxKey);
            }
            else{
                tm.put(maxKey,maxValue-1);
            }

            res+=maxKey;

            //Replacing it by a key 1 less than maxKey
            int newKey=maxKey-1;
            if (tm.containsKey(newKey)) {
                int value = tm.get(newKey);
                tm.put(newKey, value + 1);
            } else {
                tm.put(newKey, 1);
            }
        }//for

        return res;
    }//solve

        public static void main(String[] args) {

            int[] list=new int[]{1,4};
            int B=2;
            int result=new ProfitMaximisation().solve(list,B);
            System.out.println(result);
        }//main
}
