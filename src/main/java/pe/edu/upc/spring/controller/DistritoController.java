package pe.edu.upc.spring.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.service.IDistritoService;


@Controller
@RequestMapping("/distrito")
public class DistritoController {

	@Autowired
	private IDistritoService dService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoDistritos(Map<String, Object> model) {
		model.put("listaDistritos", dService.listar());
		return "#";
	}
	

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDistritos", dService.listar());
		return "listDistrito";
	}
}

