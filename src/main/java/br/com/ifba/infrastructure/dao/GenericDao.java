package br.com.ifba.infrastructure.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

// Classe genérica que faz o CRUD funcionar de verdade usando o JPA
public class GenericDao<T> implements GenericIDao<T> {

    // Gerenciador de entidades — é ele que fala com o banco de dados
    protected EntityManager em;

    // Construtor: cria o EntityManager com base no persistence.xml
    public GenericDao() {
        em = Persistence.createEntityManagerFactory("prg03persistenciaPU").createEntityManager();
    }

    // Método para salvar um novo registro no banco
    @Override
    public void save(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    // Método para atualizar um registro existente
    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    // Método para deletar um registro
    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity)); // se não estiver no contexto, faz merge
        em.getTransaction().commit();
    }

    // Método que retorna todos os registros da tabela
    @Override
    public List<T> findAll() {
        String jpql = "from " + entityClass().getSimpleName();
        TypedQuery<T> query = em.createQuery(jpql, entityClass());
        return query.getResultList();
    }

    // Método auxiliar — as classes filhas devem dizer qual é a entidade que representam
    protected Class<T> entityClass() {
        throw new UnsupportedOperationException("Defina entityClass() na subclasse!");
    }
}
