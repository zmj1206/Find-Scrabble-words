// Name: Minjie Zhao
// USC NetID: minjiezh
// CS 455 PA4
// Spring 2017

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
   HashMap<String,ArrayList<String>> map = new HashMap<>();

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      File fis = new File(fileName);
      Scanner in = new Scanner(fis);
      while(in.hasNext()){
         String word = in.next();

         if(word.equals(".")){
            break;
         }

         String ans = "";

         ArrayList<Character> unique = new ArrayList<>();    //get unique characters in each word
         for(int i = 0;i<word.length();i++){
            unique.add(word.charAt(i));
         }
         Collections.sort(unique);
         Iterator<Character> iter = unique.iterator();
         while(iter.hasNext()){
            ans = ans + iter.next();
         }

         if(map.containsKey(ans)){                       //put words in certain arreylist
            ArrayList<String> storedWords;
            storedWords = map.get(ans);

            storedWords.add(word);

            map.put(ans,storedWords);
         }else{
            ArrayList<String> storedWords = new ArrayList<>();
            storedWords.add(word);

            map.put(ans,storedWords);
         }
      }
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
      String ans = "";

      ArrayList<Character> unique = new ArrayList<>();    //get unique characters in each word
      for(int i = 0;i<s.length();i++){
         unique.add(s.charAt(i));
      }
      Collections.sort(unique);
      Iterator<Character> iter = unique.iterator();
      while(iter.hasNext()){
         ans = ans + iter.next();
      }


      return map.get(ans); // DUMMY CODE TO GET IT TO COMPILE
   }

   // input a string, get anagrams of this string
   public  void getResult(String str){

      String word = str.toLowerCase();

      String ans = "";

      TreeSet<Character> unique = new TreeSet<>();    //get unique characters in each word
      for(int i = 0;i<word.length();i++){
         unique.add(word.charAt(i));
      }
      Iterator<Character> iter = unique.iterator();
      while(iter.hasNext()){
         ans = ans + iter.next();
      }
      int[] mult = new int[ans.length()];
      for(int i = 0; i < word.length(); i++){
         for(int j = 0; j < ans.length(); j++){
            if(word.charAt(i) == ans.charAt(j)){
               mult[j]++;
            }
         }
      }


      Rack rack = new Rack();
      ScoreTable table = new ScoreTable();
      ArrayList<String> allcombos = new ArrayList<>();

      allcombos.addAll(rack.getAllSubsets(ans, mult, 0));


      TreeSet<String> result = new TreeSet<>(new myComparator());
      for(int i = 0; i < allcombos.size(); i++) {
         if (map.containsKey(allcombos.get(i))) {
            result.addAll(getAnagramsOf(allcombos.get(i)));
         }
      }

      char[] printWord = word.toCharArray();
      Arrays.sort(printWord);
      //String newWord = printWord.toString();
      myPrint(result,String.valueOf(printWord));

      for(String s : result){
         System.out.println(table.getScore(s) + ": " + s);
      }

      System.out.print("Rack? ");

   }

   class myComparator implements Comparator<String>{
      @Override
      public int compare(String s1, String s2) {

         ScoreTable myTable = new ScoreTable();

         int score1 = myTable.getScore(s1);

         int score2 = myTable.getScore(s2);

         if (score2 - score1 != 0) {
            return score2 - score1;
         }
         else {
            return s1.compareTo(s2);
         }
      }
   }

   private void myPrint(TreeSet tree, String str){
      System.out.println("We can make "+tree.size()+" words from \"" + str + "\"");
      System.out.println("All of the words with their scores (sorted by score)");
   }
   
   
}
