package exceptions;

public class SocioRegistradoException extends Exception {
	private static final long serialVersionUID = 1L;
	 
	 public SocioRegistradoException()
	  {
	    super();
	  }
	  /**This exception is triggered if the question exists 
	  *@param s String of the exception
	  */
	  public SocioRegistradoException(String s)
	  {
	    super(s);
	  }

}
