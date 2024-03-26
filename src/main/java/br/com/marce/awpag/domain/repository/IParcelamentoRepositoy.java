package br.com.marce.awpag.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marce.awpag.domain.model.Parcelamento;

public interface IParcelamentoRepositoy extends JpaRepository<Parcelamento, Long>{

}
