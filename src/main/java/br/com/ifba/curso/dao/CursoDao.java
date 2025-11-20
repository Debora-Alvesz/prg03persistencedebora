package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import javax.persistence.TypedQuery;
import java.util.List;

// DAO da entidade Curso
public class CursoDao extends GenericDao<Curso> implements CursoIDao {

    // Salva um novo curso
    @Override
    public Curso save(Curso curso) {
        // chama o método genérico
        return super.save(curso);
    }

    // Atualiza um curso existente
    @Override
    public Curso update(Curso curso) {
        // chama o método genérico
        return super.update(curso);
    }

    // Remove um curso
    @Override
    public void delete(Curso curso) {
        // chama o método genérico
        super.delete(curso);
    }

    // Busca curso pelo ID
    @Override
    public Curso findById(Long id) {
        return em.find(Curso.class, id);
    }

    // Retorna todos os cursos
    @Override
    public List<Curso> findAll() {
        TypedQuery<Curso> query =
            em.createQuery(
                "SELECT c FROM Curso c", Curso.class
            );
        return query.getResultList();
    }

    // Busca cursos pelo nome
    public List<Curso> findByName(String nome) {
        TypedQuery<Curso> query =
            em.createQuery(
                "SELECT c FROM Curso c WHERE c.nome LIKE :nome",
                Curso.class
            );
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    // Verifica se já existe um curso com o mesmo código
    @Override
    public boolean existsByCodigo(String codigo) {
        Long qtd = em.createQuery(
                "SELECT COUNT(c) FROM Curso c WHERE c.codigoCurso = :codigo",
                Long.class
        )
        .setParameter("codigo", codigo)
        .getSingleResult();

        return qtd > 0;
    }
    @Override
        protected Class<Curso> entityClass() {
            return Curso.class;
        }

}
