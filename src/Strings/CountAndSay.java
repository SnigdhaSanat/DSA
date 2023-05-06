package Strings;

public class CountAndSay {
	public String countAndSay(int n) {
/**
 * 1, 11, 21, 1211, 111221, ...
 * Initialize the first string as 1. Create a string array, and then return the nth element.
 */

String[] stringArr=new String[n];

stringArr[0]="1";

for(int i=1;i<n;i++){
	/**How to create the ith string? create a StringBuilder. Then for the stringArr[i-1], count the same chars and their count, append it to string buffer. Then check out stringArr[i-1]'s next characters, and then append them further to StringBuilder*/

	StringBuilder stringBuilder=new StringBuilder();

	String prevString=stringArr[i-1];
	int prevLen=prevString.length();

	String toCompareWith=prevString.substring(0,1);
	int currLen=1;

	for(int j=1;j<prevLen;j++){
		//while same string
		if(toCompareWith.equals(prevString.substring(j,j+1))){
			currLen+=1;
		}
		else{
			//when different string is encountered
			stringBuilder.append(currLen);
			stringBuilder.append(toCompareWith);

			//resetting things
			toCompareWith=prevString.substring(j,j+1);
			currLen=1;
		}

	}//inner for

	stringBuilder.append(currLen);
	stringBuilder.append(toCompareWith);

	//assign it to stringBuffer
	stringArr[i]=stringBuilder.toString();
}//outer for

	return stringArr[n-1];

	}//countAndSay

	public static void main(String[] args) {
		int n=5;
		String res=new CountAndSay().countAndSay(n);
		System.out.println(res);
	}
}
