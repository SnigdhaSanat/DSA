package BinarySearch;

import Strings.ImplementStrStr;

public class ImplementPowerFunction {
    public int pow(int x_short, int n_short, int d_short) {
        /*
        There are 4 concepts here.

        First: power of x^n. Strategy: We iterate over n, and at every iteration, square
        x and half n. If there is an odd value of n at any iteration, store the value of x at that iteration into the result.
        Why this works needs elaboration.  Say  x=3 and n=11. That is 3^11. We need to break it into a form of
        (3^a+3^b+3^c...). How do you find a, b anc c? For this, go to the binary form. For 11, it is 1011. So it gives 11=1+2+8.
        So we need 3^1+3^2+3^8  gives the answer. The task is then to find the numbers 1, 2 and 8. Think about how
        the binary form is obtained: we divide continuously by 2 and store the remainder. So that is what you do,
        keep on dividing 11 by 2 continuously. The points where remainder is 1 are 1, 2 and 8. Those are the points we store in res

        The second concept is that (A*B)%P = ((A%P)*(B%P))%P. So update modulo at every n iteration

        Third concept is that for negative numbers,update mod value as in JAVA, it gives a remainder which can be -ve

        Fourth: Convert the values to long type
        */

        long x=(long)x_short;
        long n=(long)n_short;
        long d=(long)d_short;

        long res=1;

        x=x%d>=0?x%d:(x%d)+d;// Update x if it is >= to p

        //to make modulo +ve
        if (x == 0)
            return 0; // In case x is divisible by d;

        while(n>0){
            if(n%2!=0){
                //if n is odd
                res=(res*x)%d>=0?((res*x)%d):((res*x)%d)+d;//to make modulo +ve,
            }
            n=n/2;

            //to make modulo +ve
            x=(x*x)%d>=0?((x*x)%d):((x*x)%d)+d;
        }//while

        return (int)res;
    }//pow

    public static void main(String[] args) {
        int x=2;
        int n=3;
        int d=3;
        int res=new ImplementPowerFunction().pow(x,n,d);
        System.out.println(res);
    }//main
}//ImplementPowerFunction
