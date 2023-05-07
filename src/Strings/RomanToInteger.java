package Strings;

public class RomanToInteger {
	public int romanToInt(String A) {
/*Start with index 1, and initialize prev char and digit to 0 index chars. Run the outer loop for index<n. Updating
prevChar and prevDigit carefully for every scenario  is important*/
		int n=A.length();
		String prevChar=Character.toString(A.charAt(0));
		int prevDigit=mapRomanLetterToNumber(prevChar);
		int currCount=1;
		int index=1;

		int currNumber=0;

		while(index<n){
			//String currChar=Character.toString(A.charAt(index));

			while(index<n && Character.toString(A.charAt(index)).contentEquals(prevChar)){
				//as long as it is the same letter, update currCount, prev values, and index
				currCount+=1;
				//update prev values
				prevChar=Character.toString(A.charAt(index));
				prevDigit=mapRomanLetterToNumber(prevChar);
				//update index
				index+=1;
			}//inner while

			//the same letter streak breaks
			if(index<n){
				//only if index is within n
				boolean isNextLetterGreater= mapRomanLetterToNumber(Character.toString(A.charAt(index)))>prevDigit;
				boolean isNextLetterSmaller=mapRomanLetterToNumber(Character.toString(A.charAt(index)))<prevDigit;
				//2 case here: either the next letter is smaller or greater
				if(isNextLetterGreater){
//Case: next char is greater than previous, combine the letters as it is a subtract combo
					StringBuilder sb=new StringBuilder();
					sb.append(prevChar);
					sb.append(Character.toString(A.charAt(index)));

					//update prev values
					prevChar=sb.toString();
					prevDigit=mapRomanLetterToNumber(prevChar);
					//reset currCount to 1
					currCount=1;
				}
				else if(isNextLetterSmaller) {
//Case: next char is less than previous, it is a new letter

					//update the current count into a number
					prevDigit=mapRomanLetterToNumber(prevChar);
					currNumber+=currCount*prevDigit;

					//update prev values
					prevChar=Character.toString(A.charAt(index));
					prevDigit=mapRomanLetterToNumber(prevChar);
					//reset currCount to 1
					currCount=1;
				}

				index+=1;
			}
		}// outer while
//for the last group, We had to exit the loop because index reached n
		currNumber+=currCount*prevDigit;
		return currNumber;
	}//romanToInt

	int mapRomanLetterToNumber(String str){
		String s=str;
		switch(s)
		{
			case "I":
				return 1;

			case "IV":
				return 4;

			case "V":
				return 5;

			case "IX":
				return 9;


			case "X":
				return 10;

			case "XL":
				return 40;

			case "L":
				return 50;


			case "XC":
				return 90;

			case "C":
				return 100;

			case "CD":
				return 400;

			case "D":
				return 500;

			case "CM":
				return 900;

			case "M":
				return 1000;

			default:
				return 0;
		}//switch

	}//mapRomanLetterToNumber

	public static void main(String[] args) {
		String A = "XIV";
		int res=new RomanToInteger().romanToInt(A);
		System.out.println(res);
	}
}//RomanToInteger
