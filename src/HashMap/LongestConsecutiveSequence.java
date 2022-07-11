package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LongestConsecutiveSequence {

    public int longestConsecutive(final int[] arr) {
        HashSet<Integer> S = new HashSet<Integer>();
        int ans = 0;
        int n=arr.length;

        // Hash all the array elements
        for (int i = 0; i < n; ++i)
            S.add(arr[i]);

        // check each possible sequence from the start
        // then update optimal length
        for (int i = 0; i < n; ++i)
        {
            int element=arr[i];
            // if current element is the starting
            // element of a sequence
            if (!S.contains(element- 1))
            {
                // Then check for next elements
                // in the sequence
                int j = element;
                while (S.contains(j))
                    j++;

                // update  optimal length if this
                // length is more
                if (ans < j - element)
                    ans = j - element;
            }
        }
        return ans;

    }//longestConsecutive

    public static void main(String args[]) {
    int[] list=new int[]{100, 4, 200, 1, 3, 2};

    int  result=new LongestConsecutiveSequence().longestConsecutive(list);
    System.out.println(result);
    }//main
}
