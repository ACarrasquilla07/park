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
@SpringBootTest(classes = { ParqueaderooApplication.class })
@DataJpaTest
public class ParqueaderoTests {
	private static final int CAPACIDAD_MAXIMA_MOTOS = 10;
	private static final int CAPACIDAD_MAXIMA_CARROS = 20;
	@Autowired
	private Vigilante vigilante;

	@Test
	public void ingresarCarroQueYaIngreso() {
		Vehiculo carro = new Carro("KHS011");
		Calendar horaIngreso = Calendar.getInstance();
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue(carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
		horaIngreso = Calendar.getInstance();
		try {
			vigilante.ingresarVehiculo(carro, horaIngreso);
			fail();
		} catch (Exception e) {
			assertEquals("EL vehiculo se encuentra adentro del parqueadero", e.getMessage());
		}
	}

	@Test
	public void ingresarCarro() {
		Vehiculo carro = new Carro("KHS009");
		Calendar horaIngreso = Calendar.getInstance();
		
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		
		assertTrue(carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
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
		assertTrue(carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}

	@Test
	public void ingresarCarroPlacaRestringidaDiaHabilLunes() {
		Vehiculo carro = new Carro("AKS004");
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.OCTOBER, 9, 8, 0);
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue(carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}

	@Test
	public void ingresarMoto() {
		Vehiculo moto = new Moto("DNS03A", 125);
		Calendar horaIngreso = Calendar.getInstance();
		Recibo recibo1 = vigilante.ingresarVehiculo(moto, horaIngreso);
		assertTrue(moto.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}

	@Test
	public void salidaCarro() {
		Vehiculo carro = new Carro("KAS902");
		Calendar horaIngreso = Calendar.getInstance();
		Recibo reciboEntrada = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue(carro.getPlaca().equals(reciboEntrada.getVehiculo().getPlaca()));
	}

//	@Test
//	public void ingresarMotoAltoCilindraje() {
//		Vehiculo BMW900 = new Moto("DNS01A", 501);
//		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.SEPTEMBER, 18, 9, 12);
//		Recibo reciboIngreso = vigilante.ingresarVehiculo(BMW900, horaIngreso);
//		assertTrue(BMW900.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));
//		assertTrue(reciboIngreso.getPrecio() == 2000d);
//	}

	@Test
	public void ingresarMotoBajoCilindraje() {
		Vehiculo BMW900 = new Moto("DNS00A", 499);
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.SEPTEMBER, 18, 9, 12);
		Recibo reciboIngreso = vigilante.ingresarVehiculo(BMW900, horaIngreso);
		assertTrue(reciboIngreso.getPrecio() == 0d);
		assertTrue(BMW900.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));
	}

	@Test
	public void ingresarMasDelMaximoDeCarros() {
		String[] Placas = { "BBB001", "BBB002", "BBB003", "BBB004", "BBB005", "BBB006", "BBB007", "BBB008", "BBB009",
				"BBB010", "BBB011", "BBB012", "BB0013", "BB0014", "BBB015", "BBB016", "BBB017", "BBB018", "BBB019",
				"BBB020", "BBB021", "BBB022" };
		Vehiculo carro;
		Calendar horaIngreso = Calendar.getInstance();

		for (int i = 0; i < CAPACIDAD_MAXIMA_CARROS; i++) {
			carro = new Carro(Placas[i]);
			Recibo reciboIngreso = vigilante.ingresarVehiculo(carro, horaIngreso);
			assertTrue(carro.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));
		}
		carro = new Carro(Placas[20]);
		try {
			vigilante.ingresarVehiculo(carro, horaIngreso);
			fail();
		} catch (Exception e) {
			assertEquals("No hay celdas para carros disponibles", e.getMessage());
		}
		carro = new Carro(Placas[21]);
		try {
			vigilante.ingresarVehiculo(carro, horaIngreso);
			fail();
		} catch (Exception e) {
			assertEquals("No hay celdas para carros disponibles", e.getMessage());
		}
		Vehiculo BMW900 = new Moto("DNS00A", 499);
		Calendar horaIngresoMoto = Utilidad.crearHora(2017, Calendar.SEPTEMBER, 18, 9, 12);
		Recibo reciboIngresoMoto = vigilante.ingresarVehiculo(BMW900, horaIngresoMoto);
		assertTrue(BMW900.getPlaca().equals(reciboIngresoMoto.getVehiculo().getPlaca()));
	}

	@Test
	public void ingresarMasDelMaximoDeMotos() {
		String[] Placas = { "BBB001A", "BBB002A", "BBB003A", "BBB004A", "BBB005A", "BBB006A", "BBB007A", "BBB008A",
				"BBB009A", "BBB010A", "BBB011A", "BBB012A", "BB0013A", "BB0014A" };
		Vehiculo moto;
		Calendar horaIngreso = Calendar.getInstance();

		for (int i = 0; i < CAPACIDAD_MAXIMA_MOTOS; i++) {
			moto = new Moto(Placas[i], 125);
			Recibo reciboIngreso = vigilante.ingresarVehiculo(moto, horaIngreso);
			assertTrue(moto.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));
		}
		moto = new Moto(Placas[10], 125);
		try {
			vigilante.ingresarVehiculo(moto, horaIngreso);
			fail();
		} catch (Exception e) {
			assertEquals("No hay celdas para motos disponibles", e.getMessage());
		}
		moto = new Moto(Placas[11], 125);
		try {
			vigilante.ingresarVehiculo(moto, horaIngreso);
			fail();
		} catch (Exception e) {
			assertEquals("No hay celdas para motos disponibles", e.getMessage());
		}
		ingresarCarro();
	}
}
