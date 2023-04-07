package br.com.senac.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.entity.ArquivoModel;
import br.com.senac.service.ArquivoService;

@Controller
public class UploadArquivoController {

	@Autowired
	ArquivoService arquivoService;

	@GetMapping("/")
	public String index() {
		return "uploadform";

	}
	
	@PostMapping("/")
	public ModelAndView uploadMultipartFile(@RequestParam("files") MultipartFile[] files) {
		ModelAndView mv = new ModelAndView("uploadform");

		List<String> fileNames = new ArrayList<String>();

		List<ArquivoModel> storedFile = new ArrayList<>();
		try {
			for (MultipartFile file : files) {
				ArquivoModel fileModel = arquivoService.buscarArquivoPorNome(file.getOriginalFilename());
				if (fileModel != null) {
					fileModel.setPic(file.getBytes());
				} else {
					fileModel = new ArquivoModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
				}
				fileNames.add(file.getOriginalFilename());
				storedFile.add(fileModel);

			}
			arquivoService.saveFiles(storedFile);
			mv.addObject("message", "Arquivos uploaded sucessfully!");
			mv.addObject("files", fileNames);

		} catch (Exception e) {
			mv.addObject("files", "Failed Process");

		}
		return mv;
	}
}
