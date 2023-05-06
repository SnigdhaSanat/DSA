package Strings;

import java.util.ArrayList;

public class VowelAndConsonantSubstrings {
	public int solve(String A) {
/*The logic is simple, for every vowel(start/end), the possible subarray will be picking each of the consonants as
end or start respectively, and vice versa. The vice-versa is redundant, as it already is covered in the first pass.
So, res=number of vowels*number of consonants*/
		int n=A.length();
		ArrayList<Integer> vowels=new ArrayList<Integer>();
		ArrayList<Integer> consonants=new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			char ch=A.charAt(i);
			String s=String.valueOf(ch);
			if ((s.contentEquals("a")) || (s.contentEquals("e")) || (s.contentEquals("i")) || (s.contentEquals("o")) ||
					(s.contentEquals("u")))
			{
				//if vowel
				vowels.add(i);

			}
			else{
				//if consonant
				consonants.add(i);

			}
		}//for

		int vowelsCount=vowels.size();
		int consonantsCount=consonants.size();

		// if(vowelsCount==0 || consonantsCount==0){
		//     return 0;
		// }
		int res= (int)((vowelsCount*consonantsCount)% (1e9 + 7));
		return res;
	}//solve

	public static void main(String[] args) {
		String A="aba";
		int res=new VowelAndConsonantSubstrings().solve(A);
		System.out.println(res);
	}
}
