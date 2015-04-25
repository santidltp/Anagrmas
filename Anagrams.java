
import java.io.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
public class Anagrams {

//	The following method takes a 2-dimensional array of Strings.
//	The first column of the array represents the word in the dictionary
//	and the second column represents the key of that word, which is 
//	the word sorted in ascending order alphabetically.
//	Thus, saves each anagram class into a line.
	public static void save(String [][] table) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("anagram2")); 
		String currentKey = "";
		
//		We want to go trhought all the words in the dictionary
		for(int i =0; i < table[0].length;i++){
			if(table[0][i].length()>0){//ignore empty strings
//			In the following if statement, I am trying 
//			to compare the keys, so that all the anagrams
//			are store in the same line for each anagram.
			if(currentKey.equals(table[1][i])){
				out.print(table[0][i] + " ");
			}else{
				if(i>=1 && currentKey.length()>0)
				out.println();
				out.print(table[0][i] + " " );
				
				
			}
			currentKey = table[1][i];
			}
		}
		out.close();
	}
	
//	The following function is a counting sort algorithm
//	that takes a word as an input and sorts it.
//	It works by converting the string into character
//	arrays, and each character will be converted to
//	a number based on their ascii code. For instance;
//	a =0, b=1... etc.	
	public static String countingSort(String word){
	        
		char c[] = new char[26];//because there are 26letters in the alphabet
	        for (int i = 0; i < word.length(); i++)
	            c[word.charAt(i)-97]++;
	        for (int i = 1; i < 26; i++)
	            c[i] += c[i-1];
	        char b[] = new char[word.length()];
	        for (int i = word.length()-1; i >= 0; i--)
	            b[--c[word.charAt(i)-97]] = word.charAt(i);
	        String last = new String(b);//convert the char array into string
	        return last; //return string
	}
	
	public static void main(String [] args) throws IOException{
		System.out.println(System.currentTimeMillis());
		File file = new File(args[0]);
		FileInputStream fis = null;
		BufferedInputStream bis = null;//used for faster reading
		BufferedReader dis =null;
		String [][] table = new String[2][6757474];
		//71885 67605
		//6757474     320,750
		
		int y=0;
		 try {
		      fis = new FileInputStream(file);
		      bis = new BufferedInputStream(fis);
		      dis =  new BufferedReader(new InputStreamReader(bis));;
		      String line ;
	    while ((line =dis.readLine()) != null) {

	    	
	        
//	    	We are reading the dictionary file into
//	    	a 2-dimensional array. The first column of
//	    	the 2-dimensional array will be the word from
//	    	the dictionary. The second column from the array,
//	    	will be the key, which is going to be the alphabetically
//	    	sorted word, which will help us separate the anagram
//	    	classes.
		    	table[0][y] =line;
		    	table[1][y]=countingSort(line);
	    	
	        	  y++;
	        }

	      
	        fis.close();
	        bis.close();
	        dis.close();
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
//		 After we read the words from the dictionary and
//		 put them in a 2-dimensional array, we want to sort
//		 the array by the key(sorted word) so that the 
//		 anagram clases are close to eachother.
//		 For that, we will use MergeSort
		 MergeSort.mergeSort(table);
	
	     save(table);   //use the save method to save the 
	     				//anagrams clases into a text file.
	     System.out.println("Done"); //message when done
	     System.out.println(System.currentTimeMillis());
	      
	}
}
