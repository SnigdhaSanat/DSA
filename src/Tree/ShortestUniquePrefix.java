package Tree;

import java.util.ArrayList;

public class ShortestUniquePrefix {
final int charCount=26;
class TrieNode{
    TrieNode[] children=new TrieNode[charCount];
    //boolean isEnd;
    int childCount;
    TrieNode(){
        for(int i=0;i<charCount;i++){
            children[i]=null;
        }//for
        //isEnd=false;
        childCount=0;
    }//ctor
}//class TrieNode

public String[] prefix(String[] A) {
    /*create a Trie for the strings in A. Then traverse each one again, and for each of them,
     * get the LAST node starting from ROOT node having, more than 1 children. Its child  will be the prefix for it.
     * Reason? Because if its parent has more than 1 children, it is the parent(prefix) of more than 1 word.
     * So we need its children of the next level to distinguish the words*/

TrieNode root=new TrieNode();
TrieNode curr=root;
int len=A.length;

//Create the Trie
for(int i=0;i<len;i++){
    String str=A[i];
    int strLen=str.length();
    for(int j=0;j<strLen;j++){
        int index=(int)str.charAt(j)-97;//97 is ASCII for 'a'
        if(curr.children[index]==null){
            curr.children[index]=new TrieNode();
            curr.childCount+=1;
        }
        else{
            //do nothing
        }
        curr=curr.children[index];
    }//inner for
    //Mark end of str, and reset curr
    //curr.isEnd=true;
    curr=root;
}//outer for

//now traverse the strings again 1 by 1
//ArrayList<String> res=new ArrayList<String>();
int[] prefixCount=new int[len];//stores the prefix len for each of the strings
for (int i=0;i<len;i++){
    String str=A[i];
    int strLen=str.length();
    curr=root;
    boolean currHasMultipleChildren=true;//always true for root
    int prefixLen=0;

    for(int j=0;j<strLen;j++){
        int index=(int)str.charAt(j)-97;//97 is ASCII for 'a'
        if(currHasMultipleChildren){
            prefixLen=j;
        }

        curr=curr.children[index];
        //if curr has multiple children. this is required for next level

        currHasMultipleChildren=curr.childCount>1;
    }//inner for
    //assign prefixLen to prefixCount[]
    prefixCount[i]=prefixLen;
}//outer for

String[] res=new String[len];
for(int i=0;i<len;i++){
    int length=prefixCount[i];
    res[i]=A[i].substring(0,length+1);
}
return  res;
}//prefix


public static void main(String[] args) {
    String[] A=new String[]{"zebra", "dog", "duck", "dove"};
    String[] result= new ShortestUniquePrefix().prefix(A);
    for (int i=0;i<A.length;i++){
        System.out.println(result[i]);
    }
}//main
}//ShortestUniquePrefix
