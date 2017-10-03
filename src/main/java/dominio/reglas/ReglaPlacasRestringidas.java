package dominio.reglas;

import java.util.Calendar;

import javax.sound.midi.Soundbank;

import dominio.Recibo;
import dominio.excepcion.IngresoExcepcion;

public class ReglaPlacasRestringidas implements ReglaIngreso{
	private static final String LETRA_INICIO_RESTRINGIDA = "A";
	private static final String MENSAJE_DIA_NO_HABIL = "No puede ingresar porque no esta en un dia habil";
	
	@Override
	public Recibo verificarPosibilidadIngreso(Recibo reciboIngreso) {
		System.out.println("------------------------------ "+ reciboIngreso.getVehiculo().getPlaca().substring(0,1));
		if(reciboIngreso.getVehiculo().getPlaca().substring(0,1)==LETRA_INICIO_RESTRINGIDA) {
			boolean noEsDomingo = reciboIngreso.getHoraIngreso().get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY;
			boolean noEsLunes = reciboIngreso.getHoraIngreso().get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY;		
			if(noEsDomingo && noEsLunes) {
				throw new IngresoExcepcion(MENSAJE_DIA_NO_HABIL);
			}
		}
		return reciboIngreso;
	}
	
	
}
