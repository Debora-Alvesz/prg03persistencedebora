package br.com.ifba.prg03springdebora;

import br.com.ifba.curso.view.CursoListar;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.ifba")
@EntityScan(basePackages = "br.com.ifba.curso.entity")
@EnableJpaRepositories(basePackages = "br.com.ifba.curso.repository")

public class Prg03springdeboraApplication {

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(Prg03springdeboraApplication.class);

        builder.headless(false);  // necessário para Swing

        ConfigurableApplicationContext context = builder.run(args);

       // Abre a tela principal ao terminar de subir o Spring
        CursoListar tela = context.getBean(CursoListar.class);
        tela.setVisible(true);
    }
}
