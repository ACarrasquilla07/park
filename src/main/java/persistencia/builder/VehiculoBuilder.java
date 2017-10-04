package persistencia.builder;

import dominio.Carro;
import dominio.Moto;
import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	public static final String TIPO_MOTO = "Moto";
	public static final String TIPO_CARRO = "Carro";

	private VehiculoBuilder() {
		super();
	}

	public static VehiculoEntity convertirAVehiculoEntity(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		if (vehiculo instanceof Moto) {
			vehiculoEntity.setCilindraje(((Moto) vehiculo).getCilindraje());
			vehiculoEntity.setTipo(TIPO_MOTO);
		} else {
			vehiculoEntity.setCilindraje(0);
			vehiculoEntity.setTipo(TIPO_CARRO);
		}
		return vehiculoEntity;
	}
	
	public static Vehiculo convertirAVehiculo(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo=null;
		String placa = vehiculoEntity.getPlaca();
		if(vehiculoEntity.getTipo()==TIPO_MOTO) {
			vehiculo = new Moto(placa, vehiculoEntity.getCilindraje());
		}
		else if(vehiculoEntity.getTipo()==TIPO_CARRO) {
			vehiculo = new Carro(placa);
		}
		return vehiculo;
	}
}
