
package br.com.ifba.curso.repository;

import br.com.ifba.curso.entity.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    public List<Curso> findByNome(String nome);
    
    // Regra de Negócio: Verifica se já existe um curso com o código.
    public boolean existsByCodigoCurso(String codigo);
    //Usa 'Nome' (o nome da propriedade) + 'Containing'
    public List<Curso> findByNomeContaining(String nome);
}
