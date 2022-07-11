package Tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class HotelReviews {
final int  charCount=26;

class TrieNode{
    TrieNode[] children=new TrieNode[charCount];
    boolean isEnd;
    TrieNode(){
        for(int i=0;i<charCount;i++){
            children[i]=null;
        }
        isEnd=false;
    }//ctor
}//TrieNode

class Pair{
    int index;
    int count;
    Pair(int a,int b){
        index=a;
        count=b;
    }//ctor
}//Pair

public int[] solve(String A, String[] B) {
    /*This problem can be solved using a HashSet to store the good words as WORDS, and then iterating through each of the words in each
     reviews in B to count goodness value. Time complexity:O(g*w), where g is the count of good words given, and w is total
      words in B.

      Trie approach: Store each of the LETTERS of the good words in Trie. Then iterate through each of the words in each
     reviews in B to count goodness value. Complexity is same as above. This is only to use Trie.*/

    //initialize root
    TrieNode root=new TrieNode();
    TrieNode curr=root;

    //append _ to A and B[i]
    A+="_";
    for(int i=0;i<B.length;i++){
        B[i]+="_";
    }
    int goodLen=A.length();

    //insert each of the letters in A into a Trie

    for(int i=0;i<goodLen;i++){
        char ch=A.charAt(i);
        int  index=(int)ch-97;//97 is lower case 'a'
        if(ch=='_'){
            //mark the end of the word
            curr.isEnd=true;
            //go back to the root for the next word
            curr=root;
        }//if
        else{
            if(curr.children[index]==null){
                //if it does not exist, create one
                curr.children[index]=new TrieNode();
            }
            else{
                //do nothing
            }
            curr=curr.children[index];//update curr
        }//outer else
    }//for

    //With the goodWord trie being constructed, iterate over B and compare it with the goodWord trie
    int reviewLen=B.length;
    Pair[] p=new Pair[reviewLen];


    for(int i=0;i<reviewLen;i++){
        String review=B[i];
        int rLen=review.length();

        p[i]=new Pair(i,0);
        curr=root;
        boolean isCurrWordGood=true;

        for(int j=0;j<rLen;j++){
            char ch=review.charAt(j);
            int index=(int)ch-97;

            if(ch=='_'){
                //if at any point it did not match, curr would have been sent back to root.
                //Hence curr!=root check is required
                //isEnd has to be true. If cools is a good word, and the review contains cool, it won't pass the test
                if(curr!=root && curr.isEnd ){
                    p[i].count+=1;
                }
                curr=root;
                isCurrWordGood=true;//for the next word
            }//if
            else if(isCurrWordGood){
                //isCurrWordGood check is required because once a word is found out to be not good, ignore the rest of the word
                if(curr.children[index]!=null){
                    //the letter is present in the Trie
                    curr=curr.children[index];
                }
                else{
                    //meaning the word is not present in trie, implying it is not a good word
                    //note that this might be middle of the word. So set the isCurrWordGood as false
                    curr=root;
                    isCurrWordGood=false;
                }
            }//outer else if
        }//inner for
    }//outer for

    Arrays.sort(p, new Comparator<Pair>() {
        @Override
        public int compare(Pair p1, Pair p2) {
            if(p2.count>p1.count)
                //as it should be in descending order
                return 1;
            if(p2.count==p1.count)
                //as it should be in ascending order
                return p1.index-p2.index;
            return -1;
        }//compare
    });

    int [] res=new int[reviewLen];
    for(int i=0;i<reviewLen;i++){
        res[i]=p[i].index;
    }
    return res;
}//solve


public static void main(String[] args) {
    String A = "cool_ice_wifi";
    String[] B= new String[]{"water_is_cool", "cold_ice_drink", "cool_wifi_speed"};
    int[] result=new HotelReviews().solve(A,B);
for(int i=0;i<result.length;i++){
   System.out.println(result[i]);
}
}//main

}//HotelReviews
