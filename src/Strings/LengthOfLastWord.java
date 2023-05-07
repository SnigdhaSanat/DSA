package Strings;

public class LengthOfLastWord {
	public int lengthOfLastWord(final String A) {
    /**Iterate over the characters. For each character, append to sb. Once a space is encountered update the last word,
    and clear the sb. At the end of the loop, again, update thelast word. This is to take care of single Worded Strings and also the last word */
		int n=A.length();
		StringBuilder sb=new StringBuilder();
		String lastWord="";

		for(int i=0;i<n;i++){
			char ch=A.charAt(i);
			if(ch==' '){
				//if space
				if(sb.length()>0){
					//append only for the 1st space character
					lastWord=sb.toString();
					sb.setLength(0);
				}

			}
			else{
				sb.append(ch);
			}
		}//for

		if(sb.length()>0) {
			//append only for the 1st space character
			//if there is only 1 word
			lastWord=sb.toString();
		}
		return lastWord.length();
	}//lengthOfLastWord

	public static void main(String[] args) {
		String A=" hello world please to meet you";
		int res=new LengthOfLastWord().lengthOfLastWord(A);
		System.out.println(res);
	}
}//LengthOfLastWord
