package dominio.reglas;

import dominio.ConstantesParqueadero;
import dominio.Recibo;
import dominio.excepcion.IngresoExcepcion;
import dominio.repositorio.RepositorioRecibo;
import persistencia.builder.VehiculoBuilder;

public class ReglaCeldasCarroMaximo implements ReglaIngreso{
	private static final String NO_HAY_CELDAS_PARA_CARROS = "No hay celdas para carros disponibles";
	private RepositorioRecibo repositorioRecibo;
	
	public ReglaCeldasCarroMaximo(RepositorioRecibo repositorioRecibo) {
		this.repositorioRecibo = repositorioRecibo;
	}

	@Override
	public Recibo verificarPosibilidadIngreso(Recibo reciboIngreso) {
		if(repositorioRecibo.numeroRecibos(VehiculoBuilder.TIPO_CARRO) >= ConstantesParqueadero.MAXIMO_CARROS) { 
			throw new IngresoExcepcion(NO_HAY_CELDAS_PARA_CARROS);
		}
		return reciboIngreso;
	}
}
