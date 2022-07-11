package Array;

import java.util.ArrayList;

public class SetMatrixZeros {
    public void setZeroes(ArrayList<ArrayList<Integer>> a) {
        int m=a.size();//rows
        int n=a.get(0).size();//columns
        /*rowTracker runs along the vertical, and column tracker along the horizontal. At any point, if a(i,j)=0, corresponding
        elements in both row and column trackers are made 0.*/

        /*As a space improvement, we can use the first row and column as colTracker and rowTracker respectively, and turn them into 0. This improvisation
        * looks simple, but the first rows and cols are the devils here*/


        boolean ifFirstRowContainsZeroOriginally=false;
        //check if the first row originally has 0
        for(int j=0;j<n;j++){
            if(a.get(0).get(j)==0){
                ifFirstRowContainsZeroOriginally=true;
            }
        }

        boolean ifFirstColContainsZeroOriginally=false;
        //check if the first col originally has 0
        for(int i=0;i<m;i++){
            if(a.get(i).get(0)==0){
                ifFirstColContainsZeroOriginally=true;
            }
        }


        /*Part 1: ALL the elements including the 1st rows and cols themselves affect the 1st row and 1st column*/
         for(int i=0;i<m;i++){
             for(int j=0;j<n;j++){
                 if(a.get(i).get(j)==0){
                     a.get(i).set(0,0);//rowTracker
                     a.get(0).set(j,0);//colTracker
                 }
             }//inner for loop
         }//outer for loop


       /*Part 2: First row and first column, which doubled up as trackers, affect all the elements EXCEPT the 1st row and 1st column*/
         for(int i=1;i<m;i++){
             for(int j=1;j<n;j++){
                 if(a.get(i).get(0)==0 || a.get(0).get(j)==0){
                     a.get(i).set(j,0);
                 }

             }//inner for loop
         }//outer for loop

        /*Part 3: 1st row and 1st column usually don't affect each other, and you can keep them as they are. HOWEVER, if the 1st row
        had a zero originally, then turn the 1st row  0. Same goes for col */

        if(ifFirstRowContainsZeroOriginally) {
            for (int j = 0; j < n; j++) {
                a.get(0).set(j, 0);
            }// for loop
        }
        if(ifFirstColContainsZeroOriginally)
        { for(int i=0;i<m;i++){
                 a.get(i).set(0,0);
            }// for loop
        }//if

        return;
    }//setZeroes

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> a=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row1= new ArrayList<Integer>();
        row1.add(0);
        row1.add(0);
        a.add(row1);
        ArrayList<Integer> row2= new ArrayList<Integer>();
        row2.add(1);
        row2.add(1);
        a.add(row2);
        new SetMatrixZeros().setZeroes(a);
    }
}//SetMatrixZeros
