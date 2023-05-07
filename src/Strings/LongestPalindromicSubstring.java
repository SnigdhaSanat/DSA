package Strings;

public class LongestPalindromicSubstring {
	public String longestPalindrome(String str) {
		int n=str.length();

		if(n==1){
			return str;
		}
		//Initializing res and max. By default, the max is 1 and res is the first character in that case.
		String res=str.substring(0,1);
		int max=1;


	/**We can do a classic memo[n][n], and proceed (0,0), (1,1), ...(n-1,n-1). Then (0,1), (1,2)....(n-1,n-1). Then (0,2), (1,3)...(n-3,n-1)...all the way to              (0,n-1). This is absolutely correct. But the issue is when n is a very large value, n*n space. So we do the same, but using a lesser space than n*n. We will          use constant space at max at any point of time.
*    We take 0....n-1 as center, which means the substrings are of odd length.
*    Then we take 0.....n-1 as the end of the left half, which means the substrings are of odd length.
	 We can take odd then even or vice versa. Order does not matter.

	 Note that this also serves the question requirement of returning the lexicographically smallest palindrome in case of a conflict. Since we take the centers/leftHalves from left to right while checking for max, so lexicographically smallest string will be returned. If any other string comes lexicographically after an equal length palindrome, max is going to ignore it anyway.
*
*    For each of them, we check and update max if required. So we have say: 0 1 2 3 4 5 6 7 8 9 as the indices*/

		//For even length substrings
		/**Start with 0 as the end of left half, then 1 then 2, then 3 and so on*/
		int leftHalfEnd=0;
		while(leftHalfEnd<n){
			int low=leftHalfEnd;
			int high=leftHalfEnd+1;
			while(low>=0 && high<n && str.charAt(low)==str.charAt(high)){
				/*so at any point if the limits are crossed OR the equality is broken, the loop is broken, as it makes no sense to check outward when there is no equality at this point itself */
				int length=high-low+1;
				if(length>max){
					max=length;
					res=str.substring(low,high+1);
				}
				low-=1;
				high+=1;
			}//inner loop for low and high
			leftHalfEnd+=1;
		}//outer loop for leftHalfEnd

		//For odd length substrings.
		/**Start with 1 as the center, then 2, then 3 and so on.*/

		int center=1;
		while(center<n){
			int low=center-1;
			int high=center+1;
			while(low>=0 && high<n && str.charAt(low)==str.charAt(high)){
				/*so at any point if the limits are crossed OR the equality is broken, the loop is broken, as it makes no sense to check outward when there is no equality at this point itself */
				int length=high-low+1;
				if(length>max){
					max=length;
					res=str.substring(low,high+1);
				}
				low-=1;
				high+=1;
			}//inner loop for low and high
			center+=1;
		}//outer loop for center

		return res;
	}//longestPalindrome


	public static void main(String[] args) {
		String A = "ac";
		String res=new LongestPalindromicSubstring().longestPalindrome(A);
		System.out.println(res);
	}
}
