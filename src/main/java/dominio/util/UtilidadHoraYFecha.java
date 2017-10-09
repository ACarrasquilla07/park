package dominio.util;

import java.util.Calendar;

import dominio.excepcion.ParqueaderoExcepcion;

public class UtilidadHoraYFecha {
	
	public static final String ERROR_INTENTANDO_CALCULAR_TIEMPO_DE_SERVICIO = "Error intentando calcular tiempo de servicio";
	private static final int HORAS_DIVISOR = 1000*60*60;
	private static final int DIAS_DIVISOR = HORAS_DIVISOR*9;

	private UtilidadHoraYFecha() {
	}

	public static Calendar crearHora(int anio, int mes, int diaMes, int hora, int minutos) {
		Calendar horaYFecha = Calendar.getInstance();
		horaYFecha.set(Calendar.YEAR, anio);
		horaYFecha.set(Calendar.MONTH, mes);
		horaYFecha.set(Calendar.DAY_OF_MONTH, diaMes);
		// 24-hour clock
		horaYFecha.set(Calendar.HOUR_OF_DAY, hora);
		horaYFecha.set(Calendar.MINUTE, minutos);
		horaYFecha.set(Calendar.SECOND, 0);
		horaYFecha.set(Calendar.MILLISECOND, 0);
		return horaYFecha;
	}
	
	public static double diferenciaHoras(Calendar horaInicial, Calendar horaFinal) {		
		return Math.ceil(diferencia(horaInicial, horaFinal, HORAS_DIVISOR));
	}
	
	public static double diferenciaDias(Calendar horaInicial, Calendar horaFinal) {
		return Math.floor(diferencia(horaInicial, horaFinal,DIAS_DIVISOR));
	}

	private static double diferencia(Calendar horaInicial, Calendar horaFinal, int divisor) {		
		long miliSegHoraInicial = horaInicial.getTimeInMillis();
		long miliSegHoraFinal = horaFinal.getTimeInMillis();
		verificarHorasCorrectas(miliSegHoraInicial,miliSegHoraFinal);
		long diferenciaMiliSeg = miliSegHoraFinal - miliSegHoraInicial;
		return ((double)diferenciaMiliSeg/divisor);
	}

	private static void verificarHorasCorrectas(long miliSegHoraInicial, long miliSegHoraFinal) {
		if(miliSegHoraInicial > miliSegHoraFinal) {
			throw new ParqueaderoExcepcion(ERROR_INTENTANDO_CALCULAR_TIEMPO_DE_SERVICIO);
		}
	}
}
