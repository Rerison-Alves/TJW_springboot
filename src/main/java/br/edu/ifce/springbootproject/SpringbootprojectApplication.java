package br.edu.ifce.springbootproject;

import br.edu.ifce.springbootproject.models.Disciplina;
import br.edu.ifce.springbootproject.models.Endereco;
import br.edu.ifce.springbootproject.models.Telefone;
import br.edu.ifce.springbootproject.models.Usuario;
import br.edu.ifce.springbootproject.models.enums.Raca;
import br.edu.ifce.springbootproject.repository.DisciplinaRepository;
import br.edu.ifce.springbootproject.repository.EnderecoRepository;
import br.edu.ifce.springbootproject.repository.UsuarioRepository;
import br.edu.ifce.springbootproject.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringbootprojectApplication implements CommandLineRunner {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	DisciplinaRepository disciplinaRepository;

	@Autowired
	UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Usuario u = new Usuario();
//		u.setNome("Israel");
//		u.setCpf("00000000000");
//		u.setEmail("pizzariadodoido@gmail.com");
//		u.setDataNasc(new SimpleDateFormat("dd/MM/yyyy").parse("08/06/2003"));
//		Endereco endereco = new Endereco(
//				"Av. Paulista", "1000", "Apto 101",
//				"Bela Vista", "São Paulo", "SP", "01310-100", "Brasil"
//		);
//		u.setEndereco(endereco);
//		endereco.setUsuario(u);
//
//		Telefone t = new Telefone(u, "988380012", true, true);
//		List<Telefone> telefoneList = new ArrayList<>();
//		telefoneList.add(t);
//
//		u.setTelefones(telefoneList);
//
//		repository.saveAndFlush(u);
//
//		Disciplina disciplina = new Disciplina();
//		disciplina.setAno("2025");
//		disciplina.setNome("Java");
//		disciplina.setPeriodo("Tarde");
//		disciplina.setProfessor("Rerison");
//
//		disciplinaRepository.saveAndFlush(disciplina);
//
//		usuarioService.cadastroDisciplina(u.getId(), disciplina.getId());
//
//		repository.findById(u.getId());
		List<Usuario> usuarios = new ArrayList<>();

		usuarios.add(new Usuario("Ana Silva", "ana.silva@email.com", "12345678900", Raca.BRANCA, createDate(1990, 5, 10)));
		usuarios.add(new Usuario("Bruno Souza", "bruno.souza@email.com", "98765432111", Raca.PRETA, createDate(1985, 8, 20)));
		usuarios.add(new Usuario("Carla Pereira", "carla.pereira@email.com", "11122233344", Raca.PARDA, createDate(1992, 3, 15)));
		usuarios.add(new Usuario("Daniel Costa", "daniel.costa@email.com", "55566677788", Raca.AMARELA, createDate(1988, 12, 5)));
		usuarios.add(new Usuario("Eduardo Lima", "eduardo.lima@email.com", "99988877766", Raca.INDIGENA, createDate(1995, 7, 25)));
		usuarios.add(new Usuario("Fernanda Melo", "fernanda.melo@email.com", "22233344455", Raca.BRANCA, createDate(1991, 11, 30)));
		usuarios.add(new Usuario("Gustavo Rocha", "gustavo.rocha@email.com", "44455566677", Raca.PRETA, createDate(1983, 4, 18)));
		usuarios.add(new Usuario("Helena Martins", "helena.martins@email.com", "77788899900", Raca.PARDA, createDate(1994, 9, 22)));
		usuarios.add(new Usuario("Igor Fernandes", "igor.fernandes@email.com", "33344455566", Raca.AMARELA, createDate(1987, 6, 3)));
		usuarios.add(new Usuario("Júlia Alves", "julia.alves@email.com", "66677788899", Raca.INDIGENA, createDate(1993, 1, 12)));

		repository.saveAll(usuarios);
	}

	private static Date createDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, 0, 0, 0); // mês começa em 0 no Calendar
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

}
