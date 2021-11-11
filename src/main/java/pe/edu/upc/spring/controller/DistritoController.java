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

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.service.IDistritoService;

@Controller
@RequestMapping("/distrito")
public class DistritoController {
	@Autowired
	private IDistritoService dService;
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("distrito", new Distrito());
		model.addAttribute("listaDistritos", dService.listar());
		return "dataDistrito";
	}
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Distrito objDistrito, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaDistritos", dService.listar());
			return "dataDistrito";
		}
		else {
			boolean flag = dService.registrar(objDistrito);
			if(flag) return "redirect:/distrito/";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/distrito/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaDistritos", dService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaDistritos", dService.listar());
		}
		return "redirect:/distrito/"; 
	}
}
