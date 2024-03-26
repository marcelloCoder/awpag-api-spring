package br.com.marce.awpag.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marce.awpag.domain.model.Parcelamento;
import br.com.marce.awpag.domain.repository.IParcelamentoRepositoy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

	private final IParcelamentoRepositoy pR;
	
	public List<Parcelamento> listar() {
		
		return pR.findAll();
	}
	
	@GetMapping("/{parcelamentoId}")
	public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId) {
		Optional<Parcelamento> p = pR.findById(parcelamentoId);
		
		if(p.isPresent()) {
			return ResponseEntity.ok(p.get());
		}
		return ResponseEntity.notFound().build();
		
	}
}
