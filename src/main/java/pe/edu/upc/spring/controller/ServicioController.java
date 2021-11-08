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
	
	@RequestMapping("/contacto")
	public String irPaginaContacto() {
		return "contacto";
	}
	
	@RequestMapping("/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "listServicio"; //panel sucursal
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
	
	@RequestMapping("/irVisualizar")
	public String irPaginaVisualizar(Model model) {
		return "visualizarServicio";
	}
	
	//Funciones
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
				return "redirect:/servicio/"; //panel sucursal
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
			return "redirect:/servicio/"; //panel sucursal
		}
		else {
			model.addAttribute("listaSucursales", suService.listar());
			model.addAttribute("listaTipoServicio", tService.listar());
			
			if (objServicio.isPresent())
				objServicio.ifPresent(o -> model.addAttribute("servicio", o));
			
			return "servicio";
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
		return "redirect:/servicio/"; //panel sucursal
	}

	@RequestMapping("/buscar")
	public String buscar(@ModelAttribute Servicio servicio, RedirectAttributes objRedir)
			throws ParseException
	{
		if (servicio.getNombre().length()==0) return "redirect:/busqueda/servicio/";
		else {
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
				objRedir.addFlashAttribute("mensaje", "No existen coincidencias");		
			}
			objRedir.addFlashAttribute("servicio", servicio);
			objRedir.addFlashAttribute("listaServicios", listaServicios);
			return "redirect:/busqueda/servicio/resultados/";
		}
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "listServicio";
	}
}