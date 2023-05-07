package Strings;

import java.math.BigInteger;

public class Atoi {
	// DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
	public int atoi(final String A) {
/**Can have whitespace before the number. NOT SURE about AFTER the number
    Can have garbage chars after the number
    Return 0 if there are only garbage chars
    Overflow: Return INT_MAX if the number is positive, INT_MIN otherwise*/

		int n=A.length();
		int start=-1;
		int end=-1;
		int res=-1;
		boolean isNegative=false;

        /**The minus/plus sign encountered is important. Why?: If there is a space before the first number, it is generally
        valid. But if a + or - was encountered, before the first number, but it is NOT immediately before the first number,
        then the string becomes invalid */
		boolean minusSignEncountered=false;
		boolean plusSignEncountered=false;
		boolean garbageEncountered=false;

		int i=0;
		while(i<n &&  !Character.isDigit(A.charAt(i))){
			//until a digit is encountered
			if(A.charAt(i)=='-'){
				minusSignEncountered=true;
			}
			if(A.charAt(i)=='+'){
				plusSignEncountered=true;
			}
			if(A.charAt(i) !=' ' && A.charAt(i) !='+' && A.charAt(i) !='-'){
				//some garbage value BEFORE the number, not space, plus or minus
				garbageEncountered=true;
			}
			i+=1;
		}//while

		if(i==n || garbageEncountered
				|| (minusSignEncountered && i>0 && A.charAt(i-1)!='-')
				|| (plusSignEncountered && i>0 && A.charAt(i-1)!='+')
			// || (!minusSignEncountered && !plusSignEncountered &&i>0 && A.charAt(i-1)!=' ')
               /**the above line is commented because it is covered by the case of garbageEncountered, in case the preceding
               character is a non garbage. Else if the immediately preceding is plus or minus,
               it is a valid string and this whole if condition fails*/
		){
        /**if no digit is found, or if a non space AND non + AND non - char is encountered before the first digit,
        or if '-' or '+' is encountered but not immediately before the number*/
			return 0;
		}



		if(i>0 && A.charAt(i-1)=='-')
		{//if negative
			isNegative=true;
		}
		start=i;//start of numbers

		while(i<n &&  Character.isDigit(A.charAt(i))){
			//as long as a digit is encountered
			i+=1;
		}//while

		end=i-1; //1 char before the string end or the first non numeric char after the numeric chars

		String str=A.substring(start,end+1);
		BigInteger number=new BigInteger(str);
		BigInteger maxInt=BigInteger.valueOf(Integer.MAX_VALUE);

		if(number.compareTo(maxInt)<0){
			//if within the range

			if(isNegative){
				res=0-number.intValue();
			}
			else{
				res=number.intValue();
			}
		}
		else{
			//if NOT within the range

			if(isNegative){
				res=0-Integer.MAX_VALUE-1;
			}
			else{
				res=Integer.MAX_VALUE;
			}
		}

		return res;
	}//atoi

	public static void main(String[] args) {
		String A="9 2704";
		int res=new Atoi().atoi(A);
		System.out.println(res);
	}
}//Atoi
