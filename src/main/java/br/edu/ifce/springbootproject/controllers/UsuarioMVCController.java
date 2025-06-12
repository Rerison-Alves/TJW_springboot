package br.edu.ifce.springbootproject.controllers;

import br.edu.ifce.springbootproject.models.Usuario;
import br.edu.ifce.springbootproject.models.VO.UsuarioVO;
import br.edu.ifce.springbootproject.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.edu.ifce.springbootproject.util.HashPass.getStringFromSHA256;

@Controller
@RequestMapping("usuarios")
public class UsuarioMVCController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/auth")
    public String precadastro(Model model) {
        model.addAttribute("usuario", new UsuarioVO());
        return "usuarios/auth";
    }

    @PostMapping("/cadastro")
    public String cadastro(Model model,
                           UsuarioVO usuarioVO) {
        try {
            usuarioService.salvar(usuarioVO.getUsusario());
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "erro";
        }
    }

    @PostMapping("/{id}/editar")
    public String editarUsuario(@PathVariable Integer id,
                                Model model,
                                UsuarioVO usuarioVO) {
        try {
            usuarioService.editar(id, usuarioVO.getUsusario());
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "erro";
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluirUsuario(@PathVariable Integer id,
                                 Model model) {
        try {
            usuarioService.excluir(id);
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "erro";
        }
    }

    @PostMapping("/login")
    public String login(UsuarioVO usuarioVO,
                        Model model,
                        HttpSession session) {
        try {
            Usuario usuario = usuarioService.getUsuarioByEmail(usuarioVO.getEmail());
            if (getStringFromSHA256(usuarioVO.getPassword()).equals(usuario.getPassword())) {
                session.setAttribute("usuarioLogado", usuario.getEmail());
                return "redirect:/usuarios";
            }
            model.addAttribute("erro", "Email ou senha incorretos");
        } catch (Exception e) {
            model.addAttribute("erro", "Email ou senha incorretos");
            return "erro";
        }
        return "erro";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/usuarios/auth";
    }

    @GetMapping
    public String listarUsuarios(@RequestParam(value = "nome", required = false) String nome,
                                 @RequestParam(value = "email", required = false) String email,
                                 @RequestParam(value = "cpf", required = false) String cpf,
                                 @RequestParam(value = "raca", required = false) String raca,
                                 @RequestParam(value = "dtNascInit", required = false) String dtNascInit,
                                 @RequestParam(value = "dtNascFinal", required = false) String dtNascFinal,
                                 HttpSession session,
                                 Model model) {

        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/usuarios/auth";
        }

        nome = normalizeParam(nome);
        email = normalizeParam(email);
        cpf = normalizeParam(cpf);
        raca = normalizeParam(raca);
        dtNascInit = normalizeParam(dtNascInit);
        dtNascFinal = normalizeParam(dtNascFinal);

        model.addAttribute("nome", nome);
        model.addAttribute("email", email);
        model.addAttribute("cpf", cpf);
        model.addAttribute("raca", raca);
        model.addAttribute("dtNascInit", dtNascInit);
        model.addAttribute("dtNascFinal", dtNascFinal);

        List<Usuario> usuarios = usuarioService.findAllUsuarios(nome, email, cpf, raca, dtNascInit, dtNascFinal);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioVO", new UsuarioVO());

        return "usuarios/lista";
    }

    private String normalizeParam(String param) {
        return (param != null && !param.trim().isEmpty()) ? param.trim() : null;
    }
}
