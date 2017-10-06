package dominiotest.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dominio.Carro;
import dominio.Recibo;
import dominio.Vehiculo;
import dominio.Vigilante;
import junit.framework.Assert;
import rest.ParqueaderooApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParqueaderooApplication.class })
@DataJpaTest
public class VigilanteRetiroVehiculoTest {
	@Autowired
	private Vigilante vigilante;
	
	
	@Test
	public void ingresarCarro() {
		Vehiculo carro = new Carro("KHS009");
		Calendar horaIngreso = Calendar.getInstance();
		
		Recibo recibo1 = vigilante.ingresarVehiculo(carro, horaIngreso);
		
		assertTrue(carro.getPlaca().equals(recibo1.getVehiculo().getPlaca()));
	}
	

}
