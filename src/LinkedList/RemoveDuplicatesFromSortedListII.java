package LinkedList;

public class RemoveDuplicatesFromSortedListII {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode deleteDuplicates(ListNode A) {
		/**This solves it in constant space */

		if(A.next==null){
			//if it contains only 1 element
			return A;
		}

		/**Create a superHead, whose next points to the first element of the result list. */
		ListNode superHead=new ListNode(-1);
		superHead.next=A;

		//currNonDup always points to the current nonDup element
		ListNode currNonDup=superHead;

		ListNode prev=null;
		ListNode curr=A;
		ListNode nxt=curr.next;

		while(curr!=null) {

			//find the next non dup element
			while (!isNonDup(prev, curr, nxt)) {
				prev = curr;
				curr = nxt;
				if(nxt!=null){
					nxt=nxt.next;
				}
			}//inner while

			currNonDup.next=curr;
			currNonDup=curr;

			prev=curr;
			curr=nxt;
			if(nxt!=null){
				nxt=nxt.next;
			}
		}//outer while

		//Return the actual head, which can be a null
		return superHead.next;
	}//deleteDuplicates

	boolean isNonDup(ListNode prev, ListNode curr, ListNode nxt){

		if((curr==null)||((prev==null || prev.val!=curr.val) && (nxt==null||curr.val!=nxt.val))){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int[] A=new int[]{1,1,1,2,2,3,3};
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

		ListNode res=new RemoveDuplicatesFromSortedListII().deleteDuplicates(head);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//RemoveDuplicatesFromSortedListII
