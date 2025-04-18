package exceptions;

public class SocioNoRegistradoException extends Exception{

	 private static final long serialVersionUID = 1L;
	 
	 public SocioNoRegistradoException()
	  {
	    super();
	  }
	  /**This exception is triggered if the question does not exists 
	  *@param s String of the exception
	  */
	  public SocioNoRegistradoException(String s)
	  {
	    super(s);
	  }

}
