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

import pe.edu.upc.spring.model.EstadoSolicitud;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Solicitud;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IEstadoSolicitudService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISolicitudService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/solicitud")
public class SolicitudController {
	@Autowired
	private ISolicitudService soService;
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IServicioService sService;
	@Autowired
	private IEstadoSolicitudService eService;
	private String url="/admin/solicitudes/";
	
	@RequestMapping("/")
	public String irPaginaRegistrar(Model model) {
		Solicitud solicitud = new Solicitud();
		Date fecha = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		solicitud.setFechaCreacion(fecha);
		solicitud.setFechaAtencion(fecha);
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("estado", new EstadoSolicitud());
		
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("listaServicios", sService.listar());
		model.addAttribute("listaEstados", eService.listar());
		
		return "/Entidad/solicitud";
	}
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Solicitud objSolicitud, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException
	{
		String mensaje = "Ocurrio un error";
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaServicios", sService.listar());
			model.addAttribute("listaEstados", eService.listar());
			model.addAttribute("mensaje", mensaje);
		}
		else {
			boolean flag = soService.registrar(objSolicitud);
			if (flag) return "redirect:" + url;
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/solicitud";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		Optional<Solicitud> objSolicitud = soService.buscarId(id);
		if (objSolicitud == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:"+url;
		}
		else {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaServicios", sService.listar());
			model.addAttribute("listaEstados", eService.listar());
			if (objSolicitud.isPresent())objSolicitud.ifPresent(o -> model.addAttribute("solicitud", o));
			return "/Entidad/solicitud";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) sService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:"+url;
	}
}
