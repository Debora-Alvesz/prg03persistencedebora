package br.com.ifba.infrastructure.dao;

import java.util.List;

// Interface genérica para operações de CRUD (Create, Read, Update, Delete)
public interface GenericIDao<T> {
    
    // Salva um novo registro no banco
    void save(T entity);
    
    // Atualiza um registro existente
    void update(T entity);
    
    // Remove um registro do banco
    void delete(T entity);
    
    // Retorna todos os registros de uma tabela
    List<T> findAll();
}
