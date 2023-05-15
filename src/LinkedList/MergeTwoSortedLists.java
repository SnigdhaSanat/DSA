package LinkedList;

public class MergeTwoSortedLists {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
		/*Here, we DO NOT use any extra space. we just modify the pointers*/

		ListNode head=null;
		//initialize the head, whichever of the 2 lists has the smaller head value
		if(head1.val<=head2.val){
			head=head1;
			head1=head1.next;
		}
		else {
			head=head2;
			head2=head2.next;
		}

		//now iterate
		ListNode curr=head;//to keep track of the merged list
		while(head1!=null && head2!=null){//head1 and head2 belong to the original list
			//NOTE that while is an AND condition, not OR condition
			//merging the lists
			if(head1.val<=head2.val){
				curr.next=head1;
				head1=head1.next;

			}
			else{
				curr.next=head2;
				head2=head2.next;
			}
			curr=curr.next;
		}//while

		//point to the remaining list, whichever of the 2 is left
		if(head1!=null){
			curr.next=head1;
		}
		else{
			curr.next=head2;
		}

		return head;//head of the merged list that we had initialized
	}//mergeTwoLists

	public static void main(String[] args) {
		int[] A=new int[]{5,8,20};
		ListNode head1=null;
		ListNode prev1=null;
		ListNode curr1=null;

		for(int i=0;i<A.length;i++){
			if(head1==null){
				curr1=new ListNode(A[i]);
				head1=curr1;
			}
			else{
				curr1=new ListNode(A[i]);
				prev1.next=curr1;
			}

			prev1=curr1;
		}

		int[] B=new int[]{4,11,15};
		ListNode head2=null;
		ListNode prev2=null;
		ListNode curr2=null;

		for(int i=0;i<B.length;i++){
			if(head2==null){
				curr2=new ListNode(B[i]);
				head2=curr2;
			}
			else{
				curr2=new ListNode(B[i]);
				prev2.next=curr2;
			}

			prev2=curr2;
		}


		ListNode res=new MergeTwoSortedLists().mergeTwoLists(head1,head2);
		ListNode curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//MergeTwoSortedLists
