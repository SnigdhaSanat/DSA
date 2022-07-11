package HashMap;

import java.util.HashMap;

public class WindowString {

public String minWindow(String str, String pat) {

HashMap<String,Integer> hmPat=new HashMap<String,Integer>();
HashMap<String,Integer> hmStr=new HashMap<String,Integer>();
int start=0;

//length of pat covered by str so far
int lenCovered=0;

//to keep track of valid results
int startIndex=-1;
int minLength=Integer.MAX_VALUE;

//create a hashmap for pat
int lenPat=pat.length();
    for(int i=0;i<lenPat;i++){
        String s=Character.toString(pat.charAt(i));
        if(hmPat.containsKey(s)){
            int value=hmPat.get(s)+1;
            hmPat.put(s,value);
        }
        else{
            hmPat.put(s,1);
        }

    }//for
// now iterate for the length of the String s
int lenStr=str.length();
    for(int i=0;i<lenStr;i++){
        //put into hmStr
        String s=Character.toString(str.charAt(i));

        if(hmStr.containsKey(s)){
            int value=hmStr.get(s)+1;
            hmStr.put(s,value);
        }
        else{
            hmStr.put(s,1);
        }

        //expand conditionally
        /*Expanding is not exactly conditional as we have been iterating over str. We only need to keep track if
        * we have reached a window where all chars of pat are present. If so, we can start contracting the window*/

        if(hmPat.containsKey(s) && hmPat.get(s) >=hmStr.get(s)){
            //if the current char of str is present in the pat AND
            //its count is still less, or just equalled to the char's count in pat
            //Why not cover if its count surpasses pat's count? In that case, there won't be any progress made by the current window
            //Say pat is ABC, and current str is ABCA or ABCB. At the 4th string, we don't incr the lensCoveredCount.
            //This means if(lenCovered==lenPat) will still hold. THIS IS AN IMP THING, to NOT INC lensCovered for the 4th string
            //Else if(lenCovered==lenPat) condition will fail
            lenCovered+=1;
        }
        //contract conditionally
        if(lenCovered==lenPat){
            //if the current window covers all chars of pat

            //String startStr=Character.toString(str.charAt(start));
            while(!hmPat.containsKey(Character.toString(str.charAt(start)))
                    || hmPat.get(Character.toString(str.charAt(start)))<hmStr.get(Character.toString(str.charAt(start))))
            {
                //if the char at the start exists only in the str OR
                //if the char at start has a lower count in the hmPat compared to hmStr

                //update hmStr
                int tempValue=hmStr.get(Character.toString(str.charAt(start)))-1;
                hmStr.put(Character.toString(str.charAt(start)),tempValue);

                //increment start, effectively moving the left pointer forward
                start+=1;
            }//while

            //update the currRes
            int currLen=i-start+1;
            //conditionally update minLength
            if(currLen<minLength){
                minLength=currLen;
                startIndex=start;
            }//if

        }//if

    }//for
if(startIndex==-1){
    return "";
}
return str.substring(startIndex,startIndex+minLength);

}//minWindow

public static void main(String args[]) {

String str="ADOBECODEBANC";
String pat="ABC";

    String  resultMain=new WindowString().minWindow(str,pat);
    System.out.println(resultMain);

}//main

}//WindowString
