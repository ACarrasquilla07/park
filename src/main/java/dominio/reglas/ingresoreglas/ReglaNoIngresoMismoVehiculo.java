package dominio.reglas.ingresoreglas;

import dominio.Recibo;
import dominio.SolicitudIngreso;
import dominio.excepcion.ParqueaderoExcepcion;
import dominio.reglas.ReglaIngreso;
import dominio.repositorio.RepositorioRecibo;

public class ReglaNoIngresoMismoVehiculo implements ReglaIngreso{
	private RepositorioRecibo repositorioRecibo;
	
	public ReglaNoIngresoMismoVehiculo(RepositorioRecibo repoRecibo) {
		this.repositorioRecibo = repoRecibo;
	}
	
	@Override
	public void verificarPosibilidadIngreso(SolicitudIngreso solicitudIngreso) {	
		Recibo reciboEnDatos = repositorioRecibo.obtenerReciboActivoPorPlaca(solicitudIngreso.getVehiculo().getPlaca());
		if(reciboEnDatos != null) {
			throw new ParqueaderoExcepcion("EL vehiculo se encuentra adentro del parqueadero");
		}
	}
}
