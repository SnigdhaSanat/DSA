package Practice;


public class KMP {
   int[] CreateLPS(String pat){
//lps[i] = the longest length of proper prefix of pat[0..i] which is also a suffix of pat[0..i].
       int m=pat.length();
       int[] LPS=new  int[m];

       LPS[0]=0;

       //j leads i
       int i=0;
       int j=1;

       while(j<m){
            if(pat.charAt(i)==pat.charAt(j)){
                LPS[j]=i+1;//to adjust the 0 based index
                i+=1;
                j+=1;
            }//if

            else{
                if(i>0){
                    i=LPS[i-1];
                }
                else{
                    LPS[j]=0;
                    j+=1;

                }//inner else
            }//outer else
       }//while

       return LPS;
   }//LPS

    int KMPAlgo(String text, String pat){

       //note that textIndex does not go back ever.
        //In addition, the going back of patIndex is optimized
       int res=-1;

       int n=text.length();
       int m=pat.length();

       int[] LPS=new KMP().CreateLPS(pat);

       int textIndex=0;
       int patIndex=0;


       while(textIndex<n){
           if (patIndex==m) {
               //pattern found
               break;
           }

           if(text.charAt(textIndex)==pat.charAt(patIndex)){
                textIndex+=1;
                patIndex+=1;
           }
           else{
               if(patIndex!=0){
                   patIndex=LPS[patIndex-1];//This is the key of KMP
               }
               else{
                patIndex=0;
                textIndex+=1;
               }

           }
       }//while

        if(patIndex==m){
            return textIndex-m;
        }
        else{
            return -1;
        }

    }//KMPAlgo

    public static void main(String[] args) {
        String A = "uwabcxyabc";
        String B = "uwabcxyabc";
        int position = new KMP().KMPAlgo(A, B);
        System.out.println(position);
    }//main
}//KMP
