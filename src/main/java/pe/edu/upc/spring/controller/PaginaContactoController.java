package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class PaginaContactoController {
	@RequestMapping("/")
	public String irContacto() { return "contacto";}
}
