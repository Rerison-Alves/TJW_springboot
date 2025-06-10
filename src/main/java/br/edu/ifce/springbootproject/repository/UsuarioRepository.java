package br.edu.ifce.springbootproject.repository;

import br.edu.ifce.springbootproject.models.Usuario;
import br.edu.ifce.springbootproject.models.enums.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUsuarioByEmail(String nome);

    @Query("""
        SELECT u FROM Usuario u
        WHERE (lower(u.nome) LIKE lower(concat('%', :nome, '%')) or :nome is null)
          AND (lower(u.email) LIKE lower(concat('%', :email, '%')) or :email is null)
          AND (:cpf is null or u.cpf = :cpf)
          AND (:raca is null or u.raca = :raca)
          AND (cast(:dtNascInit as date ) is null or u.dataNasc >= :dtNascInit)
          AND (cast(:dtNascFinal as date ) is null or u.dataNasc <= :dtNascFinal)
        """)
    List<Usuario> findAllUsuario(@Param("nome") String nome,
                                 @Param("email") String email,
                                 @Param("cpf") String cpf,
                                 @Param("raca") Raca raca,
                                 @Param("dtNascInit") Date dtNascInit,
                                 @Param("dtNascFinal") Date dtNascFinal);
}
