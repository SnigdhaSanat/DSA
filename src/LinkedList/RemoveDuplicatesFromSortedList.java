package LinkedList;

public class RemoveDuplicatesFromSortedList {
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
		ListNode res=A;
		ListNode curr=A;
		int currVal=curr.val;
		ListNode nxt=A.next;

/** Increment nxt as long as it is same as the current value. The moment it changes, make the next pointer of curr point to nxt. Also update the currVal. That way, the first occurrence of every node makes it to the res list */

while (nxt!=null){
	while(nxt!=null && nxt.val==currVal){
		nxt=nxt.next;
	}//inner while

	if(nxt!=null){
		currVal=nxt.val;
	}
	curr.next=nxt;

	curr=nxt;
	if(nxt!=null){
		nxt=nxt.next;
	}

}//outer while

		return A;
	}//deleteDuplicates

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

		ListNode res=new RemoveDuplicatesFromSortedList().deleteDuplicates(head);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//RemoveDuplicatesFromSortedList
