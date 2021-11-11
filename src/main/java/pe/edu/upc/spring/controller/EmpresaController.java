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
	
	//Páginas
	@RequestMapping("/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaEmpresas", eService.listar());
		return "listEmpresa"; //data
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresa";
	}

	@RequestMapping("/afiliacion")
	public String irPaginaAfiliacion(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "afiliacion";
	}

	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute("empresa") Empresa empresa, BindingResult binRes, Model model, RedirectAttributes objRedir)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaEmpresas", eService.listar());
			return "empresa";
		}
		else {
			boolean flag = eService.registrar(empresa);
			if (flag) {
				return "redirect:/empresa/";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/irRegistrar/";
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
			return "redirect:/empresa/"; //panel sucursal
		}
		else {
			model.addAttribute("empresa", objEmpresa);
			if (objEmpresa.isPresent())
				objEmpresa.ifPresent(o -> model.addAttribute("empresa", o));
			return "empresa";
		}
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Empresa empresa, RedirectAttributes objRedir) {
		if(empresa.getRucEmpresa().length()==11) {
			Optional<Empresa> objEmpresa;
			empresa.setRucEmpresa(empresa.getRucEmpresa());
			objEmpresa = eService.buscarId(empresa.getRucEmpresa());
			if(objEmpresa.isPresent()) objEmpresa.ifPresent(o->objRedir.addFlashAttribute("empresa", o));
			else objRedir.addFlashAttribute("empresa",empresa);
			
			return "redirect:/afiliacion/formulario/";
		}
		else{
			objRedir.addFlashAttribute("mensaje", "El RUC debe ser de 11 dígitos");
			return "redirect:/afiliacion/";
		}
	}
}
