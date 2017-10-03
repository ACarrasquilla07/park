package dominio.reglas;

import dominio.Moto;
import dominio.Recibo;


public class ReglaMotoAltoCilindraje implements ReglaIngreso {
	public static final int CILINDRAJE_RESTRICCION_MOTOS = 500;
	public static final double MONTO_A_ALTO_CILINDRAJE = 2000d; 
	
	@Override
	public Recibo verificarPosibilidadIngreso(Recibo reciboIngreso) {
		if(reciboIngreso.getVehiculo() instanceof Moto) {
			Moto moto = (Moto)reciboIngreso.getVehiculo();
			if(moto.getCilindraje() > CILINDRAJE_RESTRICCION_MOTOS) {
				reciboIngreso.setPrecio(reciboIngreso.getPrecio()+MONTO_A_ALTO_CILINDRAJE);
			}
		}
		return reciboIngreso;
	}
	
}
