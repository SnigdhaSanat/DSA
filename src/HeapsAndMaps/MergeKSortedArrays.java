package HeapsAndMaps;

import com.sun.source.tree.Tree;

import javax.lang.model.element.Element;
import java.util.*;

public class MergeKSortedArrays {

    class Element{
        int row;
        int column;

        Element(int r, int c){
            row=r;
            column=c;
        }//ctor
    }//class Element


    public int[] solve(int[][] A) {
        int k=A.length;
        int n=A[0].length;

         class ElementComparator implements  Comparator<Element>{
            @Override
            public int compare(Element e1, Element e2) {
                int row1=e1.row;
                int col1=e1.column;
                int row2=e2.row;
                int col2=e2.column;

                if(A[row1][col1]>=A[row2][col2]){
                    return 1;
                }
                else{
                    return -1;
                }
//                else{
//                    return 0;
//                }
            }//compare
        }//ElementComparator

        /*Make use of the fact that the arrays are sorted among themselves.
        Strategy: Maintain a min heap of size k, implemented by a priority queue. Initialize it with the first element of every row.
        Then, Keep removing the min of the k size heap. IF that row still has elements left, replace
        it with the next element of the row, whose element was last removed. Else do nothing */


        //store the row and index in the Priority Queue, NOT the values. Values can be accessed.
        TreeMap<Element,Integer> tm=new TreeMap<Element,Integer>(new ElementComparator());
        //PriorityQueue<Element> pq=new PriorityQueue<Element>(new ElementComparator());

        //Initialize pq with the first element(actually its row and column) of every row.
        //This way, all the elements will be unique as well
        for(int i=0;i<k;i++){
            Element e=new Element(i,0);//this will be unique
            tm.put(e,1);
            //pq.add(e);
            int test2=2;
        }//for

        /*Keep removing the min of the pq, and put that into the res[]. IF that row still has elements left,
        replace it with the next element of the row, whose element was last removed. Else do nothing */

        int[] res=new int[k*n];
        int index=0;
        while(!tm.isEmpty()){
        //while(!pq.isEmpty()){
             Element element=tm.firstEntry().getKey();

            //remove the first element

            //Element element=pq.poll();
            int row=element.row;
            int col=element.column;
            int value=A[row][col];
            res[index]=value;

             //remove the key
             //tm.remove(element,1);
             tm.pollFirstEntry();

             //replace conditionally
             int nextCol=col+1;
             if(nextCol<n){
                 Element newElem=new Element(row,nextCol);
                 tm.put(newElem,1);
                 //pq.add(newElem);
                 int test1=1;
             }//if

             index+=1;
         }//while

        return  res;
    }//solve

    public static void main(String[] args) {

        int[] list1=new int[]{1,2,3};
        int[] list2=new int[]{2,4,6};
        int[] list3=new int[]{0,9,10};

        int k=3;
        int n=list1.length;
        int[][] array=new int[k][n];
        array[0]=list1;
        array[1]=list2;
        array[2]=list3;

        int[] result=new MergeKSortedArrays().solve(array);
        for(int i=0;i<k*n;i++){
            System.out.println(result[i]);
        }

    }//main
}
