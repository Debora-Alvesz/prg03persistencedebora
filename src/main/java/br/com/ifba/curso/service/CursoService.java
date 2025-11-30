package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.repository.CursoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j // GERA: private static final Logger log = LoggerFactory.getLogger(CursoService.class);
@RequiredArgsConstructor
public class CursoService implements CursoIService {

//Declarado como 'final' para que o @RequiredArgsConstructor injete via construtor
private final CursoRepository cursoRepository;

// Salva um curso novo
@Transactional
@Override
public Curso saveCurso(Curso curso) {
   log.info("Iniciando processo de salvamento do curso. Nome: {}", curso.getNome());

        // REGRA DE NEGÓCIO 1: Verifica se os dados do curso estão preenchidos
        if (curso == null || curso.getNome() == null || curso.getNome().isEmpty() || curso.getCodigoCurso() == null || curso.getCodigoCurso().isEmpty()) {
            log.error("Erro de validação: Dados do curso inválidos ou incompletos."); // ⬅️ Log de Erro
            return null; // ou lançar uma exceção
        }

        // REGRA DE NEGÓCIO 2: Verifica se já existe um curso com o mesmo código
        if (cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())) {
            log.error("Erro de negócio: Já existe um curso com o código {}.", curso.getCodigoCurso()); // ⬅️ Log de Erro
            return null; // ou lançar uma exceção
        }

        // Se as regras de negócio forem atendidas, chama o DAO para persistir
        Curso savedCurso = cursoRepository.save(curso);
        log.info("Curso salvo com sucesso. ID: {}", savedCurso.getId()); // ⬅️ Log de Informação
        return savedCurso;
}

// Atualiza um curso
    @Override
    public Curso updateCurso(Curso curso) {
        log.info("Iniciando atualização do curso ID: {}", curso.getId());
        
        Optional<Curso> existente = cursoRepository.findById(curso.getId()); 

        // Verifica se o curso existe no banco
        if (existente.isEmpty()) { // Verifica se o Optional está vazio
            log.error("Falha na atualização: Curso ID {} não encontrado.", curso.getId()); // ⬅️ Log de Erro
            return null;
        }

        // Se existe, o método save() do JpaRepository faz o merge e atualiza.
        Curso updatedCurso = cursoRepository.save(curso);
        log.info("Curso ID {} atualizado com sucesso.", updatedCurso.getId());
        return updatedCurso;
    }

    // Remove um curso existente
    @Override
    public boolean deleteCurso(Long id) {
        log.warn("Tentativa de remoção do curso ID: {}", id); // ⬅️ Log de Aviso (WARN) para ação destrutiva

        Optional<Curso> existente = cursoRepository.findById(id); 

        // Se não existir, retorna falso
        if (existente.isEmpty()) {
            log.error("Falha na remoção: Curso ID {} não encontrado.", id); // ⬅️ Log de Erro
            return false;
        }

        cursoRepository.delete(existente.get()); 
        log.info("Curso ID {} removido com sucesso.", id);
        return true;
    }

    // Retorna todos os cursos cadastrados
    @Override
    public List<Curso> findAllCursos() {
        log.debug("Buscando todos os cursos."); // ⬅️ Log de Debug (útil para desenvolvimento)
        return cursoRepository.findAll();
    }

    // Busca um curso pelo ID
    @Override
    public Curso findCursoById(Long id) {
        log.debug("Buscando curso pelo ID: {}", id);
        return cursoRepository.findById(id).orElse(null);   
    }

    // Busca cursos pelo nome
    @Override
    public List<Curso> findCursoByName(String nome) {
        log.debug("Buscando cursos pelo nome: {}", nome);
        return cursoRepository.findByNomeContaining(nome);
    }
    // Verifica se já existe curso com o mesmo código
    @Override
    public boolean existeByCodigo(String codigo) {
        return cursoRepository.existsByCodigoCurso(codigo);
    }
 }

