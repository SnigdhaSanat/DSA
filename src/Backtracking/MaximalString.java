package Backtracking;

public class MaximalString {
    class MaxValue{
        public int val;
    }

    public String solve(String A, int B) {
        //To be passed ACROSS recursions
        MaxValue maxValue = new MaxValue();
        maxValue.val = Integer.MIN_VALUE;

        recurse(A,B, maxValue);
        return String.valueOf(maxValue.val);
    }//solve

    void recurse(String A, int B, MaxValue maxValue){
//base condition of recursion
        if(B==0){
            return;
        }

        char[] ch=A.toCharArray();

//generate the possibilities(BFS)
        int n=A.length();

        //Lateral iterations
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                //each iteration is 1 possibility
                int first=Integer.parseInt(Character.toString(ch[i]));
                int second=Integer.parseInt(Character.toString(ch[j]));

                //if this lateral iteration is valid, optionally make some changes, update whatever you need to, recurse, and then
                // during backtrack change back the optional changes
                if(second>first){
                    //this is conditional. Proceed only if jth digit is greater than ith digit. Only then it can contribute
                    //towards max lexicographical string

                    /*Note: This rearrangement is done BEFORE recursion. So rearrange it back AFTER the recursive function returns */
                    char temp=ch[i];
                    ch[i]=ch[j];
                    ch[j]=temp;

                    String rearranged=String.valueOf(ch);

                    //update max
                    if(Integer.parseInt(rearranged)>maxValue.val){
                        maxValue.val=Integer.parseInt(rearranged);
                    }//if

                    //recurse for this possibility(DFS)
                    recurse(rearranged,B-1, maxValue);

                    //backtrack: rearrange back A.
                    temp=ch[i];
                    ch[i]=ch[j];
                    ch[j]=temp;

                }//if

                else{
                    //do nothing
                }//else

            }//inner for
        }//outer for. End of lateral iteration

        return;

    }//recurse

    public static void main(String[] args) {
        String A = "254";
        int B = 2;
        String res=new MaximalString().solve(A,B);
        System.out.println(res);
    }
}//MaximalString
