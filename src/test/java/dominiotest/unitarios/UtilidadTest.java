package dominiotest.unitarios;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import dominio.util.Utilidad;

public class UtilidadTest {
	@Test
	public void crearHoraYFechaTest() {
		Calendar horaIngreso = Utilidad.crearHora(2017, Calendar.JULY, 22, 13, 30);
		Calendar horaIngreso2 = Utilidad.crearHora(2017, Calendar.OCTOBER, 2, 8, 2);
		assertEquals(Calendar.SATURDAY, horaIngreso.get(Calendar.DAY_OF_WEEK));
		assertEquals(Calendar.MONDAY, horaIngreso2.get(Calendar.DAY_OF_WEEK));
	}
}
