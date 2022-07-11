package Greedy.CodingNinja;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9347/offering/69388

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Job implements Comparable<Job>
{
int start;
int finish;
int profit;

Job(int s, int f, int p){
    start=s;
    finish=f;
    profit=p;
}
    @Override
    public int compareTo(Job j) {
        if(finish>j.finish){
            return 1;
        }
        else if(finish<j.finish){
            return -1;
        }
        else{
            return 0;
        }
    }//compareTo
}//Job
public class WeightedJobScheduling {
    /*Activity selection had the requirement of selecting the maximum number of jobs. Sorting the jobs by their finishing time, so that we get
    * free and select the next activity, which also finishes earliest, allowed us to select max number of activities.
    *
    * But this problem is 2 layered. We have to select the max profit. This means we have to select the max number of activities. But we also
    * need to check if those number of activities give us the max profit. So we do sort according to finishing time. But then as we iterate over them,
    * we also check the max profit we can get by selecting or NOT selecting the activity. That means we take either dp[i-1]  for not selecting, and
    * for selecting that activity we take the latest activity which is NOT overlapping to i, and add profit[i]. We take the max of the 2.
    *
    * Also, unlike the "FractionalKnapsack" problem of painters, we cannot fire a painter at any time after hiring. Once taken up, we
    * have to complete the activity */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        ArrayList<Job> al=new ArrayList<Job>();
        for(int i=0;i<n;i++){
            int s=sc.nextInt();
            int f=sc.nextInt();
            int p=sc.nextInt();
            Job job=new Job(s,f,p);
            al.add(job);
        }//for

        Collections.sort(al);

        int[] memo=new int[n];

        //Note that here memo[i]>=memo[j], for j<i. That is why the result is memo[n-1]
        memo[0]=al.get(0).profit;

        for(int i=1;i<n;i++){
            int exclude=memo[i-1];

            Job jobI=al.get(i);
            int include=jobI.profit;

            int includeIndex=getNonConflicting(al,jobI.start, 0,i-1);

            if(includeIndex!=-1){
              include+=memo[includeIndex];
            }

        memo[i]=Math.max(include,exclude);

        }//for

        System.out.println(memo[n-1]);
    }//main

    static int getNonConflicting(ArrayList<Job> jobs,int startI,int low, int high){
        //find the latest non overlapping job. As it is latest, so break once the condition is satisfied
        int middle=(low+high)/2;
        Job middleJob=jobs.get(middle);

        while(high-low>1){
            if(middleJob.finish>startI){
                high=middle-1;//  conflicting;
            }
            else{
                //if(middleJob.finish<=startI)
                low=middle;;//  non-conflicting;
            }
            middle=(low+high)/2;
            middleJob=jobs.get(middle);
        }//while

        if (jobs.get(high).finish<=startI) {
            return high;
        }
        else if(jobs.get(low).finish<=startI){
            return low;
        }
        return -1;
    }//getNonConflicting
}//WeightedJobScheduling
