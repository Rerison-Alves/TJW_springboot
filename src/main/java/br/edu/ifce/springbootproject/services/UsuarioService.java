package br.edu.ifce.springbootproject.services;

import br.edu.ifce.springbootproject.models.Disciplina;
import br.edu.ifce.springbootproject.models.Usuario;
import br.edu.ifce.springbootproject.models.enums.Raca;
import br.edu.ifce.springbootproject.repository.DisciplinaRepository;
import br.edu.ifce.springbootproject.repository.UsuarioRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Usuario getUsuarioByNome(String nome) {
        return repository.findUsuarioByNome(nome).orElseThrow(() -> new NoResultException("Usuário não encontrado"));
    }

    public List<Usuario> getAllUsuarios() {
        return repository.findAll();
    }

    public List<Usuario> findAllUsuarios(String nome,
                                         String email,
                                         String cpf,
                                         String raca,
                                         String dtNascInit,
                                         String dtNascFinal) {

        Raca racaObj = null;
        if (raca!=null) racaObj = Raca.valueOf(raca);

        Date dtNascInitObj = null;
        if (dtNascInit != null) {
            try {
                dtNascInitObj = sdf.parse(dtNascInit);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Data Inicio Inválida");
            }
        }

        Date dtNascFinalObj = null;
        if (dtNascFinal != null) {
            try {
                dtNascFinalObj = sdf.parse(dtNascFinal);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Data Final Inválida");
            }
        }
        return repository.findAllUsuario(nome, email, cpf, racaObj, dtNascInitObj, dtNascFinalObj);
    }

    public void cadastroDisciplina(Integer idUsuario, Integer idDisciplina) {
        Usuario u = repository.findById(idUsuario).orElseThrow(() -> new NoResultException("Não encontrado"));
        Disciplina d = disciplinaRepository.findById(idDisciplina).orElseThrow(() -> new NoResultException("Não encontrado"));
        d.getUsuarios().add(u);
        disciplinaRepository.saveAndFlush(d);
    }

}
