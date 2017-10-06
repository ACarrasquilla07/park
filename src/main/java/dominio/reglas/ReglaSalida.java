package dominio.reglas;

import dominio.Recibo;

public interface ReglaSalida {
	public Recibo calcularValorAPagarServicioParqueadero(Recibo recibo);
}
