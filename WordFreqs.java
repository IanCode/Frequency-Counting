import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;

public class WordFreqs{
	public static void main(String[] args){
		private String[] words_kept;
		private StringTokenizer words_ind;
		private BufferedReader reader;
		private String token;
		private int num_tokens;
		private int num_tokens2;
		private String f_name = args[0];
		private ArrayList text_array = new ArrayList();
		private Path path = Paths.get(f_name);
		
        try{
        	reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        	System.out.println("read the file, I think\n");
			String line;
			num_tokens = 0;
			num_tokens2 = 0;
			while((line = reader.readLine()) != null){
				line = line.toLowerCase();
				line = line.replaceAll("[^abcdefghijklmnopqrstuvwxyz1234567890_ ]", "");
				System.out.println(line);
				words_kept = line.split("[ \n]");
				System.out.printf("words in token: %d\n", words_kept.length);
				int i = 0;
				for(i = 0; i < words_kept.length; i++){
					token = words_kept[i];
					if(token.equals(" ")){

					}
					else{
						token = token.replaceAll("$\'", "");
		        		token = token.replaceAll("\'^", "");
						text_array.add(token);
						//System.out.println("token:" + token);
						num_tokens++;
					}
					System.out.printf("num of tokens: %d\n", num_tokens);
				}
				num_tokens2++;
				System.out.printf("num_tokens2: %d\n", num_tokens2);
			}
			int size = text_array.size();
			//String size_s = (String)size;
			int i = 0;
			System.out.println(size);
			/*System.out.printf("%d\n", text_array.size());
			for(i = 0; i < text_array.size(); i++){
				System.out.print(text_array.get(i) + " ");
			}*/

        } 
        catch (IOException x){
        	System.err.println("couldn't open file...\n");
        	x.printStackTrace();
        }
        
		//BufferedReader file = new BufferedReader(f_name);

	}
}