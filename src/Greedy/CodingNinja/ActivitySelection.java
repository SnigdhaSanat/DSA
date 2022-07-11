package Greedy.CodingNinja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9347/offering/69384/problem/2056
class Time implements Comparable<Time>{
    int start;
    int end;
    Time(int s, int e){
        this.start=s;
        this.end=e;
    }//ctor

//sort by end time asc
    @Override
    public int compareTo(Time t) {
        if(end>t.end){
            return 1;
        }
        else if(end<t.end){
            return -1;
        }
        else if(end==t.end)
        {
            if(start>t.start){
                return 1;
            }
            else if(start<t.start){
                return -1;
            }
            else{
                return 0;
            }
        }
        else{
            return 0;
        }
    }//compareTo
}//Time

public class ActivitySelection {
/*We can arrange either on starting times, or on ending times. If we arrange on starting times, it won't benefit us,
because an activity that starts early might go on for long, not allowing a person to go for max activities as required by the question.
But if we sort on ending times, it means that activities will end early, leaving more time for other activities*/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        ArrayList<Time> al=new ArrayList<Time>();
        for(int i=0;i<n;i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            al.add(new Time(s,e));
        }//for

        //sort by end time
        Collections.sort(al);

        int count=0;
        int endPrev=-1;

        for(int i=0;i<n;i++){
            Time t=al.get(i);
            int start=t.start;
            int end=t.end;
            if(start>=endPrev){
                count+=1;
                endPrev=end;
            }//if
        }//for
System.out.println(count);
    }//main
}//ActivitySelection
