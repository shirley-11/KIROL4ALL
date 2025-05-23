package businessLogic;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.Sala;
import domain.Sesion;
import domain.Socio;
import domain.Actividad;
import domain.Driver;
import domain.Encargado;
import domain.Factura;
import domain.Reserva;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;
import exceptions.SocioNoRegistradoException;
import exceptions.SocioRegistradoException;
import exceptions.ActAlreadyExistsException;
import exceptions.ErrorPagoException;
import exceptions.IdAlreadyExistsException;
import exceptions.IncorrectPasswordException;
import exceptions.NoMasReservasException;
/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess();
		    
		//dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    /////////////////////////////////////////////USUARIO////////
    @WebMethod
    public Socio hacerLogin(String correo, String contrasena) throws SocioNoRegistradoException, IncorrectPasswordException {
    	dbManager.open();
    	Socio login = dbManager.hacerLogin(correo, contrasena);
    	dbManager.close();
    	return login;
    }
    
    @WebMethod
    public Socio registrarse(String nombre, String correo, String contrasena) throws SocioRegistradoException {
    	dbManager.open();
    	Socio s = dbManager.registrarse(nombre, correo, contrasena);
    	dbManager.close();
    	return s;
    }
    
    @WebMethod
    public List<Sesion> getSesiones(){
    	dbManager.open();
		List<Sesion>  sesiones=dbManager.getSesiones();
		dbManager.close();
		return sesiones;
    }
    
    @WebMethod
    public List<Sesion> getSesionesG(int gradoExig){
    	dbManager.open();
		List<Sesion>  sesionesG=dbManager.getSesionesG(gradoExig);
		dbManager.close();
		return sesionesG;
    }
    
    /////////////SOCIO///////////////////////////////
    @WebMethod 
    public String reservarSesion(Socio socioR, Sesion si) throws NoMasReservasException {   	
    	dbManager.open();
    	String resultado = dbManager.reservarSesion(socioR, si);
    	dbManager.close();
    	return resultado;
    }
    
    @WebMethod 
    public List<Reserva> getReservas(Socio socioR){
    	dbManager.open();
    	List<Reserva> reservas = dbManager.getReservas(socioR);
    	dbManager.close();
    	return reservas;
    }
    
	@WebMethod 
	public boolean cancelarReserva(Reserva ri) {
		dbManager.open();
		boolean res = dbManager.cancelarReserva( ri);
		dbManager.close();
		return res;
	}
	
	@WebMethod 
	public List<Factura> getFacturas(Socio socio){
		dbManager.open();
		List<Factura> facturas = dbManager.getFacturas(socio);
		dbManager.close();
		return facturas;
	}
	
	@WebMethod 
	public String pagarFactura(int nfact) throws ErrorPagoException{
		dbManager.open();
		String pago = dbManager.pagarFactura(nfact);
		dbManager.close();
		return pago;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////ENCARGADO
	@WebMethod 
	public Encargado hacerLoginEncargado(String correo, String contrasena) throws SocioNoRegistradoException, IncorrectPasswordException{
		dbManager.open();
    	Encargado login = dbManager.hacerLoginEncargado(correo, contrasena);
    	dbManager.close();
    	return login;
	}
	
	@WebMethod 
	public Actividad añadirActividad(String nombre, int gExig, int precio) throws ActAlreadyExistsException {
		dbManager.open();
		Actividad r = dbManager.añadirActividad(nombre, gExig, precio);
    	dbManager.close();
    	return r;
	}
	
	@WebMethod 
	public Sesion añadirSesion(Actividad actividad, Sala sala, String date, String horaImparticion) throws ActAlreadyExistsException{
		dbManager.open();
		Sesion r = dbManager.añadirSesion(actividad, sala, date, horaImparticion);
    	dbManager.close();
    	return r;
	}
	
	@WebMethod 
	public List<Sala> getSalas(){
		dbManager.open();
		List<Sala> r = dbManager.getSalas();
    	dbManager.close();
    	return r;
	}
	
	@WebMethod 
	public List<Actividad> getActividades(){
		dbManager.open();
		List<Actividad> r = dbManager.getActividades();
    	dbManager.close();
    	return r;
	}
	
	@WebMethod 
	public List<Reserva> getReservasNoFacturadas (Socio s) {
		dbManager.open();
		List<Reserva> r = dbManager.getReservasNoFacturadas(s);
    	dbManager.close();
    	return r;
	}
	
	@WebMethod 
	public Factura crearFactura(int id, Socio sociof, String date, List<Reserva> reservas) throws IdAlreadyExistsException {
		dbManager.open();
		Factura r = dbManager.crearFactura(id, sociof, date, reservas);
    	dbManager.close();
    	return r;
	}
    
	@WebMethod 
	public List<Socio> getSocios() {
		dbManager.open();
		List<Socio> r = dbManager.getSocios();
    	dbManager.close();
    	return r;
	}

}

