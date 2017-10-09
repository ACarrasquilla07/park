package dominio.reglas.ingresoreglas;

import dominio.ConstantesParqueadero;
import dominio.Moto;
import dominio.SolicitudIngreso;
import dominio.excepcion.ParqueaderoExcepcion;
import dominio.reglas.ReglaIngreso;
import dominio.repositorio.RepositorioRecibo;
import persistencia.builder.VehiculoBuilder;

public class ReglaCeldasMotoMaximo implements ReglaIngreso{
	private static final String NO_HAY_CELDAS_PARA_CARROS = "No hay celdas para motos disponibles";
	private RepositorioRecibo repositorioRecibo;
	
	public ReglaCeldasMotoMaximo(RepositorioRecibo repositorioRecibo) {
		this.repositorioRecibo = repositorioRecibo;
	}

	@Override
	public void verificarPosibilidadIngreso(SolicitudIngreso solicitudIngreso) {
		if(!(solicitudIngreso.getVehiculo() instanceof Moto)) {
			return;
		}
		if(repositorioRecibo.numeroRecibos(VehiculoBuilder.TIPO_MOTO) >= ConstantesParqueadero.MAXIMO_MOTOS) {
			throw new ParqueaderoExcepcion(NO_HAY_CELDAS_PARA_CARROS);
		}
	}		
}
