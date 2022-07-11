package Tree;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public int solve(int n) {


///Math.log(2), as bu default it computes log e
        int x=(int)Math.ceil(Math.log(n)/Math.log(2));
        int len=2*(int)Math.pow(2,x)-1;
        return len;
    }

    public static void main(String[] args) {
        int param=1;
        int res=new Test().solve(param);
        System.out.println(res);
    }

}
