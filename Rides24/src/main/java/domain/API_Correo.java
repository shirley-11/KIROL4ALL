package domain;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class API_Correo {
	static String receptor = "sosorio002@ikasle.ehu.eus";
	
	public static void enviarCorreo (Factura fact) {
		try{
        
		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");  // Si activamos, entonces hay que autenticarse
//		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.ehu.es");
//		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props);
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("cesar@cesar.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receptor));
			message.setSubject("FACTURA (PROYECTO IS)");
			
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		    String fechaFact = formatoFecha.format(fact.getFechaFac());
			message.setText("Reservas realizadas: "
					+ "\n\n" + fact.getStringReservas() +
					"\n\n" + "Precio total: " + fact.getPrecioTotal() + "€" +
					"\n\n" + "Codigo factura: " + fact.getIdFactura() +
					"\n\n" + "Fecha factura: " + fechaFact);
 
			Transport.send(message);
 
			System.out.println("Hecho");
			
			
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}catch (Exception e) {System.out.println("Error: "+e.getMessage());}
	}
	
	
	

}
