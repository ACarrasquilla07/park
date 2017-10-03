package dominio.util;

import java.util.Calendar;

public class Utilidad {
	public static Calendar crearHora(int anio, int mes, int diaMes, int hora, int minutos) {
		Calendar horaYFecha = Calendar.getInstance();
		horaYFecha.set(Calendar.YEAR, anio);
		// Month. 0 is January, 11 is November
		horaYFecha.set(Calendar.MONTH, mes);
		horaYFecha.set(Calendar.DAY_OF_MONTH, diaMes);
		// or 24-hour clock
		horaYFecha.set(Calendar.HOUR_OF_DAY, 13);
		horaYFecha.set(Calendar.MINUTE, 30);
		horaYFecha.set(Calendar.SECOND, 0);
		horaYFecha.set(Calendar.MILLISECOND, 0);
		return horaYFecha;
	}
}
