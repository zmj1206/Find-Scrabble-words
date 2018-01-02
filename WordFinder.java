// Name: Minjie Zhao
// USC NetID: minjiezh
// CS 455 PA4
// Spring 2017


import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhaominjie on 3/30/17.
 */
public class WordFinder {
    public static void main(String[] args) {

        String fileName = "";

        try {

            if (args.length < 1) {
                fileName = "sowpods.txt";
            } else {
                fileName = args[0];
            }

            AnagramDictionary dict = new AnagramDictionary(fileName);

            Scanner in = new Scanner(System.in);

            System.out.print("Rack? ");
            while(in.hasNext()){
                String word = in.next();
                if(!word.equals(".")) {
                    dict.getResult(word);
                }else{
                    break;
                }

            }

        } catch (FileNotFoundException exc) {
            System.out.println("File not found: " + fileName);
        }
    }
}


