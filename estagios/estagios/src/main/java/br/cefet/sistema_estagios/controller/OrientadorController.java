package br.cefet.sistema_estagios.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.cefet.sistema_estagios.dto.EstagiarioDTO;
import br.cefet.sistema_estagios.dto.OrientadorDTO;
import br.cefet.sistema_estagios.form.AtualizaOrientadorForm;
import br.cefet.sistema_estagios.form.OrientadorForm;
import br.cefet.sistema_estagios.model.Orientador;
import br.cefet.sistema_estagios.repository.OrientadorRepository;

@RestController
@RequestMapping("/orientadores")
public class OrientadorController {
	
	@Autowired
	private OrientadorRepository orientadorRepository;
	
	@GetMapping	
	public List<OrientadorDTO> orientadores()
	{
		List<Orientador> orientadores = orientadorRepository.findAll();
		return OrientadorDTO.converter(orientadores);
	}

	@PostMapping
	public ResponseEntity<OrientadorDTO> cadastrar(@RequestBody @Valid OrientadorForm orientadorForm,
			UriComponentsBuilder uriBuilder)
	{
		Orientador orientador = orientadorForm.converter();
		orientadorRepository.save(orientador);
		
		UriComponents uriComponent = uriBuilder.path("/orientadores/{id}").buildAndExpand(orientador.getId());
		
		return ResponseEntity.created(uriComponent.toUri()).body(new OrientadorDTO(orientador));
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<OrientadorDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizaOrientadorForm orientadorForm)
	{
		Orientador orientador = orientadorForm.update(id, orientadorRepository);
		return ResponseEntity.ok(new OrientadorDTO(orientador));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id)
	{
		orientadorRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
