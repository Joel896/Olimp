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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;
import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.service.IEmpresaService;


@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	@Autowired
	private IEmpresaService eService;
	
	//Pàginas
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresa";
	}

	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Empresa objEmpresa, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaEmpresas", eService.listar());
			return "empresa";
		}
		else {
			boolean flag = eService.registrar(objEmpresa);
			if (flag)
				return "redirect:/sucursal/irRegistrar"; //redirige al panel (request) "redirect:/sucursal/empresa"
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/empresa/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, Model model, RedirectAttributes objRedir)
		throws ParseException
	{
		Optional<Empresa> objEmpresa = eService.buscarId(id);
		if (objEmpresa == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/servicio/inicio"; //redirige al panel (request) "redirect:/sucursal/empresa"
		}
		else {
			model.addAttribute("empresa", objEmpresa);
			if (objEmpresa.isPresent())
				objEmpresa.ifPresent(o -> model.addAttribute("empresa", o));
			return "empresa";
		}
	}
}
