package br.cefet.sistema_estagios.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.cefet.sistema_estagios.model.Estagiario;
import br.cefet.sistema_estagios.repository.EstagiarioRepository;

public class AtualizaEstagiarioForm {

	@NotNull @NotEmpty @Length(min = 3, max= 300)
    private String nome;
    private String email;
	public AtualizaEstagiarioForm(@NotNull @NotEmpty @Length(min = 3, max = 300) String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public Estagiario update(Long id, EstagiarioRepository estagiarioRepository) {
	    Estagiario estagiario = estagiarioRepository.findById(id).get();
		estagiario.setNome(nome);
		estagiario.setEmail(email);
		return estagiario;
	}
    
    
}
