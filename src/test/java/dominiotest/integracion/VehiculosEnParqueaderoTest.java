package dominiotest.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import dominio.Carro;
import dominio.Moto;
import dominio.Recibo;
import dominio.SolicitudIngreso;
import dominio.Vehiculo;
import dominio.Vigilante;
import rest.ParqueaderooApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParqueaderooApplication.class })
@DataJpaTest
public class VehiculosEnParqueaderoTest {
	@Autowired
	private Vigilante vigilante;
	
	@Test
	public void ingresarCarro() {
		Vehiculo carro = new Carro("KHS000");
		Calendar horaIngreso = Calendar.getInstance();
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(carro, horaIngreso);
		
		Recibo recibo1 = vigilante.ingresarVehiculo(solicitudIngreso);
		
		assertTrue(carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void ingresarMoto() {
		Vehiculo moto = new Moto("DNS03A", 125);
		Calendar horaIngreso = Calendar.getInstance();
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(moto, horaIngreso);
		
		Recibo recibo1 = vigilante.ingresarVehiculo(solicitudIngreso);
		
		assertTrue(moto.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void listarVehiculosEnParqueaderoTest() {
		ingresarCarro();
		ingresarMoto();
		
		List<Vehiculo> listaVehiculosEnParqueadero = vigilante.listarVehiculosEnParqueadero();
		
		assertTrue(listaVehiculosEnParqueadero.size() == 2);
	}
	
	@Test
	public void listarRecibosEnParqueaderoTest() {
		ingresarCarro();
		ingresarMoto();
		
		List<Recibo> listaVehiculosEnParqueadero = vigilante.listarRecibosActivos();
		
		assertTrue(listaVehiculosEnParqueadero.size() == 2);
	}
}
