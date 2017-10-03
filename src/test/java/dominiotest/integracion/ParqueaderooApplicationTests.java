package dominiotest.integracion;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dominio.Carro;
import dominio.Moto;
import dominio.Recibo;
import dominio.Vehiculo;
import dominio.Vigilante;
import dominio.reglas.ReglaMotoAltoCilindraje;
import dominio.util.Utilidad;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderooApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void ingresarCarro() {
		Vehiculo carro = new Carro("KHS558");
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Calendar.getInstance();
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		assertTrue( carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()) );
	}
	
	@Test
	public void ingresarCarroPlacaRestringida() {
		Vehiculo carro = new Carro("AKS982");
		Vigilante vigilante = new Vigilante();
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.OCTOBER, 4, 8, 0);
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		fail();
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
