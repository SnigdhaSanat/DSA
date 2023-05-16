package LinkedList;

public class ReverseAlternateKNodes {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode solve(ListNode A, int B) {
		/** This solution takes care of the case even when list size is not divisible by K. Send K elements each time to the reverse function. Do this for n/K times. For the last pass, pass the remaining elements*/

		if(A==null || A.next==null)
		{
			//for a size 1 or 0 list, return the head A itself
			return A;
		}

		ListNode globalHead=null;//start of the entire K reversed list

		ListNode prevEnd=null;//end of the previous k length reverse list

		ListNode curr=A;
		ListNode prev=null;
		boolean shouldReverse=true;

		while(curr!=null){
			int count=0;
			ListNode currHead=null;

			while(count<B && curr!=null){
				if(currHead==null){
					currHead=curr;
				}

				prev=curr;
				curr=curr.next;
				count+=1;
			}//inner loop

			prev.next=null;

			ListNode reversedOrNotHead=null;
			if(shouldReverse){
				reversedOrNotHead=reverse(currHead);//start of the current k reversed list
			}
			else{
				reversedOrNotHead=currHead;
			}
			shouldReverse=!shouldReverse;

			if(globalHead==null){
				globalHead=reversedOrNotHead;
			}

			if(prevEnd!=null){
				prevEnd.next=reversedOrNotHead;
			}

			//get the end of the reversed list, for the NEXT Iteration
			ListNode currRev=reversedOrNotHead;
			ListNode prevRev=null;
			while(currRev!=null){
				prevRev=currRev;
				currRev=currRev.next;
			}
			prevEnd=prevRev;

		}//outer loop

		return globalHead;

	}//solve

	ListNode reverse(ListNode head){

		ListNode prev=null;
		ListNode curr=head;
		ListNode nxt=curr.next;
		while(curr!=null){
			curr.next=prev;
			prev=curr;
			curr=nxt;
			if(nxt!=null){
				nxt=nxt.next;
			}
		}///while

		return prev;
	}//reverse

	public static void main(String[] args) {
		int[] A=new int[]{1,2,3,4,5};
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

		int B=3;

		ListNode res=new ReverseAlternateKNodes().solve(head,B);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//ReverseAlternateKNodes
