package exceptions;

public class IdAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public IdAlreadyExistsException(){
		super();
	}
	
	  /**Esta excepcion es para cuando ya existe un id
	  *@param s String of the exception
	  */
	  public IdAlreadyExistsException(String s)
	  {
	    super(s);
	  }

}
