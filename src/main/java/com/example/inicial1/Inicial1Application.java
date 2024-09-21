package com.example.inicial1;

import com.example.inicial1.entities.Domicilio;
import com.example.inicial1.entities.Localidad;
import com.example.inicial1.entities.Persona;
import com.example.inicial1.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Inicial1Application {
private static final Logger logger = LoggerFactory.getLogger(Inicial1Application.class);

@Autowired
private PersonaRepository personaRepository;
public static void main(String[] args) {
	SpringApplication.run(Inicial1Application.class, args);

	System.out.println("funcionando");
}

@Bean
@Transactional
CommandLineRunner init(PersonaRepository personaRepository) {
	return args -> {
				// Creo un objeto persona
				Persona per1 = Persona.builder()
						.nombre("Edgardo")
						.apellido("Beiza")
						.dni(3245622)
						.build();

				Localidad local1 = Localidad.builder()
				.denominacion("Maipu")
				.build();

				Domicilio dom1 = Domicilio.builder()
						.calle("Moron")
						.numero(1378)
						.build();

				dom1.setLocalidad(local1);
				per1.setDomicilio(dom1);

				personaRepository.save(per1);

				// Creo otra persona
						Persona per2 = Persona.builder().
								nombre("Alicia").apellido("Calderon")
								.dni(6523655).
								build();

						Localidad local2 = Localidad.builder()
								.denominacion("Lujan")
								.build();
						Domicilio dom2 = Domicilio.builder().
								calle("Lulunta").
								numero(339).build();

						dom2.setLocalidad(local2);
						per2.setDomicilio(dom2);


						// Lo grabo a través del repositorio de Spring
						personaRepository.save(per2);

						List<Persona> recuperadas = personaRepository.findAll();
						System.out.println(recuperadas);

						logger.info("Detalles de la persona: {}", recuperadas);




						Optional<Persona> recuperada = personaRepository.findById(1L);
						System.out.println(recuperada);

						logger.info("Detalles de la persona: {}", recuperada);


						dom1.setCalle("Rodriguezaaaa");

						personaRepository.save(per1);




	};

	};




}
