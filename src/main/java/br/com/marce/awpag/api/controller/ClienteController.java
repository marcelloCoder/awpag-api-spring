package br.com.marce.awpag.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.marce.awpag.domain.exception.NegocioException;
import br.com.marce.awpag.domain.model.Cliente;
import br.com.marce.awpag.domain.repository.IClienteRepository;
import br.com.marce.awpag.domain.service.CadastroClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	
	private final IClienteRepository cR;
	private final CadastroClienteService cS;
	
	@GetMapping
	public List<Cliente> listar() {
	
		return cR.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = cR.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
		
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {

		
		return cS.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
		if(!cR.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = cS.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		
		if(!cR.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cS.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
		
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<String> capturar(NegocioException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
