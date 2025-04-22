package businessLogic;

import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Ride;
import domain.Sesion;
import domain.Socio;
import domain.Actividad;
import domain.Driver;
import domain.Encargado;
import domain.Factura;
import domain.Reserva;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.SocioNoRegistradoException;
import exceptions.SocioRegistradoException;
import exceptions.ActAlreadyExistsException;
import exceptions.ErrorPagoException;
import exceptions.IncorrectPasswordException;
import exceptions.RideAlreadyExistException;

import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	@WebMethod public List<String> getDepartCities();
	
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	@WebMethod public List<String> getDestinationCities(String from);


	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driver to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideMustBeLaterThanTodayException, RideAlreadyExistException;
	
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	@WebMethod public List<Ride> getRides(String from, String to, Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	@WebMethod public List<Date> getThisMonthDatesWithRides(String from, String to, Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	/**
	 * Metodo que verifica que la cuenta existe y las credenciales son correctas
	 * @param correo del socio
	 * @param contrasena
	 */
	@WebMethod public Socio hacerLogin(String correo, String contrasena) throws SocioNoRegistradoException, IncorrectPasswordException;
	
	/**
	 * Metodo que añade un nuevo socio a la base de datos si el socio no existe
	 * @param correo del socio
	 * @param contrasena
	 */
	@WebMethod public Socio registrarse(String nombre, String correo, String contrasena) throws SocioRegistradoException;
	
	/**
	 * Metodo que devuelve las sesiones de la semana
	 * 
	 */
	@WebMethod public List<Sesion> getSesiones();
	
	/**
	 * Metodo que devuelve las sesiones de la semana por grado de exigencia
	 * 
	 */
	@WebMethod public List<Sesion> getSesionesG(int gradoExig);
	
	/**
	 * Metodo que devuelve si la reserva que ha realizado el socio está en OK o ESPERA
	 * 
	 */
	@WebMethod public String reservarSesion(Socio socioR, Sesion si);
	
	/**
	 * Metodo que devuelve las reservas de un socio
	 * 
	 */
	@WebMethod public List<Reserva> getReservas(Socio socioR);
	
	/**
	 * Metodo que devuelve si ha podido cancelar la reserva
	 * 
	 */
	@WebMethod public boolean cancelarReserva(Reserva ri);
	
	/**
	 * Metodo que devuelve las facturas no pagadas del socio
	 */
	@WebMethod public List<Factura> getFacturas(Socio socio);
	
	/**
	 * Metodo que paga la factura no pagada del socio
	 */
	@WebMethod public String pagarFactura(int nfact) throws ErrorPagoException;
	
	/**
	 * Metodo que verifica que la cuenta existe y las credenciales son correctas
	 * @param correo del Encargado
	 * @param contrasena
	 */
	@WebMethod public Encargado hacerLoginEncargado(String correo, String contrasena) throws SocioNoRegistradoException, IncorrectPasswordException;
	
	/**
	 * Metodo que añade una actividad
	 */
	@WebMethod public Actividad añadirActividad(String nombre, int gExig, int precio) throws ActAlreadyExistsException;
	
	
	
}
