package pe.edu.upc.spring.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.model.Imagen;
import pe.edu.upc.spring.service.ICalificacionService;
import pe.edu.upc.spring.service.IFavoritoService;
import pe.edu.upc.spring.service.IImagenService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.ITarifaService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/visualizar")
public class PaginaVisualizarController {
	@Autowired
	public IServicioService sService;
	@Autowired
	public ISucursalService suService;
	@Autowired
	public ICalificacionService cService;
	@Autowired
	public ITarifaService tService;
	@Autowired
	public IImagenService iService;
	@Autowired
	public IFavoritoService fService;
	@Autowired
	public IUsuarioService uService;
	
	@RequestMapping("/sucursal/{id}")
	public String irVisualizarSucursal(@PathVariable int id, Model model, Principal logeado, RedirectAttributes objRedir) {
		Optional<Sucursal> objSucursal = suService.buscarId(id);
		if (objSucursal == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/busqueda/sucursal/";
		}
		else {		
			if (objSucursal.isPresent()) {
				objSucursal.ifPresent(o -> model.addAttribute("sucursal", o));
				model.addAttribute("listaServicios", sService.buscarSucursal(id));
						
				if(logeado == null) model.addAttribute("clienteLogeado", null);
				else {
					model.addAttribute("clienteLogeado", logeado);
					model.addAttribute("comprobarFavorito", fService.buscarSucursalUsuario(id, logeado.getName()));
				}
				//Calificaciones
				model.addAttribute("total", cService.buscarSucursal(id).size());			
				model.addAttribute("media", Math.round(cService.promedioCalificaciones("sucursal", id)*10)/10.0);
				for(int i = 1; i <= 5; i++) model.addAttribute("cal"+i,cService.contarCalificaciones("sucursal", id, i));	
			}
			return "verSucursal";
		}
	}
	@RequestMapping("/servicio/{id}")
	public String irVisualizarServicio(@PathVariable int id, Model model, Principal logeado, RedirectAttributes objRedir) {
		Optional<Servicio> objServicio = sService.buscarId(id);
		if (objServicio == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/busqueda/servicio/";
		}
		else {		
			if (objServicio.isPresent()) {
				objServicio.ifPresent(o -> model.addAttribute("servicio", o));
				model.addAttribute("listaServicios", sService.buscarSucursal(id));
				model.addAttribute("listaTarifas", tService.buscarServicio(id));
				model.addAttribute("listaImagenes", iService.buscarServicio(id));
				if(logeado == null) model.addAttribute("clienteLogeado", null);
				else{
					model.addAttribute("clienteLogeado", logeado);
					model.addAttribute("comprobarFavorito", fService.buscarServicioUsuario(id, logeado.getName()));
				}
				//Calificaciones
				model.addAttribute("listaCalificaciones", cService.buscarServicio(id));
				model.addAttribute("total", cService.buscarServicio(id).size());			
				model.addAttribute("media", Math.round(cService.promedioCalificaciones("servicio", id)*10)/10.0);			
				for(int i = 1; i <= 5; i++) model.addAttribute("cal"+i,cService.contarCalificaciones("servicio", id, i));	
			}
			return "verServicio";
		}
	}
	
	@RequestMapping("/imagen/{id}")
	public String irVisualizarImagen(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Imagen> objImagen = iService.buscarId(id);
		if (objImagen == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/busqueda/servicio/";
		}
		else {
			if(objImagen.isPresent())objImagen.ifPresent(o->model.addAttribute("imagen", o));
			return "verImagen";
		}
	}
	
	@RequestMapping("/usuario/{dniUsuario}")
	public String irVisualizarUsuario(@PathVariable String dniUsuario, Model model, RedirectAttributes objRedir) {
		Optional<Usuario> objUsuario = uService.buscarId(dniUsuario);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/inicio/";
		}
		else {
			if(objUsuario.isPresent())objUsuario.ifPresent(o->model.addAttribute("usuario", o));
			return "verUsuario";
		}
	}

	
	
}
