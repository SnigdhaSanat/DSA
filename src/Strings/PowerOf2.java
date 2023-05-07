package Strings;

import java.math.BigInteger;

public class PowerOf2 {
	public int power(String A) {
		/*You need to use BigInteger.*/

		BigInteger bi=new BigInteger(A);
		BigInteger divisor = BigInteger.valueOf(2);
		BigInteger zero= BigInteger.valueOf(0);
		BigInteger one=BigInteger.valueOf(1);
		int res=1;

		if(bi.compareTo(one)==0){
			//if it is 1, return 0
			return 0;
		}

		while(bi.compareTo(one)>0){
			//iterate till the bi value is greater than 1(one)
			BigInteger   quotient=bi.divide(divisor);
			BigInteger   modu=bi.mod(divisor);//modulus value
			if(modu.compareTo(zero)!=0){
				//if modulus value is NOT 0, break the loop and return 0
				res=0;
				return res;
			}//if
			bi= quotient;//update bi value as the quotient
		}//while

		return res;
	}//power

	public static void main(String[] args) {
		String A="129";
		int res=new PowerOf2().power(A);
		System.out.println(res);
	}
}
