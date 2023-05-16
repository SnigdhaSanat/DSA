package LinkedList;

public class SortBinaryLinkedList {

//Definition for singly-linked list.
 private static class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x)
      {
        val = x;
        next = null;
      }
  }

	public ListNode solve(ListNode A) {
		/**Question requirement: Do in constant space*/
		// count the number of 0s and 1s, then traverse the list again and put them according to the counts
		int  zeroCount=0;
		int oneCount=1;

		ListNode currNode=A;

		while(currNode!=null){
			if(currNode.val==0){
				zeroCount+=1;
			}
			else{
				oneCount+=1;
			}
			currNode=currNode.next;
		}//while

		currNode=A;

		while(currNode!=null){
			if(zeroCount>0){
				currNode.val=0;
				zeroCount-=1;
			}
			else{
				currNode.val=1;

				//This is optional
				//oneCount-=1;
			}
			currNode=currNode.next;
		}//while

		return A;
	}//solve

	public static void main(String[] args) {
	int[] A=new int[]{ 0,0, 1, 1, 0};
	ListNode head=null;
	ListNode prev=null;
	ListNode curr=null;

	for(int i=0;i<A.length;i++){
		if(head==null){
			curr=new ListNode(A[i]);
			head=curr;
		}
		else{
			curr=new ListNode(A[i]);
			prev.next=curr;
		}

		prev=curr;
	}

	ListNode res=new SortBinaryLinkedList().solve(head);
	curr=res;
	while(curr!=null){
		System.out.println(curr.val);
		curr=curr.next;
	}
	}//main
}//SortBinaryLinkedList
