package LinkedList;

public class AddTwoNumbersAsLists {
	private static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}

	public ListNode addTwoNumbers(ListNode A, ListNode B) {
    /**This is a case of normal addition , with current sum being A+B+carry, digit=sum%10, and carry=sum/10.
    Only difference being the digits are reversed. Be mindful of the while condition. It runs as long as either
    of List A or B stays, OR carry is still>0*/

		ListNode currA=A;
		ListNode currB=B;
		ListNode curr=null;

		int carry=0;
		ListNode head=null;//head to be returned

		while(currA!=null || currB!=null||carry>0){
			int first=currA!=null?currA.val:0;
			int second=currB!=null?currB.val:0;

			int sum=first+second+carry;
			int digit=sum%10;
			carry=sum/10;

			ListNode node=new ListNode(digit);

			if(head==null){
				head=node;
				curr=node;
			}
			else{
				curr.next=node;
				curr=curr.next;
			}

			if(currA!=null){
				currA=currA.next;
			}
			if(currB!=null){
				currB=currB.next;
			}

		}//while

		return head;
	}//addTwoNumbers

	public static void main(String[] args) {
		int[] A=new int[]{2,4,3};
		ListNode head1=null;
		ListNode prev1=null;
		ListNode curr1=null;

		for(int i=0;i<A.length;i++){
			if(head1==null){
				curr1=new ListNode(A[i]);
				head1=curr1;
			}
			else{
				curr1=new ListNode(A[i]);
				prev1.next=curr1;
			}

			prev1=curr1;
		}

		int[] B=new int[]{5,6,4};
		ListNode head2=null;
		ListNode prev2=null;
		ListNode curr2=null;

		for(int i=0;i<B.length;i++){
			if(head2==null){
				curr2=new ListNode(B[i]);
				head2=curr2;
			}
			else{
				curr2=new ListNode(B[i]);
				prev2.next=curr2;
			}

			prev2=curr2;
		}


		ListNode res=new AddTwoNumbersAsLists().addTwoNumbers(head1,head2);
		ListNode curr=res;
		while(curr!=null){
			System.out.println(curr.val);
			curr=curr.next;
		}
	}//main

}//AddTwoNumbersAsLists
