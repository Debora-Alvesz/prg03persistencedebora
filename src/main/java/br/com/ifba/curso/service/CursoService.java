package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.repository.CursoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService implements CursoIService {

// Objeto
@Autowired
private CursoRepository cursoRepository;

// Recebe o Repository no construtor
public CursoService(CursoRepository cursoRepository) {
    this.cursoRepository = cursoRepository;
}

// Salva um curso novo
@Transactional
@Override
public Curso saveCurso(Curso curso) {
    // REGRA DE NEGÓCIO 1: Verifica se os dados do curso estão preenchidos
    if (curso == null || curso.getNome() == null || curso.getNome().isEmpty() || curso.getCodigoCurso() == null || curso.getCodigoCurso().isEmpty()) {
        System.out.println("Erro: Dados do curso inválidos ou incompletos.");
        return null; // ou lançar uma exceção
    }

    // REGRA DE NEGÓCIO 2: Verifica se já existe um curso com o mesmo código
    if (cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())) {
        System.out.println("Erro: Já existe um curso com o código " + curso.getCodigoCurso());
        return null; // ou lançar uma exceção
    }

    // Se as regras de negócio forem atendidas, chama o DAO para persistir
    return cursoRepository.save(curso);}

// Atualiza um curso
@Override
public Curso updateCurso(Curso curso) {
    // Com Spring Data JPA, findById retorna Optional<Curso>
    Optional<Curso> existente = cursoRepository.findById(curso.getId()); // <--- NOVO MÉTODO E RETORNO

    // Verifica se o curso existe no banco
    if (!existente.isPresent()) { // Verifica se o Optional está vazio
        System.out.println("Curso não encontrado para edição.");
        return null;
    }

    // Se existe, o método save() do JpaRepository faz o merge e atualiza.
    return cursoRepository.save(curso);
}

// Remove um curso existente
@Override
public boolean deleteCurso(Long id) {

    // Busca o curso pelo ID com Optional
    Optional<Curso> existente = cursoRepository.findById(id); // <--- NOVO MÉTODO E RETORNO

    // Se não existir, retorna falso
    if (!existente.isPresent()) {
        System.out.println("Curso não encontrado para remover.");
        return false;
    }

    // Se existe, remove usando o método delete(T entity) do JpaRepository
    cursoRepository.delete(existente.get()); // <--- Método delete() do JpaRepository
    return true;
}

// Retorna todos os cursos cadastrados
@Override
public List<Curso> findAllCursos() {
    return cursoRepository.findAll();
}

// Busca um curso pelo ID
@Override
public Curso findCursoById(Long id) {
    return cursoRepository.findById(id).orElse(null); 
}

// Busca cursos pelo nome
@Override
public List<Curso> findCursoByName(String nome) {
    return cursoRepository.findByNomeContaining(nome);
}
// Verifica se já existe curso com o mesmo código
@Override
public boolean existeByCodigo(String codigo) {
    return cursoRepository.existsByCodigoCurso(codigo);
}

}
