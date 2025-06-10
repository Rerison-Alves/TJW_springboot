package br.edu.ifce.springbootproject.controllers;

import br.edu.ifce.springbootproject.models.Usuario;
import br.edu.ifce.springbootproject.models.VO.UsuarioVO;
import br.edu.ifce.springbootproject.models.enums.Raca;
import br.edu.ifce.springbootproject.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Controller
@RequestMapping("usuarios")
public class UsuarioMVCController {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/auth")
    public String precadastro(Model model) {
        model.addAttribute("usuario", new UsuarioVO());
        return "usuarios/auth";
    }

    @PostMapping("/cadastro")
    public String cadastro(UsuarioVO usuarioVO) throws ParseException {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioVO.getNome());
        usuario.setEmail(usuarioVO.getEmail());
        usuario.setCpf(usuarioVO.getCpf());
        usuario.setPassword(passwordEncoder.encode(usuarioVO.getPassword()));
        usuario.setRaca(Raca.valueOf(usuarioVO.getRaca()));
        usuario.setDataNasc(sdf.parse(usuarioVO.getDataNascimento()));
        usuarioService.salvar(usuario);
        return "index";
    }

    @PostMapping("/login")
    public String login(UsuarioVO usuarioVO) {
        Usuario usuario = usuarioService.getUsuarioByEmail(usuarioVO.getEmail());
        if (passwordEncoder.encode(usuarioVO.getPassword()).equals(usuario.getPassword())) {
            return "index";
        } else {
            throw new IllegalStateException("Credenciais inválidas. Verifique seu email e senha.");
        }
    }
}
