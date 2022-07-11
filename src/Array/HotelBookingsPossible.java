package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HotelBookingsPossible {
    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
    /* Just sort both the arrays individually. Then traverse then using 2 pointers, say i and j, day wise chronologically, meaning
    at each i or j, increment whichever is smaller. If i is incremented, implying arrival, increment the room count. If j is incremented,
    implying departure, decrement the room count */

        //sort first-INDIVIDUALLY
        Collections.sort(arrive);
        Collections.sort(depart);
        int n=arrive.size();

        int i=0,j=0,roomCount=0;
        boolean res=true;

        while(i<n && j<n){
            if(arrive.get(i)<depart.get(j)){
                roomCount+=1;
                i+=1;
            }// if
            else{
                roomCount-=1;
                j+=1;

            }//else

            if(roomCount>K)
            {
                res=false;
                return res;
            }// if

        }//while

        return res;
    }//hotel

    public static void main(String[] args) {
        ArrayList<Integer> arrival =new ArrayList<Integer>(Arrays.asList(1, 3, 5));
        ArrayList<Integer> departure =new ArrayList<Integer>(Arrays.asList(2,6,8));
        int K=1;
        boolean res=new HotelBookingsPossible().hotel(arrival,departure,K);
        System.out.println(res);
    }//main
}//HotelBookingsPossible
