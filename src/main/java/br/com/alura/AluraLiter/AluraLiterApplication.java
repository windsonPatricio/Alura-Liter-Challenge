package br.com.alura.AluraLiter;

import br.com.alura.AluraLiter.principal.Principal;
import br.com.alura.AluraLiter.repository.AutorRepository;
import br.com.alura.AluraLiter.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraLiterApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AutorRepository autorRepository;


	public static void main(String[] args) {
		SpringApplication.run(AluraLiterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(livroRepository, autorRepository );
		principal.exibeMenu();
	}
}
