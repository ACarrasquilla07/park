package dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dominio.reglas.ReglaIngreso;
import dominio.reglas.ReglaMotoAltoCilindraje;
import dominio.reglas.ReglaPlacasRestringidas;

public class Vigilante {
	private List<ReglaIngreso> reglasIngreso = new ArrayList<>();
	
	public Vigilante() {	
		reglasIngreso.add(new ReglaMotoAltoCilindraje());
		reglasIngreso.add(new ReglaPlacasRestringidas());
	}

	public Recibo ingresarVehiculo(Vehiculo vehiculo, Calendar horaIngreso) {
		Recibo recibo = new Recibo(vehiculo, horaIngreso);
		for(ReglaIngreso regla : reglasIngreso) {
			recibo = regla.verificarPosibilidadIngreso(recibo);
		}
		//Ingresar a la base de datos el recibo
		return recibo;
	}
}
