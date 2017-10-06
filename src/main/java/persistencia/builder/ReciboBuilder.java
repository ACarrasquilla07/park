package persistencia.builder;

import dominio.Recibo;
import dominio.Vehiculo;
import persistencia.entidad.ReciboEntity;

public class ReciboBuilder {
	
	private ReciboBuilder() {
		super();
	}

	public static Recibo convertirARecibo(ReciboEntity reciboEntity) {
		Recibo recibo = null;
		if(reciboEntity != null) {
			Vehiculo vehiculo = VehiculoBuilder.convertirAVehiculo(reciboEntity.getVehiculoEntity());
			recibo = new Recibo(vehiculo, reciboEntity.getHoraIngreso());
			recibo.setPrecio(reciboEntity.getPrecio());
		}
		return recibo;
	}
}
