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

import pe.edu.upc.spring.model.TipoServicio;
import pe.edu.upc.spring.service.ITipoServicioService;

@Controller
@RequestMapping("/tiposervicio")
public class TipoServicioController {
	@Autowired
	private ITipoServicioService tService;

	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("tipoServicio", new TipoServicio());
		return "/Entidad/tipoServicio";
	}
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute TipoServicio objTipo, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			boolean flag = tService.registrar(objTipo);
			if (flag) return "redirect:/admin/tiposervicio/";
			else model.addAttribute("mensaje", "Ocurrio un error");
		}
		return "/Entidad/tipoServicio";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	{
		Optional<TipoServicio> objTipo = tService.buscarId(id);
		if (objTipo == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/tiposervicio/";
		}
		else {
			model.addAttribute("tipoServicio", objTipo);
			if (objTipo.isPresent()) objTipo.ifPresent(o -> model.addAttribute("usuario", o));
			return "/Entidad/tipoServicio";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) tService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
		}
		return "redirect:/admin/tiposervicio/"; 
	}
}
