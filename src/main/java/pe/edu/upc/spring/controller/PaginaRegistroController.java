package pe.edu.upc.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.model.Rol;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IEmpresaService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/registro")
public class PaginaRegistroController {
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IEmpresaService eService;
	@Autowired
	private ISucursalService sService;
	@Autowired 
	private PasswordEncoder encoder;

	@RequestMapping("/")
	public String irPaginaRegistro(@ModelAttribute Sucursal sucursal, @ModelAttribute Empresa empresa, Model model) {
		Usuario usuario = new Usuario(); 
		if(empresa.getRucEmpresa() != null) {
			if (eService.registrar(empresa)) {
				sucursal.setEmpresa(empresa);
				if(sService.registrar(sucursal)) {
					Sucursal suc = sService.listar().get(sService.listar().size()-1); //ultima sucursal
					usuario.setSucursal(suc); model.addAttribute("esSucursal", sucursal);
				}
			}
		}
		else model.addAttribute("esSucursal", null);
		model.addAttribute("usuario", usuario);
		return "registro";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Usuario objUsuario, Model model, BindingResult binRes) {
		if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			//Roles
			List<Rol> lstRoles = new ArrayList<Rol>();
			System.out.println("\n\nSUC:"+objUsuario.getSucursal());
			//USUARIO CLIENTE
			if(objUsuario.getSucursal() == null) {
				//Atributos
				if(objUsuario.getContrasenia().equals("admin"))lstRoles.add(new Rol(0,"ROLE_ADMIN"));
				else lstRoles.add(new Rol(0,"ROLE_CLIENTE")); objUsuario.setRoles(lstRoles); objUsuario.setEnabled(true);
				objUsuario.setContrasenia(encoder.encode(objUsuario.getContrasenia())); //encriptar
				if (uService.registrar(objUsuario)) return "redirect:/login/";
				model.addAttribute("mensaje", "Ocurrio un error");
			}
			//USUARIO SUCURSAL
			else {
				if(uService.registrar(objUsuario)) {
					//Atributos
					lstRoles.add(new Rol(0,"ROLE_SUCURSAL")); objUsuario.setRoles(lstRoles); 
					objUsuario.setEnabled(true); objUsuario.setContrasenia(encoder.encode(objUsuario.getContrasenia())); //encriptar
					if(uService.registrar(objUsuario)) return "redirect:/login/";
				}
				model.addAttribute("mensaje", "Ocurrio un error");
			}
		}
		return "registro";
	}
}
