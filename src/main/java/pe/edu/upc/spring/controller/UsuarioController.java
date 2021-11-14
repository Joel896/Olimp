package pe.edu.upc.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Rol;
import pe.edu.upc.spring.model.Sucursal;
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
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute("usuario") Usuario objUsuario, BindingResult binRes, Model model) throws ParseException{
		String mensaje = "Ocurrio un error";
		if(binRes.hasErrors()) model.addAttribute("mensaje", mensaje);
		else {
			//Roles
			List<Rol> lstRoles = new ArrayList<Rol>();
			if(objUsuario.getDniUsuario().equals("00000000"))lstRoles.add(new Rol(0,"ROL_ADMIN"));
			else if(objUsuario.getSucursal()==null) lstRoles.add(new Rol(0,"ROL_CLIENTE"));
			else lstRoles.add(new Rol(0,"ROL_SUCURSAL"));
			//Atributos
			objUsuario.setRoles(lstRoles); objUsuario.setEnabled(true);
			objUsuario.setContrasenia(encoder.encode(objUsuario.getContrasenia())); //encriptar
			boolean flag = uService.registrar(objUsuario); 
			if (flag) return "redirect:/login/";
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/usuario";
	}	
	@RequestMapping("/modificar/{dniUsuario}")
	public String modificar(@PathVariable String dniUsuario, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Usuario> objUsuario = uService.buscarId(dniUsuario);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/usuarios/";
		}
		else {
			model.addAttribute("usuario", objUsuario);
			if (objUsuario.isPresent()) objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			
			return "/Entidad/usuario";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") String id) {
		try {
			if (id!=null) uService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/empresas/";
	}
}
