package br.com.ifba.infrastructure.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

// DAO genérico com métodos básicos de CRUD usando JPA
public abstract class GenericDao<T> implements GenericIDao<T> {

    // Gerenciador de entidades (JPA)
    protected EntityManager em;

    // Inicializa o EntityManager usando o persistence.xml
    public GenericDao() {
        em = Persistence.createEntityManagerFactory("prg03persistenciaPU")
                .createEntityManager();
    }

    // Salva um novo registro e retorna o objeto salvo
    @Override
    public T save(T entity) {
        em.getTransaction().begin();   // abre transação
        em.persist(entity);            // salva no banco
        em.getTransaction().commit();  // confirma
        return entity;                 // devolve o objeto salvo
    }

    // Atualiza um registro e retorna o objeto atualizado
    @Override
    public T update(T entity) {
        em.getTransaction().begin();
        entity = em.merge(entity);     // merge retorna a versão gerenciada
        em.getTransaction().commit();
        return entity;
    }

    // Remove um registro
    @Override
    public void delete(T entity) {
        em.getTransaction().begin();

        // Se o objeto não estiver gerenciado, faz merge antes de remover
        em.remove(em.contains(entity) ? entity : em.merge(entity));

        em.getTransaction().commit();
    }

    // Retorna todos os registros da tabela
    @Override
    public List<T> findAll() {
        String jpql = "FROM " + entityClass().getSimpleName();
        TypedQuery<T> query = em.createQuery(jpql, entityClass());
        return query.getResultList();
    }

    // As subclasses devem informar qual tipo de entidade representam
    protected abstract Class<T> entityClass();
}
