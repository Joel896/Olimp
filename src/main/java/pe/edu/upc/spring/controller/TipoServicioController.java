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

import pe.edu.upc.spring.model.TipoServicio;
import pe.edu.upc.spring.service.ITipoServicioService;

@Controller
@RequestMapping("/tiposervicio")
public class TipoServicioController {
	@Autowired
	private ITipoServicioService tService;
	//Páginas
	@RequestMapping("/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaTipoServicio", tService.listar());
		return "listTipoServicio"; //data
	}
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("tipoServicio", new TipoServicio());
		model.addAttribute("listaTipoServicio", tService.listar());
		return "dataTS";
	}
	//funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute TipoServicio objTipo, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaDistritos", tService.listar());
			return "dataTS";
		}
		else {
			boolean flag = tService.registrar(objTipo);
			if(flag) return "redirect:/servicio/inicio";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tiposervicio/irRegistrar";
			}
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				tService.eliminar(id);
				model.put("listaTipoServicio", tService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaTipoServicio", tService.listar());
		}
		return "inicio"; 
	}
}
