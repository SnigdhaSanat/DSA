package Strings;

public class AddBinaryStrings {
	public String addBinary(String A, String B) {
/**This uses an important concept. You will start from the end of the strings. But the concept comes when computing for the
partial sum and carry. As numbers are actually chars, computations are done in int, converted back into char and placed.
To convert into int, do -'0', as it will subtract the int value of char 0, which is 48. So '0'(48) becomes 0, '1'(49)
becomes 1 and so on. If we are less than 0, either because one of the strings have exhausted, or both have exhausted but
carry value is still not 0, we default the charAt(index) value to 0. Whichever is the case, we then PREPEND the %2 value
of the currSum into the result so far, after converting it into char. The carry is the /2 value.
In decimal system, they are %10 and /10 respectively. The carry value is kept as int. In the next iteration, the charAt(index)
values are converted into int and the process is repeated.*/
		int len1=A.length();
		int len2=B.length();

		int i=len1-1;
		int j=len2-1;
		String result="";
		int currSum=0;

		while(i>=0 || j>=0 || currSum>0){

			//-'0' to convert it into int
			currSum+=(i>=0)?(A.charAt(i)-'0'):(0);
			currSum+=(j>=0)?(B.charAt(j)-'0'):(0);

			int mod=currSum%2;
			char partialRes= mod==0?'0':'1';
			result=partialRes+result;//prepended to result, NOT appended
			currSum=currSum/2;

			i-=1;
			j-=1;
		}//while

		return result;
	}//addBinary

	public static void main(String[] args) {
		String A="111";
		String B="11";
		String res=new AddBinaryStrings().addBinary(A,B);
		System.out.println(res);
	}
}//AddBinaryStrings
