package Array;

public class AddOneToNumber {

    /*Strip 0s at the starting, then reverse the number. Then perform the carry operation for each index, carrying the carry value if required,
    at every iteration. For the last carry, after the loop ends, if there is a carry, append the carry into a new array, and then un-reverse
    the array. Else, simply un-reverse the array*/
    public int[] plusOne(int[] A) {
// strip 0s at the starting
        int n=A.length;
        int zero_count=0, i=0;
        while(i<n && A[i]==0 )
        {
            zero_count+=1;
            i+=1;
        }//while

        if(i==n) return new int[]{1};//meaning it is all 0

        int n_stripped=n-zero_count;
        int[] A_stripped=new int[n_stripped];

        for(int k=0;k<n_stripped;k++)
        {
            A_stripped[k]=A[k+zero_count];
        }//for


//reverse
        int[] A_rev=new int[n_stripped];
        for(int k=0;k<n_stripped;k++){
            A_rev[k]=A_stripped[n_stripped-1-k];
        }

//perform the operation

        int carry=1;//or whatever you need to add
        int idx=0;
        while( idx<n_stripped)
        {
            carry=A_rev[idx]+carry;
            if(carry<10){
                A_rev[idx]=carry;
                carry=0;
            }//if
            else{
                A_rev[idx]=carry%10;
                carry=carry/10;

            }//else
            idx+=1;
        }//while

        //At the end
        if(carry>0){
            //append the carry and reverse back
            int[] A_append= new int[n_stripped+1];
            A_append[0]=carry;
            for(int j=1;j<=n_stripped;j++){
                A_append[j]=A_rev[n_stripped-j];
            }//for
            return A_append;
        }//if
        else{
            //just reverse back
            int[] A_append= new int[n_stripped];
            for(int j=0;j<n_stripped;j++){
                A_append[j]=A_rev[n_stripped-1-j];
            }//for
            return A_append;
        }//else

    }//plus One

    public static void main(String[] args) {
        int[] A=new int[]{1, 2, 3};
        int[] res=new AddOneToNumber().plusOne(A);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }
}// AddOneToNumber
