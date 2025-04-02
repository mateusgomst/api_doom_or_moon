package DoomOrMoon.api_doom_or_moon;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiDoomOrMoonApplication {

	public static void main(String[] args) {
		// Carregar variáveis do .env
		Dotenv dotenv = Dotenv.load();

		// Setar no ambiente para o Spring Boot reconhecer
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		// Iniciar a aplicação
		SpringApplication.run(ApiDoomOrMoonApplication.class, args);
	}
}
