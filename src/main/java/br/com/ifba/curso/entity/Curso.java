package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Indica que essa classe é uma entidade JPA e será mapeada para uma tabela no banco
@Entity
// Define o nome da tabela no banco de dados como "cursos"
@Table(name = "cursos")
public class Curso extends PersistenceEntity { // Herdando o ID da classe PersistenceEntity

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

    // ----- Getters e Setters -----

    // Retorna o nome do curso
    public String getNome() {
        return nome;
    }

    // Define o nome do curso
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o código do curso
    public String getCodigoCurso() {
        return codigoCurso;
    }

    // Define o código do curso
    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    // Retorna o coordenador do curso
    public String getCoordenador() {
        return coordenador;
    }

    // Define o coordenador do curso
    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    // Retorna se o curso está ativo
    public boolean isAtivo() {
        return ativo;
    }

    // Define se o curso está ativo
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Retorna a disponibilidade do curso
    public String getDisponibilidade() {
        return disponibilidade;
    }

    // Define a disponibilidade do curso
    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
