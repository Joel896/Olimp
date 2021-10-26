package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private IUsuarioService uService;
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Usuario objUsuario, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaUsuarios",uService.listar());
			return "usuario";
		}
		else {
			return "usuario";
		}
	}
}
