package dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dominio.excepcion.ParqueaderoExcepcion;
import dominio.reglas.ReglaIngreso;
import dominio.reglas.ReglaSalida;
import dominio.repositorio.RepositorioRecibo;
import dominio.repositorio.RepositorioVehiculo;

public class Vigilante {
	private List<ReglaIngreso> reglasIngreso = new ArrayList<>();
	private List<ReglaSalida> reglasSalida = new ArrayList<>();
	private RepositorioRecibo repositorioRecibo;
	private RepositorioVehiculo repositorioVehiculo;

	public Vigilante(RepositorioRecibo repoRecibo,RepositorioVehiculo repoVehiculo, List<ReglaIngreso> reglasIngreso, List<ReglaSalida> reglaSalidas) {
		this.repositorioRecibo = repoRecibo;
		this.reglasIngreso=reglasIngreso;
		this.repositorioVehiculo = repoVehiculo;
		this.reglasSalida = reglaSalidas;
	}

	public Recibo ingresarVehiculo(SolicitudIngreso solicitudIngreso) {
		reglasIngreso.stream().forEach(regla -> regla.verificarPosibilidadIngreso(solicitudIngreso));
	
		Recibo recibo = new Recibo(solicitudIngreso.getVehiculo(), solicitudIngreso.getHoraIngreso());		
		registrarVehiculo(solicitudIngreso.getVehiculo());
		
		repositorioRecibo.ingresarRecibo(recibo);
		return recibo;		
	}
	
	private void registrarVehiculo(Vehiculo vehiculo) {
		try {
			repositorioVehiculo.encontrarVehiculoPorPlaca(vehiculo.getPlaca());	//--------------- 
		} catch (Exception e) {
			repositorioVehiculo.registrarVehiculo(vehiculo);
		}
	}

	public Recibo generarReciboDeSalidaVehiculo(String placaCarroARetirar, Calendar horaSalida) {
		Recibo recibo = obtenerReciboPorPlaca(placaCarroARetirar);
		recibo.setHoraSalida(horaSalida);
		recibo.setPrecio(calcularTotalValorAPagar(recibo));
		repositorioRecibo.actualizarReciboParaSalida(recibo);		
		return recibo;
	}
	
	private double calcularTotalValorAPagar(Recibo reciboIngreso) {
		double precioTotal=0d;
		for (ReglaSalida reglaSalida: reglasSalida) {
			precioTotal += reglaSalida.calcularValorAPagarServicioParqueadero(reciboIngreso);
		}
		return precioTotal;
	}
	
	private Recibo obtenerReciboPorPlaca(String placa) {
		Recibo recibo = repositorioRecibo.obtenerReciboActivoPorPlaca(placa);
		if(recibo == null) {
			throw new ParqueaderoExcepcion("El vehiculo no se encuentra en el parqueadero");
		}
		return recibo;
	}

	public List<Vehiculo> listarVehiculosEnParqueadero() {
		return repositorioRecibo.listarVehiculosEnParqueadero();
	}
}



