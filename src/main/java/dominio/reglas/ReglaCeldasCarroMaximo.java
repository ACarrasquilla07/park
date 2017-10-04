package dominio.reglas;

import java.util.List;

import dominio.Recibo;
import dominio.excepcion.IngresoExcepcion;
import dominio.repositorio.RepositorioRecibo;
import dominio.repositorio.RepositorioVehiculo;

public class ReglaCeldasCarroMaximo implements ReglaIngreso{
	private static final String NO_HAY_CELDAS_PARA_CARROS = "No hay celdas para carros disponibles";
	private RepositorioRecibo repositorioRecibo;
	
	public ReglaCeldasCarroMaximo(RepositorioRecibo repositorioRecibo) {
		this.repositorioRecibo = repositorioRecibo;
	}

	@Override
	public Recibo verificarPosibilidadIngreso(Recibo reciboIngreso) {
		List<Recibo> listaRecibos = repositorioRecibo.listarRecibosCarros();
		if(listaRecibos.size() <= 3) {  //Ojo aqui se debe cambiar por 20 carrros maximo esto solo es para probar
			throw new IngresoExcepcion(NO_HAY_CELDAS_PARA_CARROS);
		}
		return reciboIngreso;
	}
}
