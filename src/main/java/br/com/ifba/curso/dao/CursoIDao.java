package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericIDao;
import java.util.List;

// Interface do DAO de Curso

  public interface CursoIDao extends GenericIDao<Curso> {

      // Verifica se já existe um curso com o código informado
      boolean existsByCodigo(String codigo);
      // Buscar cursos pelo nome
      List<Curso> findByName(String nome);

}
