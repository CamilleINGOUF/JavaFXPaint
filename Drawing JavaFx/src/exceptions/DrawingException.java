package exceptions;

public class DrawingException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public DrawingException(String text) {
		super(text);
	}
}
