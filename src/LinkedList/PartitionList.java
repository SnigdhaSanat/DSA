package LinkedList;

public class PartitionList {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode partition(ListNode A, int B) {
        /** Question requirement: preserve the original relative order of the nodes in each of the two partitions.
        Create 2 lists, currBefore list for <B, and currAfter list for >=B. Finally point the end of currBefore to
        the head of currAfter. If currAfter is null it is fine. But if currBefore is null, then return the currAfter list*/
		ListNode currNode=A;
		ListNode currBefore=null;
		ListNode currAfter=null;
		ListNode firstBeforeNode=null;
		ListNode firstAfterNode=null;

		while(currNode!=null){

			if(currNode.val<B){
				//put it into before list
				if(currBefore==null){
					ListNode node=new ListNode(currNode.val);
					currBefore=node;
					firstBeforeNode=node;
				}//iiner if
				else{
					ListNode node=new ListNode(currNode.val);
					currBefore.next=node;
					currBefore=node;
				}//inner else
			}

			else{
				//put in into after list
				if(currAfter==null){
					ListNode node=new ListNode(currNode.val);
					currAfter=node;
					firstAfterNode=node;
				}//inner if
				else{
					ListNode node=new ListNode(currNode.val);
					currAfter.next=node;
					currAfter=node;
				}//inner else
			}
			currNode=currNode.next;
		}//while

		if(currBefore!=null){
			//If currAfter is null it is fine, return the first list itself. It will take care of the second list being null
			currBefore.next=firstAfterNode;
			return firstBeforeNode;
		}
		else{
			//if currBefore is null, then return the currAfter list
			return firstAfterNode;
		}

	}//partition

	public static void main(String[] args) {
		int[] A=new int[]{1, 4, 3, 2, 5, 2};
		int B=3;
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

		ListNode res=new PartitionList().partition(head, B);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//PartitionList
