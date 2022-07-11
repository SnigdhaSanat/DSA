package Greedy.CodingNinja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Painter implements Comparable<Painter>{
int timeAvailableFrom;
int cost;
int speed;
Painter(int t, int c, int s){
    timeAvailableFrom=t;
    cost=c;
    speed=s;
}

    /*Sort the painters according to time(asc), then speed(desc) and then cost(asc)*/
    @Override
    public int compareTo(Painter p) {
        if(timeAvailableFrom>p.timeAvailableFrom){
            return 1;
        }
        else if(timeAvailableFrom<p.timeAvailableFrom){
            return -1;
        }
        else{
            if(speed>p.speed){
                return -1;
            }
            else if(speed<p.speed){
                return 1;
            }
            else{
                if(cost>p.cost){
                    return 1;
                }
                else if(cost<p.cost){
                    return -1;
                }
                else return 0;
            }//if speeds are equal
        }//if timeAvailableFrom are equal
    }//compareTo
}//Painter

public class FractionalKnapsack {
/*Note that speed is the main factor here. Cost is in 2nd priority*/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long d=sc.nextLong();

        ArrayList<Painter> al=new ArrayList<Painter>();
        for(int i=0;i<n;i++)
        {
            int t=sc.nextInt();
            int x=sc.nextInt();
            int y=sc.nextInt();
            Painter p=new Painter(t,x,y);
            al.add(p);
        }//for

        Collections.sort(al);

        /*We have made the sorting according to time, then prevSpeed then cost. In the output, we need to track the cost.
        * So we hire an worker, and keep him, unless we find someone speedier than him now or in the future. If so,
        * we then count the time interval the worker worked for, and multiply it with the prevSpeed. That gives us the covered area.
        * If the covered area exceeds D, we break the loop.
        * Also when hiring, we add the cost to the total*/

        int totalCost=0;
        int prevSpeed=Integer.MIN_VALUE;
        int prevTimeStarted=-1;
        long areaLeft=d;

        int i=0;
        while(i<al.size() && areaLeft>0){
            //continue only till areaLeft>0
            Painter curr=al.get(i);
             //hire only if prevSpeed is greater than the current worker
             if(curr.speed>prevSpeed){

                 //calculate the area covered by the last worker and update the areaLeft
                 if(i>0){
                     int timeElapsed=curr.timeAvailableFrom-prevTimeStarted;
                     long covered=prevSpeed*timeElapsed;
                     areaLeft-=covered;

                 }//if

                 //for the first worker in the al, prevSpeed was MIN_VALUE, so this condition passes
                 //if the whole area has already been covered, skip the cost
                 //IMP: If the question required time, and areaLeft<=0, then we had to account for the exact time when it was completed
                 if(areaLeft>0){
                     totalCost+=curr.cost;
                 }

                 //Now update prevTimeStarted and prevSpeed
                 prevTimeStarted=curr.timeAvailableFrom;
                 prevSpeed=curr.speed;
             }//if
            i+=1;
        }//while

        /* For the last worker hired, or the first worker in case it has only 1 worker, if the array ends,
        * we don't have to worry about the fact that the array ended and areaLeft is still >0. He will complete the job in his own speed.
        * And we already added his hiring cost to the totalCost.
        * IMP:We had to account for the exact time when it was completed, if the question required the total time.
        *
        * The other case where the array elements are left, but the area is covered, is taken care of in the if condition before adding to the totalCost*/
        System.out.println(totalCost);
    }//main
}//FractionalKnapsack
