package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Empresa;

@Controller
@RequestMapping("/afiliacion")
public class AfiliacionController {
	@RequestMapping("/")
	public String irAfiliacion(Model model) { 
		model.addAttribute("empresa", new Empresa());
		return "afiliacion";
	}
}
