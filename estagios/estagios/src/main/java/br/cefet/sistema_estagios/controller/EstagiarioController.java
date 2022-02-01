package br.cefet.sistema_estagios.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.cefet.sistema_estagios.dto.EstagiarioDTO;
import br.cefet.sistema_estagios.form.AtualizaEstagiarioForm;
import br.cefet.sistema_estagios.form.EstagiarioForm;
import br.cefet.sistema_estagios.model.Estagiario;
import br.cefet.sistema_estagios.repository.EstagiarioRepository;
import io.swagger.annotations.ApiOperation;

//API do Estagiario
@RestController

@RequestMapping("/estagiarios")
public class EstagiarioController {
	
	@Autowired
	private EstagiarioRepository estagiarioRepository;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@ApiOperation(value = "Retorna uma lista de estagiários do banco de dados.")
	@GetMapping
	public List<EstagiarioDTO> estagiarios()
	{
		List<Estagiario> tabelaEstagiarios = estagiarioRepository.findAll();					
		return EstagiarioDTO.converter(tabelaEstagiarios);
	}
	
	@ApiOperation(value = "Cadastra um novo estagiário no banco de dados.")
	@PostMapping
	public ResponseEntity<EstagiarioDTO> cadastrar(@RequestBody @Valid EstagiarioForm estagiarioForm,
																		UriComponentsBuilder uriBuilder)
	{
		Estagiario estagiario = estagiarioForm.converter();
		estagiarioRepository.save(estagiario);
		//HTTPStatus 200 : sucesso  HTTPStatus 201: created (um recurso foi criado -> um estagiário)   
		UriComponents uriComponents =
								uriBuilder.path("/estagiarios/{id}").buildAndExpand(estagiario.getId());
		//retorna HTTPStatus 201 (created) e no corpo da resposta manda o json do EstagiarioDTO
		return ResponseEntity.created(uriComponents.toUri()).body(new EstagiarioDTO(estagiario)); 
		
	}
	
	@ApiOperation(value = "Atualiza dados de um estagiário já cadastrado no banco de dados.")
	@PutMapping("/{id}")
	//@Transactional
	public ResponseEntity<EstagiarioDTO> atualizar(@PathVariable Long id, 
			@RequestBody @Valid AtualizaEstagiarioForm estagiarioForm)
	{
		Estagiario  estagiario = estagiarioForm.update(id, estagiarioRepository);
		estagiarioRepository.save(estagiario);
		//ok : retorna HTTPStatus = 200
		return ResponseEntity.ok(new EstagiarioDTO(estagiario));		
	}
	
	@ApiOperation(value = "Deleta um estagiário do banco de dados.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id)
	{
		estagiarioRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
