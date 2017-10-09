package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import dominio.Vigilante;
import dominio.reglas.ReglaIngreso;
import dominio.reglas.ReglaSalida;
import dominio.reglas.ingresoreglas.ReglaCeldasCarroMaximo;
import dominio.reglas.ingresoreglas.ReglaCeldasMotoMaximo;
import dominio.reglas.ingresoreglas.ReglaNoIngresoMismoVehiculo;
import dominio.reglas.ingresoreglas.ReglaPlacasRestringidas;
import dominio.reglas.salidareglas.ReglaCalculaPrecioPorCarro;
import dominio.reglas.salidareglas.ReglaCalculaPrecioPorMoto;
import dominio.reglas.salidareglas.ReglaMotoAltoCilindraje;
import dominio.repositorio.RepositorioRecibo;
import dominio.repositorio.RepositorioVehiculo;

@Configuration
public class VigilanteConfig {
	@Bean
	public Vigilante inicializarVigilante(RepositorioRecibo repositorioRecibo, RepositorioVehiculo repositorioVehiculo) {
		List<ReglaIngreso> reglaIngresos = agregarReglasIngreso(repositorioRecibo);
		List<ReglaSalida> reglaSalidas = agregarReglasSalida();
		return new Vigilante(repositorioRecibo, repositorioVehiculo, reglaIngresos, reglaSalidas);
	}

	private List<ReglaIngreso> agregarReglasIngreso(RepositorioRecibo repositorioRecibo) {
		List<ReglaIngreso> reglaIngresos = new ArrayList<>();
		reglaIngresos.add(new ReglaPlacasRestringidas());
		reglaIngresos.add(new ReglaCeldasCarroMaximo(repositorioRecibo));
		reglaIngresos.add(new ReglaCeldasMotoMaximo(repositorioRecibo));
		reglaIngresos.add(new ReglaNoIngresoMismoVehiculo(repositorioRecibo));
		return reglaIngresos;
	}

	private List<ReglaSalida> agregarReglasSalida() {
		List<ReglaSalida> reglaSalidas = new ArrayList<>();
		reglaSalidas.add(new ReglaCalculaPrecioPorCarro());
		reglaSalidas.add(new ReglaMotoAltoCilindraje());
		reglaSalidas.add(new ReglaCalculaPrecioPorMoto());
		return reglaSalidas;
	}
}
		