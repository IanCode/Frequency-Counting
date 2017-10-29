import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;

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
        	System.out.println("read file successfully\n");
			String line;
			num_tokens = 0;
			num_tokens2 = 0;
			while((line = reader.readLine()) != null){
				line = line.toLowerCase();
				words_kept = line.split("[ \n]");
				System.out.println(line);

				int i = 0;
				for(i = 0; i < words_kept.length; i++){
					token = words_kept[i];
					if(token.length() == 0){
						//System.out.println("LENGTH OF STRING ZERO BROTENDO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					if(token.equals(" ") || token.equals("\n") || token.equals(null) || token.equals("")){
						//System.out.println("NULL TOKEN BROTENTO WHAT'S GOOD!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					else{
						token = token.replaceAll("[^abcdefghijklmnopqrstuvwxyz1234567890_]", "");
						token = token.replaceAll("$\'", "");
		        		token = token.replaceAll("\'^", "");
						text_array.add(token);
						//System.out.println("token:" + token);

						num_tokens++;
					}
					//System.out.printf("num of tokens: %d\n", num_tokens);
				}
				num_tokens2++;
				//System.out.printf("num_tokens2: %d\n", num_tokens2);
			}
			int size = text_array.size();
			//String size_s = (String)size;
			int i = 0;
			System.out.println(size);
			String key;
			//System.out.printf("%d\n", text_array.size());
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
			System.out.println();
			for(i = 0; i < text_array.size(); i++){
				String temp = (String)text_array.get(i);
				System.out.println("index: " + i);
				System.out.println("key: " + temp);
				if(hash_table.get(temp) != null){
					System.out.printf("value: %d\n", hash_table.get(temp));
				}
			}
			//Integer test = hash_table.get("was");
			System.out.println("Please enter a word you wish to find.");
			System.out.println("To exit press enter");
			sc = new Scanner(System.in);
			while(true){
				System.out.print(">");
				String next = sc.next();
				if(next.equals("\n")){
					System.exit(0);
				}

			}

        } 
        catch (IOException x){
        	System.err.println("couldn't open file...\n");
        	x.printStackTrace();
        }
        
		//BufferedReader file = new BufferedReader(f_name);

	}
}