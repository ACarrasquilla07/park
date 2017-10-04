package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"persistencia.repositorio","rest"})
@EntityScan("persistencia.entidad")
@SpringBootApplication
public class ParqueaderooApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderooApplication.class, args);
	}
}
