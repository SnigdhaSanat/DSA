package LinkedList;

public class RotateList {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode rotateRight(ListNode A, int k) {
		/**find the (size-k-1)th element and (size-k)th element, then split the list into 2 between them. Then point the end of
		/the 2nd list to the head of first. Return head of the 2nd list*/

		if(A==null || A.next==null){
			//if list has 0 or 1 element, return it
			return A;
		}

		//count the list size
		int size=0;
		ListNode currNode=A;

		while(currNode!=null){
			size+=1;
			currNode=currNode.next;
		}//while

//if k>=size, take its mod with size
		if(k>=size){
			k=k%size;
		}

		if(k==0){
			//it k or its mod turns out to be 0, return A as it is
			return A;
		}

		//now find the 2 elements
		int counter=0;

		currNode=A;
		ListNode prev=null;

		while(counter<(size-k)){
			counter+=1;
			prev=currNode;
			currNode=currNode.next;
		}//while

//element found, now split the list at that point
		ListNode head2=currNode;
		prev.next=null;

//travel to the end of the 2nd list
		currNode=head2;
		prev=null;
		while(currNode!=null){
			prev=currNode;
			currNode=currNode.next;
		}//while

//point prev: end of the 2nd list to A:the head of the first list
		prev.next=A;

//return head of the 2nd list
		return head2;

	}//rotateRight

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

		int B=2;
		ListNode res=new RotateList().rotateRight(head,B);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//RotateList
