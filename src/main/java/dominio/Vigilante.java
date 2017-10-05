package dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import dominio.reglas.ReglaIngreso;
import dominio.repositorio.RepositorioRecibo;
import dominio.repositorio.RepositorioVehiculo;

public class Vigilante {
	private List<ReglaIngreso> reglasIngreso = new ArrayList<>();
	private RepositorioRecibo repositorioRecibo;
	private RepositorioVehiculo repositorioVehiculo;

	public Vigilante(RepositorioRecibo repoRecibo,RepositorioVehiculo repoVehiculo, List<ReglaIngreso> reglasIngreso) {
		this.repositorioRecibo = repoRecibo;
		this.reglasIngreso=reglasIngreso;
		this.repositorioVehiculo = repoVehiculo;
	}

	public Recibo ingresarVehiculo(Vehiculo vehiculo, Calendar horaIngreso) {
		Recibo recibo = new Recibo(vehiculo, horaIngreso);
		for(ReglaIngreso regla : reglasIngreso) {
			recibo = regla.verificarPosibilidadIngreso(recibo);
		}
		registrarVehiculo(vehiculo);
		repositorioRecibo.ingresarRecibo(recibo);
		return recibo;
	}
	
	private void registrarVehiculo(Vehiculo vehiculo) {
		try {
			repositorioVehiculo.encontrarVehiculoPorPlaca(vehiculo.getPlaca());			
		} catch (Exception e) {
			repositorioVehiculo.registrarVehiculo(vehiculo);
		}
	}
}
