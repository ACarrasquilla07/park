package dominio.reglas.ingresoreglas;

import java.util.Calendar;
import dominio.SolicitudIngreso;
import dominio.excepcion.ParqueaderoExcepcion;
import dominio.reglas.ReglaIngreso;

public class ReglaPlacasRestringidas implements ReglaIngreso{
	private static final String LETRA_INICIO_RESTRINGIDA = "A";
	private static final String MENSAJE_DIA_NO_HABIL = "No puede ingresar porque no esta en un dia habil";
	
	@Override
	public void verificarPosibilidadIngreso(SolicitudIngreso solicitudIngreso) {
		if(solicitudIngreso.getVehiculo().getPlaca().substring(0,1).equals(LETRA_INICIO_RESTRINGIDA)) {
			boolean noEsDomingo = solicitudIngreso.getHoraIngreso().get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY;
			boolean noEsLunes = solicitudIngreso.getHoraIngreso().get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY;		
			if(noEsDomingo && noEsLunes) {
				throw new ParqueaderoExcepcion(MENSAJE_DIA_NO_HABIL);
			}
		}		
	}
}
