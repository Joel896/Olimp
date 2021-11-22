package pe.edu.upc.spring.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Imagen;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IImagenService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/imagen")
public class ImagenController {
	@Autowired
	private IImagenService iService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private IServicioService sService;
	@Autowired
	private IUsuarioService uService;
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal aux = new Sucursal(); objUsuario.ifPresent(o->aux.setIdSucursal(o.getSucursal().getIdSucursal()));
		Optional<Sucursal> objSucursal = suService.buscarId(aux.getIdSucursal());

		objSucursal.ifPresent(o->model.addAttribute("listaServicios", sService.buscarSucursal(o.getIdSucursal())));
		model.addAttribute("imagen", new Imagen());
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("titulo", "REGISTRAR IMAGEN");
		return "/Entidad/imagen";
	}
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Imagen objImagen, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			boolean flag = iService.registrar(objImagen);
			if (flag) return "redirect:/panel/sucursal/galeria/";
			else model.addAttribute("mensaje", "Ocurrio un error");
		}
		return "/Entidad/imagen";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, Principal logeado, RedirectAttributes objRedir)
	{
		Optional<Imagen> objImagen = iService.buscarId(id);
		if (objImagen == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/galeria/";
		}
		else {
			Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
			Sucursal aux = new Sucursal(); objUsuario.ifPresent(o->aux.setIdSucursal(o.getSucursal().getIdSucursal()));
			Optional<Sucursal> objSucursal = suService.buscarId(aux.getIdSucursal());

			objSucursal.ifPresent(o->model.addAttribute("listaServicios", sService.buscarSucursal(o.getIdSucursal())));
			model.addAttribute("titulo", "MODIFICAR IMAGEN");
			if (objImagen.isPresent()) objImagen.ifPresent(o -> model.addAttribute("imagen", o));
			return "/Entidad/imagen";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) iService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/panel/sucursal/galeria/";
	}
}
