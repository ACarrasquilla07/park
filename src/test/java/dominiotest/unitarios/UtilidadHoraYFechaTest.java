package dominiotest.unitarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

import dominio.util.UtilidadHoraYFecha;

public class UtilidadHoraYFechaTest {
	@Test
	public void crearHoraYFechaTest() {
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.JULY, 22, 13, 30);
		Calendar horaIngreso2 = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 2, 8, 2);
		
		assertEquals(Calendar.SATURDAY, horaIngreso.get(Calendar.DAY_OF_WEEK));
		assertEquals(Calendar.MONDAY, horaIngreso2.get(Calendar.DAY_OF_WEEK));
	}
	
	@Test 
	public void diferencia1HoraTest() {
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 5, 6, 00);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 5, 6, 01);
		
		double diferenciaHoras = UtilidadHoraYFecha.diferenciaHoras(horaIngreso, horaSalida);
		
		assertTrue(diferenciaHoras == 1d);
	}
	
	@Test 
	public void diferenciaHorasTest() {
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 5, 6, 00);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 5, 15, 00);
		
		double diferenciaHoras = UtilidadHoraYFecha.diferenciaHoras(horaIngreso, horaSalida);
		
		assertTrue(diferenciaHoras == 9d);
	}
	
	@Test 
	public void diferenciaHorasTest2() {
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.JULY, 22, 12, 30);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 6, 13, 30);
		
		double diferenciaHoras = UtilidadHoraYFecha.diferenciaHoras(horaIngreso, horaSalida);
		
		assertTrue(diferenciaHoras == 1825d);
	}	
	
	@Test
	public void numeroDias() {
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 7, 1, 00);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 7, 19, 00);
		
		double diferenciaDias = UtilidadHoraYFecha.diferenciaDias(horaIngreso, horaSalida);
		
		assertTrue(diferenciaDias == 2);
	}
	
	@Test
	public void ceroDiasDe9Horas() {
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 7, 1, 00);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 7, 9, 59);
		
		double diferenciaDias = UtilidadHoraYFecha.diferenciaDias(horaIngreso, horaSalida);
		
		assertTrue(diferenciaDias == 0);
	}
	
	@Test
	public void unDiaDe9Horas() {
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 7, 1, 00);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 7, 18, 59);
		
		double diferenciaDias = UtilidadHoraYFecha.diferenciaDias(horaIngreso, horaSalida);
		
		assertTrue(diferenciaDias == 1);
	}
	
	@Test
	public void horasAlContrarioTest() {
		Calendar horaIngreso = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 8, 1, 00);
		Calendar horaSalida = UtilidadHoraYFecha.crearHora(2017, Calendar.OCTOBER, 7, 1, 00);
		try {
			UtilidadHoraYFecha.diferenciaDias(horaIngreso, horaSalida);
			fail();
		} catch (Exception excepcion) {
			assertEquals(UtilidadHoraYFecha.ERROR_INTENTANDO_CALCULAR_TIEMPO_DE_SERVICIO, excepcion.getMessage());
		}
	}
}
