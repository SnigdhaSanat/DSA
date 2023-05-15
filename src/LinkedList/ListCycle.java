package LinkedList;

public class ListCycle {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	/**WHY IT WORKS: (k: length of straight line, d: loop length)
At any given point n, the slow pointer travels n steps, and the fast pointer does 2n steps. At the loop entry, the slow pointer travels k steps, and the fast pointer does 2k steps.Effectively, slow pointer is at the entry of the loop, say  step 0 of the loop, and fast pointer travels k steps INSIDE the loop.Since it is a loop, it is then k%d steps away from slow pointer. The difference between the two pointers increase by 1 at each step.To meet, the difference should be a multiple of d. It is then only a matter of time that the difference , currently k%d, hits the next multiple of d, and the pointers meet. Hence the certainty of them meeting.

How to find out the start of cycle? Let's say we have 1->2->3->4->5->
                                                                 13      6->
	                                                            |             7->
	                                                          12<-                 8
                                                                    11<-       9<-
	                                                                      10<-

	 where 13 points to 5. So, k=4, d=9. Say they meet at x(starting at 5) in the loop in the slow and fast pointer. dist(fast)=2*dist(slow). dist(fast)=k+x+i*d. Dist(slow)=k+x+j*d.
	 So, k+x+i*d=2(k+x+j*d)=>k+x=(i-2*j)d, for some i and j. This means at the SAME speed, if p1 starts from 1, it reaches k+x, which is the meeting point of slow and fast pointer; and if p2 starts at the meeting point of slow and fast pointers x, it takes some number of whole circles around the loop and ends at x, so p1 and p2 meet. Since they were travelling at the same speed now, so they have had travelled together from 5 to x(before which they were at 4 and 13 respectively.) So they meet at 5, which is the required starting point.
	 */
	public ListNode detectCycle(ListNode A) {
		//Question requirement: using constant additional space.

		// detect if there is a cycle
		if(A==null  || A.next==null){
			return null;
		}

		ListNode slow=A;
		ListNode fast=A;

		//advance the slow and fast pointers, so that (fast==slow) condition does not satisfy in a false way
		slow=slow.next;
		fast=fast.next.next;
		boolean loopExists=false;

		while(slow!=null && fast!=null && fast.next!=null){
			if(fast==slow){
				loopExists=true;
				break;
			}
			slow=slow.next;
			fast=fast.next.next;
		}//while

		if(!loopExists){
			return null;
		}

		//initialize slow to head:A, and keep fast where it is. Move BOTH by one step now. They WILL meet at the start
		//of the loop.
		slow=A;
		while(slow!=fast){
			slow=slow.next;
			fast=fast.next;
		}//while

		return slow;//or return fast
	}//detectCycle

	public static void main(String[] args) {
		int[] A=new int[]{1,2,3,4,5,6};
		int n=A.length;
		ListNode head=null;
		ListNode prev=null;
		ListNode curr=null;

		ListNode cycleNode=null;

		for(int i=0;i<A.length;i++){
			if(head==null){
				curr=new ListNode(A[i]);
				head=curr;
			}
			else{
				curr=new ListNode(A[i]);
				prev.next=curr;
			}

			//choose a random node for cycle
			if(i==2){
				cycleNode=curr;
			}
			prev=curr;
		}


		prev.next=cycleNode;

		ListNode res=new ListCycle().detectCycle(head);
		curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main
}//ListCycle
