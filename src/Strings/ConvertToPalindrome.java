package Strings;

public class ConvertToPalindrome {
	public int solve(String A) {
		//traverse from left and right towards mid
		int n=A.length();
		int i=0;
		int j=n-1;

//keep travelling as long as the chars at both ends match
		while(i<j && A.charAt(i)==A.charAt(j)){
			//if both are equal
			i+=1;
			j-=1;
		}//while

		//loop breaks because of i and j reached the end
		if(i==j){
			//meaning it is a palindrome with odd size, and the mid char is removable, so return 1
			return 1;
		}

		else if(i==(j+1)){
			//meaning it is a palindrome with even size, and nothing is removable, so return 0
			return 0;
		}


		//loop breaks because of mismatch
		int misMatchLeft=i;
		int misMatchRight=j;

//try skipping misMatchLeft and proceed and check if it is a palindrome, return
		i=misMatchLeft+1;
		j=misMatchRight;
		boolean match=true;

//At this point, skip is already made
		while(i<j){
			if(A.charAt(i)!=A.charAt(j)){
				//you cannot have another mismatch
				match=false;
				break;
			}//if
			i+=1;
			j-=1;
		}//while


		if (match){
			return 1;
		}

//if not a palindrome, try skipping misMatchRight, and check if it is a palindrome, and return

		i=misMatchLeft;
		j=misMatchRight-1;
		match=true;

		while(i<j){
			if(A.charAt(i)!=A.charAt(j)){
				match=false;
				break;
			}//if
			i+=1;
			j-=1;
		}//while

		if (match){
			return 1;
		}
		return 0;
	}//solve

	public static void main(String[] args) {
		String A="abecbea";
		int res=new ConvertToPalindrome().solve(A);
		System.out.println(res);
	}
}//ConvertToPalindrome
