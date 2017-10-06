package dominio.reglas.ingresoreglas;

import dominio.Recibo;
import dominio.SolicitudIngreso;
import dominio.excepcion.IngresoExcepcion;
import dominio.reglas.ReglaIngreso;
import dominio.repositorio.RepositorioRecibo;

public class ReglaNoIngresoMismoVehiculo implements ReglaIngreso{
	private RepositorioRecibo repositorioRecibo;
	
	public ReglaNoIngresoMismoVehiculo(RepositorioRecibo repoRecibo) {
		this.repositorioRecibo = repoRecibo;
	}
	
	@Override
	public Recibo verificarPosibilidadIngreso(SolicitudIngreso solicitudIngreso) {	
		Recibo reciboEnDatos = repositorioRecibo.obtenerReciboActivoPorPlaca(solicitudIngreso.getVehiculo().getPlaca());
		if(reciboEnDatos != null) {
			throw new IngresoExcepcion("EL vehiculo se encuentra adentro del parqueadero");
		}
		return new Recibo(solicitudIngreso.getVehiculo(), solicitudIngreso.getHoraIngreso());
		
			
	}
}
