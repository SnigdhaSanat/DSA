package Strings;

public class IntegerToRoman {
	// Function to convert decimal to Roman Numerals
/*Basics: The roman numerals are in the buckets of 1(I), 4(IV),5(V),9(IX),10(X),40(XL),50(L),90(XC),100(C),
400(CD),500(D),900(CM), and 1000(M). So decide the appropriate bucket. IMP: for each bucket, the sign will be the previous.
Eg: for <4 bucket, sign is I, for <5 bucket, sign is 4 and so on. Also arrange the buckets in ascending order. Run a loop
and update the quotient, divisor, remainder constantly, and append the correct string, 'divisor' number of times. The loop
runs until the remainder is 0*/
	String intToRoman(int number) {
		int quotient=-1;
		int divisor=-1;
		int remainder=-1;
		int numCurr=number;
		StringBuilder sbCurr=new StringBuilder();

		while(remainder!=0){

			if(numCurr<1){
				return "0";
			}
			else if(numCurr<4){
				divisor=1;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"I");
				numCurr=remainder;
			}
			else if(numCurr<5){
				divisor=4;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"IV");
				numCurr=remainder;
			}
			else if(numCurr<9){
				divisor=5;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"V");
				numCurr=remainder;
			}
			else if(numCurr<10){
				divisor=9;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"IX");
				numCurr=remainder;
			}
			else if(numCurr<40){
				divisor=10;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"X");
				numCurr=remainder;
			}
			else if(numCurr<50){
				divisor=40;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"XL");
				numCurr=remainder;
			}
			else if(numCurr<90){
				divisor=50;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"L");
				numCurr=remainder;
			}
			else if(numCurr<100){
				divisor=90;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"XC");
				numCurr=remainder;
			}
			else if(numCurr<400){
				divisor=100;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"C");
				numCurr=remainder;
			}
			else if(numCurr<500){
				divisor=400;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"CD");
				numCurr=remainder;
			}
			else if(numCurr<900){
				divisor=500;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr, quotient,"D");
				numCurr=remainder;
			}
			else if(numCurr<1000){
				divisor=900;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"CM");
				numCurr=remainder;
			}
			else{
				//>=1000
				divisor=1000;
				quotient=numCurr/divisor;
				remainder=numCurr%divisor;
				sbCurr=createRoman(sbCurr,quotient,"M");
				numCurr=remainder;
			}
		}//while

		return sbCurr.toString();
	}//intToRoman

	StringBuilder createRoman(StringBuilder sb,int times, String ch){
		for(int i=1;i<=times;i++){
			sb.append(ch);
		}
		return sb;
	}//createRoman

	public static void main(String[] args) {
		int A=38;
		String res=new IntegerToRoman().intToRoman(A);
		System.out.println(res);
	}
}//IntegerToRoman
