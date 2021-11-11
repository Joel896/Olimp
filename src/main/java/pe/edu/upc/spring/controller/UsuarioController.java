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
	
	//Páginas
	@RequestMapping("/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaUsuarios", uService.listar());
		return "listUsuario"; //data
	}
	
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute("usuario") Usuario objUsuario, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaUsuarios",uService.listar());
			return "registro";
		}
		else {
			//Roles
			List<Rol> lstRoles = new ArrayList<Rol>();
			if(objUsuario.getDniUsuario().equals("00000000"))lstRoles.add(new Rol(0,"ROL_ADMIN"));
			else if(objUsuario.getSucursal()==null) lstRoles.add(new Rol(0,"ROL_CLIENTE"));
			else lstRoles.add(new Rol(0,"ROL_SUCURSAL"));
			//Atributos
			objUsuario.setRoles(lstRoles); objUsuario.setEnabled(true);
			System.out.println("ANTES: "+objUsuario.getContrasenia());
			objUsuario.setContrasenia(encoder.encode(objUsuario.getContrasenia())); //encriptar
			System.out.println("DESPUES: "+objUsuario.getContrasenia());
			boolean flag = uService.registrar(objUsuario);
			if(flag) {
				return "redirect:/login/";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/registrar/";
			}
		}
	}
	
	@RequestMapping("/modificar/{dniUsuario}")
	public String modificar(@PathVariable String dniUsuario, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Usuario> objUsuario = uService.buscarId(dniUsuario);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/usuario/"; //panel usuario
		}
		else {
			model.addAttribute("usuario", objUsuario);
			if (objUsuario.isPresent())
				objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			
			return "registro";
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
