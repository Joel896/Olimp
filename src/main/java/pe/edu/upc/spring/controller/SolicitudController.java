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
	
	@RequestMapping("/{idServicio}")
	public String irPaginaEntidad(@PathVariable int idServicio, Model model, Principal logeado) {
		Solicitud solicitud = new Solicitud();
		Date fecha = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		solicitud.setFechaCreacion(fecha); solicitud.setFechaAtencion(fecha);
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Optional<Servicio> objServicio = sService.buscarId(idServicio);
		Optional<EstadoSolicitud> objEstado = eService.buscarId(4);
		objUsuario.ifPresent(o->solicitud.setUsuario(o)); objServicio.ifPresent(o->solicitud.setServicio(o));
		objEstado.ifPresent(o->solicitud.setEstado(o));
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("listaEstados", eService.listar());
		
		return "/Entidad/solicitud";
	}
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Solicitud objSolicitud, Principal logeado, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException
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
			if (flag) {
				Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
				Usuario aux = new Usuario(); objUsuario.ifPresent(o -> aux.setSucursal(o.getSucursal()));
				model.addAttribute("titulo", "REGISTRAR SOLICITUD");
				if (aux.getSucursal()==null) return "redirect:/panel/cliente/solicitudes/";
				else return "redirect:/panel/sucursal/solicitudes/";
			}
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
			model.addAttribute("titulo", "MODIFICAR SOLICITUD");
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
