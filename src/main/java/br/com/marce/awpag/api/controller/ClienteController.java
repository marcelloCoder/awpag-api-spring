package br.com.marce.awpag.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marce.awpag.domain.model.Cliente;


@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cli1 = new Cliente();
		cli1.setId(1L);
		cli1.setName("Marcello");
		cli1.setEmail("marcello@email.com");
		cli1.setTelefone("11111111");
		
		var cli2 = new Cliente();
		cli2.setId(2L);
		cli2.setName("Lucas");
		cli2.setEmail("lucas@email.com");
		cli2.setTelefone("22222222");
		
		return Arrays.asList(cli1,cli2);
		
	}

}
