package br.cefet.sistema_estagios.config.validation;

public class InputDataErrorDTO {
	
	private String campo;
	private String mensagemErro;
	
	
	public InputDataErrorDTO(String campo, String mensagemErro) {
		super();
		this.campo = campo;
		this.mensagemErro = mensagemErro;
	}
	
	public String getCampo() {
		return campo;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	
	

}
