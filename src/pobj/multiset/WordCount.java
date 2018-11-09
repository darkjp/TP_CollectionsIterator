package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordCount {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file = "data/WarAndPeace.txt";
		HashMultiSet<String> hm = new HashMultiSet<>();
		List<String> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line= br.readLine())!=null) {
				for (String word : line.split("\\P{L}+")) {
					if(word.equals("")) continue;
					hm.add(word);
				}
			}
			list = hm.elements();
			System.out.println(list);
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
