package br.edu.ifce.springbootproject.services;

import br.edu.ifce.springbootproject.models.Disciplina;
import br.edu.ifce.springbootproject.models.Usuario;
import br.edu.ifce.springbootproject.models.enums.Raca;
import br.edu.ifce.springbootproject.repository.DisciplinaRepository;
import br.edu.ifce.springbootproject.repository.UsuarioRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Usuario salvar(Usuario usuario) {
        return repository.saveAndFlush(usuario);
    }

    public Usuario getUsuarioById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoResultException("Usuário não encontrado"));
    }

    public Usuario getUsuarioByEmail(String email) {
        return repository.findUsuarioByEmail(email).orElseThrow(() -> new NoResultException("Usuário não encontrado"));
    }

    public Usuario editar(Integer id, Usuario usuarioEditado) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NoResultException("Usuário não encontrado"));
        usuario.setNome(usuarioEditado.getNome());
        usuario.setEmail(usuarioEditado.getEmail());
        usuario.setCpf(usuarioEditado.getCpf());
        usuario.setDataNasc(usuarioEditado.getDataNasc());
        usuario.setEndereco(usuarioEditado.getEndereco());
        usuario.setTelefones(usuarioEditado.getTelefones());
        usuario.setDisciplinas(usuarioEditado.getDisciplinas());

        return repository.save(usuario);
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
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
