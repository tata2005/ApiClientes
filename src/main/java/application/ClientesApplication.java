package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta anotação marca a classe principal da aplicação Spring Boot.
// Ela combina várias outras anotações (@Configuration, @EnableAutoConfiguration, @ComponentScan)
// e permite que o Spring configure tudo automaticamente.
@SpringBootApplication
public class ClientesApplication {

    /**
     * Este é o ponto de entrada da aplicação.
     * O método main é executado quando você inicia o projeto.
     */
    public static void main(String[] args) {
        // Este comando inicia a aplicação Spring Boot.
        // Ele configura o contexto da aplicação, carrega os beans e inicia o servidor web (como Tomcat).
        SpringApplication.run(ClientesApplication.class, args);
    }
}

