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

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoEmpresas(Map<String, Object> model) {
		model.put("listaEmpresas", eService.listar());
		return "listEmpresa";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresa";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Empresa objEmpresa, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "empresa";
		else {
			boolean flag = eService.registrar(objEmpresa);
			if (flag)
				return "redirect:/empresa/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/empresa/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException
	{
		Optional<Empresa> objEmpresa = eService.listarId(id);
		if (objEmpresa == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/empresa/listar";
		}
		else {
			model.addAttribute("empresa", objEmpresa);
			return "empresa";
		}
	}
	

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEmpresas", eService.listar());
		return "listEmpresa";
	}
}
