package br.edu.ifce.springbootproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    @Column(nullable = false)
    private String numero;

    private Boolean isWpp;

    private Boolean isPrincipal;

    public Telefone() {
    }

    public Telefone(Usuario usuario, String numero, Boolean isWpp, Boolean isPrincipal) {
        this.usuario = usuario;
        this.numero = numero;
        this.isWpp = isWpp;
        this.isPrincipal = isPrincipal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Boolean getWpp() {
        return isWpp;
    }

    public void setWpp(Boolean wpp) {
        isWpp = wpp;
    }

    public Boolean getPrincipal() {
        return isPrincipal;
    }

    public void setPrincipal(Boolean principal) {
        isPrincipal = principal;
    }
}
