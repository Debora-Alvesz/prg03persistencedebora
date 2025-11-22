package br.com.ifba.infrastructure.dao;

import java.util.List;

// Interface genérica para operações de CRUD (Create, Read, Update, Delete)
public interface GenericIDao<T> {

    // Salvar retorna o objeto salvo
    T save(T entity);

    // Atualizar retorna o objeto atualizado
    T update(T entity);

    // Deletar continua void
    void delete(T entity);

    // Buscar todos
    List<T> findAll();

    // Buscar por ID
    T findById(Long id);
}
