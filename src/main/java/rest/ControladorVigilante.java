package rest;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
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
import dominio.SolicitudIngreso;
import dominio.Vigilante;

@EnableAutoConfiguration
@RestController
@Transactional
@RequestMapping(value="/parqueadero")
public class ControladorVigilante {
	
	@Autowired
	Vigilante vigilante;
	
	@RequestMapping(value = "/ingreso/carro", method = RequestMethod.POST)
	@ResponseBody
	public Recibo ingresarVehiculo(@RequestBody Carro carro) {
		Calendar horaIngreso = Calendar.getInstance();
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(carro, horaIngreso);
		return vigilante.ingresarVehiculo(solicitudIngreso);
	}
	
	@RequestMapping(value = "/ingreso/moto", method = RequestMethod.POST)
	@ResponseBody
	public Recibo ingresarVehiculo(@RequestBody Moto moto) {
		Calendar horaIngreso = Calendar.getInstance();
		SolicitudIngreso solicitudIngreso = new SolicitudIngreso(moto, horaIngreso);
		return vigilante.ingresarVehiculo(solicitudIngreso);
	}
}
