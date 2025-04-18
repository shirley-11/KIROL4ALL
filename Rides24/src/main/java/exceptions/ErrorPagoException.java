package exceptions;

public class ErrorPagoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ErrorPagoException(){
		super();
	}
	
	  /**Esta excepcion es para cuando los parámteros no está acorde con lo que hay en la base de datos 
	  *@param s String of the exception
	  */
	  public ErrorPagoException(String s)
	  {
	    super(s);
	  }

}
