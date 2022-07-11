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

        int i = 0; // index for the string A
        int j=0;//index for the pattern
        while(i<N){
            if(j==M){
                //case when the match happens, but N is still not traversed. So break the loop
                res=i-j;
                break;
            }

            if(A.charAt(i)==pattern.charAt(j)){
                i+=1;
                j+=1;
            }//if

            else if(i<N && A.charAt(i)!=pattern.charAt(j)){
        /*In case of mismatch,always update j=LPS[j-1]. Now in case j==0, (update) j=0. As for i, you do not need to
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
            res=i-j;
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
        //lps[i] = the longest length of proper prefix of pat[0..i] which is also a suffix of pat[0..i].
        int i=0;
        lps[0]=0;

        int j=1;

        // the loop calculates lps[i] for i = 1 to M-1
        while(j<M){
            if(pat.charAt(i)==pat.charAt(j)){
                /*IMP: Note that lps kind of uses a 1 based index*/
                lps[j]=i+1;
                i+=1;
                j+=1;
            }//outer if
            else{
                //if not equal
                if(i!=0)
                {
/*for ABCDABCEABCF, say the indices E at i=7 and F at j=11 are being matched. Since there is a mismatch, 'i' need to go back. Till what? C at i=6 is already
matched, so LPS[C] at i=6, which is 3 cannot be matched. 3 means there is a matching LPS from 0 to 2(which is why LPS is 3). So position i at 2+1=3,
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
    }//main
}//ImplementStrStr
