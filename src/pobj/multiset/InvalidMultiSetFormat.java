package pobj.multiset;

public class InvalidMultiSetFormat extends Exception {

	private static final long serialVersionUID = -2038933171523275896L;

	public InvalidMultiSetFormat(String msg) {
		super(msg);
	}

	public InvalidMultiSetFormat(String msg, Throwable cause) {
		super(msg, cause);
	}

}