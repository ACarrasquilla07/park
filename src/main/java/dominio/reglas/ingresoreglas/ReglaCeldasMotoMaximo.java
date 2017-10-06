package dominio.reglas.ingresoreglas;

import dominio.ConstantesParqueadero;
import dominio.Moto;
import dominio.Recibo;
import dominio.SolicitudIngreso;
import dominio.excepcion.IngresoExcepcion;
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
	public Recibo verificarPosibilidadIngreso(SolicitudIngreso solicitudIngreso) {
		Recibo recibo = new Recibo(solicitudIngreso.getVehiculo(), solicitudIngreso.getHoraIngreso());
		if(!(recibo.getVehiculo() instanceof Moto)) {
			return recibo;
		}
		if(repositorioRecibo.numeroRecibos(VehiculoBuilder.TIPO_MOTO) >= ConstantesParqueadero.MAXIMO_MOTOS) {
			throw new IngresoExcepcion(NO_HAY_CELDAS_PARA_CARROS);
		}
		return recibo;
	}		
}
