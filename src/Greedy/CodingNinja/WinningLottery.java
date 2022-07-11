package Greedy.CodingNinja;

import java.util.Scanner;
//https://classroom.codingninjas.com/app/classroom/me/567/content/9347/offering/69395/problem/3004

public class WinningLottery {
    public static void main(String[] args) {
        /*Keep on placing 9s from the end. When the sum remaining reduces to below 9, place the remaining sum. But be mindful of the case, where
        * the remaining sum is <9, but more than 1 place is left*/
        Scanner sc=new Scanner(System.in);
        int s=sc.nextInt();
        int d=sc.nextInt();

        String str="";

        //Case if it is a multiple of 9 and d is exactly that. Eg: s=9 and d=1, s=18 and d=2, s=27 and d=3
        if(d*9==s){
            for(int i=0;i<d;i++){
                str+="9";
            }
            System.out.println(str);
        }
        else{
            int sumSoFar=0;
        /*Note that the first place has to hold a 1 for the number to be minimum. Then the problem reduces to fn(s-1, d-1).
        This is applicable for the multiples of 9 as well , where d*9!=s. Eg for s=18, if d=3 or 4, and s=17 effectively, place 9, then 8 and then 0(if d=4)*/
            //Now finding or s-1 and d-1
            s=s-1;

            for(int i=d-1;i>=1;i--){
                if(s-sumSoFar>=9){
                    int digit=9;
                    //prepend to the string
                    str=String.valueOf(digit)+str;
                    sumSoFar+=digit;
                }
                else {
                    /*Note that sum<9 may be the case not just in the first(actually second) position, but even in the positions before reaching the first position.
                    * So (s-sumSoFar) will automatically take care of that*/
                    int digit=s-sumSoFar;
                    str=String.valueOf(digit)+str;
                    sumSoFar+=digit;

                }//outer else
            }//for

            //Prepend a 1 finally
            str="1"+str;
            System.out.println(str);
        }

    }//main
}//WinningLottery
