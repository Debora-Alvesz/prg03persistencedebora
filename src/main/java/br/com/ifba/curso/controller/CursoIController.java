package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

// Interface do Controller para manter padrão entre camadas
public interface CursoIController {

    // Retorna todos os cursos cadastrados
    List<Curso> findAll();

    // Salva um novo curso
    Curso save(Curso curso);

    // Atualiza um curso existente
    Curso update(Curso curso);

    // Remove um curso pelo ID
    boolean delete(Long id);

    // Busca um curso por ID
    Curso findById(Long id);

    // Busca cursos pelo nome
    List<Curso> findByName(String nome);
}
