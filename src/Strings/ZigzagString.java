package Strings;

public class ZigzagString {
	public String convert(String A, int B) {
		String[] arrayString=new String[B];//to hold the result row wise
		int n=A.length();
		int rowIndex=0;//pointer to current row
		int i=0;
		boolean increasing=true;

		if(B==1){
			//if row value is one, return the string as it is
			return A;
		}

		for(int row=0;row<B;row++){
			//initialize each row
			arrayString[row]="";
		}//for

		while(i<n){
			//append the character at the appropriate row
			arrayString[rowIndex]+=Character.toString(A.charAt(i));

			if(increasing && rowIndex==(B-1)){
				//change direction and go up
				increasing=false;
			}
			else if(!increasing && rowIndex==0){
				//change direction and go down
				increasing=true;
			}

			if(increasing){
				//if increasing
				rowIndex+=1;
			}//if

			else{
				//if decreasing
				rowIndex-=1;
			}//else

			i+=1;

		}//while

//now create the res string from the rows
		String res="";
		for(int row=0;row<B;row++){
			res+=arrayString[row];
		}//for

		return res;
	}//convert

	public static void main(String[] args) {
		String A="PAYPALISHIRING";
		int n=3;
		String res=new ZigzagString().convert(A,n);
		System.out.println(res);
	}
}//ZigzagString
