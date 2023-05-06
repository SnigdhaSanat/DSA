package Strings;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] A) {
		int n=A.length;
		//Starting from first string, compare consecutive strings

		//initialize prefix as the first string
		String prefix=A[0];
		int index=1;//so loop starts from the 2nd string

		while(index<n){

			String s1=prefix;
			String s2=A[index];

			//assign the shorter string length to len
			int len=s1.length()<=s2.length()?s1.length():s2.length();//smaller length

			//in the while loop, find the prefix of the current 2 strings, and assign that to the prefix for the next iteration
			while(!s1.substring(0,len).contentEquals(s2.substring(0,len)) && len>0){
				//len>0 condition is important. If it falls to 0, prefix will be empty
				len-=1;
			}//inner while

			prefix=s1.substring(0,len);
			index+=1;
		}//outer while

		return prefix;

	}//longestCommonPrefix

	public static void main(String[] args) {
		String[] A={"abcdefgh", "aefghijk", "abcefgh"};
		String res=new LongestCommonPrefix().longestCommonPrefix(A);
		System.out.println(res);

	}
}
