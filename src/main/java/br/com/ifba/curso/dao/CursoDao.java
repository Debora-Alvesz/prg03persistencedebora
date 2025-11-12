package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;

// Implementa o DAO específico para Curso
public class CursoDao extends GenericDao<Curso> implements CursoIDao {

    // Informa à classe genérica qual entidade ela deve usar (Curso)
    @Override
    protected Class<Curso> entityClass() {
        return Curso.class;
    }
    
    public boolean existsByCodigo(String codigo) {
    try {
        String jpql = "SELECT COUNT(c) FROM Curso c WHERE c.codigoCurso = :codigo";
        Long count = em.createQuery(jpql, Long.class)
                       .setParameter("codigo", codigo)
                       .getSingleResult();
        return count != null && count > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false; 
     }
    }
}
    
