package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import java.util.List;

public class CursoController implements CursoIController {

    private final CursoIService cursoService;

    // Controller recebe o service no construtor
    public CursoController(CursoIService cursoService) {
        this.cursoService = cursoService;
    }

    @Override
    public List<Curso> findAll() {
        return cursoService.findAllCursos();
    }

    @Override
    public Curso save(Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @Override
    public Curso update(Curso curso) {
        return cursoService.updateCurso(curso);
    }

    @Override
    public boolean delete(Long id) {
        return cursoService.deleteCurso(id);
    }

    @Override
    public Curso findById(Long id) {
        return cursoService.findCursoById(id);
    }

    @Override
    public List<Curso> findByName(String nome) {
        return cursoService.findCursoByName(nome);
    }
}
