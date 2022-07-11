package Backtracking;

import java.util.*;

public class Sudoku {

    /*Strategy: Recurse for every empty square. Base condition is when the last empty square is reached.
    Laterally recurse for every value from 1 to 9. Recurse for the iteration if it is not present in the row,
    AND in the column AND in the sub-grid*/
    class Utility{
        ArrayList<Set<Integer>> rows;//to hold filled rows
        ArrayList<Set<Integer>> cols; //to hold filled cols
        ArrayList<Set<Integer>> subGrids; //to hold filled subGrids

        ArrayList<Integer> emptyRows;//to hold rows of empty grids
        ArrayList<Integer> emptyCols;//to hold cols of empty grids

        Stack<Integer> stk;
        ArrayList<Integer> res;
    }
    public void solveSudoku(ArrayList<ArrayList<Character>> config) {
        Utility util=new Utility();
        util.rows= new ArrayList<Set<Integer>>();
        for(int i=0;i<9;i++){
            util.rows.add(new HashSet<Integer>());
        }

        util.cols=new ArrayList<Set<Integer>>();
        for(int i=0;i<9;i++){
            util.cols.add(new HashSet<Integer>());
        }
        util.subGrids= new ArrayList<Set<Integer>>();
        for(int i=0;i<9;i++){
            util.subGrids.add(new HashSet<Integer>());
        }

        util.emptyRows=new ArrayList<Integer>();
        util.emptyCols=new ArrayList<Integer>();

        util.stk=new Stack<Integer>();
        util.res=new ArrayList<Integer>();

        //initialize the rows, cols and subGrids from the given config
        int len=config.size();
        for(int i=0;i<len;i++){
            ArrayList<Character> configRow= config.get(i);
            for(int j=0;j<configRow.size();j++){
                char str=configRow.get(j);

                if(str!='.'){
                    //add it to the row, col and sub grid it belongs to
                    util.rows.get(i).add(Integer.parseInt(Character.toString(str)));
                    util.cols.get(j).add(Integer.parseInt(Character.toString(str)));
                    int subGrid=getSubGrid(i,j);
                    util.subGrids.get(subGrid).add(Integer.parseInt(Character.toString(str)));
                }//if
                else{
                    //add the row and col to the emptyRows and emptyCols lists
                    util.emptyRows.add(i);
                    util.emptyCols.add(j);
                }
            }//inner for
        }//outer for


        //entry point
        int index=0;

        int row=util.emptyRows.get(index);
        int col=util.emptyCols.get(index);
        int subGrid=getSubGrid(row,col);

        for(int i=1;i<=9;i++) {
            if(!util.rows.get(row).contains(i) && !util.cols.get(col).contains(i) && !util.subGrids.get(subGrid).contains(i)){
                util.rows.get(row).add(i);
                util.cols.get(col).add(i);
                util.subGrids.get(subGrid).add(i);

                util.stk.push(i);

                recurse(config,i,index,index+1,util.emptyRows.size(),util);
            }//if
        }//for


//---------after recursion
        Iterator<Integer> itr1=util.res.iterator();

        for(int i=0;i<9;i++){
            ArrayList<Character> strRow=config.get(i);//original input
            ArrayList<Character> rowTemp=new ArrayList<Character>();
            for(int j=0;j<9;j++){
                //rowTemp.add();
                char st=strRow.get(j);
                //check the original input
                if(st=='.'){
                    String str=String.valueOf(itr1.next());
                    Character ch=str.charAt(0);
                    rowTemp.add(ch);
                }
                else{
                    rowTemp.add(st);
                }
            }//inner for
            config.set(i,rowTemp);
        }//outer for

        return;
    }//solveSudoku
    void recurse(ArrayList<ArrayList<Character>> config,int num,int currentIndex,int nextIndex,int totalEmptyGrids,Utility util){
        //num for this is already pushed into the currentIndex
        int baseRow=util.emptyRows.get(currentIndex);
        int baseCol=util.emptyCols.get(currentIndex);
        int baseSubGrid=getSubGrid(baseRow,baseCol);

        if(currentIndex==totalEmptyGrids-1){
            //base condition
            util.rows.get(baseRow).remove(num);
            util.cols.get(baseCol).remove(num);
            util.subGrids.get(baseSubGrid).remove(num);

            //take a snapshot of stk into res

            Iterator<Integer>itr=util.stk.iterator();
            while(itr.hasNext()){
                util.res.add(itr.next());
            }

            return;
        }//base condition

        //value for this is considered to be pushed into the nextIndex
        int nextRow=util.emptyRows.get(nextIndex);
        int nextCol=util.emptyCols.get(nextIndex);
        int nextSubGrid=getSubGrid(nextRow,nextCol);

        for(int i=1;i<=9;i++) {
            if(!util.rows.get(nextRow).contains(i) && !util.cols.get(nextCol).contains(i) && !util.subGrids.get(nextSubGrid).contains(i)){
                util.rows.get(nextRow).add(i);
                util.cols.get(nextCol).add(i);
                util.subGrids.get(nextSubGrid).add(i);

                util.stk.push(i);
                //now i is pushed to nextIndex
                recurse(config,i,nextIndex,nextIndex+1,totalEmptyGrids,util);
            }//if
        }//for

        //backtrack
        util.rows.get(baseRow).remove(num);
        util.cols.get(baseCol).remove(num);
        util.subGrids.get(baseSubGrid).remove(num);

        util.stk.pop();
        return;
    }//recurse

    int getSubGrid(int row, int col){
        int grid=-1;
        if(0<=row && row<=2){
            //grid can be 0,1,2
            if(0<=col && col<=2){
                grid=0;
            }
            else if(3<=col && col<=5){
                grid=1;
            }
            else if(6<=col && col<=8){
                grid=2;
            }

        }
        else if(3<=row && row<=5){
            //grid can be 3,4,5
            if(0<=col && col<=2){
                grid=3;
            }
            else if(3<=col && col<=5){
                grid=4;
            }
            else if(6<=col && col<=8){
                grid=5;
            }
        }
        else if(6<=row && row<=8){
            //grid can be 6,7,8
            if(0<=col && col<=2){
                grid=6;
            }
            else if(3<=col && col<=5){
                grid=7;
            }
            else if(6<=col && col<=8){
                grid=8;
            }
        }
        return grid;
    }//getSubGrid

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> param=new ArrayList<ArrayList<Character>>();
        param.add(new ArrayList<Character>(Arrays.asList('5','3','.','.','7','.','.','.','.')));
        param.add(new ArrayList<Character>(Arrays.asList('6','.','.','1','9','5','.','.','.')));
        param.add(new ArrayList<Character>(Arrays.asList('.','9','8','.','.','.','.','6','.')));
        param.add(new ArrayList<Character>(Arrays.asList('8','.','.','.','6','.','.','.','3')));
        param.add(new ArrayList<Character>(Arrays.asList('4','.','.','8','.','3','.','.','1')));
        param.add(new ArrayList<Character>(Arrays.asList('7','.','.','.','2','.','.','.','6')));
        param.add(new ArrayList<Character>(Arrays.asList('.','6','.','.','.','.','2','8','.')));
        param.add(new ArrayList<Character>(Arrays.asList('.','.','.','4','1','9','.','.','5')));
        param.add(new ArrayList<Character>(Arrays.asList('.','.','.','.','8','.','.','7','9')));

        new Sudoku().solveSudoku(param);

    }
}//Sudoku
