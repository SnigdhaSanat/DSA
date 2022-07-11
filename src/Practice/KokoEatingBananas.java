package Practice;
//https://leetcode.com/submissions/detail/684498878/

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int n=piles.length;
        boolean found=false;

        long k=1;

        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(piles[i]>max){
                max=piles[i];
            }//if
        }//for

//binary search. low:1, high: max of piles[]

        long low=1;
        long high=max;



        while(low<high){

            long mid=(low+high)/2;

            long sum=0;
            for(int i=0;i<n;i++){
                long current=((long)piles[i]/mid);
                if(piles[i]%mid!=0){
                    current+=1;
                }
                sum+=current;
            }//for

            if(sum>h){
                //k has to be increased
                low=mid;

                if(low+1==high){
                    /*for sum>h, we do low=mid. But if low+1==high, mid will always be low, and we will be
                    * stuck in an infinite loop. */
                    break;
                }
            }

            else{
                //if sum is less OR equal to h, we can still decrease k, as min is required

                k=mid;// store the value
                high=mid;
            }
        }//while

        if(k==1) return (int)high;
        return (int)k;
    }//minEatingSpeed

    public static void main(String[] args) {
        int[] piles=new int[]{30,11,23,4,20};
        int h=4;
        //int res=new KokoEatingBananas().minEatingSpeed(piles,h);
        double res=(double)229/990;
        System.out.println(res);
    }
}//KokoEatingBananas
