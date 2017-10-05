package dominiotest.unitarios;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dominio.Carro;
import dominio.Moto;
import dominio.Vehiculo;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.VehiculoEntity;

public class VehiculoBuilderTest {
	
	@Test
	public void convertirAVehiculoMoto() {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca("JUM00A");
		vehiculoEntity.setTipo("Moto");
		vehiculoEntity.setCilindraje(125);
		Vehiculo moto = VehiculoBuilder.convertirAVehiculo(vehiculoEntity);
		boolean esMoto =  moto instanceof Moto ? true:false;
		boolean placaIgual = (moto.getPlaca() == "JUM00A") ? true:false;
		assertTrue(placaIgual && esMoto);
	}
	
	@Test
	public void convertirAVehiculoCarro() {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca("KAS902");
		vehiculoEntity.setCilindraje(0);
		vehiculoEntity.setTipo("Carro");
		Vehiculo carro = VehiculoBuilder.convertirAVehiculo(vehiculoEntity);
		boolean esCarro = carro instanceof Carro ? true:false;
		boolean placaIgual = carro.getPlaca() == "KAS902"?true:false;
		assertTrue(esCarro && placaIgual);
	
	}
}
