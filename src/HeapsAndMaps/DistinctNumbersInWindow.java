package HeapsAndMaps;

import java.util.TreeMap;

public class DistinctNumbersInWindow {
    public int[] dNums(int[] A, int B) {
        int n = A.length;
        if (B > n) {
            int[] res = new int[]{};
            return res;
        }

        //create a TreeMap of size B, so that it holds only distinct numbers.
        //calculate the size of it every time

        int[] res=new int[n-B+1];

        //initialize TM for 0 to B-1
        TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();

        //int start=start;
        for (int i = 0; i < B; i++) {
            int elem = A[i];

            if(tm.containsKey(elem)){
                int value=tm.get(elem);
                tm.put(elem,value+1);
            }
            else{
                tm.put(elem, 1);
            }

        }// for

        res[0]=tm.size();


        //calculate the distinct integers in that window, and put that in res[]
        //delete the old element at start-1, and add the one at start+B-1
        for (int start=1;start<=n-B;start++) {
            int toDelete=A[start-1];
            int toAdd=A[start+B-1];

            //delete toDelete
            if(tm.get(toDelete)==1){
                tm.remove(toDelete);
            }
            else{
                int value=tm.get(toDelete);
                tm.put(toDelete,value-1);
            }

            //add toAdd
            if(tm.containsKey(toAdd)){
                int value=tm.get(toAdd);
                tm.put(toAdd,value+1);
            }
            else{
                tm.put(toAdd,1);
            }

            res[start]=tm.size();
        }// for

        return res;
    }//dNums

    public static void main(String[] args){
        int[] list=new int[]{1, 2, 1, 3, 4, 3};
        int B=3;
        int[] result=new DistinctNumbersInWindow().dNums(list,B);
        for(int i=0;i<result.length;i++)
        {
            System.out.println(result[i]);
        }

    }//main
}//DistinctNumbersInWindow
