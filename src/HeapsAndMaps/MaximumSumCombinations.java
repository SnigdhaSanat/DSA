package HeapsAndMaps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

public class MaximumSumCombinations {
    public static class ElementDupChecker implements Comparable<ElementDupChecker>{
        int aIndex;
        int bIndex;
        int sum;

        public ElementDupChecker(int aIndexParam, int bIndexParam,int sum){
            this.aIndex=aIndexParam;
            this.bIndex=bIndexParam;
            this.sum=sum;
        }//ctor

        @Override
        public int hashCode() {
            return Objects.hash(aIndex, bIndex);
        }

        @Override
        public boolean equals(Object o) {
            if(o==null){
                return false;
            }
            if(!(o instanceof ElementDupChecker)){
                return false;
            }
            ElementDupChecker obj=(ElementDupChecker) o;
            return(aIndex==obj.aIndex && bIndex==obj.bIndex);
        }

        @Override
        public int compareTo(ElementDupChecker e1) {
            //descending
            return Integer.compare(e1.sum, sum);
        }//compare

    }//class ElementDupChecker

    public int[] solve(int[] A, int[] B, int C) {
        int n=A.length;

        //ascending order
        Arrays.sort(A);
        Arrays.sort(B);

        //This will store the index pairs, in descending order of sums
        //Ordering is its job
        PriorityQueue<ElementDupChecker> pq=new PriorityQueue<ElementDupChecker>();

        //dupChecker will have same insertion order as pq(although ordering is not its job).
        // EXCEPT that no items will be deleted from it
        //IMP: pq cannot check for duplicacy once an element is popped out. dupChecker will do this job
        HashSet<ElementDupChecker> dupChecker=new HashSet<ElementDupChecker>();

        int[] res=new int[C];

        //putting the (0,0) element

        pq.add(new ElementDupChecker(n-1,n-1,A[n-1]+B[n-1]));
        dupChecker.add(new ElementDupChecker(n-1,n-1,A[n-1]+B[n-1]));
        int resIndex=0;

        while(resIndex<C){
            ElementDupChecker max=pq.peek();
            int aInd=max.aIndex;
            int bInd=max.bIndex;


            int sum=A[aInd]+B[bInd];


            if(aInd-1>=0 && bInd>=0 && !dupChecker.contains(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]))){
                pq.add(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]));
                dupChecker.add(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]));
            }


            if(aInd>=0 && bInd-1>=0 && !dupChecker.contains(new ElementDupChecker(aInd,bInd-1,A[aInd]+B[bInd-1]))){
                pq.add(new ElementDupChecker(aInd,bInd-1,A[aInd]+B[bInd-1]));
                dupChecker.add(new ElementDupChecker(aInd,bInd-1,A[aInd]+B[bInd-1]));
            }

            res[resIndex++]=sum;
            pq.poll();
        }//outer while

        return res;
    }//solve

    public static void main(String[] args) {
        int[] A={1, 4, 2, 3};
        int[] B={2, 5, 1, 6};
        int C=4;

        int[] res=new MaximumSumCombinations().solve(A,B,C);

        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }
}//MaximumSumCombinations
