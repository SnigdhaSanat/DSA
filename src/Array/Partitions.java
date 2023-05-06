package Array;

public class Partitions {
    public int solve(int n, int[] A) {
/*The idea is to create arrays sum_so_far and sum_from_here. Compute the p=sum/3, which should be the sum of every 1/3rd partition.
* Then, mark the indices as 1 where sum+from+here=p. Then from the right, calculate the cumulative sum of those indices marked as 1.
* For instance, if an index has this cumulative sum as 2, it means that in 2 places from and after it till n, sum_from_here is p.
*
* Now, starting from the left, for every index i, if sum_so_far=p, get the value of the cumulative sum at i+2.
* If i+2 has a value of 2, it means at i as the end of first partition, we have 2 options to create the start for the 3rd partition from.
* Keep summing them, and finally return the sum*/

        //create an array of sum so far from the left
        int[] sum_so_far=new int[n];

        int sum_curr=0;
        for(int i=0;i<n;i++)
        {
            sum_curr+=A[i];
            sum_so_far[i]=sum_curr;
        }//for


        //create an array of sum from here from the right
        int[] sum_from_here=new int[n];
        sum_curr=0;
        for(int i=n-1;i>=0;i--)
        {
            sum_curr+=A[i];
            sum_from_here[i]=sum_curr;
        }//for

        int sum_total=sum_so_far[n-1];

        //not splittable if the sum_total is not divisible by 3
        if(sum_total%3!=0) return 0;

        //else, required sum in each partition is par_sum
        int par_sum=sum_total/3;

/*Let us call the end index of left partition as left, and start of right partition as right. The logic is,
for every left, where sum_so_far=par_sum,for right>=left+2, add to the result every right value, where sum_from_here=par_sum*/

//creating array to maintain bool sum from here=par sum
        int[] sum_from_here_is_part_sum=new int[n];
        for(int i=0;i<n;i++){
            if(sum_from_here[i]==par_sum){
                sum_from_here_is_part_sum[i]=1;
            }
            else{
                sum_from_here_is_part_sum[i]=0;
            }
        }//for loop

//creating array to maintain count of indices from right, where sum_from_here_is_part_sum=1
        int[] count_part_sum_from_here=new int[n];
        int sum=0;
        for(int i=n-1;i>=0;i--){
            sum+=sum_from_here_is_part_sum[i];
            count_part_sum_from_here[i]=sum;
        }

//for every i where sum so far=par_sum, check the value of sum_from_here_is_part_sum at i+2, and add that to count
        int count=0;
        for(int i=0;i<n;i++){
            if(i+2<n && sum_so_far[i]==par_sum){
                count+=count_part_sum_from_here[i+2];
            }//for
        }

        return count;
    }//solve


    public static void main(String[] args) {
        int[] A=new int[]{1, 2, 3, 0, 3};
        int B=A.length;
        int res=new Partitions().solve(B,A);
        System.out.println(res);
    }
}//Partitions
