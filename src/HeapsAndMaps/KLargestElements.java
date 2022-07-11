package HeapsAndMaps;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class KLargestElements {

public int[] solve(int[] A, int B) {
    int n=A.length;

    TreeMap<Integer,Integer> tm=new TreeMap<Integer,Integer>();

    for(int i=0;i<n;i++){
        int elem=A[i];
        if(tm.containsKey(elem)){
            int value=tm.get(elem);
            tm.put(elem,value+1);
        }
        else{
            tm.put(elem,1);
        }

    }//for

    int[] res=new int[B];
    for(int i=0;i<B;i++){
        //System.out.println("i:"+i);
        int maxKey=tm.lastEntry().getKey();
        int maxValue=tm.lastEntry().getValue();
        //System.out.println("max:"+max);
        if(maxValue==1){
            //if it is a NON DUPLICATE
            tm.remove(maxKey);
        }
        else{
            tm.put(maxKey,maxValue-1);
        }
        res[i]=maxKey;
    }

    return res;
}//solve

public static void main(String[] args) {
int[] list=new int[]{543, 620, 914, 592, 705, 819, 233, 751, 206, 976, 540, 304, 423, 99, 248, 585, 649, 972, 865, 914, 76, 546, 713, 547, 679, 770, 263, 520, 986,
        290, 945, 866, 541, 246, 509, 319, 871, 602, 324, 133, 473, 153, 88, 571, 764, 902, 104, 424, 528, 601, 970, 16, 566, 29, 544, 348, 89, 944, 638, 410, 464,
        50, 682, 589, 343, 609, 61, 222, 759, 955, 889, 147, 691, 950, 844, 431, 621, 749, 68, 537, 784, 36, 227, 186, 39, 854, 630, 225, 749, 924, 360, 258, 767,
        945, 956, 319, 727, 412, 26, 356, 2, 550, 497, 585, 516, 965, 343, 76, 914, 143, 197, 949, 73, 427, 607, 174, 430, 405, 706, 627, 813, 376, 94, 566, 37, 737,
        142, 815, 995, 257, 653, 937, 839, 483, 356, 16, 132, 231, 842, 626, 12, 638, 187, 691, 651, 663, 635, 894, 354, 417, 453, 9, 263, 234, 455, 304, 635, 304, 257,
        149, 125, 318, 214, 110, 29 };

int B=161;

new KLargestElements().solve(list,B);
    }//main
}
