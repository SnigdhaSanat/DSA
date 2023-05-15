package LinkedList;

public class RemoveNthNodeFromListEnd {

	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	//Question requirement. USe constant additional space
	public ListNode removeNthFromEnd(ListNode A, int B) {
		//get the list size
		int size=0;

		ListNode currNode=A;
		while(currNode!=null){
			size+=1;
			currNode=currNode.next;
		}//while

		if(size==1){
			return null;
		}

		//now find the Bth node from the end
		if(B>=size){
			//if B is equal or greater than list list, remove the first node
			ListNode head=A;
			head=A.next;
			A.next=null;
			return head;
		}

		else{
			//find the node previous to the node to be deleted
			int count=size-B-1;

			int ctr=0;
			currNode=A;

			while(ctr<count){
				ctr+=1;
				currNode=currNode.next;
			}//while

			if(currNode.next!=null){
				currNode.next=currNode.next.next;
			}
			else{
				currNode.next=null;
			}
			return A;
		}//else
	}//removeNthFromEnd

	public static void main(String[] args) {
		int[] A=new int[]{1,2,3,4,5,6,7,8,9,10};
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

		ListNode res=new RemoveNthNodeFromListEnd().removeNthFromEnd(head,B);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//RemoveNthNodeFromListEnd
