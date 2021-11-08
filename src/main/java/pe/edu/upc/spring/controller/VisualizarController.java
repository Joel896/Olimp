package pe.edu.upc.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.service.ICalificacionService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;

@Controller
@RequestMapping("/visualizar")
public class VisualizarController {
	@Autowired
	public IServicioService sService;
	@Autowired
	public ISucursalService suService;
	@Autowired
	public ICalificacionService cService;
	
	@RequestMapping("/sucursal/{id}")
	public String irVisualizarSucursal(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Sucursal> objSucursal = suService.buscarId(id);
		if (objSucursal == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/busqueda/sucursal/";
		}
		else {		
			if (objSucursal.isPresent()) {
				objSucursal.ifPresent(o -> model.addAttribute("sucursal", o));
				model.addAttribute("listaServicios", sService.buscarSucursal(id));
				model.addAttribute("total", cService.contarCalificaciones("sucursal", id, -1));			
				model.addAttribute("media", cService.contarCalificaciones("sucursal", id, 0));			
				model.addAttribute("cal1", cService.contarCalificaciones("sucursal", id, 1));	
				model.addAttribute("cal2", cService.contarCalificaciones("sucursal", id, 2));			
				model.addAttribute("cal3", cService.contarCalificaciones("sucursal", id, 3));			
				model.addAttribute("cal4", cService.contarCalificaciones("sucursal", id, 4));			
				model.addAttribute("cal5", cService.contarCalificaciones("sucursal", id, 5));	
			}
			return "verSucursal";
		}
	}
	@RequestMapping("/servicio/{id}")
	public String irVisualizarServicio(@PathVariable int id, Model model) throws ParseException {
		return "verSucursal";
	}
}
