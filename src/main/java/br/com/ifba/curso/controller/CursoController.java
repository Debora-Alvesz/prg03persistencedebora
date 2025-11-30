package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@Slf4j //gera o objeto log para rastrear requisições
@RequiredArgsConstructor //gera o construtor automaticamente

public class CursoController implements CursoIController {

    private final CursoIService cursoIService;

   @Override
    public List<Curso> findAll() {
        log.info("Requisição recebida: Buscar todos os cursos."); // ⬅️ Log de Informação
        return cursoIService.findAllCursos();
    }

    @Override
    public Curso save(Curso curso) {
        log.info("Requisição POST: Salvar novo curso. Nome: {}", curso.getNome()); // ⬅️ Log de Informação
        Curso novoCurso = cursoIService.saveCurso(curso);
        // O log de sucesso ou falha já deve estar na camada Service, mas um log aqui é útil.
        log.debug("Novo curso salvo com ID: {}", novoCurso.getId());
        return novoCurso;
    }

    @Override
    public Curso update(Curso curso) {
        log.info("Requisição PUT: Atualizar curso ID: {}", curso.getId());
        return cursoIService.updateCurso(curso);
    }

    @Override
    public boolean delete(Long id) {
        log.warn("Requisição DELETE: Tentativa de remover curso ID: {}", id); // ⬅️ Log de Aviso (Ação destrutiva)
        return cursoIService.deleteCurso(id);
    }

    @Override
    public Curso findById(Long id) {
        log.debug("Requisição GET: Buscar curso pelo ID: {}", id);
        return cursoIService.findCursoById(id);
    }

    @Override
    public List<Curso> findByName(String nome) {
        log.debug("Requisição GET: Buscar cursos pelo nome que contenha: {}", nome);
        return cursoIService.findCursoByName(nome);
    }
}
