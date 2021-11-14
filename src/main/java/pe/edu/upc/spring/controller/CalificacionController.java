package pe.edu.upc.spring.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import pe.edu.upc.spring.model.Calificacion;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.ICalificacionService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/calificacion")
public class CalificacionController {
	@Autowired
	private ICalificacionService cService;
	@Autowired
	private IServicioService sService;
	@Autowired
	private IUsuarioService uService;
	private String url = "/admin/calificaciones/";
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		Calificacion calificacion = new Calificacion(); 
		Date fecha = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		calificacion.setFecha(fecha); calificacion.setPuntos(1);
		model.addAttribute("calificacion", calificacion);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("servicio", new Servicio());
		
		model.addAttribute("listaUsuarios", uService.listar());	
		model.addAttribute("listaServicios", sService.listar());
		return "/Entidad/calificacion";
	}	
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Calificacion objCalificacion, BindingResult binRes, Model model) throws ParseException
	{
		String mensaje="Ocurrio un error";
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaServicios", sService.listar());	
			model.addAttribute("mensaje", mensaje);
		}
		else {
			boolean flag = cService.registrar(objCalificacion);
			if (flag) return "redirect:" + url;
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/calificacion";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	{
		Optional<Calificacion> objCalificacion = cService.buscarId(id);
		if (objCalificacion == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:"+url;
		}
		else {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaServicios", sService.listar());			
			if (objCalificacion.isPresent()) objCalificacion.ifPresent(o -> model.addAttribute("calificacion", o));
			return "/Entidad/calificacion";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) cService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:"+url;
	}
}
