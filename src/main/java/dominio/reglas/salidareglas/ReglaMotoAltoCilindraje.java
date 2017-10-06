package dominio.reglas.salidareglas;

import dominio.Moto;
import dominio.Recibo;

public class ReglaMotoAltoCilindraje {
	public static final int CILINDRAJE_RESTRICCION_MOTOS = 500;
	public static final double MONTO_A_ALTO_CILINDRAJE = 2000d;

	public Recibo verificarPosibilidadIngreso(Recibo reciboIngreso) { //Esta es la regla para cobrar
		if (!(reciboIngreso.getVehiculo() instanceof Moto)) {
			return reciboIngreso;
		}
		Moto moto = (Moto) reciboIngreso.getVehiculo();
		if (moto.getCilindraje() > CILINDRAJE_RESTRICCION_MOTOS) {
			reciboIngreso.setPrecio(reciboIngreso.getPrecio() + MONTO_A_ALTO_CILINDRAJE);
		}		
		return reciboIngreso;
	}
}
