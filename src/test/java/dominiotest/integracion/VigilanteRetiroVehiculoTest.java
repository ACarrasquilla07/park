package dominiotest.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import dominio.Carro;
import dominio.ConstantesParqueadero;
import dominio.Moto;
import dominio.Recibo;
import dominio.SolicitudIngreso;
import dominio.Vehiculo;
import dominio.Vigilante;
import dominio.util.UtilidadHoraYFecha;
import rest.ParqueaderooApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParqueaderooApplication.class })
@DataJpaTest
public class VigilanteRetiroVehiculoTest {
	private static final String ERROR_VEHICULO_NO_ESTA_ADENTRO = "El vehiculo no se encuentra en el parqueadero";

	private static final double MONTO_MOTO_ALTO_CILINDRAJE = 2000d;

	private static final double PRECIO_UN_DIA_CARRO = 8000d;
	
	@Autowired
	private Vigilante vigilante;
	
	
	@Test
	public void ingresarCarro() {
		Vehiculo carro = new Carro("KHS009");
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 5, 6, 00);
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(carro, horaIngreso);
		
		Recibo recibo1 = vigilante.ingresarVehiculo(solicitudIngreso);
		
		assertTrue(carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	
	@Test
	public void retirarVehiculoDeParquederoUsoMenosDeNueveHoras() {
		ingresarCarro();
		String placaCarroARetirar = "KHS009";
		Calendar horaSalida= UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 5, 15, 00);
		
		Recibo recibo = vigilante.generarReciboDeSalidaVehiculo(placaCarroARetirar, horaSalida);
		
		assertTrue(PRECIO_UN_DIA_CARRO == recibo.getPrecio());
	}
	
	@Test
	public void retirarMotoAltoCilindraje() {
		Vehiculo moto = new Moto("DNS02A", 501);
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 9, 7, 00);
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(moto, horaIngreso);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 13, 14, 01); //104 horas = 11 dias y 5 horas
		
		Recibo reciboIngreso = vigilante.ingresarVehiculo(solicitudIngreso);
		Recibo reciboSalida = vigilante.generarReciboDeSalidaVehiculo(reciboIngreso.getVehiculo().getPlaca(), horaSalida);
		
		assertTrue(moto.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));
		assertTrue(reciboSalida.getPrecio() == (ConstantesParqueadero.VALOR_DIA_MOTO*11)+(ConstantesParqueadero.VALOR_HORA_MOTO*5)+MONTO_MOTO_ALTO_CILINDRAJE);
	}
	
	@Test
	public void retirarMotoBajoCilindraje() {
		Vehiculo moto = new Moto("DNS03A", 500);
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 9, 7, 00);
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(moto, horaIngreso);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 9, 14, 01);
		
		Recibo reciboIngreso = vigilante.ingresarVehiculo(solicitudIngreso);
		Recibo reciboSalida = vigilante.generarReciboDeSalidaVehiculo(reciboIngreso.getVehiculo().getPlaca(), horaSalida);
		
		assertTrue(moto.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));
		assertTrue(reciboSalida.getPrecio() == ConstantesParqueadero.VALOR_HORA_MOTO*8);
		
	}
	
	@Test
	public void ingresarYRetirarCarroSinRestriccion() {
		Vehiculo carro = new Carro("KHS031");
		Calendar horaIngreso1 = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 5, 6, 07);
		Calendar horaSalida1 = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 5, 8, 23);
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(carro, horaIngreso1);
		
		Recibo reciboIngreso1 = vigilante.ingresarVehiculo(solicitudIngreso);
		Recibo reciboSalida1 = vigilante.generarReciboDeSalidaVehiculo(reciboIngreso1.getVehiculo().getPlaca(), horaSalida1);
		
		assertTrue(carro.getPlaca().equals(reciboIngreso1.getVehiculo().getPlaca()));
		assertTrue(reciboSalida1.getPrecio() == 3*ConstantesParqueadero.VALOR_HORA_CARRO);
	}
	
	@Test
	public void ingresarYRetirarMismoCarro() {
		ingresarYRetirarCarroSinRestriccion();
		Vehiculo carro = new Carro("KHS031");
		Calendar horaIngreso2 = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 9, 6, 07);
		Calendar horaSalida2 = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 9, 8, 23);
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(carro, horaIngreso2);
		
		Recibo reciboIngreso1 = vigilante.ingresarVehiculo(solicitudIngreso);
		Recibo reciboSalida1 = vigilante.generarReciboDeSalidaVehiculo(reciboIngreso1.getVehiculo().getPlaca(), horaSalida2);
		
		assertTrue(carro.getPlaca().equals(reciboIngreso1.getVehiculo().getPlaca()));
		assertTrue(reciboSalida1.getPrecio() == 3*ConstantesParqueadero.VALOR_HORA_CARRO);
	}
	
	@Test
	public void retirarVehiculoNoIngresado() {
		Vehiculo carro = new Carro("KHS032");
		Calendar horaSalida2 = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 9, 8, 23);
		
		try {
			vigilante.generarReciboDeSalidaVehiculo("KHS032", horaSalida2);
			fail();
		} catch (Exception excepcion) {
			assertEquals(excepcion.getMessage(), ERROR_VEHICULO_NO_ESTA_ADENTRO);
		}
		
	}
	
}
