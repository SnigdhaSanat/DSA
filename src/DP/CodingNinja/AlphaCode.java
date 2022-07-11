package DP.CodingNinja;

import java.util.Scanner;

/*https://classroom.codingninjas.com/app/classroom/me/567/content/9694/offering/73036/problem/1701*/
public class AlphaCode {

    private static long decodeCount(String s){
        /*Take 2143. How do you utilize its sub-problems? fn(2143)=fn(214)+fn(21), where the remaining parts are 3 and 43 respectively.
        * Of course you can prepend instead of appending. That is, fn(2143)=fn(143)+fn(43), where the remaining parts are 2 and 21
        * respectively. But the advantage of appending is that for fn(0..n), you find fn(0...n-1) and fn(0...n-2), while in prepending,
        * for fn(0...n), you find fn(1....n) and fn(2....n), which means you will have to travel backwards.
        *
        * 2nd, you will choose fn(n-1) and fn(n-2) conditionally. In the example, choose 2 if it is >0, and choose '21' if it is <=26*/

        int len=s.length();

        if(len==0){
            return 1;//will return empty string
        }
        else if(len==1){
            return 1;//first char or the only char will have only 1 combo
        }
        else{
            //Take a res[] of length len+1, and store the result of the empty string in res[0],
            //result of(till) 1st char in res[1] and so on.

            //NOTE: res[i] means #combos from 0th to (i-1)th char, or 1st to ith char
            int[] res=new int[len+1];//1st char will start at res[1]. res[0] will store empty string

            //Eg: 2143
            res[0]=1; // empty string can have only 1 combo, that is ''
            res[1]=1;//1...1 , that is '2' can have only 1 combination:'b'.

            //eg: fn(2143) can be fn(214) or fn(21) where the remaining parts are 3 and 43 respectively.
            //So check if '3' is valid in the 1st case and '43' is valid in the 2nd case
            for(int i=2;i<=len;i++){
                //Note: although we are comparing current and prev char, we are using i-1 and i-2 instead of
                //i and i-1. This is because although res[i] stores 1st to ith char res in 1-based index, while
                //accessing chars, we will have to use the original 0 based index
                int prevChar=Character.getNumericValue(s.charAt(i-2));
                int currChar=Character.getNumericValue(s.charAt(i-1));
                int prevAndCurrCombined=prevChar*10+currChar;

                /*Say we are currently calculating '214'. So we have prevChar:1, currChar:4, prevAndCurrCombined:14
                The first 'if' condition checks  if currChar(4 in this case) is valid.As currChar is a single digit, so checking
                currChar>0 is sufficient. If so, we just add to res[i], res[i-1], meaning for every combinations of 21, we just append 4.*/
                if(currChar>0){
                    //if it is 0, reject
                    res[i]=res[i]+res[i-1]%1000000007;
                }
/*We also need to check what if we append '14'. As validation, we have to check if prevAndCurrCombined:14, which is a double digit,
* is valid. So we check <=26. But what if it is like 05? So we also check if prevChar is >0. If so, we append '14' to all combinations of '2',
* which is effectively res[i]+=res[i-2].
*
* Note that in this case since we are dealing with alphabets, we only have to go till [i-2], since i-3 would mean appending a 3 digit which is beyond the
* alphabet limit of 26. If the question was something else, we might have to go till (i-3), (i-4), and so on, whatever the question demanded. */
                if(prevChar>0 && prevAndCurrCombined<=26){
                    res[i]=(res[i]+res[i-2]%1000000007)%1000000007;
                }//if
            }//for

            return res[len]%1000000007;
        }//else
    }//decodeCount

    public static void main(String[] args) {
        //Scanner sc=new Scanner(System.in);
        //String input=sc.next();
        //while(!input.equals("0")){
            System.out.println(decodeCount("2143"));//3020
            //input=sc.next();
       // }//while
    }//main
}//AlphaCode
