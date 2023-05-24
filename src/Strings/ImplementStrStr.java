package Strings;

public class ImplementStrStr {
    public int strStr(final String A, final String B) {
//A is the haystack, B is the needle. This is solved by KMP-LPS algo
        if(A.isEmpty()||B.isEmpty()){
            return -1;
        }//if

        return KMPSearch(B,A);
    }//strStr

    int  KMPSearch(String pattern, String A)
    {

        int M = pattern.length();
        int N = A.length();
        int res=-1;

        int lps[]=new int[M];//length of pattern

        computeLPSArray(pattern,M,lps);
        //computeLPSArray("ABCDABCEABCF",12,new int[12]);
/**
 * We now compare with i as the index of the string and j as the index of the pattern. Let's say pattern WXYZ is matched with AB'WX'CD'WXY'DD'WXYZ'FF. So we have occurrences of WX, WXY and WXYZ in the string, and we need the pattern WXYZ. We move the i index of the string linearly. We move j index of the pattern back and forth, using the created LPS. If i and j matches, all good. If j does not match with i? We move j index of the pattern back, to LPS[j-1]. So we had i-1 and j-1 matching. Now i and j do not match. So LPS[j-1], say 5, is the length of the prefix, which is from 0 to 4 in a 0 based index. At 5, that is LPS[j-1], we start matching the pattern again with i. If j cannot be moved back since it is already 0, increment i. Why? We try our best to find a match for i, by moving back j, But now that j cannot be moved back any further, we sort of accept that and move ahead in i(the string).
 *
 * How do we move back? Say the pattern is ABDABC, with LPS=000120. Say we are matching the C at the last index. So we have found a match till ABDAB, which is till B at 4.. If C and the current i in the string do not match, we find the previous B(till the current B at 4 it matches) at 1 and match again. Effectively we say that let's match the previous B of the pattern. Sine we reach the previous B through the LPS, we can say that till the previous B(at i=1), all matches. That is the crux of the LPS.
 */


        int i = 0; // index for the string A
        int j=0;//index for the pattern

        while(i<N){
            if(j==M){
                //case when the match happens, but N is still not traversed. So break the loop
                res=i-M;
                break;
            }

            if(A.charAt(i)==pattern.charAt(j)){
                i+=1;
                j+=1;
            }//if

            else if(i<N && A.charAt(i)!=pattern.charAt(j)){
        /**In case of mismatch,always update j=LPS[j-1]. Now in case j==0, (update) j=0. As for i, you do not need to
        update i in general, only update j. IMP: But if j=0, it it means that current i and j=0 mismatch, means that j
        being 0, there is no more j to compare i with. So increment i as well
        */

                if (j != 0)
                {
                    //case where you DO NOT start over, update j appropriately, and DO NOT update i
                    j = lps[j - 1];
                }
                else
                {/*if j==0, you start over. It is equivalent to saying j=0*/
                    j=0;//this is redundant, only keep it for understandability
                    i = i + 1;
                }
            }//outer else

        }//while

        //Now N is fully traversed, so 2 cases can arise, either match is found(j==M), else a mismatch
        if(j==M){
            res=i-M;
        }
        else{
            //This is actually redundant. This is just for understanding.
            res=-1;
        }
        return res;
    }//KMPSearch

    int[] computeLPSArray(String pat, int M, int lps[])
    {
/*Note that this is quite tricky. So believe in the robustness of this tried and tested algo*/
/**lps[i] = the longest length of PROPER prefix of pat[0..i] which is also a suffix of pat[0..i].
 When comparing i and j, 0 to i-1 will always be the prefix, which means starting right from index 0 to i-1, matches with index j-i to j-1. So now if i and j matches, LPS j takes the length 0 to i, which is i+1(0 based index). If there is a mismatch, i goes back. How much?  Say the prefix "ABCDDEABC" is being matched with a possible suffix "ABCDDEABC", with indices 0 to 8. Now if index 9, say X does not match with j: Y, i does not go back all the way to 0. It checks index 8's LPS value. Considering index 8 itself now as a suffix, it finds its prefix, meaning the ABC at index 6,7,8 is a suffix to ABC at the beginning, index:0 to 2. So essentially the prefix is broken down into a prefix and suffix in itself, and the new smaller prefix is now considered. That is LPS[8] as a suffix now ,8(i-1) has the length of the smaller prefix. As a 0 based index, the new prefix ends at LPS[8]-1. So place i at LPS[i-1]. Why? We already had the original prefix ending at 8 matching with the j-1. If j does not match, we find a smaller prefix further left, which matches with the current prefix as the suffix, and try matching the new smaller prefix with index j. If the prefix from 0 to j-1, ABCDDEABC, matched with the suffix ending at i-1, surely the smaller prefix(ABC), which matches with the bigger prefix will match with the suffix will j-1. We only need to compare further now.

 If the smaller prefix does not match, we repeat the process until we get to 0*/
        int i=0;
        lps[0]=0;

        int j=1;

        // the loop calculates lps[i] for i = 1 to M-1
        while(j<M){
            if(pat.charAt(i)==pat.charAt(j)){
                /**IMP: Note that lps kind of uses a 1 based index*/
                lps[j]=i+1;
                i+=1;
                j+=1;
            }//outer if
            else{
                //if not equal
                if(i!=0)
                {
/**for ABCDABCEABCF, say the indices E at i=7 and F at j=11 are being matched. Since there is a mismatch, 'i' need to go back. Till what? C at i=6 is already
matched, so LPS[C] at i=6, which is 3, cannot be matched. 3 means there is a matching LPS from 0 to 2(which is why LPS is 3). So position i at 2+1=3,
which is the LPS[i-1].
* */
                    i=lps[i-1];
                }
                else{
//in this case you cannot adjust i. So j should move on
                    lps[j]=0;
                    j+=1;
                }

            }//outer else

        }//while

        return lps;
    }//computeLPSArray

    public static void main(String[] args) {
        String A="AAAAABAAABA";
        String B="AAAA";
        int position=new ImplementStrStr().strStr(A,B);
        System.out.println(position);

//        String pat="ABCDABCEABCF";
//        int[] res=new ImplementStrStr().computeLPSArray(pat, pat.length(),new int[pat.length()]) ;
    }//main
}//ImplementStrStr
