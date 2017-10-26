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
		String f_name = args[0];
		ArrayList text_array = new ArrayList();
		Path path = Paths.get(f_name);
		
        try{
        	reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        	System.out.println("read the file, I think\n");
			String line;
			while((line = reader.readLine()) != null){
				line = line.toLowerCase();
				line = line.replaceAll("[^abcdefghijklmnopqrstuvwxyz1234567890_ ]", "");
				words_kept = line.split(" ");
				int i = 0;
				for(i = 0; i < words_kept.length; i++){
					token = words_kept[i];
					token = token.replaceAll("$\'", "");
		        	token = token.replaceAll("\'^", "");
					text_array.add(token);
					System.out.print(token);
				}
				//System.out.println();
			}
			System.out.printf("%d", text_array.size());

        } 
        catch (IOException x){
        	System.err.println("couldn't open file...\n");
        	x.printStackTrace();
        }
        
		//BufferedReader file = new BufferedReader(f_name);

	}
}