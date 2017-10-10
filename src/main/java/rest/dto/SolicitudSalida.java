package rest.dto;

import java.util.Calendar;

public class SolicitudSalida {
	
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Calendar getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(Calendar horaSalida) {
		this.horaSalida = horaSalida;
	}
	String placa;
	Calendar horaSalida;
}
