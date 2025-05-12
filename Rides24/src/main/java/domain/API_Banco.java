package domain;

import java.util.Random;

public class API_Banco {
	 private static Random random = new Random();

	 public static boolean pagar(int ntarjeta, int precio) {
		 boolean exito = random.nextDouble() < 0.5;  ///50% POSIBILIDADES DE QUE FUNCIONE
		 ///boolean exito = random.nextDouble() < 1.0; ///SIEMPRE VA A FUNCIONAR
		//boolean exito = random.nextDouble() < 0.0; ///NUNCA VA A FUNCIONAR
		 System.out.println("Intentando pagar con tarjeta: " + ntarjeta + " | Precio: " + precio + " | Resultado: " + exito);
	     return exito;
	 }

}
