package br.edu.ifce.springbootproject.controllers;

import br.edu.ifce.springbootproject.models.Usuario;
import br.edu.ifce.springbootproject.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/find")
    public ResponseEntity<List<Usuario>> findAllUsuarios(@RequestParam(value = "nome", required = false) String nome,
                                                         @RequestParam(value = "email", required = false) String email,
                                                         @RequestParam(value = "cpf", required = false) String cpf,
                                                         @RequestParam(value = "raca", required = false) String raca,
                                                         @RequestParam(value = "dtNascInit", required = false) String dtNascInit,
                                                         @RequestParam(value = "dtNascFinal", required = false) String dtNascFinal) {
        return ResponseEntity.ok().body(service.findAllUsuarios(nome, email, cpf, raca, dtNascInit, dtNascFinal));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(service.salvar(usuario));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(service.getUsuarioById(id));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/get-by-nome/{nome}")
    public ResponseEntity<Usuario> getUsuarioByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok().body(service.getUsuarioByEmail(nome));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable("id") Integer id,
                                                 @RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(service.editar(id, usuario));
    }
}
