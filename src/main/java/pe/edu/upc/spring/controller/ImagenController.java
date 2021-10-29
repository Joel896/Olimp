package pe.edu.upc.spring.controller;

import java.util.List;
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

import pe.edu.upc.spring.model.Imagen;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.service.IImagenService;
import pe.edu.upc.spring.service.IServicioService;

@Controller
@RequestMapping("/imagen")
public class ImagenController {
	@Autowired
	private IImagenService iService;
	@Autowired
	private IServicioService sService;
	
	//Páginas
	@RequestMapping("/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaImagenes", iService.listar());
		return "listImagen"; //panel sucursal
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("imagen", new Imagen());
		model.addAttribute("servicio", new Servicio());
		
		model.addAttribute("listaImagenes", iService.listar());
		model.addAttribute("listaServicios", sService.listar());
		return "imagen";
	}
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Imagen objImagen, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaImagenes", iService.listar());
			model.addAttribute("listaServicios", sService.listar());
			return "imagen";
		}
		else {
			boolean flag = iService.registrar(objImagen);
			if(flag) return "redirect:/imagen/"; //panel sucursal
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/imagen/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Imagen> objImagen = iService.buscarId(id);
		if (objImagen == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/imagen/"; //panel sucursal
		}
		else {
			model.addAttribute("listaImagenes", iService.listar());
			model.addAttribute("listaServicios", sService.listar());			
					
			if (objImagen.isPresent())
				objImagen.ifPresent(o -> model.addAttribute("imagen", o));
			
			return "imagen";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				iService.eliminar(id);
				model.put("listaImagenes", iService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaImagenes", iService.listar());
		}
		return "redirect:/imagen/"; //panel sucursal
	}
}
