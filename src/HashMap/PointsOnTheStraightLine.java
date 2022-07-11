package HashMap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PointsOnTheStraightLine {

public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
int n=a.size();

if(n<=2){
//for 0 1 and 2 number of points
    return n;
}

//lists to keep all the points
ArrayList<Integer> xList=new ArrayList<Integer>();
ArrayList<Integer> yList=new ArrayList<Integer>();

//Hashmap that uses m and c as key, and as values, contains the points,  EACH of whose pairwise value is m and c
HashMap<String,ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
DecimalFormat df2 = new DecimalFormat("#.##");

for(int i=0;i<n;i++){
    //outer loop iterates through the input list
    int x=a.get(i);
    int y=b.get(i);


    //pair it with all the points, and get the slope m and intersection c
    if(xList.size()>0){
    //and implicitly ylist.size()>0
        int len=xList.size();
        for(int j=0;j<len;j++){
            String key="";
            //inner loop pairs the current point with all the previous points
            int xPrev=xList.get(j);
            int yPrev=yList.get(j);
            double m=-1;
            double c=-1;
            if(x-xPrev==0){
                //there will be an exception for lines parallel to y axis
                //Having m and c DOES NOT necessarily mean that the points are collinear.
                //So instead of assigning INT MAX to c, assign the x intercept
                m=Integer.MAX_VALUE;
                c=x;
                key+=String.valueOf(m)+"|"+String.valueOf(c);
            }
            else if(y-yPrev==0){
                //parallel to x axis
                m=0;
                c=y-(m*x);
                key+=String.valueOf(m)+"|"+String.valueOf(c);
            }
            else{
                double ydiff=y-yPrev;
                double xdiff=x-xPrev;

                int[] mFraction=asFraction((int)ydiff,(int)xdiff);
                String numerator=String.valueOf(mFraction[0]);
                String denominator=String.valueOf(mFraction[1]);

                double slope=(ydiff/xdiff);
                m=Double.valueOf(df2.format(slope));

                double intercept=y-(m*x);
                c=Double.valueOf(df2.format(intercept));

                key+=numerator+"|"+denominator+"|"+String.valueOf(c);
            }


            //Add the points into the hashmap, indexed by string "mc"
            //i and j is put into the string because the input list might have duplicate points
            String strVal=String.valueOf(x)+"|"+String.valueOf(y)+"|"+String.valueOf(i);
            String strPrevVal= String.valueOf(xPrev)+"|"+String.valueOf(yPrev)+"|"+String.valueOf(j);
            if(hm.containsKey(key)){
                //if hm already contains points of this slope and intersection,
                //order of adding is important.Add the prev first CONDITIONALLY
                // then simply add the current point into its string list
                ArrayList<String> alTemp=hm.get(key);
                if(!alTemp.contains(strPrevVal)){
                    alTemp.add(strPrevVal);
                }
                if(!alTemp.contains(strVal)){
                    alTemp.add(strVal);
                }

                int test1=1;
            }
            else{
                //else add a key into the hm, with a new array list.
                //This list contains BOTH the points
                ArrayList<String> temp= new ArrayList<String>();
                //order of adding is important.Add the prev first
                temp.add(strPrevVal);
                temp.add(strVal);
                hm.put(key,temp);
                int test2=1;
            }
        }//inner for
    }//if

    xList.add(x);
    yList.add(y);
    int test=1;
}//outer for

int max=Integer.MIN_VALUE;
Iterator itr=hm.entrySet().iterator();
int counterTest=0;
while(itr.hasNext()){
    //iterates through the hashmap
    Map.Entry me=(Map.Entry)itr.next();

    ArrayList<String> al=(ArrayList<String>)me.getValue();
    int syz=al.size();

   int count=syz;


    if(count>max){
        max=count;
    }
    counterTest+=1;
}//while
return max;
}//maxPoints

public  int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
}

public  int[] asFraction(int a, int b) {
    long gcd = gcd(a, b);
    int[] res=new int[2];
    res[0]=(int)(a/gcd);
    res[1]=(int)(b/gcd);
    return res;
}

public static void main(String args[]) {
// 6 (-6 -17) 5 -16) -18 -17) 2 -4) 5 -13) -2 20
ArrayList<Integer> aList=new ArrayList<Integer>();
ArrayList<Integer> bList=new ArrayList<Integer>();
int[] list=new int[]{15,8,1,-7,-8,-4,3,-2,-14,-14,-7,-17,-2,-10,-11,11,-18,2,9,-4,7,-15,1,-16,9,16,6,1};

for(int i=0;i<list.length;i++){
  if(i%2==0){
     aList.add(list[i]);
  }
  else{
      bList.add(list[i]);
  }
}

int res=new PointsOnTheStraightLine().maxPoints(aList,bList);
System.out.println(res);

}//main

}//Solution
