package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.entity.Curso;
import java.util.List;

public class CursoService implements CursoIService {

// DAO que executará as operações no banco
private final CursoIDao cursoDao;

// Recebe o DAO no construtor
public CursoService(CursoIDao cursoDao) {
    this.cursoDao = cursoDao;
}

// Salva um curso novo
@Override
public Curso saveCurso(Curso curso) {
    return cursoDao.save(curso);
}

// Atualiza um curso
@Override
public Curso updateCurso(Curso curso) {

    // Verifica se o curso existe no banco
    Curso existente = cursoDao.findById(curso.getId());
    if (existente == null) {
        // Caso não exista, retorna nulo ou lança erro
        System.out.println("Curso não encontrado para edição.");
        return null;
    }

    // Se existe, então atualiza
    return cursoDao.update(curso);
}

// Remove um curso existente
@Override
public boolean deleteCurso(Long id) {

    // Busca o curso pelo ID
    Curso existente = cursoDao.findById(id);

    // Se não existir, retorna falso
    if (existente == null) {
        System.out.println("Curso não encontrado para remover.");
        return false;
    }

    // Se existe, remove
    cursoDao.delete(existente);
    return true;
}

// Retorna todos os cursos cadastrados
@Override
public List<Curso> findAllCursos() {
    return cursoDao.findAll();
}

// Busca um curso pelo ID
@Override
public Curso findCursoById(Long id) {
    return cursoDao.findById(id);
}

// Busca cursos pelo nome
@Override
public List<Curso> findCursoByName(String nome) {
    return cursoDao.findByName(nome);
}

}
