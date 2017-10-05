package dominio.reglas;

import dominio.ConstantesParqueadero;
import dominio.Recibo;
import dominio.excepcion.IngresoExcepcion;
import dominio.repositorio.RepositorioRecibo;
import persistencia.builder.VehiculoBuilder;

public class ReglaCeldasMotoMaximo implements ReglaIngreso{
	private static final String NO_HAY_CELDAS_PARA_CARROS = "No hay celdas para motos disponibles";
	private RepositorioRecibo repositorioRecibo;
	
	public ReglaCeldasMotoMaximo(RepositorioRecibo repositorioRecibo) {
		this.repositorioRecibo = repositorioRecibo;
	}

	@Override
	public Recibo verificarPosibilidadIngreso(Recibo reciboIngreso) {
		if(repositorioRecibo.numeroRecibos(VehiculoBuilder.TIPO_MOTO) >= ConstantesParqueadero.MAXIMO_MOTOS) {
			throw new IngresoExcepcion(NO_HAY_CELDAS_PARA_CARROS);
		}
		return reciboIngreso;
	}
}
