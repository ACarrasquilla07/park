package dominio.reglas.salidareglas;

import dominio.ConstantesParqueadero;
import dominio.Moto;
import dominio.Recibo;
import dominio.reglas.ReglaSalida;
import dominio.util.UtilidadHoraYFecha;

public class ReglaCalculaPrecioPorMoto implements ReglaSalida{

	@Override
	public double calcularValorAPagarServicioParqueadero(Recibo recibo) {
		if(!(recibo.getVehiculo() instanceof Moto)) {
			return recibo.getPrecio();
		}
		double precio = recibo.getPrecio();
		double numeroDias = UtilidadHoraYFecha.diferenciaDias(recibo.getHoraIngreso(), recibo.getHoraSalida());
		double numeroHorasTotal = UtilidadHoraYFecha.diferenciaHoras(recibo.getHoraIngreso(), recibo.getHoraSalida());
		double numeroHoras = numeroHorasTotal - numeroDias*9;
		precio +=  numeroDias*ConstantesParqueadero.VALOR_DIA_MOTO;
		precio += numeroHoras*ConstantesParqueadero.VALOR_HORA_MOTO;
		return precio;
	}

}
