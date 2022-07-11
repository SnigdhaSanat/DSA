package HeapsAndMaps;

import java.util.*;

public class Test {
    /*This was failing repeatedly. Reson being same class was used for pq and dupChecker. Their purposes are however different.
    pq is used for maintaing the order. So its class, Element, should be implementing only Comparable. dupChecker
    is to check duplicates. So its class, ElementDupChecker, should be implementing only hashCode and equals*/
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
            int compValue= Integer.compare(e1.sum, sum);
            return compValue;
        }//compare

    }//class ElementDupChecker

    public int[] solve(int[] A, int[] B) {
        int n=A.length;

        //ascending order
        Arrays.sort(A);
        Arrays.sort(B);

        //This will store the index pairs, in descending order of sums
        //Ordering is its job
        //PriorityQueue<ElementDupChecker> pq=new PriorityQueue<ElementDupChecker>();
        TreeMap<ElementDupChecker,Integer> pq=new TreeMap<ElementDupChecker,Integer>();
        //PriorityQueue<ElementDupChecker> pq=new PriorityQueue<ElementDupChecker>();

        //dupChecker will have same insertion order as pq(although ordering is not its job).
        // Except that no items will be deleted from it
        //pq cannot check for duplicacy once an element is popped out. dupChecker will do this job
        //HashSet<ElementDupChecker> dupChecker=new HashSet<ElementDupChecker>();
        TreeMap<ElementDupChecker,Integer> dupChecker=new TreeMap<ElementDupChecker,Integer>();
        //HashSet<ElementDupChecker> dupChecker=new HashSet<ElementDupChecker>();

        int[] res=new int[n];

        //putting the (0,0) element

        //pq.add(new ElementDupChecker(n-1,n-1,A[n-1]+B[n-1]));
        //dupChecker.add(new ElementDupChecker(n-1,n-1,A[n-1]+B[n-1]));

        pq.put(new ElementDupChecker(n-1,n-1,A[n-1]+B[n-1]),1);
        dupChecker.put(new ElementDupChecker(n-1,n-1,A[n-1]+B[n-1]),1);

        //pq.add(new ElementDupChecker(n-1,n-1,A[n-1]+B[n-1]));
        //dupChecker.add(new ElementDupChecker(n-1,n-1,A[n-1]+B[n-1]));
        int resIndex=0;

        while(resIndex<n){
            ElementDupChecker max=pq.firstEntry().getKey();
            //ElementDupChecker max=pq.peek();

            int aInd=max.aIndex;
            int bInd=max.bIndex;


            int sum=A[aInd]+B[bInd];


            if(aInd-1>=0 && bInd>=0 && !dupChecker.containsKey(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]))){
            //if(aInd-1>=0 && bInd>=0 && !dupChecker.contains(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]))){
                //pq.add(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]));
                //dupChecker.add(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]));

                pq.put(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]),1);
                dupChecker.put(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]),1);

                //pq.add(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]));
                //dupChecker.add(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]));
            }


            int test1=aInd;
            int test2=bInd-1;
            //boolean test3= !dupChecker.containsKey(new ElementDupChecker(aInd,bInd-1,A[aInd]+B[bInd-1]));
            if(aInd>=0 && bInd-1>=0 && !dupChecker.containsKey(new ElementDupChecker(aInd,bInd-1,A[aInd]+B[bInd-1]))){
            //if(aInd>=0 && bInd-1>=0 && !dupChecker.contains(new ElementDupChecker(aInd,bInd-1,A[aInd]+B[bInd-1]))){
                //pq.add(new ElementDupChecker(aInd,bInd-1,A[aInd]+B[bInd-1]));
                //dupChecker.add(new ElementDupChecker(aInd,bInd-1,A[aInd]+B[bInd-1]));

                pq.put(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]),1);
                dupChecker.put(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]),1);

                //pq.add(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]));
                //dupChecker.add(new ElementDupChecker(aInd-1,bInd,A[aInd-1]+B[bInd]));
            }

            res[resIndex++]=sum;
            //pq.poll();
            pq.remove(max);
        }//outer while

        return res;


    }//solve

    public static void main(String[] args){

        int[] A=new int[]{ 3, 2, 4, 2 };
        int[] B=new int[]{4, 3, 1, 2};
        int[] result=new Test().solve(A,B);

        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }

    }//main
}