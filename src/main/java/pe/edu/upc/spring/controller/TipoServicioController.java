package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.service.ITipoServicioService;

@Controller
@RequestMapping("/TipoServicio")
public class TipoServicioController {
	@Autowired
	private ITipoServicioService rService;

	@RequestMapping("/tipoServicio")
	public String irPaginaBienvenida() {
		return "tipoServicio";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoServicio(Map<String, Object> model) {
		model.put("listaTipoServicios", rService.listar());
		return "listTipoServicio";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaTipoServicios", rService.listar());
		return "listTipoServicio";
	}
}
