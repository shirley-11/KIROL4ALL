package exceptions;

public class ActAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ActAlreadyExistsException(){
		super();
	}
	
	  /**Esta excepcion es para cuando un encargado intenta crear una actividad que ya existe
	  *@param s String of the exception
	  */
	  public ActAlreadyExistsException(String s)
	  {
	    super(s);
	  }

}
