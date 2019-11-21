package br.com.feedev.jdevtreinamento.jdevtreinamentospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.model") // talvez não necessário na versão mais recente do SB
public class JdevtreinamentoSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdevtreinamentoSpringbootApplication.class, args);
	}

}
