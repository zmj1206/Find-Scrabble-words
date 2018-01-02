// Name: Minjie Zhao
// USC NetID: minjiezh
// CS 455 PA4
// Spring 2017

import java.util.ArrayList;

/**
 * Created by zhaominjie on 4/8/17.
 */
public class ScoreTable {

    private final int[] scoreChar;

    public ScoreTable(){
        scoreChar = new int[] {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};


    }
    public   int getScore(String str){
        int total = 0;
        for(int i = 0; i < str.length(); i++){
            total = total + scoreChar[str.charAt(i)-'a'];
        }
        return total;
    }
}
