package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import dominio.Vigilante;
import dominio.reglas.ReglaIngreso;
import dominio.reglas.ingresoreglas.ReglaCeldasCarroMaximo;
import dominio.reglas.ingresoreglas.ReglaCeldasMotoMaximo;
import dominio.reglas.ingresoreglas.ReglaNoIngresoMismoVehiculo;
import dominio.reglas.ingresoreglas.ReglaPlacasRestringidas;
import dominio.reglas.salidareglas.ReglaMotoAltoCilindraje;
import dominio.repositorio.RepositorioRecibo;
import dominio.repositorio.RepositorioVehiculo;

@Configuration
public class VigilanteConfig {
	@Bean 
	public Vigilante inicializarVigilante(RepositorioRecibo repositorioRecibo , RepositorioVehiculo repositorioVehiculo) {
		List<ReglaIngreso> reglaIngresos = new ArrayList<>();
		reglaIngresos.add(new ReglaPlacasRestringidas());
		reglaIngresos.add(new ReglaCeldasCarroMaximo(repositorioRecibo));
		reglaIngresos.add(new ReglaCeldasMotoMaximo(repositorioRecibo));
		reglaIngresos.add(new ReglaNoIngresoMismoVehiculo(repositorioRecibo));  // En esta regla esta el error
		return new Vigilante(repositorioRecibo, repositorioVehiculo,reglaIngresos);
		
	}
}

		