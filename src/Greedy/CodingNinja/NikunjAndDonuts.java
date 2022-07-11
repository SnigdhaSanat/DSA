package Greedy.CodingNinja;

import java.util.Arrays;
import java.util.Scanner;

public class NikunjAndDonuts {
    /*Note that ALL of the donuts have to be eaten. Then it becomes a matter of which one to eat first.
    * Since the expression is a sum of the products c(i)*2^(number of donuts already eaten), to minimise the second part of the products
    * we can start with the highest calorie donut first. So sort in descending order.*/
    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int[] calories=new int[n];
    for(int i=0;i<n;i++){
        calories[i]=sc.nextInt();
    }//for
        //sort(ascending by default)
        Arrays.sort(calories);
//Now proceed in descending order
        long sum=0;
        int count=0;
          for(int i=n-1;i>=0;i--){
            double power=Math.pow(2,count);
            double tempSum=calories[i]*power;
            sum+=tempSum;
            count+=1;
        }//for
System.out.println(sum);
    }//main
}//NikunjAndDonuts
