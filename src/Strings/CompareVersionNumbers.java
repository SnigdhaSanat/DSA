package Strings;

import java.math.BigInteger;

public class CompareVersionNumbers {
	public int compareVersion(String A, String B) {
		int compare=0;//initialize to 0
		int len1=A.length();
		int len2=B.length();

		int i=-1; //initialize to -1
		int j=-1; //initialize to -1

		while(i<len1 && j<len2 && compare==0){
			//break as soon as any of the string reaches the end, or compare becomes 1 or -1
			StringBuilder sb1=new StringBuilder();
			StringBuilder sb2=new StringBuilder();

			i+=1; //as it is initialized to -1, or it is set to '.' in the last iteration
			while(i<len1 && A.charAt(i)!='.'){
				//compare till the '.' is encountered
				sb1.append(A.charAt(i));
				i+=1;
			}//inner while
			String stringA=sb1.toString();
			BigInteger bigIntegerA=new BigInteger(stringA);//BigInteger


			j+=1; //as it is initialized to -1, or it is set to '.' in the last iteration
			while(j<len2 && B.charAt(j)!='.'){
				//compare till the '.' is encountered
				sb2.append(B.charAt(j));
				j+=1;
			}//inner while
			String stringB=sb2.toString();
			BigInteger bigIntegerB=new BigInteger(stringB);//BigInteger

			if(bigIntegerA.compareTo(bigIntegerB)>0){
				compare= 1;
			}

			else if (bigIntegerA.compareTo(bigIntegerB)<0){
				compare= -1;
			}
		}//outer while

		if(i<len1 && j>=len2 && A.charAt(i+1)!='0' && compare==0){
        /* If one string still has chars left. Compare only if equality is still unresolved(compare=0), and next char!=0.
        Eg- 13.0 and 13: Here, next char is 0, but still they will be equal*/
			return 1;
		}

		else if(j<len2 && i>=len1 && B.charAt(j+1)!='0' && compare==0){
        /* If one string still has chars left. Compare only if equality is still unresolved(compare=0), and next char!=0.
        Eg- 13.0 and 13: Here, next char is 0, but still they will be equal*/
			return -1;
		}

		return compare;
	}//compareVersion

	public static void main(String[] args) {
		String A = "1.13";
		String B = "1.13.4";
		int res=new CompareVersionNumbers().compareVersion(A, B);
		System.out.println(res);

	}
}//CompareVersionNumbers
