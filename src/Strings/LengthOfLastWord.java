package Strings;

public class LengthOfLastWord {
	public int lengthOfLastWord(final String A) {
/** Start from the end of the string. Pass through the spaces if any, until you encounter the first character, if any. Star  counting. Continue tll you encounter a space, of the beginning of the string
* */

	int n=A.length();

	int len=0;

	int i=n-1;


	//find the last non-space char
	while( i>=0 && A.charAt(i)==' ' ){
		i-=1;
	}

	//if no char found at all
	if(i<0){
	return 0;
	}

	//non-space char found. Start counting till  you encounter a space, of the beginning of the string
	while(i>=0 && A.charAt(i)!=' '){
		len+=1;
		i-=1;
	}//while

		return len;

	}//lengthOfLastWord

	public static void main(String[] args) {
		String A=" hello world please to meet you";
		int res=new LengthOfLastWord().lengthOfLastWord(A);
		System.out.println(res);
	}
}//LengthOfLastWord
