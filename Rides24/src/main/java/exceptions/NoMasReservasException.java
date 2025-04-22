package exceptions;

public class NoMasReservasException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NoMasReservasException(){
		super();
	}
	
	  /**Esta excepcion es para cuando un socio intenta reservar y ha alcanzado su límite
	  *@param s String of the exception
	  */
	  public NoMasReservasException(String s)
	  {
	    super(s);
	  }

}
