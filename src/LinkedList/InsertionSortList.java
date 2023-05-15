package LinkedList;

public class InsertionSortList {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode insertionSortList(ListNode A) {

  /**Create a new linked list for keeping the sorted nodes. In array insertion sort, you traverse backward in the inner loop,
  here you traverse forward*/

		//resHead MUST point to a new list
		ListNode resHead=new ListNode(A.val);//a separate result linked list

		//currNode MUST point to the original list
		ListNode currNode=A.next;//equivalent to initializing index=1 in array

		if(currNode==null){
			//if it contains a single node, return the list itself
			return A;
		}

		while(currNode!=null){
			//IMP: create a NEW node with the value of the currNode. currNode points to the original list
			ListNode node=new ListNode(currNode.val);

			if(currNode.val<resHead.val){
				//if it is smaller than head, insert at the head
				node.next=resHead;
				resHead=node;
			}
			else{
				//if it is not smaller than head, then find the appropriate position
				ListNode innerPrevNode=resHead;
				ListNode innerNextNode=resHead.next;

				while(innerNextNode!=null && node.val>innerNextNode.val){
					innerPrevNode=innerNextNode;
					innerNextNode=innerNextNode.next;
				}//inner while

				innerPrevNode.next=node;

				if(innerNextNode!=null)
				{
					node.next=innerNextNode;
				}
				else{
					//if innerNextNode is null, the currNode is basically appended at the end of the list
					node.next=null;
				}

			}//else

			currNode=currNode.next;//points to the original list
		}//outer while

		return resHead;
	}//insertionSortList

	public static void main(String[] args) {
		int[] A=new int[]{7,6,2,9,5};
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

		ListNode res=new InsertionSortList().insertionSortList(head);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//InsertionSortList
