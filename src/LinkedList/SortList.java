package LinkedList;

public class SortList {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode sortList(ListNode A) {
/**We will be using an in place merge sort. The concept remains the same: find the middle element and recursively call
the sortList function. Add the base condition. Finally merge the lists and work your way out of the recursive stack, just
like merge sort in arrays */

//Base condition
		if(A==null || A.next==null){
			//if it is empty, or contains only 1 element, return the element
			return A;
		}


//Find the middle element
		ListNode slow=A;
		ListNode fast=A;

		while(fast.next!=null && fast.next.next!=null){
			slow=slow.next;
			fast=fast.next.next;
		}//while

//split up the lists at slow as the last element of the first list
		ListNode h2=slow.next;
		slow.next=null;
		ListNode h1=A;

//recursively call the sortList function
		ListNode res1=sortList(h1);
		ListNode res2=sortList(h2);

//call merge for the  split lists, and IMP: return its result as the result of this function
		return merge(res1,res2);

	}//sortList

	private ListNode merge(ListNode head1, ListNode head2) {
		/**Here, we DO NOT use any extra space. we just modify the pointers*/

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
		while(head1!=null && head2!=null){
			//head1 and head2 belong to the original list
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
	}//merge

	public static void main(String[] args) {
		int[] A=new int[]{1,5,4,3};
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

		ListNode res=new SortList().sortList(head);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main

}//SortList
