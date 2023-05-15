package LinkedList;

public class ReverseLinkListII {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode reverseBetween(ListNode A, int B, int C) {
		//Question requirement: Do it in-place and in one-pass.

        /** Cases to take care of:  1) B=1 2)B=C 3) C=size of list
        If single pass were not a requirement, Strategy: take out the list between B and C, reverse it, and then join it back.
        The challenge is the single pass, which requires it to be solved the way it is solved. It also satisfies the
        in place requirement*/

		if(A.next==null || B==C){
			//if list contains a single node, or B==C(needing no reverse operation) return A
			return A;
		}

		ListNode start=null;//to store the Bth node
		ListNode beforeStart=null;//to store the node before Bth node
		ListNode last=null;// //to store the node before Cth node

		//head, which is to be returned
		ListNode head=A;

		int count=0;
		ListNode prev=null;
		ListNode curr=A;
		ListNode nxt=curr.next;


		while(count<=C){
			count+=1;

			if(B<=count && count<=C+1){
				if(count==B){
					//if Bth node, cut off the prev and the curr nodes
					beforeStart=prev;
					if(beforeStart!=null){
						//beforeStart will be null if B=1
						beforeStart.next=null;
					}
					start=curr;
					curr.next=null;
				}//if

				else if(count==C){
					//reverse for Cth node too. In addition, mark curr as the last node.Will be used in (C+1) iteration
					curr.next=prev;
					last=curr;
				}   //else if

				else if(count==(C+1)){
					//coming out of the B to C list
					if(beforeStart!=null){
						//that is, if B!=1
						beforeStart.next=last;
					}
					else{
						//for the case B=1
						head=last;
					}
					start.next=curr;//if C==size of list, curr will be null
				}//else if

				else{
					//between B+1 and C-1, simply keep reversing the nodes
					curr.next=prev;
				}   //else

			}//if between B and C

			//do this from start till (C+1)th node
			prev=curr;
			curr=nxt;
			if(nxt!=null){
				nxt=nxt.next;
			}
		}//while

		return head;
	}//reverseBetween

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

		int m=2;
		int n=4;

		ListNode res=new ReverseLinkListII().reverseBetween(head,m,n);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//ReverseLinkListII
