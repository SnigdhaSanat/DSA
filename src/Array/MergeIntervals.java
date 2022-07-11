package Array;

import java.util.ArrayList;

public class MergeIntervals {

    enum MergeState{
        noMergeExists,merging,mergeJustEnded;
    }
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

         if(currState==MergeState.merging){
             //if merging state still exists
             res.add(new Interval(start, end));
             newIntervalAdded=true;
         }

         //if the newInterval is non overlapping and larger than the last interval
         if(!newIntervalAdded){
             res.add(new Interval(start, end));
         }

         return res;

     }//insert


    public static void main(String[] args) {

        Interval i1=new Interval(3,5);
        Interval i2=new Interval(8,10);

        Interval toInsert=new Interval(1,12);

        ArrayList<Interval> al=new ArrayList<Interval>();
        al.add(i1);
        al.add(i2);

        ArrayList<Interval> res= new MergeIntervals().insert(al, toInsert);

        for(int i=0;i<res.size();i++){
            Interval curr=res.get(i);
            System.out.print("[");
            System.out.print(curr.start);
            System.out.print(",");
            System.out.print(curr.end);
            System.out.print("]");
            System.out.println();
        }//for

    }//main

}//MergeIntervals
