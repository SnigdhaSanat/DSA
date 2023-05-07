package Strings;

import java.util.ArrayList;

public class JustifiedText {
	public String[] fullJustify(String[] A, int B) {

		/*First divide the string into appropriate rows. Then append space as required*/

		if(A.length<1){
			//if there is no param A passed, simply return it, effectively the null value
			return A;
		}

		ArrayList<String> al=new ArrayList<>();//ArrayList, because count of rows is unknown
		al.add("");//add an element to ArrayList. This is required to be done for ArrayLists
		int n=A.length;

		//divide the strings into lines first

		int row=-1;//to keep track of row;
		int lineIndex=-1;//to keep track of the line length<=B, within a row
		int idxWord=0;//keeps track of words, NOT chars
		boolean newRow=true;//if it is NOT a new row, a space needs to be prepended to the word

		while(idxWord<n){
			String word=A[idxWord];
			if(lineIndex+word.length()+1>=B || (row==-1 && lineIndex==-1)){
				//if it exceeds B, + 1 to accommodate the space before the word
				//or if it the the first word of the first row
				row+=1;//increment the row
				newRow=true;
				al.add("");
				lineIndex= -1;//reset to -1

			}//if

			else{
				newRow=false;
			}

			String s= al.get(row);//the string of the current row


			if(!newRow){
				//if it is an existing row, prepend  space before the word
				s+=" ";
				lineIndex+=1;
			}
			s+= A[idxWord];

			//then set the al element to the String s
			al.set(row, s);
			lineIndex+=A[idxWord].length();//update lineIndex

			idxWord+=1;//move on to the next word

		}//while

		int rowIndex=al.size()-1;
		int size= -1;

		//This is to exclude any row having only spaces
		while(al.get(rowIndex).contentEquals("")){
			rowIndex-=1;
		}//while

		size=rowIndex+1;//since it is a 0 based index

		//copy the ArrayList to a String[] res
		String[] res=new String[size];
		for(int index=0;index<size;index++){
			res[index]=al.get(index);

		}

		//then space them out appropriately
		//create a String[] finalRes to hold the final result

		String[] finalRes=new String[size];
		//initialize it
		for(int index=0;index<size;index++){
			finalRes[index]="";
		}


		for(int rowIdx=0;rowIdx<size;rowIdx++)
		{
			String s=res[rowIdx];//The string int current row
			int rowLength=s.length();//length of s
			int diff=B-rowLength; //buffer left
			int spaceCount=0;

			//count the space
			for(int k=0;k<rowLength;k++){
				if(s.charAt(k)==' '){
					spaceCount+=1;
				}//if

			}//inner for

			/*if it is the last row, or there is no space, place the string in the row, and then append (diff) number of spaces to it*/
			if(rowIdx==size-1 || spaceCount==0){
				finalRes[rowIdx]=res[rowIdx];

				for (int index = 1; index <= (diff); index++) {
					finalRes[rowIdx] += ' ';

				}
			}

			else {
				//for other rows
				int quotient = diff / spaceCount;//the space to be added to each space
				int remainder = diff - (spaceCount * quotient);//to the FIRST remainder spaces, an additional space to be given

				for (int k = 0; k < rowLength; k++) {
					//this loop is to traverse the length of the string at the current row
					if (s.charAt(k) == ' ') {

						//if it is a space
						finalRes[rowIdx] += ' ';//the original space

						for (int index = 1; index <= (quotient); index++) {
							//the spaces available given to all spaces
							finalRes[rowIdx] += ' ';
						}
						if(remainder>0){
							//  an additional space to be given to the FIRST remainder spaces

							finalRes[rowIdx] += ' ';
							remainder-=1;
						}

					}//if

					else {
						//if it is not a space
						finalRes[rowIdx] += s.charAt(k);
					}//else

				}//inner for

			}//else


		}//outer for
		return finalRes;

	}//fullJustify

	public static void main(String[] args) {
		String[] A={"This", "is", "an", "example", "of", "text", "justification."};
		int B=16;
		String[] res=new JustifiedText().fullJustify(A,B);
		for(int i=0;i<res.length;i++){
			System.out.println(res[i]);
		}
	}
}//JustifiedText
