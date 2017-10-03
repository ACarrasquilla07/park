package dominio.rest;

import java.util.Calendar;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dominio.Carro;
import dominio.Moto;
import dominio.Recibo;
import dominio.Vehiculo;
import dominio.Vigilante;

@EnableAutoConfiguration
@RestController
@Transactional
public class ControladorVigilante {
	Vigilante vigilante = new Vigilante();
	
	@RequestMapping(value = "/ingresocarro", method = RequestMethod.POST)
	@ResponseBody
	public Recibo ingresarVehiculo(@RequestBody Carro carro) {
		Calendar horaIngreso = Calendar.getInstance();
		return vigilante.ingresarVehiculo(carro, horaIngreso);
	}
	
	@RequestMapping(value = "/ingresomoto", method = RequestMethod.POST)
	@ResponseBody
	public Recibo ingresarVehiculo(@RequestBody Moto moto) {
		Calendar horaIngreso = Calendar.getInstance();
		return vigilante.ingresarVehiculo(moto, horaIngreso);
	}
}
