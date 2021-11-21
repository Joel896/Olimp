package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.service.IDistritoService;

@Controller
@RequestMapping("/afiliacion")
public class PaginaAfiliacionController {
	@Autowired
	private IDistritoService dService;
	
	//Páginas
	@RequestMapping("/")
	public String irAfiliacion(Model model) { 
		model.addAttribute("empresa", new Empresa());
		return "afiliacion";
	}
	@RequestMapping("/formulario/")
	public String irFormularioAfiliacion(Model model, @ModelAttribute Empresa empresa) {
		if(empresa.getRucEmpresa()==null) return "redirect:/afiliacion/";
		else {
			model.addAttribute("sucursal", new Sucursal());
			model.addAttribute("empresa", empresa);
			model.addAttribute("listaDistritos", dService.listar());
			return "formulario";
		}
	}
	//Funciones
	@RequestMapping("/afiliarse")
	public String afiliarDuenioSucursal(@ModelAttribute("sucursal") Sucursal sucursal, @ModelAttribute("empresa") Empresa empresa, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaDistritos", dService.listar());
			return "afiliacion";
		}
		else {
			sucursal.setEmpresa(empresa);
			objRedir.addFlashAttribute("empresa", empresa);
			objRedir.addFlashAttribute("sucursal", sucursal);
			return "redirect:/registro/";
		}
	}
}
