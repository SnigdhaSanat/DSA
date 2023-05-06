package DP.CodingNinja;

//https://www.hackerrank.com/challenges/sam-and-substrings/problem
public class SamAndSubstrings {
    public static int substrings(String str) {
        int modulo=1000000007;

        int len=str.length();

        //base case of i=0
        long lastVal= Integer.parseInt(str.substring(0,1));//the first(left most) digit
        long sum=lastVal;

        //Note that we proceed from left to right,unlike a number
        for(int i=1;i<len;i++){
            /*Say we have for "1": 1.
            So for "12", we will add to it, 12 and 2 in addition to 1.
            For "123" we add to it, 23, 123 and 3.
            For "1234" we add to it 4, 34, 234, 1234.

            Diff for 1:            1//Add it to sum
            Diff for 12: ('2')+ ('1'*10+2) = 2+12= 14 //Add it to sum
            Diff for 123:('3')+('2'*10+3)+('12'*10+3) =3+23+123=149 //Add it to sum
            Diff for 1234:('4')+('3'*10+4)+('23'*10+4)+('123'*10+4) =4+34+234+1234=1506 //Add it to sum


As shown above, for each of the diffs, we add 'itself' (i+1) number of times. But the other, slightly complicated realization is that we multiply
10 to each of constituents of the previous diff. Eg:  Diff for 12 is 2+12. So for diff for 123, we add *10 to 2 and 12. Meaning, 2*10+12*10.
For diff of 1234,we add 3*10+23*10+123*10. So effectively, we add multiply the previous diff by 10. This is what 'a' below represents.


            Finally we return the sum from memo[0] to memo[len-1]*/

            char ch=str.charAt(i);
            int itself=Character.getNumericValue(ch);//will be 0-9
            long a=lastVal*10; //mod will anyway have been applied at lastVal
            long b=itself*(i+1); //'itself' is a small number, and so is (i+1)

            // update lastValue, which is the diff
            lastVal=(a+b)%modulo; //the sum may be get big
            sum=(sum+lastVal)%modulo; //lastVal has already been moded and so is prev sum. Just mod the current sum
        }//for

        return (int)sum;
    }//substrings

    public static void main(String[] args) {
        //972698438521   ....445677619
        String s="1234";//972698438521
        int res=SamAndSubstrings.substrings(s);
        System.out.println(res);
    }//main
}//SamAndSubstrings
