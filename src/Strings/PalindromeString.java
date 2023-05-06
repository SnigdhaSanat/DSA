package Strings;

public class PalindromeString {
	public int isPalindrome(String A) {
		int res=1;//res is defaulted to 1
		int n=A.length();
		int i=0;
		int j=n-1;

		while(i<j){
			if(Character.isLetterOrDigit(A.charAt(i)) && Character.isLetterOrDigit(A.charAt(j))){
				//this is when both i and j are alpha numeric characters
				if (Character.toLowerCase(A.charAt(i)) == Character.toLowerCase(A.charAt(j))) {
					i += 1;
					j -= 1;
				} else {
					res = 0;
					break;
				}
			}//else
			else {
				//if char at i or j is not alphanumeric
				if (!Character.isLetterOrDigit(A.charAt(i))) {
					i += 1;
				}//inner while

				if (!Character.isLetterOrDigit(A.charAt(j))) {
					j -= 1;
				}//inner while
			}

		}//while

		if((!Character.isLetterOrDigit(A.charAt(i)) && !Character.isLetterOrDigit(A.charAt(j)) )){
//Reached mid but still no alpha numeric chars. This block is redundant as res is defaulted to 1;
//this is just for explanation
			//res=1;

		}
		return res;
	}//isPalindrome

	public static void main(String[] args) {
		String A="race a car";//A man, a plan, a canal: Panama";
		int res=new PalindromeString().isPalindrome(A);
		System.out.println(res);
	}//main
}
