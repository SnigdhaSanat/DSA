package Strings;

public class MinimumParantheses {
	public int solve(String A) {
		int n=A.length();
		int count=0;
		int res=0;
		for(int i=0;i<n;i++){
/*In general, you would incr and decr count for ( and ) respectively(or vice-versa). But here, since order matters too,
so for cases like )( you need to take care of it. If at any point, the count drops to <0, it means that at that point, it is
deficit of '(', and since ordering matters, this can't be 'fixed' no matter how many '(' comes after this. So this deficit
goes to the res. So update res as count, and reset count. For count>0 case, of course it can be updated by subsequent ')',
so you don't have to assign count to res, or reset count in this case. Simply update the res at the end of the loop for
this case */
			char ch=A.charAt(i);
			if(ch==')')
			{
				count-=1;
			}//if
			else{
				//if '(''
				count+=1;
			}//else

			if(count<0){
				//for the negative count ')'
				res+=Math.abs(count);
				count=0;
			}

		}//for

		res+=count;//for the positive count '('
		return res;// Math.abs is not required here, since this is only for the positive case
		//return Math.abs(res);
	}//solve

	public static void main(String[] args) {
		String A="(((";//"())"
		int res=new MinimumParantheses().solve(A);
		System.out.println(res);
	}
}//MinimumParantheses
