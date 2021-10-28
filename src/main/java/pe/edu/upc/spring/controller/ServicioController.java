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

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.TipoServicio;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.ITipoServicioService;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
	@Autowired
	private IServicioService sService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private ITipoServicioService tService;
	
	//Páginas
	@RequestMapping("/inicio")
	public String irPaginaInicio() {
		return "inicio";
	}
	
	@RequestMapping("/busqueda")
	public String irPaginaBusqueda(Model model) {
		model.addAttribute("servicio", new Servicio());
		return "busquedaServicio";
	}
	
	@RequestMapping("/contacto")
	public String irPaginaContacto() {
		return "contacto";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("sucursal", new Sucursal());
		model.addAttribute("tipoServicio", new TipoServicio());
		
		model.addAttribute("listaSucursales", suService.listar());
		model.addAttribute("listaTipoServicio", tService.listar());
		return "servicio";
	}
	//Funciones
		
	@RequestMapping("/")
	public String irPaginaListadoServicio(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "listServicio";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Servicio objServicio, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaSucursales", suService.listar());
			model.addAttribute("listaTipoServicio", tService.listar());
			return "servicio";
		}
		else {
			boolean flag = sService.registrar(objServicio);
			if (flag)
				return "redirect:/servicio/inicio"; //redirige al panel (request) "redirect:/sucursal/servicios"
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/servicio/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException
	{
		Optional<Servicio> objServicio = sService.buscarId(id);
		if (objServicio == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/servicio/inicio"; //redirige al panel (request) "sucursal/servicios"
		}
		else {
			model.addAttribute("servicio", objServicio);
			return "inicio"; //redirige al panel (página) "sucursalServicios"
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				sService.eliminar(id);
				model.put("listaServicios", sService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaServicios", sService.listar());
		}
		return "inicio"; //redirige al panel (página) "sucursalServicios"
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Servicio servicio)
			throws ParseException
	{
		List<Servicio> listaServicios;
		servicio.setNombre(servicio.getNombre());
		listaServicios = sService.buscarDistrito(servicio.getNombre());
		if (listaServicios.isEmpty()) {
			listaServicios = sService.buscarTipoServicio(servicio.getNombre());
		}
		if (listaServicios.isEmpty()) {
			listaServicios = sService.buscarNombre(servicio.getNombre());
		}
		if (listaServicios.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");		
		}
		model.put("listaServicios", listaServicios);
		return "busquedaServicio";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "listServicio";
	}
}