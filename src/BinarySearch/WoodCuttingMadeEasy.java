package BinarySearch;

public class WoodCuttingMadeEasy {
    public int solve(int[] A, int B) {
/**Sorting is not required. In general, for the main binary search we will have nlogX time complexity(where X is the max height of the tree, n: array
size). We multiple by n because we call getWood every time. Sorting will add further nlogn complexity.

Find the max height. So answer can be from 0 to max height. Search it via binary search. At every iteration, when mid is found, calculate
the wood_sum, by adding A[i]-mid. Since aim is to find the maximum possible mid(chop as less trees as possible), so if wood_sum>=B, left=mid+1 */
        int n=A.length;
        int maxHt=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            maxHt=Math.max(maxHt,A[i]);
        }


/**
Decide:
Start:l=0, r=maxHt
During:At every iteration update what:  wood=Sum(A[i]-mid). If wood>B, left=mid+1, and we can optimize further So store mid in possibleValue. Else,right=mid-1
If wood=B, short circuit
End when:l<=r
Return what: possibleValue.
*/
        int left=0;
        int right=maxHt;

        int possibleValue=-1;

        while(left<=right){
            int mid=(left+right)/2;

            long wood=getWood(A,  mid);

            if(wood==(long)B){
                //for short circuiting in case of exact match
                return mid;
            }

            if(wood>(long)B){
                //shift right. We have more wood than required. But we would like to optimize further
                possibleValue=mid;
                left=mid+1;
            }//if

            else{
                //wood<(long)B
                //shift left
                right=mid-1;
            }//else
        }//while

        return possibleValue;

    }//solve

    static public long getWood(int[] A, int mid)
    {
        long wood=0;
        for (int idx=0;idx<A.length;idx++ )
        {
            wood+=Math.max(0,(long)A[idx]-(long)mid);
        }// for inner loop
        return wood;
    }//getWood

    public static void main(String[] args) {
        int[] A = {4, 42, 40, 26, 46};
        int B = 20;
        int res=new WoodCuttingMadeEasy().solve(A,B);
        System.out.println(res);
    }//main
}//WoodCuttingMadeEasy
