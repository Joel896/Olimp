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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.EstadoSolicitud;
import pe.edu.upc.spring.service.IEstadoSolicitudService;

@Controller
@RequestMapping("/estado")
public class EstadoSolicitudController {
	@Autowired
	private IEstadoSolicitudService eService;
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("estado", new EstadoSolicitud());
		return "/Entidad/estadoSolicitud";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute EstadoSolicitud objEstado, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		String mensaje="Ocurrio un error";
		if(binRes.hasErrors()) model.addAttribute("mensaje", mensaje);
		else {
			boolean flag = eService.registrar(objEstado);
			if (flag) return "redirect:/admin/estados/";
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/estadoSolicitud";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	{
		Optional<EstadoSolicitud> objEstado = eService.buscarId(id);
		if (objEstado == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/estados/";
		}
		else {
			if (objEstado.isPresent()) objEstado.ifPresent(o -> model.addAttribute("estado", o));
			return "/Entidad/estadoSolicitud";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id, RedirectAttributes objRedir) {
		try {
			if (id!=null && id>0) eService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/estados/"; 
	}
}
