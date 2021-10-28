package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.EstadoSolicitud;
import pe.edu.upc.spring.service.IEstadoSolicitudService;

@Controller
@RequestMapping("/estado")
public class EstadoSolicitudController {
	@Autowired
	private IEstadoSolicitudService eService;
	
	//Páginas
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("estado", new EstadoSolicitud());
		model.addAttribute("listaEstados", eService.listar());
		return "dataEstado";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute EstadoSolicitud objEstado, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaEstados", eService.listar());
			return "dataEstado";
		}
		else {
			boolean flag = eService.registrar(objEstado);
			if(flag) return "redirect:/servicio/inicio";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/estado/irRegistrar";
			}
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				eService.eliminar(id);
				model.put("listaEstados", eService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaEstados", eService.listar());
		}
		return "inicio"; 
	}
}
