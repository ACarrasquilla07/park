package dominio.repositorio;

import dominio.Recibo;

public interface RepositorioRecibo {
	public Recibo ingresarRecibo(Recibo reciboIngreso);
	public Long numeroRecibos(String tipoVehiculo);
}
