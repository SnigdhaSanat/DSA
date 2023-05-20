package HeapsAndMaps;

import java.util.*;


public class LRUCache {
    /**dq stores keys, NOT values of cache. Maintains insertion order
     MOST recently used elements will be towards the rear, as insertion here happens at the back*/
    Deque<Integer> dq;

    //stores the key value pairs in cache
    HashMap<Integer,Integer> hm;

    //capacity of cache
    final int cacheCapacity;

    //ctor
    public LRUCache(int capacity) {
        dq = new LinkedList<Integer>();
        hm=new HashMap<Integer,Integer>();
        cacheCapacity =capacity;
    }

    public int get(int key) {
        if(!hm.containsKey(key)){
            int value=-1;
            //System.out.println(value);
            return  value;
        }
        else{
            int value=hm.get(key);

            //remove it and move it to the rear(most recent) of the queue in dq.
            //hm stays the same
            dq.remove(key);
            dq.addLast(key);

            //return the value
            //System.out.println(value);
            return value;
        }
    }//get

    public void set(int key, int value) {
        if(hm.size()== cacheCapacity){
            if(hm.containsKey(key)){
                //capacity hit, and key(maybe with a different value) is already present, so no need to remove, just rearrange the dq

                //move it to the rear(most recent) of the queue in dq.
                dq.remove(key);
                dq.addLast(key);

                //update with the new value
                hm.put(key,value);

            }
            else{
                //capacity hit, and key is NOT already present
                //remove the oldest element(one in the front)  from dq and hm
                int oldest=dq.removeFirst();
                hm.remove(oldest);

                //then add this new element to dq and hm
                dq.addLast(key);
                hm.put(key,value);
            }

        }
        else{
            //if capacity is not hit
            if(hm.containsKey(key)){
                //if capacity is not hit and if already present

                //move it to the rear(most recent) of the queue in dq.
                dq.remove(key);
                dq.addLast(key);

                //update the element in hm
                hm.put(key,value);
            }
            else{
                //if capacity is not hit and hm DOES NOT contain the key

                //add it to the rear(most recent) of the queue in dq.
                dq.addLast(key);

                //in this case, set the element in hm
                hm.put(key,value);
            }

        }//else
    }//set

    static class Input{
        String op;
        int key;
        int value;

        Input(String opParam, int keyParam, int valParam){
            op=opParam;
            key=keyParam;
            value=valParam;

        }
    }//class

    public static void main(String[] args){
        /** Input example:capacity = 2
        set(1, 10)
        set(5, 12)
        get(5)        returns 12
        get(1)        returns 10
        get(10)       returns -1
        set(6, 14)    this pushes out key = 5 as LRU is full.
        get(5)        returns -1
         */

        Input[] list=new Input[]{new Input("S" ,1,10),new Input("S" ,5,12),
                new Input("G" ,5,-1),new Input("G" ,1,-1),
                new Input("G" ,10,-1),new Input("S" ,6,14),
                new Input("G" ,5,-1)
        };

        int cap=2;
        LRUCache lc=new LRUCache(cap);

        int opCount=7;
        for(int i=0;i<opCount;i++){
            Input ip=list[i];
            if(ip.op=="G"){
                lc.get(ip.key);
            }
            else{
                lc.set(ip.key,ip.value);
            }

        }//for
    }
}//LRUCache
