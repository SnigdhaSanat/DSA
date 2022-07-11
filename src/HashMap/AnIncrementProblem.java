package HashMap;

import java.util.ArrayList;
import java.util.HashMap;

public class AnIncrementProblem {

public int[] solve(int[] A) {
    int n=A.length;
    HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
    ArrayList<Integer> res=new ArrayList<Integer>();
    for(int i=0;i<n;i++){
        int elem=A[i];
        //Add it to the res arraylist
        res.add(elem);
        if(hm.containsKey(elem)){
            //hm contains the element, meaning the element has come before, increment the first occurrence's count
            int position=hm.get(elem);//this is the index of its first occurrence
            int count=res.get(position);
            res.set(position,count+1);
            //update the hm
            hm.put(count+1,position);

            //get the next occurrence of count
            int index=position;
            while (index<res.size() && res.get(index)!=count){
                index+=1;
            }//while

            if(index<res.size()){
                //update the hm
                hm.put(count,index);
            }
            else{
                //delete the key if the next occurrence is not found
                hm.remove(count);
            }
        }//if
        else{
            //else, simply put it into the hashmap, with key as the element, and value as the index(of its 1st occurrence)
            hm.put(elem,i);
        }//else


    }//for

    int syz=res.size();
    int[] result=new int[syz];
    for(int i=0;i<syz;i++){
        result[i]=res.get(i);
    }

    return result;
}//solve


    public static void main(String args[]) {

        int[] list=new int[]{1, 2, 3, 2, 3, 1, 4, 2, 1, 3};

        int[] resultMain=new AnIncrementProblem().solve(list);
        for(int i=0;i<resultMain.length;i++){
            System.out.println(resultMain[i]);
        }

    }//main
}//AnIncrementProblem
