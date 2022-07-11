package Array;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    /*The outline is simple: convert the int array into a string array, then sort it, then create a string buffer out of the
sorted string array, and finally convert the string buffer into string. But the meat of the algo is the sorting, where
the sorting logic needs to be tweaked. Firstly, the compared elements are strings instead of integers. Next, the sorting logic
has to be modified. Instead of checking int a> int b, we check if string a+string b> string b+string a. Meaning: for 21 and 3,
we will check which of the strings 213 vs 321 will result in a bigger integer. Then, as we are hunting for the largest number
formed here, we place the larger one first, not the smaller one: 321 before 213, that is, descending order */

    public String largestNumber(final int[] A) {
        int n=A.length;

        String[] strArr=new String[n];
        boolean all0=true;

        for(int i=0;i<n;i++){
            strArr[i]=Integer.toString(A[i]);
            if(A[i]!=0){
                all0=false;
            }
        }

        if(all0){
            return "0";
        }

        Arrays.sort(strArr, new Comparator<String>(){
            public int compare(String a, String b){
                if((a+b).compareTo(b+a)>0){
                    return  -1;
                }
                else if((a+b).compareTo(b+a)<0){
                    return  1;
                }
                else{
                    return 0;
                }
                //return (b+a).compareTo(a+b);
            }
        });
        StringBuilder sbf = new StringBuilder();

        for(int i=0;i<n;i++)
        {
            sbf.append(strArr[i]);
        }

        return sbf.toString();

    }//largestNumber


    public static void main(String[] args) {
        int[] A=new int[] {3, 30, 34, 5, 9};
        String res=new LargestNumber().largestNumber(A);
        System.out.println(res);
    }//main
}//LargestNumber
