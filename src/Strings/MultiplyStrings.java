package Strings;

public class MultiplyStrings {
	public String multiply(String A, String B) {
/** How do we normally multiply? Say for A and B, we multiply the last digit of B by A, then SHIFT to left by 1,
then multiply the second last digit of B by A, SHIFT to left by one and so on, and then finally sum all. This is the same,
except that here instead of summing at the end, we sum at each row, the previous row. The tricky part is the SHIFTING.
This is where idx1 and idx2 comes in. idx1 is incremented at every inner iteration, and idx2 at every outer iteration.

For the first row, res[idx1+idx2] is 0. Second row onwards, it has a value.

If for idx2(outer loop)=0, the values of(idx1+idx2) are 0,1,2,3,4. For idx2(outer loop)=1, for the sums to be equal, each
of them have to be shifted to the right by 1 place, and for idx2(outer loop)=2, further to the right by a place. So the
sum at every point will be the actual (n1*n2), plus carry+res[idx1+idx2]. We update the carry, and update res[idx1+idx2]
by the /10 value of this sum.

After the loop is done, we remove the trailing(leading 0s in the reversed result) 0s, and then reverse and return the
result.*/

		int len1=A.length();
		int len2=B.length();

		if(A.contentEquals("0") || B.contentEquals("0")){
			//if any of the string param is 0, return 0
			return "0";
		}
		if(len2>len1){
			//such that length of the first string is always equal or greater to the second string
			return multiply( B,  A);
		}

		int i;
		int j=len2-1;
		int carry=0;
		int[] res=new int[len1+len2];//to store the result
		//to keep track of the positions shifted in each row
		int idx1=0;
		int idx2=0;

		while(j>=0){
			int n2=B.charAt(j)-'0';//digit to be multiplied, convert the char into int

			idx1=0;
			i=len1-1;
			carry=0;
			int sum=0;

			while(i>=0){
				int n1=A.charAt(i)-'0';//digit to be multiplied, convert the char into int
				sum=(n1*n2)+(carry)+(res[idx1+idx2]);
				res[idx1+idx2]=sum%10;
				carry=sum/10;

				i-=1;
				idx1+=1;
			}//inner while

			while(carry>0){
				//for the left over digits
				res[idx1+idx2]=carry%10;
				carry=carry/10;
				idx1+=1;
			}

			idx2+=1;
			j-=1;
		}//outer while

//remove leading 0s
		int index=res.length-1;
		while(index>=0 && res[index]==0){
			index-=1;
		}

		if(index==-1){
			//if it is all 0
			return "0";
		}

//Now reverse the res[] array. Remember, index is the current positions
		String result="";
		while(index>=0){
			result=result+res[index];
			index-=1;
		}
		return result;

	}//multiply

	public static void main(String[] args) {
		String A="56";
		String B="32";
		String res=new MultiplyStrings().multiply(A,B);
		System.out.println(res);
	}
}//MultiplyStrings
