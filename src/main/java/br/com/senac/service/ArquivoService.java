package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.ArquivoModel;
import br.com.senac.repository.ArquivoRepository;

@Service
public class ArquivoService {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	
	
	public ArquivoModel buscarArquivoPorNome(String nome) {
		return arquivoRepository.findByName(nome);
		
	}
	
	public void saveFiles(List<ArquivoModel> files) {
		arquivoRepository.saveAll(files);
		
	}
	
	public List<ArquivoModel> buscarTodosArquivos(){
		return arquivoRepository.findAll();
		
	}
}
