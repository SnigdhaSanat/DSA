package Strings;

public class AmazingSubarrays {
	public int solve(String A) {
		int count=0;
		int n=A.length();

/*If current char is a vowel, no. of subarrays starting with it are the ones ending in itself till ending at string's
length*/
		for(int i=0;i<n;i++){
			char ch=A.charAt(i);
			String s=String.valueOf(ch).toLowerCase();
			if ((s.contentEquals("a")) || (s.contentEquals("e")) || (s.contentEquals("i")) || (s.contentEquals("o")) ||
					(s.contentEquals("u")))
			{
				//if char at index i is a vowel
				int incr=n-i;
				count=(count+incr)%10003;
			}//if

		}//for

		return count;
	}//solution

	public static void main(String[] args) {
		String A="ABEC";
		int res=new AmazingSubarrays().solve(A);
		System.out.println(res);
	}
}
