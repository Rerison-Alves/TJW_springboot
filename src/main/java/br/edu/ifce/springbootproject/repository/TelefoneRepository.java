package br.edu.ifce.springbootproject.repository;

import br.edu.ifce.springbootproject.models.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
}
