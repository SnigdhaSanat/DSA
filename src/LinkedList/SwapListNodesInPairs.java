package LinkedList;

public class SwapListNodesInPairs {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}
	public ListNode swapPairs(ListNode A) {
        /*Question requirement: Use constant space.
        You may not modify the values in the list; only nodes themselves can be changed.*/

        /* This is a special case of K=2. also, in this case, list size is divisible by K. But this solution takes
        care of the case even when list size  is not divisible by K*/

		if(A==null || A.next==null)
		{
			//for a size 1 or 0 list, return the head A itself
			return A;
		}

		ListNode globalHead=null;//start of the entire K reversed list
		int K=2;

		ListNode prevEnd=null;//end of the previous k length reverse list

		ListNode curr=A;
		ListNode prev=null;
		while(curr!=null){
			int count=0;
			ListNode currHead=null;

			while(count<K && curr!=null){
				if(currHead==null){
					currHead=curr;
				}

				prev=curr;
				curr=curr.next;
				count+=1;
			}//inner loop

			prev.next=null;

			ListNode reversedHead=reverse(currHead);//start of the current k reversed list
			if(globalHead==null){
				globalHead=reversedHead;
			}

			if(prevEnd!=null){
				prevEnd.next=reversedHead;
			}

			//get the end of the reversed list, for the NEXT Iteration
			ListNode currRev=reversedHead;
			ListNode prevRev=null;
			while(currRev!=null){
				prevRev=currRev;
				currRev=currRev.next;
			}
			prevEnd=prevRev;

		}//outer loop

		return globalHead;

	}//swapPairs

	ListNode reverse(ListNode start){
		ListNode prev=null;
		ListNode curr=start;
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
	}

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


		ListNode res=new SwapListNodesInPairs().swapPairs(head);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//SwapListNodesInPairs
