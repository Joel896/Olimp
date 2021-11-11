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

import pe.edu.upc.spring.model.EstadoSolicitud;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Solicitud;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.TipoServicio;
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
	
	//Páginas
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("solicitud", new Solicitud());
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("estado", new EstadoSolicitud());
		
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("listaServicios", sService.listar());
		model.addAttribute("listaEstados", eService.listar());
		return "solicitud";
	}
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Solicitud objSolicitud, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaServicios", sService.listar());
			model.addAttribute("listaEstados", eService.listar());
			return "solicitud";
		}
		else {
			boolean flag = soService.registrar(objSolicitud);
			if (flag)
				return "redirect:/solicitud/"; //panel usuario
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/solicitud/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException
	{
		Optional<Servicio> objSolicitud = sService.buscarId(id);
		if (objSolicitud == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/solicitud/"; //panel sucursal
		}
		else {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaServicios", sService.listar());
			model.addAttribute("listaEstados", eService.listar());

			if (objSolicitud.isPresent())
				objSolicitud.ifPresent(o -> model.addAttribute("solicitud", o));
			return "solicitud";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				sService.eliminar(id);
				model.put("listaSolicitudes", soService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaSolicitudes", soService.listar());
		}
		return "redirect:/solicitud/"; //panel usuario
	}
	
	
	
	
	
	
	
	
	
	
	
}
