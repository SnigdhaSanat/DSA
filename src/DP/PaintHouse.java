package DP;

public class PaintHouse {

    public int solve(int[][] A) {
        /*Don't make it a greedy algorithm. where you take min of the 3 colors in 0th house, adn then keep on building the next
        * house by choosing res(i-1)+min[i]. This will pre determine the result based on the min cost color in the 0th house.*/

        /*Row 0 for memoCost will be same as that of A. For every ith row jth col, memoCost[i][j]= A[i][j] + min of all
        * memoCost[i-1][k], where k!=j*/
        /*This is an example of a problem where iterative solution looks easier. Not all problems are easier by recursive way*/

    int len=A.length;
    int COLORS=3;
    int[][] memoCost=new int[len][COLORS];

    for(int i=0;i<len;i++){
        for(int j=0;j<COLORS;j++){
            if(i==0){
                //first row stays as it is
                memoCost[i][j]=A[i][j];
            }//if

            else{
                int min=Integer.MAX_VALUE;

                for(int k=0;k<COLORS;k++){
                    if(k!=j && memoCost[i-1][k]<min){
                        min=memoCost[i-1][k];
                        //util.color[i]=k;
                    }//if
                }//for
                memoCost[i][j]=min+A[i][j];//min plus its own cost
            }//else

        }//inner for
    }//outer for

    return Math.min(memoCost[len-1][0], Math.min(memoCost[len-1][1], memoCost[len-1][2]));
    }//solve

    public static void main(String[] args) {
    int[][] A={
            {468, 335, 501},
            {170, 725, 479},
            {359, 963, 465},
            {706, 146, 282},
            {828, 962, 492},
            {996, 943, 828},
            {437, 392, 605},
            {903, 154, 293},
            {383, 422, 717},
            {719, 896, 448},
            {727, 772, 539},
            {870, 913, 668},
            {300, 36, 895},
            {704, 812, 323},
            {334, 674, 665},
            {142, 712, 254},
            {869, 548, 645},
            {663, 758, 38},
            {860, 724, 742},
            {530, 779, 317},
            {36, 191, 843},
            {289, 107, 41},
            {943, 265, 649},
            {447, 806, 891},
            {730, 371, 351},
            {7, 102, 394},
            {549, 630, 624},
            {85, 955, 757},
            {841, 967, 377},
            {932, 309, 945},
            {440, 627, 324},
            {538, 539, 119},
            {83, 930, 542},
            {834, 116, 640},
            {659, 705, 931},
            {978, 307, 674},
            {387, 22, 746},
            {925, 73, 271},
            {830, 778, 574},
            {98, 513, 987},
            {291, 162, 637},
            {356, 768, 656},
            {575, 32, 53},
            {351, 151, 942},
            {725, 967, 431},
            {108, 192, 8},
            {338, 458, 288},
            {754, 384, 946},
            {910, 210, 759},
            {222, 589, 423},
            {947, 507, 31}};
    int res=new PaintHouse().solve(A);
    System.out.println(res);
    }//main
}//PaintHouse
