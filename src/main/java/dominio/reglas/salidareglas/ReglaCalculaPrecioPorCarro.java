package dominio.reglas.salidareglas;

import dominio.Carro;
import dominio.ConstantesParqueadero;
import dominio.Recibo;
import dominio.reglas.ReglaSalida;
import dominio.util.UtilidadHoraYFecha;

public class ReglaCalculaPrecioPorCarro implements ReglaSalida{

	@Override
	public double calcularValorAPagarServicioParqueadero(Recibo recibo) {
		if(!(recibo.getVehiculo() instanceof Carro)) {
			return recibo.getPrecio();
		}
		double precio = recibo.getPrecio();
		double numeroDias = UtilidadHoraYFecha.diferenciaDias(recibo.getHoraIngreso(), recibo.getHoraSalida());
		double numeroHorasTotal = UtilidadHoraYFecha.diferenciaHoras(recibo.getHoraIngreso(), recibo.getHoraSalida());
		double numeroHoras = numeroHorasTotal - numeroDias*9;
		precio +=  numeroDias*ConstantesParqueadero.VALOR_DIA_CARRO;
		precio += numeroHoras*ConstantesParqueadero.VALOR_HORA_CARRO;
		return precio;
	}
}
