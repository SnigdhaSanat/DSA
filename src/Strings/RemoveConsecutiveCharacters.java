package Strings;

public class RemoveConsecutiveCharacters {
	public String solve(String A, int B) {
		/*Question clarification: remove only if it is exactly =B. Retain for >B and <B.*/
		int n = A.length();
		int currLength = 1;
		char lastChar = A.charAt(0);
		StringBuilder sb = new StringBuilder();

		int indx = 1;
		while (indx < n) {
			//at the end of every loop, increment index, and update the last char
			if (A.charAt(indx) == (lastChar)) {
				//increment the count of equal characters
				indx += 1;
				currLength += 1;
			} else {
				//if a new character is encountered, reset the currLength to 1 invariably. If currLength=B, simply
				//ignore. Else, append the characters
				if (currLength == B) {
					//do nothing. This is just for understanding
				} else {
					int start = indx - currLength;
					int end = indx;
					sb.append(A.substring(start, end));
				}//inner else

				currLength = 1;
				lastChar = A.charAt(indx);
				indx += 1;
			}//outer else

		}//while

//appending the last set of characters like the case of currLength!=B
		if (currLength != B) {
			int start = indx - currLength;
			int end = indx;
			sb.append(A.substring(start, end));
		}

		return sb.toString();
	}//solve


	public static void main(String[] args) {
	String A="aabbccd";
	int B=2;
	String res=new RemoveConsecutiveCharacters().solve(A,B);
	System.out.println(res);
	}
}
