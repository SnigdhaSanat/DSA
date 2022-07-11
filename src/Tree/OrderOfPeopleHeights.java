package Tree;

import java.util.ArrayList;
import java.util.Collections;


class SegUtility{
    int toLookFor;
}
public class OrderOfPeopleHeights {
/* https://www.youtube.com/watch?v=ZBHKZF5w4YU

* Why query complexity is log(n)?: Since the range query is asking for a contiguous range, we know that the 3 or 4 nodes visited at level i can be
* categorized into 3 partitions of nodes: a leftmost single node whose segment range is only partially covered by the query range, a rightmost single
* node whose segment range is only partially covered by the query range, and 1 or 2 middle nodes whose segment range is fully covered by the query range.
* Since the middle nodes have their segment range(s) fully covered by the query range, there would be no recursion at the next level; we just use their
* precomputed sums. We are left with possible recursions on the leftmost node and the rightmost node at the next level, which is obviously at most 4.*/

    public int[] order(int[] A, int[] B) {

        class Utility implements Comparable<Utility>{
            int height;
            int inFront;
            Utility(int h, int front){
                height=h;
                inFront=front;
            }

            @Override
            public int compareTo(Utility u) {
                //ascending order of height
                if(this.height>u.height){
                    return 1;
                }
                else if(this.height<u.height){
                    return -1;
                }
                else{
                    return 0;
                }
            }//compareTo
        }//Utility

        int n=A.length;
        ArrayList<Utility> al=new ArrayList<Utility>();

        for(int i=0;i<n;i++){
            Utility elem=new Utility(A[i], B[i]);
            al.add(elem);
        }//for

        //sort A and B by ascending order of A
        Collections.sort(al);


        //-------------Without using segment tree-------------------
/*Now that we have the arrays sorted in increasing order of heights, we need to place each of the height
elements in its right place. How? The sorted array we have is:
        heights: 1 2 3 4 5 6
        inFront: 3 2 1 2 0 0
For each of the height element starting from the start, we have to place it such that there are 'inFront' number
* of empty spaces in front of it. We also need to take care of the spaces which are now filled. First let us solve
without the use of segment tree.
        */

/*         int[] res=new int[n];

             //placing each element at its right place
             for(int i=0;i<n;i++){
             int count=0;
             int emptyPlacesRequiredInFront=al.get(i).inFront;

             int j=0;
             while(count<emptyPlacesRequiredInFront){
                 if(res[j]==0){
                     count+=1;
                 }
                 j+=1;
             }//inner loop

             //required #empty places found, so find the next empty place to place the element
             while(res[j]!=0){
                 j+=1;
             }//inner loop
             res[j]=al.get(i).height;

             }//outer for

             return res;*/

        //-------------Using segment tree-------------------
        //IMP:We query on #empty spaces.  We look at the node having the exact match to this number

        SegUtility segUtility=new SegUtility();
        segUtility.toLookFor=-1;

        int[] res=new int[n];

        //if the array len n is a power of 2, len=2*n-1,
        //else len=2*(next power of 2)-1; So find the generic way

        //Math.log(2), as by default it computes log e
        int x=(int)Math.ceil(Math.log(n)/Math.log(2));
        int len=2*(int)Math.pow(2,x)-1;
        int[] segmentTree=new int[len];

        constructSegmentTree(segmentTree,0,n-1,0);

        for(int i=0;i<n;i++)
        {
            segUtility.toLookFor=al.get(i).inFront+1;
            int posToInsert=querySegmentTree(segUtility,segmentTree,len,0,n-1,0);
            res[posToInsert]=al.get(i).height;

            //decrements the #empty nodes along the root to leaf path
            updateSegmentTree( posToInsert, segmentTree, len, 0,  n-1, 0 );
        }//for

        return res;
    }//order


    /*This will contain the list of empty nodes in each segment*/
    int constructSegmentTree( int[] segArr, int start, int end, int pos ){
        //IMP: The information regarding the range is not put in the tree. But
        //will be computed again for min query, sum query or update query as start and end

        //Put the leaf node values as 1, meaning the #empty nodes there is 1.
        //For rest, sum its 2 children
        if(start==end){
            //base condition
            segArr[pos]=1;
            return segArr[pos] ;
        }
        int mid=(start+end)/2;
        segArr[pos]= constructSegmentTree(segArr,start,mid,2*pos+1)+
        constructSegmentTree(segArr,mid+1,end,2*pos+2);

        return segArr[pos];
    }

    int querySegmentTree(SegUtility segUtility,int[] segArr,int len,int start, int end,int pos){
        int valToCheck=segUtility.toLookFor;
        int valueAtNode=segArr[pos];

        int mid=(start+end)/2;


        if(valueAtNode<valToCheck){
            //if less, get diff remaining, and update segUtility.toLookFor
            //return -1
            int diff=valToCheck-valueAtNode;
            segUtility.toLookFor=diff;
            return -1;
        }


        else
            //if(valueAtNode==valToCheck) or if(valueAtNode>valToCheck)
            {

            /*if exact match or more, it MIGHT still be present in its left child rather than the root. So recurse left
            * Eg: if the segment tree of 3 elements has range (0,1), (0,0) and (1,1), and if the RC's value has already been decremented
            * to 0 in the previous iteration, then (0,1)'s end value is 1, which is deceptive, as in this case we need its LC (0,0).
            * One way to get around this is updating start/end of a node as and when its children's value become 0.*/

            //unless of course the start and end values are the same. In that case return end(or start)
            if(start==end){
                //IMP: base condition, which is the exact match to the #empty nodes we are looking for
                return end;
            }
            int leftRightRes=-1;

            if(2*pos+1<len && start<=mid ){
                //recurse left
                 leftRightRes= querySegmentTree(segUtility,segArr,len,start,mid,2*pos+1);
            }
            if(leftRightRes==-1 && 2*pos+2<len && mid+1<=end){
                //if the left child returns -1(less #empty nodes present), only then recurse right
                leftRightRes= querySegmentTree(segUtility,segArr,len,mid+1,end,2*pos+2);
            }
                /*return the end, meaning the end of range. Why end?
                we are passing front+1 to the param. So for front=2, we are passing 3.
                So if a node(Say, start:7, end:15) has value 3, and it is an exact match,
                it means NONE of its children has an exact match. It cumulatively has. So the 3rd space after
                2 empty spaces has to be at 15th position*/

            //since this is the case of 'exact', leftRightRes can't be -1
                return leftRightRes;
        }

    }//querySegmentTree

    void updateSegmentTree(int param,int[] segArr,int len,int start, int end,int pos ){

        //if not in range, return
        if(param <start || end<param)
        {
            return;
        }

        else {
        //if(start<=param && param<=end), that is, it is in range

            //decrement by 1, leaf or non-leaf
            segArr[pos]-=1;

            if(start==end){
                //if it is a leaf node, return from here
                return;
            }

        int mid=(start+end)/2;

        //recurse left
        if(2*pos+1<len && start<=mid) {
            updateSegmentTree(param, segArr, len, start, mid, 2 * pos + 1);
        }

        //recurse right
        if(2*pos+2<len && mid+1<=end) {
            updateSegmentTree(param, segArr, len, mid + 1, end, 2 * pos + 2);
        }

        return;

        }//else
    }//updateSegmentTree

    public static void main(String[] args) {
        int[] heights=new int[]{86,77};
        int[] inFront=new int[]{0, 1};

      int[] result= new  OrderOfPeopleHeights().order(heights,inFront);
      for(int i=0;i<result.length;i++){
          System.out.println(result[i]);
      }

    }
}//OrderOfPeopleHeights
