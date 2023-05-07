package Strings;

public class ReverseTheString {
	public String solve(String A) {
		/*IMP: The difference in the expected vs returned value was because of the missing trim function*/
		/*Focus on each word Iterate from the last, maintain the last and first index of every word, and append */
		int n=A.length();
		StringBuilder sbSentence=new StringBuilder();
		int i=n-1;

		int lastIndex=-1;
		int firstIndex=-1;
		boolean firstWord=true;

		while(i>=0){
			//char ch=A.charAt(i);
			while(i>=0 && A.charAt(i)==' '){
				i-=1;
			}//inner while

			//first char or last space
			lastIndex=i;
			while(i>=0 && A.charAt(i)!=' '){
				i-=1;
			}

			if(i<0){
				firstIndex=0;
			}
			else{
				firstIndex=i+1;
			}

			String word=A.substring(firstIndex, lastIndex+1);

			if(!firstWord){
				sbSentence.append(" ");
			}
			sbSentence.append(word);
			firstWord=false;

		}//outer while
		return sbSentence.toString().trim();

	}//solve

	public static void main(String[] args) {
		String A="the sky is blue";
		String res=new ReverseTheString().solve(A);
		System.out.println(res);
	}
}//ReverseTheString
