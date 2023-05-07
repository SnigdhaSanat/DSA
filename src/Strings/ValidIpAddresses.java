package Strings;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ValidIpAddresses {
	/** Strategy:For the given start to end, partition for (start,start), (start,start+1)...(start,end). Eg: for "25525511135",
partition for "2","25","255","2552",....."25525511135". For each of these lateral iterations, find the remaining. That is, for
the above, the corresponding ones will be "5525511135", "525511135", "25511135", "5511135", ..."".
If the first part is a valid(>=0 and <=255), push into stack.For the second part, if it is non "", recurse.
If level==4, it is a base condition. Within base condition, if it is an empty string "", push into the result.

*/
	class Utility{
		Stack<String> stk;
		ArrayList<String> res;
	}

	public ArrayList<String> restoreIpAddresses(String A) {
		Utility util=new Utility();
		util.stk=new Stack<String>();
		util.res=new ArrayList<String> ();

		int n=A.length();

//entry point
		int start=0;
		int end=n-1;
		int level=0;
		for(int currentEnd=start; currentEnd<=end;currentEnd++){
			String s=A.substring(start, currentEnd+1);
			if(isValid(s)){
				util.stk.push(s);
				if(currentEnd<n-1){
					String secondPart=A.substring(currentEnd+1,end+1);
					recurse(A,n, secondPart,currentEnd+1, end,util,level+1);
				}
				else{
					String secondPart="";
					recurse(A,n, secondPart,currentEnd+1, end,util,level+1);
				}
			}//if
		}//for

		return util.res;
	}//restoreIpAddresses

	void recurse(String A,int n,String str,int start, int end, Utility util,int level){
		if(level==4){
			//4, since the address can have only 4 parts
			//  If level=4, you HAVE to pop
			//However, putting them into the res is conditional,and happens only if str=""

			//take a snapshot conditionally
			if(str.contentEquals("")){
				Iterator itr=util.stk.iterator();
				boolean isFirstPart =true;

				String temp="";
				while(itr.hasNext()){
					if(isFirstPart){
						temp+=itr.next();
						isFirstPart=false;

					}//if
					else{
						temp+="."+itr.next();
					}//else
				}//while

				util.res.add(temp);
			}

			util.stk.pop();
			return;
		}//base condition

		for(int currentEnd=start; currentEnd<=end;currentEnd++){
			String s=A.substring(start, currentEnd+1);

			if(isValid(s)){
				util.stk.push(s);
				if(currentEnd<n-1){
					String secondPart=A.substring(currentEnd+1,n);
					recurse(A,n, secondPart,currentEnd+1, end,util,level+1);
				}
				else{
					String secondPart="";
					recurse(A,n, secondPart,currentEnd+1, end,util,level+1);
				}

			}//if valid

			//continue with next lateral iteration
		}//for

		util.stk.pop();
		return;

	}//recurse

	boolean isValid(String s){
		boolean res=false;
		BigInteger bi=new BigInteger(s);

		if(s.charAt(0)=='0' && s.length()!=1){
			return false;
		}

		BigInteger biZero=BigInteger.valueOf(0);
		BigInteger bi255=BigInteger.valueOf(255);
//int sInt=Integer.parseInt(s);

		if( bi.compareTo(biZero)>=0 && bi255.compareTo(bi)>=0){
			res=true;
		}//if

		return res;
	}//isValid

	public static void main(String[] args) {
		String A="25525511135";
		ArrayList<String> res=new ValidIpAddresses().restoreIpAddresses(A);
		for(int i=0;i<res.size();i++){
			System.out.println(res.get(i));
		}
	}//main
}//ValidIpAddresses
