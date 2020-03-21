import java.util.ArrayList;
public class lzw {
	
	public static ArrayList<Integer> compress(String to_be_compressed) {
		
		//putting all ASCII values into the dictionary
		ArrayList<String> dictionary = new ArrayList<>();
		for(int i = 0; i < 256; i++) {
			dictionary.add(""+(char)i);
		}
		
		String seq = "";
		ArrayList<Integer> result_list = new ArrayList<Integer>();
		for(char next_character : to_be_compressed.toCharArray() ) {
			//looping thru the whole text we want to compress
			
			String seq_withChar = seq + next_character;
			if(dictionary.contains(seq_withChar)) {
				seq = seq_withChar;
			} else {
				//adding previous to the result
				int index = dictionary.indexOf(seq);
				result_list.add(index);
				
				//putting new sequence to the dictionary
				dictionary.add(seq_withChar);
				
				//clearing our sequence
				seq = "" + next_character;
				
			}	
		}
		
		//we have to add the last sequence to the result
		if(seq != "") {
		int index = dictionary.indexOf(seq);
		result_list.add(index);
		}
		
		
		return result_list;
		
	}
	
	public static String decompress(ArrayList<Integer> compressed_list) {
		
		ArrayList<String> dictionary = new ArrayList<>();
		for(int i = 0; i < 256; i++) {
			dictionary.add(""+(char)i);
		}
		
		String seq = "" + (char)(int)compressed_list.get(0);
		compressed_list.remove(0);
		
		String decompressed = seq;
		for(int dictionary_index : compressed_list) {
			String temp = null;
			//if we already have this entry
			if(dictionary.contains(dictionary.get(dictionary_index))) {
				temp = dictionary.get(dictionary_index);
			} 
			//if we encounter the end of ASCII
			else if(dictionary_index == 256) {
				temp = seq + seq.charAt(0);
			}
			
			//we add our piece of text to the decompressed text
			decompressed += temp;
			//we create another element in our dictionary
			dictionary.add(seq + temp.charAt(0));
			//we update our global sequence variable
			seq = temp;
			
			
		}
		
		return decompressed;
		
	}
	
	
	
	
	
	
}
