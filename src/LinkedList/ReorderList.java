package LinkedList;

public class ReorderList {

	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}
	//Question requirement: You must do this in-place without altering the nodes values.
	public ListNode reorderList(ListNode A) {
		if(A==null || A.next==null){
			return A;
		}
		//split into 2 lists at the mid, reverse the 2nd list, and the merge the 2 lists
		ListNode slow=A;
		ListNode fast=A;

		while(fast.next!=null && fast.next.next!=null){
			slow=slow.next;
			fast=fast.next.next;
		}//while

		ListNode head2=null;
		//split the list into 2
		head2=slow.next;
		slow.next=null;

		//reverse the 2nd list
		ListNode revHead=reverse(head2);

		//now merge the 2 list
		ListNode mergeHead=merge(A,revHead);

		return mergeHead;
	}//reorderList

	ListNode reverse(ListNode head){
		if(head.next==null){
			//single node to be reversed, return the node itself
			return head;
		}
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

	ListNode merge(ListNode head1, ListNode head2){
		ListNode curr1=head1;
		ListNode next1=curr1.next;
		ListNode curr2=head2;
		ListNode next2=curr2.next;

		while (curr1!=null) {
			curr1.next = curr2;
			if (curr2 != null) {
				curr2.next = next1;
			}

			curr1 = next1;
			if (next1 != null) {
				next1 = next1.next;
			}


			if (curr2 != null) {
				curr2 = next2;
			}

			if (next2 != null) {
				next2 = next2.next;
			}

		}
		return head1;
	}//merge

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


		ListNode res=new ReorderList().reorderList(head);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//ReorderList
