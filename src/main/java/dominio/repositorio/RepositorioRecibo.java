package dominio.repositorio;

import java.util.List;

import dominio.Recibo;
import dominio.Vehiculo;

public interface RepositorioRecibo {
	public Recibo ingresarRecibo(Recibo reciboIngreso);
	public Long numeroRecibos(String tipoVehiculo);
	public Recibo obtenerReciboActivoPorPlaca(String placa);
	public void actualizarReciboParaSalida(Recibo recibo);
	public List<Vehiculo> listarVehiculosEnParqueadero();
	public List<Recibo> listarRecivosActivos();
}
