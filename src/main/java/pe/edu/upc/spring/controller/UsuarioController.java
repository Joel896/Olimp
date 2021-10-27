package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private IUsuarioService uService;
	
	//Páginas
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listaUsuarios", uService.listar());
		return "registro";
	}
	
	@RequestMapping("/irLogin")
	public String irPaginaLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listaUsuarios", uService.listar());
		return "login";
	}
	
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Usuario objUsuario, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaUsuarios",uService.listar());
			return "registro";
		}
		else {
			boolean flag = uService.registrar(objUsuario);
			if(flag) {
				return "redirect:/usuario/irLogin";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/usuario/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable String dniUsuario, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Usuario> objPet = uService.buscarId(dniUsuario);
		if (objPet == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/servicio/inicio"; //redirige al panel (request) "usuario/perfil"
		}
		else {
			model.addAttribute("listaUsuarios", uService.listar());
					
			if (objPet.isPresent())
				objPet.ifPresent(o -> model.addAttribute("usuario", o));
			
			return "usuario";
		}
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUsuarios", uService.listar());
		return "listUsuario";
	}		
	
	@RequestMapping("/listarId")
	public String buscarId(Map<String, Object> model, @ModelAttribute Usuario usuario) 
	throws ParseException
	{
		uService.buscarId(usuario.getDniUsuario());
		return "usuario";
	}	
	
}
