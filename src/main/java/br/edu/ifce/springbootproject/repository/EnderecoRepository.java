package br.edu.ifce.springbootproject.repository;

import br.edu.ifce.springbootproject.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
