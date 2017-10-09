package dominio.reglas.ingresoreglas;

import dominio.Carro;
import dominio.ConstantesParqueadero;
import dominio.SolicitudIngreso;
import dominio.excepcion.ParqueaderoExcepcion;
import dominio.reglas.ReglaIngreso;
import dominio.repositorio.RepositorioRecibo;
import persistencia.builder.VehiculoBuilder;

public class ReglaCeldasCarroMaximo implements ReglaIngreso{
	private static final String NO_HAY_CELDAS_PARA_CARROS = "No hay celdas para carros disponibles";
	private RepositorioRecibo repositorioRecibo;
	
	public ReglaCeldasCarroMaximo(RepositorioRecibo repositorioRecibo) {
		this.repositorioRecibo = repositorioRecibo;
	}

	@Override
	public void verificarPosibilidadIngreso(SolicitudIngreso solicitudIngreso) {
		if(esCarro(solicitudIngreso) && excedeElNumeroMaximoDeCarros()) { 
			throw new ParqueaderoExcepcion(NO_HAY_CELDAS_PARA_CARROS);
		}
	}

	private boolean excedeElNumeroMaximoDeCarros() {
		return repositorioRecibo.numeroRecibos(VehiculoBuilder.TIPO_CARRO) >= ConstantesParqueadero.MAXIMO_CARROS;
	}

	private boolean esCarro(SolicitudIngreso solicitudIngreso) {
		return solicitudIngreso.getVehiculo() instanceof Carro;
	}
}
