package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MergeIntervals {

    enum MergeState{
        /*
        * noMergeExists: nothing much to be done. Just add the interval AFTER the current one, conditionally, as explained below.
        * merging: Still merging, so don't put it into the interval yet.
        * mergeJustEnded: just ended. So put the merged interval BEFORE the current one.*/
        noMergeExists,merging,mergeJustEnded;
    }

    /*With the above 3 steps in mind, at any point, if noMergeExists, we add the interval AFTER the current one, only if it is smaller and non-overlapping than the next one. In that case, we set the newIntervalAdded to true. Else not. That way, the interval smaller and non overlapping with the current interval will be placed in the previous iteration. For the first iteration, this is taken care of as an exception.

If mergingJustEnded, meaning merging was on but no overlap exists now, we place the interval BEFORE the current one.

If merging is still on, we don't place it at all.

Now at the end, if newIntervalAdded is false, meaning it was either 1. noMergeExists but condition did not satisfy as it was the last interval, or it was overlapping with the next interval, or 2. mergeJustEnded condition never was true. In that case, place the interval after the end of the loop*/

     //Definition for an interval.
     public static class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }

     public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

        //new interval is not necessarily sorted
        int start=Math.min(newInterval.start,newInterval.end);
        int end=Math.max(newInterval.start,newInterval.end);;

        MergeState currState=MergeState.noMergeExists;

        boolean newIntervalAdded=false;

         ArrayList<Interval> res=new ArrayList<Interval>();

         for(int i=0;i<intervals.size();i++){

             int currStart=intervals.get(i).start;
             int currEnd=intervals.get(i).end;

             //Edge case: if the newInterval is non overlapping and smaller than the first interval
             if(i==0 && end<currStart){
                 res.add(new Interval(start, end));
                 newIntervalAdded=true;
             }

             //Update the state
             //There is an overlap if the start, end , or both falls in between currStart and currEnd
             //There is also an overlap if the currStart, currEnd, or both falls between start and end
             boolean overlapExists=(currStart<=start && start<=currEnd) || (currStart<=end && end<=currEnd)||
                     (start<=currStart && currStart<=end)||(start<=currEnd && currEnd<=end);

             if(currState==MergeState.merging && !overlapExists){
                 //if no overlap and current state is merging
                 currState=MergeState.mergeJustEnded;
             }

             else if(!overlapExists){
                 currState=MergeState.noMergeExists;
             }

             else if(overlapExists){
                 currState=MergeState.merging;
             }
//------------------------------------------------------------

             //merge conditionally
             if(currState==MergeState.merging){
                 start=Math.min(start,currStart);
                 end=Math.max(end,currEnd);
             }

 //------------------------------------------------------------

             //add to the res arraylist conditionally
             if(currState==MergeState.noMergeExists){
                 res.add(new Interval(currStart, currEnd));

                 //no merge exists, but found the right place for the newInterval
                 if(currEnd<start && i<intervals.size()-1 && end<intervals.get(i+1).start){
                     res.add(new Interval(start, end));
                     newIntervalAdded=true;
                 }
             }
             else if(currState==MergeState.mergeJustEnded){
                 res.add(new Interval(start, end));
                 newIntervalAdded=true;

                 res.add(new Interval(currStart, currEnd));
                 currState=MergeState.noMergeExists;
             }
         }//for


         //if the newInterval is non overlapping and larger than the last interval
         if(!newIntervalAdded){
             res.add(new Interval(start, end));
         }


         return res;

     }//insert

    public ArrayList<Interval> insertSimpler(ArrayList<Interval> intervals, Interval newInterval){
        //First sort the array by startings

        ArrayList<Interval> combinedInterval=new ArrayList<Interval>();
        combinedInterval.addAll(intervals);
        combinedInterval.add(newInterval);


        Collections.sort(combinedInterval, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return Integer.compare(a.start, b.start);
            }
        });

        int n=combinedInterval.size();
        int lastStart=combinedInterval.get(0).start;
        int lastEnd=combinedInterval.get(0).end;
        ArrayList<Interval> res=new ArrayList<Interval>();


        int i;
        for(i=1;i<n;i++){

            /*We start with 1 and not 0 index because at each iteration we compare i with i-1. If i-1 overlaps i, we create a new merged
            interval and move on. Only when i-1 does not overlap i, we place i-1(which might be merged with previous intervals) into the
            res ArrayList. At the end, we come out of the loop, and place the last merged interval into the res ArrayList*/

            int currStart=combinedInterval.get(i).start;
            int currEnd=combinedInterval.get(i).end;

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
        Interval i2=new Interval(6,9);

        Interval toInsert=new Interval(2,5);

        ArrayList<Interval> al=new ArrayList<Interval>();
        al.add(i1);
        al.add(i2);

        //ArrayList<Interval> res= new MergeIntervals().insert(al, toInsert);
//
//        for(int i=0;i<res.size();i++){
//            Interval curr=res.get(i);
//            System.out.print("[");
//            System.out.print(curr.start);
//            System.out.print(",");
//            System.out.print(curr.end);
//            System.out.print("]");
//            System.out.println();
//        }//for


        ArrayList<Interval> res2= new MergeIntervals().insertSimpler(al, toInsert);

        for(int i=0;i<res2.size();i++){
            Interval curr=res2.get(i);
            System.out.print("[");
            System.out.print(curr.start);
            System.out.print(",");
            System.out.print(curr.end);
            System.out.print("]");
            System.out.println();
        }//for

    }//main

}//MergeIntervals
