package dominio.repositorio;

import dominio.Vehiculo;

public interface RepositorioVehiculo {
	public Vehiculo registrarVehiculo(Vehiculo vehiculo);
	public Vehiculo encontrarVehiculoPorPlaca(String placa);
	
}
