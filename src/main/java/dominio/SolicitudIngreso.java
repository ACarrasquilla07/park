package dominio;

import java.util.Calendar;

public class SolicitudIngreso {
	private Vehiculo vehiculo;
	private Calendar horaIngreso;
	
	public SolicitudIngreso(Vehiculo vehiculo, Calendar horaIngreso) {
		this.vehiculo = vehiculo;
		this.horaIngreso = horaIngreso;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public Calendar getHoraIngreso() {
		return horaIngreso;
	}
}
