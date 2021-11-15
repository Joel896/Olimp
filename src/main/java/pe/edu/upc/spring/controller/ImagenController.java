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
	private String url="/admin/imagenes/";
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("imagen", new Imagen());
		model.addAttribute("servicio", new Servicio());
		
		model.addAttribute("listaServicios", sService.listar());
		return "/Entidad/imagen";
	}
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Imagen objImagen, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		String mensaje = "Ocurrio un error";
		if(binRes.hasErrors()) model.addAttribute("mensaje", mensaje);
		else {
			boolean flag = iService.registrar(objImagen);
			if (flag) return "redirect:" + url;
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/imagen";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	{
		Optional<Imagen> objImagen = iService.buscarId(id);
		if (objImagen == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:"+url;
		}
		else {
			model.addAttribute("listaServicios",  sService.listar());
			if (objImagen.isPresent()) objImagen.ifPresent(o -> model.addAttribute("imagen", o));
			return "/Entidad/imagen";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) iService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:"+url;
	}
}
