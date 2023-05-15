package LinkedList;

public class PalindromeList {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public int lPalin(ListNode A) {

		if(A.next==null){
			//if it is a single element list, return 1
			return 1;
		}
		ListNode slow=A;
		ListNode fast=A;

		while(fast.next!=null && fast.next.next!=null){
			slow=slow.next;
			fast=fast.next.next;
		}//while

		//splitting the 2 lists in the middle. If it is of odd size, the mid node comes in the first list
		ListNode h2=slow.next;
		slow.next=null;
		ListNode h1=A;

		//reverse the 2nd list
		ListNode head2=reverse(h2);

		//now compare the 2 lists
		ListNode curr1=A;
		ListNode curr2=head2;

		while(curr1!=null && curr2!=null){
			if(curr1.val!= curr2.val){
				return 0;
			}//if
			curr1=curr1.next;
			curr2=curr2.next;
		}//while

		return 1;

	}//lPalin

	ListNode reverse(ListNode head){

		if(head.next==null){
			//if the 2nd list has only a single element, return the element itself
			return head;
		}
		ListNode prev=null;
		ListNode current=head;
		ListNode nxt=head.next;

		while(current!=null){
			current.next=prev;
			prev=current;
			current=nxt;//next, which may be null , hence the check in the next line
			if(nxt!=null){
				nxt=nxt.next;
			}
		}//while

		return prev;
	}//reverse

	public static void main(String[] args) {
		int[] A=new int[]{1,2,3,2,1};
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

		int res=new PalindromeList().lPalin(head);
		System.out.println(res);
	}//main
}//PalindromeList
