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
import domain.Actividad;
import domain.Driver;
import domain.Encargado;
import domain.Factura;
import domain.Reserva;
import domain.Ride;
import domain.Sala;
import domain.Sesion;
import domain.Socio;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.ActAlreadyExistsException;
import exceptions.ErrorPagoException;
import exceptions.IdAlreadyExistsException;
import exceptions.IncorrectPasswordException;
import exceptions.NoMasReservasException;
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

    public java.sql.Date convertirStringADate(String fechaStr) { ////PARA PONER FECHAS EN FORMATO SQL
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fechaUtil = formato.parse(fechaStr);
            return new java.sql.Date(fechaUtil.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null; //
        }
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
			
			///////////////////////////////////////////////////////////////////////////////PROYCTO GLOBAL////////////
			//Crear socios			
			Socio socio1 = new Socio ("Aitor Fernández", "socio1@gmail.com", "socio1");
			
			//Crear Salas
			Sala sala1Max = new Sala("Sala Máxima", 12);
			Sala sala2Grande = new Sala("Sala Grande", 11);
			Sala sala3Intermedia = new Sala("Sala Intermedia", 10);
			Sala sala4Media = new Sala("Sala Media", 7);
			Sala sala5Compacta = new Sala("Sala Compacta", 5);
			
			//Crear Actividades
			Actividad act1Pilates = new Actividad("Pilates", 3, 9);
			Actividad act2Yoga = new Actividad("Yoga", 2, 7);
			Actividad act3Zumba = new Actividad("Zumba", 2, 7);
			Actividad act4Spinning = new Actividad("Spinning", 4, 11);
			Actividad act5Boxeo = new Actividad("Boxeo", 5, 13);
			Actividad act6CrossFit= new Actividad("CrossFit", 5, 13);
			Actividad act7GimnasiaMayores= new Actividad("Gimnasia para Mayores", 1, 5);
			Actividad act8CiclismoIndoor= new Actividad("Ciclismo Indoor", 3, 9);
			
			//Crear Sesiones
	        java.sql.Date fechaLUNES = this.convertirStringADate("07/04/2025");//LUNES	        
			//Sesion s1LUNES = new Sesion (act2Yoga, sala1Max, fechaLUNES, "09:00");
			Sesion s2LUNES = new Sesion (act1Pilates, sala2Grande, fechaLUNES, "10:30");
			Sesion s3LUNES = new Sesion (act3Zumba, sala3Intermedia, fechaLUNES, "11:30");
			Sesion s4LUNES = new Sesion (act4Spinning, sala4Media, fechaLUNES, "15:00");
			//Sesion s5LUNES = new Sesion (act6CrossFit, sala5Compacta, fechaLUNES, "16:30");						
			java.sql.Date fechaMARTES = this.convertirStringADate("08/04/2025");//MARTES	        
			//Sesion s1MARTES = new Sesion (act1Pilates, sala3Intermedia, fechaMARTES, "09:00");
			Sesion s2MARTES = new Sesion (act2Yoga, sala2Grande, fechaMARTES, "10:00");
			Sesion s3MARTES = new Sesion (act6CrossFit, sala4Media, fechaMARTES, "11:30");
			Sesion s4MARTES = new Sesion (act4Spinning, sala5Compacta, fechaMARTES, "15:00");
			//Sesion s5MARTES = new Sesion (act5Boxeo, sala1Max, fechaMARTES, "16:00");
			java.sql.Date fechaMIERCOLES = this.convertirStringADate("09/04/2025");//MIERCOLES
			Sesion s1MIERCOLES = new Sesion (act5Boxeo, sala1Max, fechaMIERCOLES, "09:00");
			Sesion s2MIERCOLES = new Sesion (act7GimnasiaMayores, sala4Media, fechaMIERCOLES, "18:00");
			java.sql.Date fechaJUEVES = this.convertirStringADate("10/04/2025");//JUEVES
			Sesion s1JUEVES = new Sesion (act3Zumba, sala3Intermedia, fechaJUEVES, "11:00");
			Sesion s2JUEVES = new Sesion (act8CiclismoIndoor, sala2Grande, fechaJUEVES, "17:30");
			java.sql.Date fechaVIERNES = this.convertirStringADate("11/04/2025");//VIERNES
			Sesion s1VIERNES = new Sesion (act8CiclismoIndoor, sala1Max, fechaVIERNES, "18:30");
			Sesion s2VIERNES = new Sesion (act5Boxeo, sala5Compacta, fechaVIERNES, "17:30");
			java.sql.Date fechaSABADO = this.convertirStringADate("12/04/2025");//SABADO
			Sesion s1SABADO = new Sesion (act7GimnasiaMayores, sala4Media, fechaSABADO, "09:00");
			Sesion s2SABADO = new Sesion (act6CrossFit, sala3Intermedia, fechaSABADO, "11:00");
			Sesion s3SABADO = new Sesion (act3Zumba, sala2Grande, fechaSABADO, "12:00");
			java.sql.Date fechaDOMINGO = this.convertirStringADate("13/04/2025");//DOMINGO
			Sesion s1DOMINGO = new Sesion (act1Pilates, sala5Compacta, fechaDOMINGO, "09:00");
			Sesion s2DOMINGO = new Sesion (act2Yoga, sala5Compacta, fechaDOMINGO, "10:00");
			Sesion s3DOMINGO = new Sesion (act8CiclismoIndoor, sala1Max, fechaDOMINGO, "10:00");
			////////////////////////////////////////////ENCARGADOS//////////////
			Encargado e= new Encargado("Ane Fernández", "encargado@gmail.com", "encargado");
			
			
			
			
			
			db.persist(socio1);/////SOCIOS
			db.persist(sala1Max);////SALAS
			db.persist(sala2Grande);
			db.persist(sala3Intermedia);
			db.persist(sala4Media);
			db.persist(sala5Compacta);
			db.persist(act1Pilates);///ACTIVIDADES
			db.persist(act2Yoga);
			db.persist(act3Zumba);			
			db.persist(act4Spinning);
			db.persist(act5Boxeo);
			db.persist(act6CrossFit);
			db.persist(act7GimnasiaMayores);
			db.persist(act8CiclismoIndoor);	
			//db.persist(s1LUNES);////SESIONES 18 en total
			db.persist(s2LUNES);
			db.persist(s3LUNES);
			db.persist(s4LUNES);
			//db.persist(s5LUNES);
			//db.persist(s1MARTES);
			db.persist(s2MARTES);
			db.persist(s3MARTES);
			db.persist(s4MARTES);
			//db.persist(s5MARTES);
			db.persist(s1MIERCOLES);
			db.persist(s2MIERCOLES);
			db.persist(s1JUEVES);
			db.persist(s2JUEVES);
			db.persist(s1VIERNES);
			db.persist(s2VIERNES);
			db.persist(s1SABADO);
			db.persist(s2SABADO);
			db.persist(s3SABADO);
			db.persist(s1DOMINGO);
			db.persist(s2DOMINGO);
			db.persist(s3DOMINGO);
			////////////////////ENCARGADO
			db.persist(e);
			/////////////////////RESERVAS PARA EL SOCIO
			

	
			db.getTransaction().commit();
			
			this.reservarSesion(socio1, s3DOMINGO);
			
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
	
	//////////////////////////////////7USUARIO7////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Socio hacerLogin(String correo, String contrasena) throws SocioNoRegistradoException, IncorrectPasswordException{
		Socio s = db.find(Socio.class, correo);
		if(s==null) {
			throw new SocioNoRegistradoException("Este correo no es válido, por favor revisa que lo has introducido correctamente o regístrate.");
		} 
		else if(!s.getContrasena().equals(contrasena)){
			throw new IncorrectPasswordException("Contraseña incorrecta, inténtalo de nuevo.");
		}
		else return s;
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
	
	public List<Sesion> getSesiones(){ //////////SESIONES DEL 7 DE ABRIL AL 13, LUNES A DOMINGO. Tengo que pnerlo el 14 porque sólo es hasta 00:00
		System.out.println(">> DataAccess: getSesiones");
		List<Sesion> res = new ArrayList<>();
		
		try {
			
			//////inicioSemana y finSemana//////////////
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        Date inicioSemana = formato.parse("07/04/2025");
	        Date finSemana = formato.parse("14/04/2025");

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
	        Date finSemana = formato.parse("14/04/2025");

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
	public String reservarSesion(Socio socioR, Sesion si) throws NoMasReservasException {
		Sesion sesionbd = db.find(Sesion.class, si.getIdSesion());
		Socio sociobd = db.find(Socio.class, socioR.getCorreo());
		int reservasDisponibles = sociobd.getNumMaxReservas();
		if (reservasDisponibles == 0) {
			throw new NoMasReservasException ( "El socio: " + sociobd.getNombre() + " no puede hacer más reservas");
		}
		else {
			String resultado = "Error en dataAcces reservarSesion";
			try {
				db.getTransaction().begin();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		        Date fechaReserva = formato.parse("07/04/2025");
				Reserva reserva = new Reserva(sociobd, sesionbd, fechaReserva);				
				sociobd.setNumMaxReservas(reservasDisponibles - 1);
				resultado = sesionbd.addReserva(reserva);
				
				db.persist(reserva);
				db.getTransaction().commit();
				
				resultado = resultado + " " + reserva.getIdReserva();
				
				
			}catch(ParseException e) {
	            e.printStackTrace();
	        }
			return resultado;
			
		}
	}
	
	public List<Reserva> getReservas(Socio socioR){
		System.out.println(">> DataAccess: getReservas");
		Socio sociodb = db.find(Socio.class, socioR.getCorreo());
		List<Reserva> resultado = new ArrayList<Reserva>();
		
		TypedQuery<Reserva> query = db.createQuery("SELECT r FROM Reserva r WHERE r.socioReserva=?1 ORDER BY r.fechaReserva, r.idReserva",Reserva.class);   
		query.setParameter(1, sociodb);

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
		
		/////SI EL SOCIO HA CANCELADO PUEDE VOLVER A RESERVAR
		Socio socioReserva = r.getSocioReserva();
		socioReserva.setNumMaxReservas(socioReserva.getNumMaxReservas() + 1);
		
		db.getTransaction().commit();
		return true;
	}
	
	
	public List<Factura> getFacturas (Socio socio){
		System.out.println(">> DataAccess: getFacturas");
		Socio sociodb = db.find(Socio.class, socio.getCorreo());
		List<Factura> res = new ArrayList<>();
		TypedQuery<Factura> queryFactura = db.createQuery("SELECT f FROM Factura f WHERE f.socioFac=?1 AND f.estado=?2 ORDER BY f.fechaFac, f.idFactura",Factura.class);   
		queryFactura.setParameter(1, sociodb);
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
	
	//////////////////////////////////////////////ENCARGADO////////////////////////////////////////
	public Encargado hacerLoginEncargado(String correo, String contrasena) throws SocioNoRegistradoException, IncorrectPasswordException{
		Encargado e = db.find(Encargado.class, correo);
		if(e==null) {
			throw new SocioNoRegistradoException("Este correo no es válido, introdúcelo correctamente.");
		} 
		else if(!e.getContrasena().equals(contrasena)){
			throw new IncorrectPasswordException("Contraseña incorrecta, inténtalo de nuevo.");
		}
		else return e;
	}
	
	public Actividad añadirActividad(String nombre, int gExig, int precio) throws ActAlreadyExistsException {
		Actividad existente = db.find(Actividad.class, nombre);
		if(existente!=null) {
			throw new ActAlreadyExistsException("Esta actividad ya existe.");
		}
		else {
			existente = new Actividad(nombre, gExig, precio);
			db.getTransaction().begin();
			db.persist(existente);
			db.getTransaction().commit();
			return db.find(Actividad.class, existente.getNombre());
		}
	}
	
	public Sesion añadirSesion(Actividad actividad, Sala sala, String date, String horaImparticion) throws ActAlreadyExistsException{
		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.actividad=?1 AND s.sala=?2 AND s.date=?3 AND s.horaImpartición=?4",Sesion.class);   
		query.setParameter(1, actividad);
		query.setParameter(2, sala);
		java.sql.Date dateQ = this.convertirStringADate(date);
		query.setParameter(3, dateQ);
		query.setParameter(4, horaImparticion);
		
		List<Sesion> sesiones = query.getResultList();
		if(!sesiones.isEmpty()) {
			throw new ActAlreadyExistsException("Esta sesión ya existe");
		}
		else {
			TypedQuery<Sesion> query2 = db.createQuery("SELECT s FROM Sesion s WHERE s.sala=?1 AND s.date=?2 AND s.horaImpartición=?3",Sesion.class);   
			query2.setParameter(1, sala);
			query2.setParameter(2, dateQ);
			query2.setParameter(3, horaImparticion);
			List<Sesion> sesiones2 = query2.getResultList();
			if(!sesiones2.isEmpty()){
				throw new ActAlreadyExistsException("Ya hay una sesión en este mismo lugar y hora");
			}
			else {
				java.sql.Date fechaSesion = this.convertirStringADate(date);
				Sesion s = new Sesion(actividad, sala, fechaSesion, horaImparticion);
				db.getTransaction().begin();
				db.persist(s);
				db.getTransaction().commit();
				return db.find(Sesion.class, s.getIdSesion());
			}
			
		}
	}
	
	/////// 
	public List<Sala> getSalas(){
		System.out.println(">> DataAccess: getSalas");
		List<Sala> res = new ArrayList<>();
		
		TypedQuery<Sala> query = db.createQuery("SELECT s FROM Sala s",Sala.class);   
		List<Sala> salas = query.getResultList();
		for (Sala s:salas){
				res.add(s);
		}		
		return res;
	}
	
	public List<Actividad> getActividades(){
		System.out.println(">> DataAccess: getActividades");
		List<Actividad> res = new ArrayList<>();
		
		TypedQuery<Actividad> query = db.createQuery("SELECT a FROM Actividad a",Actividad.class);   
		List<Actividad> actividades = query.getResultList();
		for (Actividad a:actividades){
				res.add(a);
		}		
		return res;
	}
	
	public Factura crearFactura(int id, Socio sociof, String date, List<Reserva> reservas) throws IdAlreadyExistsException {
		Factura facturaExiste = db.find(Factura.class, id);
		
		if (facturaExiste != null) {
			throw new IdAlreadyExistsException ("Este id lo tiene una factura existente");		
		}
		else {
			TypedQuery<Factura> query2 = db.createQuery("SELECT f FROM Factura f WHERE f.socioFac=?1 AND f.reservasPagar=?2", Factura.class);   
			Socio s = db.find(Socio.class, sociof.getCorreo());			
			query2.setParameter(1, s);
			query2.setParameter(2, reservas);
			List<Factura> facturas = query2.getResultList();
			if(!facturas.isEmpty()){
				throw new IdAlreadyExistsException("Estas reservas ya han sido facturadas");
			}
			else {
				db.getTransaction().begin();
				java.sql.Date fechaFactura = this.convertirStringADate(date);
				facturaExiste = new Factura (id, s, fechaFactura, reservas);
				db.persist(facturaExiste);
				db.getTransaction().commit();
				return db.find(Factura.class, facturaExiste.getIdFactura());
				
			}
			
		}
		
	}
	
	
	
	
}
