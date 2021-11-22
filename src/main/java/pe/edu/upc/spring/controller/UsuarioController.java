package pe.edu.upc.spring.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Rol;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private IUsuarioService uService;
	@Autowired 
	private PasswordEncoder encoder;

	//CRUD
	@RequestMapping("/cliente")
	public String actualizarCliente(@ModelAttribute Usuario objUsuario,
			BindingResult binRes, Model model, Principal logeado) throws ParseException{
		if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			List<Rol> lst = new ArrayList<Rol>(); lst.add(new Rol(0,"ROLE_CLIENTE")); 
			objUsuario.setEnabled(true); objUsuario.setRoles(lst); //Atributos
			objUsuario.setContrasenia(encoder.encode(objUsuario.getContrasenia())); //encriptar
			boolean flag = uService.registrar(objUsuario);
			if (flag) return "redirect:/panel/";
			else model.addAttribute("mensaje", "Ocurrio un error");
		}
		return "/panel/";
	}	
	@RequestMapping("/sucursal")
	public String actualizarSucursal(@ModelAttribute Usuario objUsuario,
			BindingResult binRes, Model model, Principal logeado) throws ParseException{
		if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			List<Rol> lst = new ArrayList<Rol>(); lst.add(new Rol(0,"ROLE_SUCURSAL")); 
			objUsuario.setEnabled(true); objUsuario.setRoles(lst); //Atributos
			objUsuario.setContrasenia(encoder.encode(objUsuario.getContrasenia())); //encriptar
			boolean flag = uService.registrar(objUsuario);
			if (flag) return "redirect:/panel/cuenta/";
			else model.addAttribute("mensaje", "Ocurrio un error");
		}
		return "/panel/";
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") String id) {
		try {
			if (id!=null) uService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/usuarios/";
	}
}
