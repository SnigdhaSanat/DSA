package Array;

import java.util.ArrayList;

public class RotateMatrix {
    public void rotate(ArrayList<ArrayList<Integer>> a) {

        /*IMPORTANT: The question requires an in-place rotation*/
        /*Convert A to transpose of A, and then reverse it along the y axis*/

        int n=a.size();
        if(n<2){
            return;
        }//if

        //Create a transpose(interchange rows and columns) of the matrix
        for (int i=0;i<n;i++)
        {
            for (int j=i+1;j<n;j++){
                int temp=a.get(i).get(j);
                a.get(i).set(j,a.get(j).get(i));
                a.get(j).set(i,temp);
            }//inner loop
        }//outer loop


        /*Do a lateral inversion along the y axis, meaning first column becomes last column,
        2nd column becomes second last and so on*/
        for(int j=0;j<n/2;j++){
            //traverse along the column
            //ArrayList<Integer> left=new ArrayList<Integer>();
            //ArrayList<Integer> right=new ArrayList<Integer>();
            for(int i=0;i<n;i++){
                //traverse along the row
                int temp=a.get(i).get(j);//left;
                a.get(i).set(j,a.get(i).get(n-1-j));
                a.get(i).set(n-1-j,temp);
            }
        }

        return;

    }//rotate

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> a=new ArrayList<ArrayList<Integer>> ();
        ArrayList<Integer> r1=new ArrayList<Integer>();
        r1.add(1);
        r1.add(2);
        r1.add(3);

        ArrayList<Integer> r2=new ArrayList<Integer>();
        r2.add(4);
        r2.add(5);
        r2.add(6);

        ArrayList<Integer> r3=new ArrayList<Integer>();
        r3.add(7);
        r3.add(8);
        r3.add(9);

        a.add(r1);
        a.add(r2);
        a.add(r3);
        new RotateMatrix().rotate(a);
    }//main
}//RotateMatrix
