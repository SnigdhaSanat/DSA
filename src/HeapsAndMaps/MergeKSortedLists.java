package HeapsAndMaps;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// Definition for singly-linked list.
class ListNode {
    public int val;
    public ListNode next;
    ListNode(int x) { val = x; next = null; }
}

public class MergeKSortedLists {
    class Element{
        int row;
        int column;

        Element(int r, int c){
            row=r;
            column=c;
        }//ctor
    }//class Element



    /*Using a TreeMap does not allow storing those row -col Elements, whose values are the same, as the comparator
    compares on the basis of values A[row][col], and for those, the comparator returns 0, hence treats those elements
    as duplicates.*/
    public ListNode mergeKLists(ArrayList<ListNode> AList) {
        int k=AList.size();//AList is the list of heads of linked lists

        //Converting the List of LinkedLists to ArrayList<ArrayList<Integer>> A
        ArrayList<ArrayList<Integer>> A=new  ArrayList<ArrayList<Integer>>();
        for(int i=0;i<k;i++){
            ListNode head=AList.get(i);
            ArrayList<Integer> al= new ArrayList<Integer>();
            ListNode curr=head;
            while(curr!=null){
                al.add(curr.val);
                curr=curr.next;
            }//while
            A.add(al);
        }//outer for

        class ElementComparator implements Comparator<Element> {
            @Override
            public int compare(Element e1, Element e2) {
                int row1=e1.row;
                int col1=e1.column;
                int row2=e2.row;
                int col2=e2.column;

                if(A.get(row1).get(col1)>A.get(row2).get(col2)){
                    return 1;
                }
                else  if(A.get(row1).get(col1)<A.get(row2).get(col2)){
                    return -1;
                }
                else{
                    return 0;
                }
            }//compare
        }//ElementComparator

    /*Make use of the fact that the arrays are sorted among themselves.
    Strategy: Maintain a min heap of size k, implemented by a priority queue. Initialize it with the first element of every row.
    Then, Keep removing the min of the k size heap. IF that row still has elements left, replace
    it with the next element of the row, whose element was last removed. Else do nothing */


        //store the row and index in the Priority Queue, NOT the values. Values can be accessed.
        PriorityQueue<Element> pq=new PriorityQueue<Element>(new ElementComparator());

        //Initialize pq with the first element(actually its row and column) of every row.
        //This way, all the elements will be unique as well
        for(int i=0;i<k;i++){
            Element e=new Element(i,0);//this will be unique
            pq.add(e);
        }//for

    /*pq is already sorted by the values. Keep removing the min of the pq, and put that into the res[]. IF that row still has elements left,
    replace it with the next element of the row, whose element was last removed. Else do nothing */

        ListNode head=null;
        ListNode currNode=null;
        //int[] res=new int[k*n];
        //int index=0;

        while(!pq.isEmpty()){

            //remove the first element
            Element element=pq.poll();

            int row=element.row;
            int col=element.column;
            int value=A.get(row).get(col);

            ListNode node=new ListNode(value);
            if(head==null){
                head=node;
                currNode=node;
            }
            else{
                currNode.next=node;
                currNode=node;
            }

            //replace conditionally
            int nextCol=col+1;
            int n=A.get(row).size();
            if(nextCol<n){
                Element newElem=new Element(row,nextCol);
                pq.add(newElem);
            }//if

        }//while

        return  head;
    }//mergeKLists

    public static void main(String[] args){

//        1 -> 10 -> 20
//        4 -> 11 -> 13
//        3 -> 8 -> 9

        ArrayList<ListNode> param=new ArrayList<ListNode>();
     //---------------------------------
        int[] list1=new int[]{ 1,10,20};

        ListNode head1=null;
        ListNode curr1=null;

        //creating a link list out of an array
        for (int i=0;i<list1.length;i++){
            if(i==0){
                curr1=new ListNode(list1[i]);
                head1=curr1;
            }
            else{
                ListNode n=new ListNode(list1[i]);
                curr1.next=n;
                curr1=n;
            }
        }//for
        param.add(head1);

//-----------------------------
        int[] list2=new int[]{ 4,11,13};

        ListNode head2=null;
        ListNode curr2=null;

        //creating a link list out of an array
        for (int i=0;i<list2.length;i++){
            if(i==0){
                curr2=new ListNode(list2[i]);
                head2=curr2;
            }
            else{
                ListNode n=new ListNode(list2[i]);
                curr2.next=n;
                curr2=n;
            }
        }//for
        param.add(head2);
//-------------------------------------
        int[] list3=new int[]{ 3,8,9};

        ListNode head3=null;
        ListNode curr3=null;

        //creating a link list out of an array
        for (int i=0;i<list3.length;i++){
            if(i==0){
                curr3=new ListNode(list3[i]);
                head3=curr3;
            }
            else{
                ListNode n=new ListNode(list3[i]);
                curr3.next=n;
                curr3=n;
            }
        }//for
        param.add(head3);
        new MergeKSortedLists().mergeKLists(param);
    }//main
}//MergeKSortedLists
