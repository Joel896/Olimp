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
import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.service.IEmpresaService;


@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	@Autowired
	private IEmpresaService eService;
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "/Entidad/empresa";
	}
	@RequestMapping("/buscar")
	public String comprobarRUC(Map<String, Object> model, @ModelAttribute Empresa empresa, RedirectAttributes objRedir) {
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
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Empresa objEmpresa, BindingResult binRes, Model model) throws ParseException
	{
		String mensaje="Ocurrio un error";
		if (binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			boolean flag = eService.registrar(objEmpresa);
			if (flag) return "redirect:/admin/empresas/";
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/empresa";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, Model model, RedirectAttributes objRedir)
		throws ParseException
	{
		Optional<Empresa> objEmpresa = eService.buscarId(id);
		if (objEmpresa == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/empresas/";
		}
		else {
			if (objEmpresa.isPresent()) objEmpresa.ifPresent(o -> model.addAttribute("empresa", o));
			return "/Entidad/empresa";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") String id) {
		try {
			if (id!=null) eService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/empresas/";
	}

}
