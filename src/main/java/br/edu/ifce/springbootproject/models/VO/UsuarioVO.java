package br.edu.ifce.springbootproject.models.VO;

import br.edu.ifce.springbootproject.models.Usuario;
import br.edu.ifce.springbootproject.models.enums.Raca;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static br.edu.ifce.springbootproject.util.HashPass.getStringFromSHA256;

public class UsuarioVO {
    private Integer id;
    private String nome;
    private String password;
    private String confirmPassword;
    private String cpf;
    private String email;
    private String raca;
    private String dataNascimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Usuario getUsusario() throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Usuario usuario = new Usuario();
            usuario.setNome(getNome());
            usuario.setEmail(getEmail());
            usuario.setCpf(getCpf());
            if (getPassword() != null) usuario.setPassword(getStringFromSHA256(getPassword()));
            usuario.setRaca(Raca.valueOf(getRaca()));
            usuario.setDataNasc(sdf.parse(getDataNascimento()));
            return usuario;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
