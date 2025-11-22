package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

// Classe DAO responsável por acessar o banco para a entidade Curso.
// Ela herda de GenericDao, que já tem os métodos básicos (save, update, delete, findAll),
// evitando repetição de código.
@Repository
public class CursoDao extends GenericDao<Curso> implements CursoIDao {

// Informa ao GenericDao qual é a classe de entidade manipulada.
// Obrigatório quando herdamos GenericDao.
@Override
protected Class<Curso> entityClass() {
    return Curso.class;
}

// SALVAR (CREATE)
// Apenas chama a implementação genérica do pai "GenericDao"
@Override
public Curso save(Curso curso) {
    return super.save(curso);
}

// ATUALIZAR (UPDATE)
// Também delega ao método da classe pai
@Override
public Curso update(Curso curso) {
    return super.update(curso);
}

// REMOVER (DELETE)
// Apenas chama o genérico
@Override
public void delete(Curso curso) {
    super.delete(curso);
}

// BUSCAR POR ID (READ)
// Agora usa "entityManager" herdado do GenericDao
@Override
public Curso findById(Long id) {
    return entityManager.find(Curso.class, id);
}

// BUSCAR TODOS
// Executa consulta JPQL para listar todos os cursos cadastrados
@Override
public List<Curso> findAll() {
    TypedQuery<Curso> query =
        entityManager.createQuery(
            "SELECT c FROM Curso c", Curso.class
        );
    return query.getResultList();
}

// BUSCAR POR NOME (LIKE)
// Procura cursos contendo parte do texto informado

@Override
public List<Curso> findByName(String nome) {
    TypedQuery<Curso> query =
        entityManager.createQuery(
            "SELECT c FROM Curso c WHERE c.nome LIKE :nome",
            Curso.class
        );
    query.setParameter("nome", "%" + nome + "%");
    return query.getResultList();
}

// VERIFICAR SE JÁ EXISTE CURSO COM O MESMO CÓDIGO
// Útil para validação antes de salvar
@Override
public boolean existsByCodigo(String codigo) {
    Long qtd = entityManager.createQuery(
            "SELECT COUNT(c) FROM Curso c WHERE c.codigoCurso = :codigo",
            Long.class
    )
    .setParameter("codigo", codigo)
    .getSingleResult();

    return qtd > 0; // retorna true se já existe
}
}
