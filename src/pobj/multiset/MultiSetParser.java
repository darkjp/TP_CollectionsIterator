package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {
	public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat {
		MultiSet<String> m = new HashMultiSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				String[] paire = line.split(":");
				m.add(paire[0], Integer.parseInt(paire[1]));
			}
			br.close();
		} catch (IOException e) {
			throw new InvalidMultiSetFormat("Problème de lecture ou d'écriture du fichier.");
		} catch (IllegalArgumentException e) {
			throw new InvalidMultiSetFormat(
					"Le format d'une ou plusieurs valeurs présentes dans le fichier n'est pas celui voulu.");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidMultiSetFormat("Le fichier présente un ou plusieurs erreurs par rapport au format voulu.");
		}
		return m;
	}
}