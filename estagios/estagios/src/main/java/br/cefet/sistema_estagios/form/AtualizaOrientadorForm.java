package br.cefet.sistema_estagios.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.cefet.sistema_estagios.model.Orientador;
import br.cefet.sistema_estagios.repository.OrientadorRepository;

public class AtualizaOrientadorForm {
	@NotNull @NotEmpty @Length(min=3, max=300)
	private String nome;
	private String email;
	
	public AtualizaOrientadorForm(@NotNull @NotEmpty @Length(min = 3, max = 300) String nome, String email) {
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

	public Orientador update(Long id, OrientadorRepository orientadorRepository) {
		Orientador orientador = orientadorRepository.findById(id).get();
		orientador.setNome(nome);
		orientador.setEmail(email);
		
		return orientador;
	}
	
	

}
