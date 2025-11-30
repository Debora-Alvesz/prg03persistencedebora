package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor; 
import lombok.AllArgsConstructor; 
import lombok.Data;

// Indica que essa classe é uma entidade JPA e será mapeada para uma tabela no banco
@Entity
// Define o nome da tabela no banco de dados como "cursos"
@Table(name = "cursos")
@NoArgsConstructor // ️ GERA O CONSTRUTOR VAZIO (essencial para JPA)
@AllArgsConstructor //  GERA O CONSTRUTOR COM TODOS OS CAMPOS
@Data

public class Curso extends PersistenceEntity {// Herdando o ID da classe PersistenceEntity

    // O ID (id) é herdado de PersistenceEntity. Não precisamos de anotação de acesso aqui 

    // Coluna "nome" na tabela, não pode ser nula
    @Column(name = "nome", nullable = false)
    private String nome;

    // Coluna "codigo_curso" na tabela, não pode ser nula e deve ser única
    @Column(name = "codigo_curso", nullable = false, unique = true)
    private String codigoCurso;

    // Coluna "coordenador" na tabela, não pode ser nula
    @Column(name = "coordenador", nullable = false)
    private String coordenador;

    // Coluna "ativo" na tabela, indica se o curso está ativo ou não
    @Column(name = "ativo")
    private boolean ativo;

    // Coluna "disponibilidade" na tabela, armazena a disponibilidade do curso
    @Column(name = "disponibilidade")
    private String disponibilidade;

}
