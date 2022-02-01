package br.cefet.sistema_estagios.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.cefet.sistema_estagios.model.Orientador;

public class OrientadorForm {
	
	@NotNull @NotEmpty
	private String matricula;
	@NotNull @NotEmpty @Length(min=3, max=300)
	private String nome;
	private String cargo;
    private String email;
    @NotNull @NotEmpty
	private String curso;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Orientador converter() {
		return new Orientador(matricula, nome, cargo, email, curso);
	}
	
	
}
