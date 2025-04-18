package dataAccess;

import java.io.File;
import java.net.NoRouteToHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import configuration.ConfigXML;
import configuration.UtilDate;
import domain.API_Banco;
import domain.Driver;
import domain.Factura;
import domain.Reserva;
import domain.Ride;
import domain.Sesion;
import domain.Socio;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.ErrorPagoException;
import exceptions.IncorrectPasswordException;
import exceptions.SocioNoRegistradoException;
import exceptions.SocioRegistradoException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
				} else {
				  System.out.println("Operation failed");
				}
		}
		open();
		if  (c.isDatabaseInitialized())initializeDB();
		
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();

		try {

		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=1; year+=1;}  
	    
		   
		    //Create drivers 
			Driver driver1=new Driver("driver1@gmail.com","Aitor Fernandez");
			Driver driver2=new Driver("driver2@gmail.com","Ane GaztaÃ±aga");
			Driver driver3=new Driver("driver3@gmail.com","Test driver");

			
			//Create rides
			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 4, 7);
			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year,month,6), 4, 8);
			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 4, 4);

			driver1.addRide("Donostia", "IruÃ±a", UtilDate.newDate(year,month,7), 4, 8);
			
			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 3, 3);
			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 2, 5);
			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year,month,6), 2, 5);

			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,14), 1, 3);

			
						
			db.persist(driver1);
			db.persist(driver2);
			db.persist(driver3);

	
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	public List<String> getDepartCities(){
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
			List<String> cities = query.getResultList();
			return cities;
		
	}
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from){
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList(); 
		return arrivingCities;
		
	}
	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driverEmail to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= "+from+" to= "+to+" driver="+driverEmail+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();
			
			Driver driver = db.find(Driver.class, driverEmail);
			if (driver.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = driver.addRide(from, to, date, nPlaces, price);
			//next instruction can be obviated
			db.persist(driver); 
			db.getTransaction().commit();

			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
		
		
	}
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= "+from+" to= "+to+" date "+date);

		List<Ride> res = new ArrayList<>();	
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",Ride.class);   
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
	 	 for (Ride ride:rides){
		   res.add(ride);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",Date.class);   
		
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(){
		
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());

		
	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}
	
	//////////////////////////////////7USUARIO7//////////////////////////////////////////////
	
	public boolean hacerLogin(String correo, String contrasena) throws SocioNoRegistradoException, IncorrectPasswordException{
		Socio s = db.find(Socio.class, correo);
		if(s==null) {
			throw new SocioNoRegistradoException("Este correo no es válido, por favor revisa que lo has introducido correctamente o regístrate.");
		} 
		else if(!s.getContrasena().equals(contrasena)){
			throw new IncorrectPasswordException("Contraseña incorrecta, inténtalo de nuevo.");
		}
		else return (true);
	}
	
	public Socio registrarse(String nombre, String correo, String contrasena) throws SocioRegistradoException {
		Socio s = db.find(Socio.class, correo);
		if(s!=null) {
			throw new SocioRegistradoException("Este correo está en uso, no puedes registrarte de nuevo.");
		} 
		else {
			s = new Socio(nombre, correo, contrasena);
			db.getTransaction().begin();
			db.persist(s);
			db.getTransaction().commit();
			return db.find(Socio.class, correo);
		}
	}
	
	public List<Sesion> getSesiones(){ //////////SESIONES DEL 7 DE ABRIL AL 13, LUNES A DOMINGO
		System.out.println(">> DataAccess: getSesiones");
		List<Sesion> res = new ArrayList<>();
		
		try {
			
			//////inicioSemana y finSemana//////////////
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        Date inicioSemana = formato.parse("07/04/2025");
	        Date finSemana = formato.parse("13/04/2025");

			TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.date BETWEEN ?1 and ?2 ORDER BY s.date, s.horaImpartición",Sesion.class);   
			query.setParameter(1, inicioSemana);
			query.setParameter(2, finSemana);

			List<Sesion> sesiones = query.getResultList();
			for (Sesion s:sesiones){
				res.add(s);
			}
	 	
	 	
		}catch(ParseException e) {
            e.printStackTrace();
        }
		
		return res;
	}
	
	public List<Sesion> getSesionesG(int gradoExig){ //////////SESIONES DEL 7 DE ABRIL AL 13, LUNES A DOMINGO. GRADO EXIGENCIA
		System.out.println(">> DataAccess: getSesionesG");
		List<Sesion> res = new ArrayList<>();
		
		try {
			//////inicioSemana y finSemana//////////////
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        Date inicioSemana = formato.parse("07/04/2025");
	        Date finSemana = formato.parse("13/04/2025");

			TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.actividad.gradoExigencia=?1 AND s.date BETWEEN ?2 and ?3 ORDER BY s.date, s.horaImpartición",Sesion.class);   
			query.setParameter(1, gradoExig);
			query.setParameter(2, inicioSemana);
			query.setParameter(3, finSemana);

			List<Sesion> sesiones = query.getResultList();
			for (Sesion s:sesiones){
				res.add(s);
			}
	 	
		}catch(ParseException e) {
            e.printStackTrace();
        }
		
		return res;
	}
	
	//////////////////////////////////////SOCIO///////////////////////
	public String reservarSesion(Socio socioR, Sesion si) {
		int reservasDisponibles = socioR.getNumMaxReservas();
		if (reservasDisponibles == 0) {
			return "El socio: " + socioR.getNombre() + " no puede hacer más reservas";
		}
		else {
			String resultado = "";
			try {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		        Date fechaReserva = formato.parse("07/04/2025");
				Reserva reserva = new Reserva(socioR, si, fechaReserva);
				resultado = si.addReserva(reserva);
				socioR.setNumMaxReservas(reservasDisponibles - 1);
				db.getTransaction().begin();
				db.persist(reserva);
				db.getTransaction().commit();
				
			}catch(ParseException e) {
	            e.printStackTrace();
	        }
			return resultado;
			
		}
	}
	
	public List<Reserva> getReservas(Socio socioR){
		System.out.println(">> DataAccess: getReservas");
		List<Reserva> resultado = new ArrayList<Reserva>();
		
		TypedQuery<Reserva> query = db.createQuery("SELECT r FROM Reserva r WHERE r.socioReserva=?1 ORDER BY r.fechaReserva, r.idReserva",Reserva.class);   
		query.setParameter(1, socioR);

		List<Reserva> reservas = query.getResultList();
		for (Reserva r:reservas){
			resultado.add(r);
		}
		return resultado;
		
	}
	
	public boolean cancelarReserva(Reserva ri) {
		int idReserva = ri.getIdReserva();
		db.getTransaction().begin();
		
		Reserva r = db.find(Reserva.class, idReserva);
		String estadoDeReservaCancelada = r.getEstadoReserva();
		if (estadoDeReservaCancelada.equals("CANCELADO")) return false; 
		
		r.setEstadoReserva("CANCELADO");
		
		////ASEGURARME QUE EN TODOS LOS SITIOS ESTÁ CANCELADA//////
		List<Reserva> sesionParaEspera = r.getSesionReserva().getListaReservas();	
		for (Reserva reserva: sesionParaEspera) {
			if (reserva.getIdReserva() == idReserva) {
	            reserva.setEstadoReserva("CANCELADO");
	            break;
	        }
		}	
		
		////SI LA RESERVA ESTABA EN OK////
		if (estadoDeReservaCancelada.equals("OK")) {
			///SABER SI HAY ALGUNA RESERVA EN LISTA DE ESPERA, si es así coger la primera////////
			boolean habiaAlgunoEsperando = false;
			int i = 0;
			while (i < sesionParaEspera.size() && !habiaAlgunoEsperando) {
				Reserva reservaEnEspera = sesionParaEspera.get(i);		
				if (reservaEnEspera.getEstadoReserva().equals("ESPERA")) {
					reservaEnEspera.setEstadoReserva("OK");			
					habiaAlgunoEsperando = true;	
				}
				i++;
			}
		}/////SI LA RESERVA TAMBIÉN ESTABA EN ESPERA en consecuencia las reservas hechas 
		//// después de ella también lo estarán
		
		
		///////Hay que restar la reserva cancelada
		int nReservas = r.getSesionReserva().getNReservasHechas();
		r.getSesionReserva().setNReservasHechas(nReservas - 1);
		
		db.getTransaction().commit();
		return true;
	}
	
	
	public List<Factura> getFacturas (Socio socio){
		System.out.println(">> DataAccess: getFacturas");
		List<Factura> res = new ArrayList<>();
		TypedQuery<Factura> queryFactura = db.createQuery("SELECT f FROM Factura f WHERE f.socioFac=?1 AND f.estado=?2 ORDER BY f.fechaFac, f.idFactura",Factura.class);   
		queryFactura.setParameter(1, socio);
		queryFactura.setParameter(2, "NOPAGADO");
		
		List<Factura> facturas = queryFactura.getResultList();
		for (Factura f:facturas){
			res.add(f);
		}		
		return res;
	}
	
	public String pagarFactura(int nfact) throws ErrorPagoException{
		Factura f = db.find(Factura.class, nfact);
		if (f == null) {
			throw new ErrorPagoException("Factura no encontrada con número: " + nfact);
		}
		
		int precio = f.getPrecioTotal();
		if (API_Banco.pagar(1234, precio)) {
			db.getTransaction().begin();
			f.setEstado("PAGADO");
			db.getTransaction().commit();
			return f.getSocioFac().getNombre() + " has pagado la factura: " + nfact;
		}
		else {
			throw new ErrorPagoException("No se puedo realizar el pago. Inténtalo de nuevo");
		}
		
		
	}
	
	
	
}
