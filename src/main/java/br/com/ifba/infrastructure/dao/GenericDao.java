package br.com.ifba.infrastructure.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public abstract class GenericDao<T> implements GenericIDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        entityManager.remove(
            entityManager.contains(entity) ? entity : entityManager.merge(entity)
        );
    }

    @Override
    public List<T> findAll() {
        String jpql = "FROM " + entityClass().getSimpleName();
        TypedQuery<T> query = entityManager.createQuery(jpql, entityClass());
        return query.getResultList();
    }

    protected abstract Class<T> entityClass();
}
