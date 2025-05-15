package br.edu.ifce.springbootproject.repository;

import br.edu.ifce.springbootproject.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

}
