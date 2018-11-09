package pobj.multiset;

public class MultiSetParserTest {

	public static void main(String[] args) throws InvalidMultiSetFormat {
		final String FILENAME = "data/monTxt.txt";
		MultiSet<String> multi = MultiSetParser.parse(FILENAME);
		System.out.println(multi);
	}

}