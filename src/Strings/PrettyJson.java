package Strings;

import java.util.ArrayList;

public class PrettyJson {
	public String[] prettyJSON(String A) {
		int n=A.length();
		ArrayList<String> al=new ArrayList<String>();
		int rowCurrent=-1;

		int index=0;
		int indent=0;

		while(index<n){
			char c=A.charAt(index);

			if(c=='[' || c=='{'){
				//add a new line, add the current indentation, add the char and increase the indentation AFTER it
				al.add("");
				rowCurrent+=1;
				String s="";
				for(int i=1;i<=indent;i++){
					s+="\t";
				}
				s+=Character.toString(c);
				al.set(rowCurrent,s);
				indent+=1;

			}
			else if(c==']' || c=='}'){
				//decrease the indentation first, and THEN add the new indentation in a new line, and then the char
				indent-=1;
				al.add("");
				rowCurrent+=1;
				String s="";
				for(int i=1;i<=indent;i++){
					s+="\t";
				}
				s+=Character.toString(c);
				al.set(rowCurrent,s);

			}

			else if(c==','|| c==':'){
				//if it is a comma or colon, just append it to the current row
				String s=al.get(rowCurrent);
				s+=Character.toString(c);
				al.set(rowCurrent,s);

			}
			else {
				//for other chars like double quote and letters, add a new row and the current indentation CONDITIONALLY,
				//only if it is preceded by a comma,{,},[, or]
				if (A.charAt(index-1)==',' || A.charAt(index-1)=='{'|| A.charAt(index-1)=='['
						||A.charAt(index-1)==']'||A.charAt(index-1)=='}')
				{
					al.add("");
					rowCurrent += 1;
					String s = "";
					for (int i = 1; i <= indent; i++) {
						s += "\t";
					}
					al.set(rowCurrent, s);
				}//if

				String s=al.get(rowCurrent);
				s+=Character.toString(c);
				al.set(rowCurrent,s);
			}

			index+=1;
		}//while

		int len=al.size();
		//removing the last empty rows
		int rowIndex=len-1;
		while(al.get(rowIndex).trim()==""){
			len-=1;
			rowIndex-=1;
		}

		String[] res=new String[len];
		for(int i=0;i<len;i++){
			res[i]=al.get(i);
		}

		return res;
	}//prettyJSON

	public static void main(String[] args) {
		String A = "[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]";
		String[] res=new PrettyJson().prettyJSON(A);
		for(int i=0;i<res.length;i++){
			System.out.println(res[i]);
		}
	}
}//PrettyJson
