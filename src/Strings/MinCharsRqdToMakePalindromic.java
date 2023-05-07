package Strings;

public class MinCharsRqdToMakePalindromic {
	public int solve(String A) {
		/*This uses KMP-LPS algorithm*/
		/*Place a sentinel character after the string, then the reverse of the string. Compute the LPS of that*/
		int N = A.length();

		StringBuilder sb=new StringBuilder();
		sb.append(A);//append the string
		sb.append("!");//append any sentinel character

//Now append the reverse string
		for(int i=N-1;i>=0;i--){
			sb.append(A.charAt(i));
		}//for

//Convert back into string
		String B= sb.toString();
		int len=B.length();

/**
Eg: ABBAXX IS NOW ABBAXX!XXABBA
In the original array, we were basically trying to find out how many letters to be prefixed, which is equivalent to how
many letters need to be omitted at the end to get a palindrome. The LPS[len-1] of (original string+sentinel+rev) array
is that number of characters makes up that palindrome after those omitted letters. Let us call it x.Hence the res is the
diff of original string A's length and x, which gives us the count of letters to be prefixed(or omitted at the end)
What if you need to return the letters? Ans: Return the letters between the sentinel char and LPS[len-1]

What if you need to suffix instead of prefix?:Eg:XXABBA
In that case, since you need to omit it from the beginning this time, create it as (rev+sentinel+original string).
ABBAXX sentinel XXABBA
*/
		int lps[]=new int[len];//length of pattern
		int[] resArray=computeLPSArray(B,len,lps);
		int res=N-resArray[len-1];
		return res;
	}//solve

	int[] computeLPSArray(String pat, int M, int lps[])
	{
		/**lps[i] = the longest length of PROPER prefix of pat[0..i] which is also a suffix of pat[0..i].
		 When comparing i and j, 0 to i-1 will always be the prefix, which means starting right from index 0 to i-1, matches with index j-i to j-1. So now if i and j matches, LPS j takes the length 0 to i, which is i+1(0 based index). If there is a mismatch, i goes back. How much?  Say the prefix "ABCDDEABC" is being matched with a possible suffix "ABCDDEABC", with indices 0 to 8. Now if index 9, say X does not match with j: Y, i does not go back all the way to 0. It checks index 8's LPS value. Considering index 8 itself now as a suffix, it finds its prefix, meaning the ABC at index 6,7,8 is a suffix to ABC at the beginning, index:0 to 2. So essentially the prefix is broken down into a prefix and suffix in itself, and the new smaller prefix is now considered. That is LPS[8] as a suffix now ,8(i-1) has the length of the smaller prefix. As a 0 based index, the new prefix ends at LPS[8]-1. So place i at LPS[i-1]. Why? We already had the original prefix ending a 8 matching with the j-1. If j does not match, we find a smaller prefix further right, which matches with the current prefix as the suffix, and try matching the new smaller prefix with index j. If the prefix from 0 to j-1, ABCDDEABC, matched with the suffix ending at i-1, surely the smaller prefix(ABC), which matches with the bigger prefix will match with the suffix will j-1. We only need to compare further now.

		 If the smaller prefix does not match, we repeat the process until we get to 0*/
		int i=0;
		int j=1;
		lps[0]=0;

		// the loop calculates lps[i] for i = 1 to M-1
		while(j<M){
			if(pat.charAt(i)==pat.charAt(j)){
				lps[j]=i+1;
				i+=1;
				j+=1;
			}//outer if
			else{
				//if not equal
				if(i!=0)
				{
//if lps[i-1]=5(say), it means this suffix is equal till pat[4](0 based index) prefix. So position i next to it
					i=lps[i-1];
				}
				else{
//in this case you cannot adjust i. So j should move on
					lps[j]=0;
					j+=1;
				}

			}//outer else

		}//while

		return lps;
	}//computeLPSArray


	public static void main(String[] args) {
		String A="ABC";
		int res=new MinCharsRqdToMakePalindromic().solve(A);
		System.out.println(res);
	}
}//MinCharsRqdToMakePalindromic
