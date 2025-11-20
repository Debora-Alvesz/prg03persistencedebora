package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

// Interface que define as operações que o Service deve oferecer
public interface CursoIService {

// Salva um novo curso
Curso saveCurso(Curso curso);

// Atualiza um curso existente
Curso updateCurso(Curso curso);

// Remove um curso pelo ID, retornando sucesso ou falha
boolean deleteCurso(Long id);

// Lista todos os cursos
List<Curso> findAllCursos();

// Busca curso pelo ID
Curso findCursoById(Long id);

// Busca cursos pelo nome
List<Curso> findCursoByName(String nome);
}
