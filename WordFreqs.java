import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;

/**
A word counting program using the HashTable class. 
Takes a text file and runs an interactive program that allows the user
to query the count of specific words relatively quickly. 
*/
public class WordFreqs{
	public static void main(String[] args){
		String[] words_kept;
		StringTokenizer words_ind;
		BufferedReader reader;
		String token;
		int num_tokens;
		int num_tokens2;
		String f_name;
		ArrayList text_array = new ArrayList<String>();
		Path path;
		HashTable<String, Integer> hash_table;
		Scanner sc;
		if(args[0] == null){
			System.out.println("Please enter a text file.");
			System.out.println("Usage: java WordFreqs [file_path]");
			System.exit(0);
		}
		f_name = args[0];
		path = Paths.get(f_name);
        try{
        	reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String line;
			num_tokens = 0;
			num_tokens2 = 0;
			while((line = reader.readLine()) != null){
				line = line.toLowerCase();
				words_kept = line.split("[^abcdefghijklmnopqrstuvwxyz1234567890_']");
				int i = 0;
				for(i = 0; i < words_kept.length; i++){
					token = words_kept[i];
					if(token.length() == 0){
					}
					else if(token.equals("'")){
					}
					else{
						token = token.replaceAll("'$", "");
		        		token = token.replaceAll("^'", "");
						text_array.add(token);
						num_tokens++;
					}
				}
				num_tokens2++;
			}
			int size = text_array.size();
			int i = 0;
			String key;
			hash_table = new HashTable<String, Integer>();
			for(i = 0; i < text_array.size(); i++){
				Integer value;
				key = (String)text_array.get(i);
				//if this word already exists the table, increment its count
				if(hash_table.get(key) != null){
					//getting the wordcount associated with the word entered
					value = hash_table.get(key);
					//increment the wordcount of this word
					value++;
				}
				//if this is a new word in the table
				else{
					value = new Integer(1);
				}
				//enter the new key, value pair into the table
				hash_table.put(key, value);
			}
			System.out.println("Please enter a word you wish to find.");
			System.out.println("To exit press enter");
			System.out.println("This text contains "+hash_table.getDistinct()+" distinct words.");
			sc = new Scanner(System.in);
			while(true){
				System.out.print(">");
				String word = sc.nextLine();
				if(word.equals("")){
					System.out.println("Goodbye!");
					System.exit(0);
				}
				else if(word.startsWith("-")){
					word = word.replaceAll("-", "");
					hash_table.delete(word);
				}
				else if(hash_table.get(word) == null){
					System.out.println("\"" + word + "\" does not appear.");
				}
				else{
					Integer count = hash_table.get(word);
					System.out.println("\"" + word + "\" appears " + count + " times.");
				}
			}

        } 
        catch (IOException x){
        	System.err.println("couldn't open file...\n");
        	x.printStackTrace();
        }
	}
}