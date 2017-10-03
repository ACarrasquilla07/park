package dominiotest.integracion;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import dominio.Carro;
import dominio.Moto;
import dominio.Recibo;
import dominio.Vehiculo;
import dominio.Vigilante;
import dominio.excepcion.IngresoExcepcion;
import dominio.util.Utilidad;

public class ParqueaderoTests {
	@Test
	public void ingresarCarro() {
		Vehiculo carro = new Carro("KHS558");
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Calendar.getInstance();
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue( carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void ingresarCarroPlacaRestringidaViernes() {
		Vehiculo carro = new Carro("AKS982");
		Vehiculo carro2 = new Carro("ALO000");
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.OCTOBER, 6, 8, 0);
		Calendar horaIngreso2 = Utilidad.crearHora(2020, Calendar.OCTOBER, 14, 8, 0);
		try {
			vigilante.ingresarVehiculo(carro, horaIngreso);
			fail();
		} catch (IngresoExcepcion e) {
			assertEquals("No puede ingresar porque no esta en un dia habil", e.getMessage());
		}
	}
	
	@Test
	public void ingresarCarroPlacaRestringidaMiercoles() {
		Vehiculo carro2 = new Carro("ALO000");
		Vigilante vigilante = new Vigilante();
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
		Vehiculo carro = new Carro("AKS982");
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.OCTOBER, 8, 8, 0);
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue( carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void ingresarCarroPlacaRestringidaDiaHabilLunes() {
		Vehiculo carro = new Carro("AKS982");
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.OCTOBER, 9, 8, 0);
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue( carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void ingresarMoto(){
		Vehiculo moto = new Moto("DNS89A", 125);
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Calendar.getInstance();
		Recibo recibo1 = vigilante.ingresarVehiculo(moto, horaIngreso);
		assertTrue( moto.getPlaca().equals(recibo1.getVehiculo().getPlaca()) );	
	}
	
	@Test
	public void salidaCarro() {
		Vehiculo carro = new Carro("KAS902");
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Calendar.getInstance();
		Recibo reciboEntrada = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue(carro.getPlaca().equals(reciboEntrada.getVehiculo().getPlaca()));
		Recibo reciboSalida = vigilante.retirarVehiculo(reciboEntrada);
	}
	
	@Test
	public void ingresarMotoAltoCilindraje() {
		Vehiculo BMW900 = new Moto("DNS89A", 900);
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.SEPTEMBER, 18, 9, 12);
		Recibo reciboIngreso = vigilante.ingresarVehiculo(BMW900, horaIngreso);
		assertTrue(BMW900.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));	
		assertTrue(reciboIngreso.getPrecio()==2000d);
	}
	
	@Test
	public void ingresarMotoBajoCilindraje() {
		Vehiculo BMW900 = new Moto("DNS89A", 125);
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.SEPTEMBER, 18, 9, 12);
		Recibo reciboIngreso = vigilante.ingresarVehiculo(BMW900, horaIngreso);
		assertTrue(BMW900.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));	
		assertTrue(reciboIngreso.getPrecio()==0d);
	}
}
