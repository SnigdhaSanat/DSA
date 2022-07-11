package Greedy.CodingNinja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//https://classroom.codingninjas.com/app/classroom/me/567/content/9347/offering/69395/problem/2996
public class PerimeterWithConditions {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        ArrayList<Integer> al=new ArrayList<>();
        for(int i=0;i<n;i++){
            al.add(sc.nextInt());
        }//for

        /*Arrange in descending order first*/
        Collections.sort(al, Collections.reverseOrder());

        int maxPeri=-1;

        /*Now check for each of the triplets of the descending order arraylist, to cheek if it is a valid triangle */

        /*We have sorted in desc order. It means if a triangle is valid, it will be having the max perimeter. For a VALID triangle after this iteration
        to have the same perimeter as the current one, it has to have the exact same triplet. EG: (10, 9, 8) and (9,8,7). Since it is already sorted by desc,
        even for adjacent triplets, the perimeters will be equal only if 10 is replaced by 7 now, and 10==7 and so the in between numbers 9 and 8 also should
        be equal. But then this rules out the longest side and the longest smallest side conditions*/
        for(int i=0;i<al.size()-2;i++){
            //update the sides
            int side1=al.get(i);//the longest side, as it is in decs order
            int side2=al.get(i+1);
            int side3=al.get(i+2);

            if(side1<side2+side3){
                //valid triangle
                maxPeri=side1+side2+side3;

                System.out.println(side3+" "+side2+ " "+ side1);//as the question requires in asc order
                break;
            }//if
        }//for

        if(maxPeri==-1){
            System.out.println(-1);
        }
    }//main
}//PerimeterWithConditions
