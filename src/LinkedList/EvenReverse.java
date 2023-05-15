package LinkedList;

public class EvenReverse {
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
		ListNode curr=A;

		ListNode headOdd=null;
		ListNode headEven=null;
		ListNode currOdd=null;
		ListNode currEven=null;

		int positionCtr=0;
		//create 2 Linked Lists of elements at odd positions and another with elements at even positions
		while(curr!=null){
			positionCtr+=1;

			if(positionCtr%2!=0){
				//odd position
				if(headOdd==null){
					ListNode node=new ListNode(curr.val);
					headOdd=node;
					currOdd=headOdd;
				}
				else{
					ListNode node=new ListNode(curr.val);
					currOdd.next=node;
					currOdd=currOdd.next;
				}

			}//if

			else{
				//even position
				if(headEven==null){
					ListNode node=new ListNode(curr.val);
					headEven=node;
					currEven=headEven;
				}
				else{
					ListNode node=new ListNode(curr.val);
					currEven.next=node;
					currEven=currEven.next;
				}

			}//else
			curr=curr.next;
		}//while

		//reverse the even positions linked list
		ListNode headEvenRev=reverse(headEven);

		//merge the 2 lists
		ListNode mergedList=merge(headOdd, headEvenRev);
		return mergedList;

	}//solve
//--------------------------------------------

	ListNode reverse(ListNode head){

		if(head==null || head.next==null){
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
	//---------------------------------------------------
	ListNode merge(ListNode head1, ListNode head2) {
		/**Here, we DO NOT use any extra space. we just modify the pointers*/
		ListNode prevHead1=head1;
		ListNode nextHead1=head1.next;
		ListNode prevHead2=head2;
		ListNode nextHead2=head2.next;

		while(prevHead1!=null){
			prevHead1.next=prevHead2;
			if(prevHead2!=null){
				prevHead2.next=nextHead1;
			}

			prevHead1=nextHead1;
			if(nextHead1!=null){
				nextHead1=nextHead1.next;
			}
			prevHead2=nextHead2;
			if(nextHead2!=null){
				nextHead2=nextHead2.next;
			}
		}//while

		return head1;
	}//merge

	public static void main(String[] args) {
		int[] A=new int[]{1,2,3,4,5,6,7,8,9};
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

		int k=3;

		ListNode res=new EvenReverse().solve(head);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//EvenReverse
