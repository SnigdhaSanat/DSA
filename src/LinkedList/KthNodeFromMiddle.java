package LinkedList;

public class KthNodeFromMiddle {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public int solve(ListNode A, int B) {
		if(A==null || A.next==null){
			return -1;
		}
		//find the middle element ACCORDING TO THE GIVEN CONDITION

		ListNode fast=A;
		ListNode slow=A;

		while(fast.next!=null && fast.next.next!=null){
			slow=slow.next;
			fast=fast.next.next;
		}//while

		ListNode mid=null;
		if(fast.next==null){
			mid=slow;
		}
		else if(fast.next.next==null){
			mid=slow.next;
		}

		//split the list at mid, and then reverse the list. Basically reverse the first half of the original list
		mid.next=null;
		ListNode revHead=reverse(A);

		//then find the Bth element of this reverse list
		ListNode curr=revHead;
		int count=0;
		while(curr!=null && count<B){
			curr=curr.next;
			count+=1;
		}
		if(curr!=null){
			//if the list has not ended
			return curr.val;
		}
		return -1;



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
		}//while
		return prev;
	}//reverse

	public static void main(String[] args) {
		int[] A=new int[]{1,2,3,4,5,6};
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
		int res=new KthNodeFromMiddle().solve(head,B);
		System.out.println(res);
	}//main
}//KthNodeFromMiddle
