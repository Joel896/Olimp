package pe.edu.upc.spring.controller;

import java.security.Principal;
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
	
	@RequestMapping("/{idServicio}")
	public String irPaginaEntidad(@PathVariable int idServicio, Model model, Principal logeado) {
		//comprobar usuario ha calificado servicio
		if (cService.buscarUsuarioServicio(logeado.getName(), idServicio)!=null) {
			return "redirect:/calificacion/modificar/"+cService.buscarUsuarioServicio(logeado.getName(), idServicio).getIdCalificacion();
		}
		else{
			Calificacion calificacion = new Calificacion(); 
			Date fecha = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
			calificacion.setFecha(fecha); calificacion.setPuntos(1);
			Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
			Optional<Servicio> objServicio = sService.buscarId(idServicio);
			objUsuario.ifPresent(o->calificacion.setUsuario(o)); objServicio.ifPresent(o->calificacion.setServicio(o));
			model.addAttribute("calificacion", calificacion);
			
			model.addAttribute("listaUsuarios", uService.listar());	
			model.addAttribute("listaServicios", sService.listar());
			return "/Entidad/calificacion";
		}
	}	
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Calificacion objCalificacion, BindingResult binRes, Model model, RedirectAttributes objRedir, Principal logeado) throws ParseException
	{
		String mensaje="Ocurrio un error";
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaServicios", sService.listar());	
			model.addAttribute("mensaje", mensaje);
		}
		else {
			boolean flag = cService.registrar(objCalificacion);
			if (flag) {
				Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
				Usuario aux = new Usuario();
				if (objUsuario == null) {
					objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
					return "redirect:/inicio/";
				}
				else {
					if (objUsuario.isPresent()) objUsuario.ifPresent(o -> aux.setSucursal(o.getSucursal()));	
					if (aux.getSucursal()==null) return "redirect:/visualizar/servicio/"+objCalificacion.getServicio().getIdServicio();
					else return "redirect:/panel/sucursal/";
				}
			}
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
