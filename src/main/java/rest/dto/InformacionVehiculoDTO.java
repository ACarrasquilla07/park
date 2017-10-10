package rest.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dominio.Carro;
import dominio.Moto;
import dominio.Recibo;

public class InformacionVehiculoDTO {
	public static final String TIPO_CARRO = "Carro";
	public static final String TIPO_MOTO = "Moto";
	private String placaVehiculo;
	private String tipoVehiculo;
	private Calendar fechaIngreso;
	
	public InformacionVehiculoDTO(String placaVehiculo, String tipoVehiculo, Calendar fechaIngreso) {
		this.placaVehiculo = placaVehiculo;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaIngreso = fechaIngreso;
	}
	
	public static List<InformacionVehiculoDTO> convertirListaReciboAInformacionVehiculo(List<Recibo> listaRecivosAct) {
		List<InformacionVehiculoDTO> listaInformacionVehiculosActivos = new ArrayList<>();
		for (Recibo recibo : listaRecivosAct) {
			String tipoVehiculo = "";
			if(recibo.getVehiculo() instanceof Carro) {
				tipoVehiculo = TIPO_CARRO;
			}else if(recibo.getVehiculo() instanceof Moto) {
				tipoVehiculo = TIPO_MOTO;
			}
			InformacionVehiculoDTO infoVehiculoActivo = new InformacionVehiculoDTO(recibo.getVehiculo().getPlaca(), tipoVehiculo, recibo.getHoraIngreso());
			listaInformacionVehiculosActivos.add(infoVehiculoActivo);
		}
		return listaInformacionVehiculosActivos;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}
	
	
}
