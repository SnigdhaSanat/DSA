package HeapsAndMaps;

import java.util.*;

class NMaxPairCombinations {
    public static class ElementDupChecker{
        int aIndex;
        int bIndex;

        public ElementDupChecker(int aIndexParam, int bIndexParam){
            this.aIndex=aIndexParam;
            this.bIndex=bIndexParam;
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

    }//class ElementDupChecker

    public static class Element implements Comparable<Element>{
        int aIndex;
        int bIndex;
        int sum;

        public Element(int aIndexParam, int bIndexParam, int sum){
            this.aIndex=aIndexParam;
            this.bIndex=bIndexParam;
            this.sum=sum;
        }//ctor

        @Override
        public int compareTo(Element e1) {
            return Integer.compare(e1.sum, sum);
        }//compare
    }//Element



public int[] solve(int[] A, int[] B) {
    int n=A.length;

    //ascending order
    Arrays.sort(A);
    Arrays.sort(B);

//    int[] ASorted=new int[n];
//    int[] BSorted=new int[n];
//    //descending order
//    for(int i=n-1;i>=0;i--){
//        ASorted[n-i-1]=A[i];
//        BSorted[n-i-1]=B[i];
//    }//for



    //This will store the index pairs, in descending order of sums
    //Ordering is its job
    PriorityQueue<Element> pq=new PriorityQueue<Element>();

    //dupChecker will have same insertion order as pq(although ordering is not its job).
    // Except that no items will be deleted from it
    //pq cannot check for duplicacy once an element is popped out. dupChecker will do this job
    HashSet<ElementDupChecker> dupChecker=new HashSet<ElementDupChecker>();

    int[] res=new int[n];

    //putting the (0,0) element

    pq.add(new Element(n-1,n-1,A[n-1]+B[n-1]));
    dupChecker.add(new ElementDupChecker(n-1,n-1));
    int resIndex=0;

    while(resIndex<n){
        Element max=pq.peek();
        int aInd=max.aIndex;
        int bInd=max.bIndex;


        int sum=A[aInd]+B[bInd];


        if(aInd-1>=0 && bInd>=0 && !dupChecker.contains(new ElementDupChecker(aInd-1,bInd))){
            pq.add(new Element(aInd-1,bInd,A[aInd-1]+B[bInd]));
            dupChecker.add(new ElementDupChecker(aInd-1,bInd));
        }


        if(aInd>=0 && bInd-1>=0 && !dupChecker.contains(new ElementDupChecker(aInd,bInd-1))){
            pq.add(new Element(aInd,bInd-1,A[aInd]+B[bInd-1]));
            dupChecker.add(new ElementDupChecker(aInd,bInd-1));
        }

        res[resIndex++]=sum;
        pq.poll();
    }//outer while

    return res;
    }

public static void main(String[] args){

        int[] A=new int[]{48,46,45,43,43,42,41,40,37,36,36,34,34,32,32,31,30,28,27,26,23,23,22,20,20,19,18,17,17,17,17,14,13,12,12,12,9,8,7,6,6,0,
                -1,-4,-5,-7,-8,-10,-13,-14,-14,-15,-15,-16,-20,-21,-21,-21,-21,-23,-23,-24,-24,-24,-25,-26,-26,-27,-28,-29,-29,-31,-35,-35,-36,-37,
                -37,-39,-39,-42,-45,-45,-48 };
        int[] B=new int[]{49,47,47,47,46,45,44,43,42,39,38,38,36,36,36,34,33,31,30,29,28,26,25,21,21,20,20,18,18,17,17,15,15,14,10,6,6,5,4,3,2,1,1,
                0,-1,-4,-5,-6,-9,-9,-10,-10,-11,-11,-16,-18,-18,-19,-21,-21,-22,-22,-23,-24,-26,-27,-29,-31,-31,-31,-32,-33,-33,-35,-38,-41,-42,-44,
                -47,-47,-48,-49,-49};
        int[] result=new NMaxPairCombinations().solve(A,B);

        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }

    }//main
}//NMaxPairCombinations


