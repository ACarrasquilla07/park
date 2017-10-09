package dominio.reglas.salidareglas;

import dominio.Moto;
import dominio.Recibo;
import dominio.reglas.ReglaSalida;

public class ReglaMotoAltoCilindraje implements ReglaSalida{
	public static final int CILINDRAJE_RESTRICCION_MOTOS = 500;
	public static final double MONTO_A_ALTO_CILINDRAJE = 2000d;

	@Override
	public double calcularValorAPagarServicioParqueadero(Recibo reciboIngreso) {
		double precio = reciboIngreso.getPrecio();
		if (!(reciboIngreso.getVehiculo() instanceof Moto)) {
			return precio;
		}
		Moto moto = (Moto) reciboIngreso.getVehiculo();
		if (moto.getCilindraje() > CILINDRAJE_RESTRICCION_MOTOS) {
			precio += MONTO_A_ALTO_CILINDRAJE;
		}		
		return precio;
	}
}
