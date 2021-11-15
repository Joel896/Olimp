package pe.edu.upc.spring.controller;

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

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.service.IDistritoService;

@Controller
@RequestMapping("/distrito")
public class DistritoController {
	@Autowired
	private IDistritoService dService;
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("distrito", new Distrito());
		return "/Entidad/distrito";
	}
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Distrito objDistrito, BindingResult binRes, Model model) throws ParseException{
		String mensaje="Ocurrio un error";
		if(binRes.hasErrors()) {
			model.addAttribute("mensaje", mensaje);
			return "/Entidad/distrito";
		}
		else {
			boolean flag = dService.registrar(objDistrito);
			if (flag) return "redirect:/admin/distritos/";
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/distrito";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Distrito> objDistrito = dService.buscarId(id);
		if (objDistrito == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/distritos/";
		}
		else {					
			if (objDistrito.isPresent()) objDistrito.ifPresent(o -> model.addAttribute("distrito", o));
			return "/Entidad/distrito";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) dService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/distritos/"; 
	}
}
