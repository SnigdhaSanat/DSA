package Array;

public class MaximumAreaOfTriangle {
    public int solve(String[] A) {
/* Maximise height and base. For base, which is parallel to the y axis, for each column, find the max base length of each of the
3 pairs. Then column wise, for each of the 3 pairs at a time(RG, RB, GB), find the 3rd vertex(If base is RG, find B, and so on).
For this, you need to store the rightmost and leftmost column, and top and bottommost row of each of R, G and B. Total Time complexity: MN */
        int R = A.length;
        int C = A[0].length();

        //Note that top and bottom is for every column
        int topR[] = new int[C];
        int topG[] = new int[C];
        int topB[] = new int[C];
        int bottomR[] = new int[C];
        int bottomG[] = new int[C];
        int bottomB[] = new int[C];

        //initializing left and right to max and min respectively

        //Note that left and right is 1 for 1 color each
        int leftR = C+1;
        int leftG = C+1;
        int leftB = C+1;

        int rightR = -1;
        int rightG = -1;
        int rightB = -1;

        //initializing top and bottom
        for (int i=0;i<C;i++) {
            //Time complexity: M
            topR[i]=-1;
            topG[i]=-1;
            topB[i]=-1;
            bottomR[i]=-1;
            bottomG[i]=-1;
            bottomB[i]=-1;
        }

        //pre-computing left, top, bottom and right most values for all the 3 colors
        for (int i=0;i<R;i++) {
            String S = A[i];
            for (int j=0;j<S.length();j++) {
                //Time complexity: NM
                char c = S.charAt(j);
                if (c=='r') {
                    if (topR[j]==-1) topR[j]=i;//the topmost r is changed, and then not changed again. Same for other colors.
                    bottomR[j] = i;//bottom value is updated as we go down. Same for other colors.
                    if (rightR < j) rightR = j;//update right value to current rightmost which is j. Same for other colors
                    if (leftR > j) leftR = j;//update left value to current leftmost which is j. Same for other colors
                }
                if (c=='g') {
                    if (topG[j]==-1) topG[j]=i;
                    bottomG[j] = i;
                    if (rightG < j) rightG = j;
                    if (leftG > j) leftG = j;
                }
                if (c=='b') {
                    if(topB[j]==-1) topB[j]=i;
                    bottomB[j] = i;
                    if (rightB < j) rightB = j;
                    if (leftB > j) leftB = j;
                }
            }
        }
//now, maximise the height and base for every column
        int x,y,z,z1,z2;
        double area;
        double ans = -2147483648;
        for (int j=0;j<C;j++) {
            //Time complexity: M
            // R-G  or G-R edge
            int RGBaseMax=Math.max(bottomG[j]-topR[j], bottomR[j]-topG[j]);
            z1 = (rightB-j+1);
            z2 = (j-leftB+1);
            z = Math.max(z1,z2);
            if (topR[j]!=-1 && bottomG[j]!=-1 && topG[j]!=-1 && bottomR[j]!=-1 && z!=-1) {
                area = Math.ceil(z*(RGBaseMax+1)*1.0/2);
                if (area>ans) ans=area;
            }

            // R-B or BR base
            int BRBaseMax=Math.max(bottomB[j]-topR[j], bottomR[j]-topB[j]);

            z1 = (rightG-j+1);
            z2 = (j-leftG+1);
            z = Math.max(z1,z2);
            if (bottomB[j]!=-1 && topR[j]!=-1 && bottomR[j]!=-1 && topB[j]!=-1  && z!=-1) {
                area = Math.ceil(z*(BRBaseMax+1)*1.0/2);
                if (area>ans) ans=area;
            }


            // GB or BG edge
            int GBBaseMax=Math.max(bottomG[j]-topB[j], bottomB[j]-topG[j]);
            z1 = (rightR-j+1);
            z2 = (j-leftR+1);
            z = Math.max(z1,z2);
            if (bottomG[j]!=-1 && topB[j]!=-1 && bottomB[j]!=-1 && topG[j]!=-1 && z!=-1) {
                area = Math.ceil(z*(GBBaseMax+1)*1.0/2);
                if (area>ans) ans=area;
            }
        }
        return Math.max((int) ans,0);
    }//solve

    public static void main(String[] args) {
        String[] A=new String[]{"rrr", "rrr", "rrr", "rrr"};
        int res=new MaximumAreaOfTriangle().solve(A);
        System.out.println(res);
    }
}//MaximumAreaOfTriangle
