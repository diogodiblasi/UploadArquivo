package br.com.senac.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.com.senac.entity.ArquivoModel;
import br.com.senac.model.ArquivoInfo;
import br.com.senac.service.ArquivoService;

@Controller
public class DownloadArquivoController {
	
	@Autowired
	ArquivoService arquivoService;
	
	
	@GetMapping("/files")
	public ModelAndView getListFiles() {	
		List<ArquivoInfo> fileInfos = arquivoService.buscarTodosArquivos().stream().map(
				fileModel -> {
					String filename = fileModel.getName();
					String url = MvcUriComponentsBuilder.fromMethodName(DownloadArquivoController.class, "downloadFile",
							fileModel.getName().toString()).build().toString();
					return new ArquivoInfo(filename, url);

				}
				).collect(Collectors.toList());
		
		ModelAndView mv = new ModelAndView("listaDeArquivos");
		mv.addObject("files", fileInfos);
		return mv;
	}
	
	@GetMapping("/files/{filename}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String filename){
		ArquivoModel file = arquivoService.buscarArquivoPorNome(filename);
		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"").body(file.getPic());
		
	}
	
}
