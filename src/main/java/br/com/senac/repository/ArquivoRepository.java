package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.entity.ArquivoModel;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivoModel, Long>{
	
	public ArquivoModel findByName(String name);
}
