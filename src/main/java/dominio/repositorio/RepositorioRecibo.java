package dominio.repositorio;

import java.util.List;

import dominio.Recibo;

public interface RepositorioRecibo {
	public Recibo ingresarRecibo(Recibo reciboIngreso);
	public List<Recibo> listarRecibosCarros();
}
