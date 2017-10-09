package dominiotest.integracion;

import static org.junit.Assert.assertTrue;
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
	public void ingresarMotoAltoCilindraje() {
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
	public void ingresarMotoBajoCilindraje() {
		Vehiculo moto = new Moto("DNS03A", 500);
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 9, 7, 00);
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(moto, horaIngreso);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 9, 14, 01);
		
		Recibo reciboIngreso = vigilante.ingresarVehiculo(solicitudIngreso);
		Recibo reciboSalida = vigilante.generarReciboDeSalidaVehiculo(reciboIngreso.getVehiculo().getPlaca(), horaSalida);
		
		assertTrue(moto.getPlaca().equals(reciboIngreso.getVehiculo().getPlaca()));
		assertTrue(reciboSalida.getPrecio() == ConstantesParqueadero.VALOR_HORA_MOTO*8);
		
	}
}
