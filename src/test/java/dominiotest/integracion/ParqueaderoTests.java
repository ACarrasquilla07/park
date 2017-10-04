package dominiotest.integracion;

import static org.junit.Assert.*;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import dominio.Carro;
import dominio.Moto;
import dominio.Recibo;
import dominio.Vehiculo;
import dominio.Vigilante;
import dominio.excepcion.IngresoExcepcion;
import dominio.util.Utilidad;
import rest.ParqueaderooApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ParqueaderooApplication.class})
@DataJpaTest
public class ParqueaderoTests {
	@Autowired
	private Vigilante vigilante;
	@Test
	public void ingresarCarro() {
		Vehiculo carro = new Carro("KHS009");
		Calendar horaIngreso = Calendar.getInstance();
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue( carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void ingresarCarroPlacaRestringidaViernes() {
		Vehiculo carro = new Carro("AKS008");
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.OCTOBER, 6, 8, 0);
		try {
			vigilante.ingresarVehiculo(carro, horaIngreso);
			fail();
		} catch (IngresoExcepcion e) {
			assertEquals("No puede ingresar porque no esta en un dia habil", e.getMessage());
		}
	}
	
	@Test
	public void ingresarCarroPlacaRestringidaMiercoles() {
		Vehiculo carro2 = new Carro("ALO006");
		Calendar horaIngreso2 = Utilidad.crearHora(2020, Calendar.OCTOBER, 14, 8, 0);
		try {
			vigilante.ingresarVehiculo(carro2, horaIngreso2);
			fail();
		} catch (IngresoExcepcion e) {
			assertEquals("No puede ingresar porque no esta en un dia habil", e.getMessage());
		}
	}
	
	@Test
	public void ingresarCarroPlacaRestringidaDiaHabilDomingo() {
		Vehiculo carro = new Carro("AKS005");
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.OCTOBER, 8, 8, 0);
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue( carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void ingresarCarroPlacaRestringidaDiaHabilLunes() {
		Vehiculo carro = new Carro("AKS004");
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.OCTOBER, 9, 8, 0);
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue( carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void ingresarMoto(){
		Vehiculo moto = new Moto("DNS03A", 125);
		Calendar horaIngreso = Calendar.getInstance();
		Recibo recibo1 = vigilante.ingresarVehiculo(moto, horaIngreso);
		assertTrue( moto.getPlaca().equals(recibo1.getVehiculo().getPlaca()) );	
	}
	
	@Test
	public void salidaCarro() {
		Vehiculo carro = new Carro("KAS902");
		Calendar horaIngreso = Calendar.getInstance();
		Recibo reciboEntrada = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue(carro.getPlaca().equals(reciboEntrada.getVehiculo().getPlaca()));
	}
	
	@Test
	public void ingresarMotoAltoCilindraje() {
		Vehiculo BMW900 = new Moto("DNS01A", 900);
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.SEPTEMBER, 18, 9, 12);
		Recibo reciboIngreso = vigilante.ingresarVehiculo(BMW900, horaIngreso);
		assertTrue(BMW900.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));	
		assertTrue(reciboIngreso.getPrecio()==2000d);
	}
	
	@Test
	public void ingresarMotoBajoCilindraje() {
		Vehiculo BMW900 = new Moto("DNS00A", 125);
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.SEPTEMBER, 18, 9, 12);
		Recibo reciboIngreso = vigilante.ingresarVehiculo(BMW900, horaIngreso);
		assertTrue(BMW900.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));	
		assertTrue(reciboIngreso.getPrecio()==0d);
	}	
	
}
