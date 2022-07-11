package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MergeOverlappingIntervals {

//Definition for an interval.
public static class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        //First sort the array by startings

        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return Integer.compare(a.start, b.start);
            }
        });

        int n=intervals.size();
        int lastStart=intervals.get(0).start;
        int lastEnd=intervals.get(0).end;
        ArrayList<Interval> res=new ArrayList<Interval>();

        if(n==1){
            //if single interval
            res.add(new Interval(lastStart,lastEnd));
            return res;
        }

        int i;
        for(i=1;i<n;i++){

            /*We start with 1 and not 0 index because at each iteration we compare i with i-1. If i-1 overlaps i, we create a new merged
            interval and move on. Only when i-1 does not overlap i, we place i-1(which might be merged with previous intervals) into the
            res ArrayList. If we reach the end of the iteration while still merging(if i==n), we come out of the loop, and place the last
            merged interval into the res ArrayList*/

            int currStart=intervals.get(i).start;
            int currEnd=intervals.get(i).end;

            if(currStart<=lastEnd){
                //if overlaps
                currStart=lastStart;
                currEnd=Math.max(lastEnd,currEnd);
            }
            else{
                //if does not overlap
                res.add(new Interval(lastStart, lastEnd));
            }
            lastStart=currStart;
            lastEnd=currEnd;

        }//for
        if(i==n){
            res.add(new Interval(lastStart, lastEnd));
        }
        return res;
    }


    public static void main(String[] args) {
        Interval i1=new Interval(1,3);
        Interval i2=new Interval(2,6);
        Interval i3=new Interval(8,10);
        Interval i4=new Interval(15,18);

        ArrayList<Interval> al=new ArrayList<Interval>();
        al.add(i1);
        al.add(i2);
        al.add(i3);
        al.add(i4);

        ArrayList<Interval> res= new MergeOverlappingIntervals().merge(al);

        for(int i=0;i<res.size();i++){
            Interval curr=res.get(i);
            System.out.print("[");
            System.out.print(curr.start);
            System.out.print(",");
            System.out.print(curr.end);
            System.out.print("]");
            System.out.println();
        }//for
    }
}//MergeOverlappingIntervals
