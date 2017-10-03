package dominio;

import java.util.Calendar;

public class Recibo {
	private Calendar horaIngreso;
	private Calendar horaSalida;
	private Vehiculo vehiculo;
	private double precio;
	
	
	public Recibo(Vehiculo vehiculo, Calendar horaIngreso1) {
		this.vehiculo = vehiculo;
		this.horaIngreso = horaIngreso1;
		this.precio = 0d;
	}
	public Calendar getHoraIngreso() {
		return horaIngreso;
	}
	public Calendar getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(Calendar horaSalida) {
		this.horaSalida = horaSalida;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}