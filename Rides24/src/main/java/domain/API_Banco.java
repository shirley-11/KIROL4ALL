package domain;

import java.util.Random;

public class API_Banco {
	 private static Random random = new Random();

	 public static boolean pagar(int ntarjeta, int precio) {
		 boolean exito = random.nextDouble() < 0.7;
		 System.out.println("Intentando pagar con tarjeta: " + ntarjeta + " | Precio: " + precio + " | Resultado: " + exito);
	     return exito;
	 }

}
