package br.com.marce.awpag.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.marce.awpag.domain.exception.NegocioException;
import br.com.marce.awpag.domain.model.Cliente;
import br.com.marce.awpag.domain.repository.IClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroClienteService {
	
	private final IClienteRepository cR;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		
		boolean emailEmUso = cR.findByEmail(cliente.getEmail())
				.filter(c -> !c.equals(cliente))
				.isPresent();
		
		
		if(emailEmUso) {
			throw new NegocioException("Ja existe um cliente com este email cadastrado");
		}
		
		return cR.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		cR.deleteById(clienteId);
	}
	
	

}
