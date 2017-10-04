package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import dominio.Vigilante;
import dominio.reglas.ReglaCeldasCarroMaximo;
import dominio.reglas.ReglaIngreso;
import dominio.reglas.ReglaMotoAltoCilindraje;
import dominio.reglas.ReglaPlacasRestringidas;
import dominio.repositorio.RepositorioRecibo;
import dominio.repositorio.RepositorioVehiculo;

@Configuration
public class VigilanteConfig {
	@Bean 
	public Vigilante inicializarVigilante(RepositorioRecibo repositorioRecibo , RepositorioVehiculo repositorioVehiculo) {
		List<ReglaIngreso> reglaIngresos = new ArrayList<>();
		reglaIngresos.add(new ReglaMotoAltoCilindraje());
		reglaIngresos.add(new ReglaPlacasRestringidas());
		//reglaIngresos.add(new ReglaCeldasCarroMaximo(repositorioRecibo));
		return new Vigilante(repositorioRecibo, repositorioVehiculo,reglaIngresos);
		
	}
}

		