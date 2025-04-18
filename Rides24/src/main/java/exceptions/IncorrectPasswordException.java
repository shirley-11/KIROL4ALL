package exceptions;

public class IncorrectPasswordException extends Exception {
	 private static final long serialVersionUID = 1L;
	 
	 public IncorrectPasswordException()
	  {
	    super();
	  }
	  /**Esta excepcion es para cuando los parámetros no están acorde con lo que hay en la base de datos 
	  *@param s String of the exception
	  */
	  public IncorrectPasswordException(String s)
	  {
	    super(s);
	  }

}
