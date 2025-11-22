package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CursoController implements CursoIController {

    @Autowired
    private final CursoIService cursoIService;

    // Controller recebe o service no construtor
    public CursoController(CursoIService cursoService) {
        this.cursoIService = cursoService;
    }

    @Override
    public List<Curso> findAll() {
        return cursoIService.findAllCursos();
    }

    @Override
    public Curso save(Curso curso) {
        return cursoIService.saveCurso(curso);
    }

    @Override
    public Curso update(Curso curso) {
        return cursoIService.updateCurso(curso);
    }

    @Override
    public boolean delete(Long id) {
        return cursoIService.deleteCurso(id);
    }

    @Override
    public Curso findById(Long id) {
        return cursoIService.findCursoById(id);
    }

    @Override
    public List<Curso> findByName(String nome) {
        return cursoIService.findCursoByName(nome);
    }
}
