package pe.edu.upc.spring.controller;

import java.security.Principal;
import java.util.List;
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
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.ITipoServicioService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
	@Autowired
	private IServicioService sService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private ITipoServicioService tService;
	@Autowired
	private IUsuarioService uService;

	@RequestMapping("/")
	public String irPaginaEntidad(Model model, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal aux = new Sucursal(); objUsuario.ifPresent(o->aux.setIdSucursal(o.getSucursal().getIdSucursal()));
		Optional<Sucursal> objSucursal = suService.buscarId(aux.getIdSucursal());

		Servicio objServicio = new Servicio(); objSucursal.ifPresent(o->objServicio.setSucursal(o));
		model.addAttribute("servicio", objServicio);
		model.addAttribute("tipoServicio", new TipoServicio());
		model.addAttribute("titulo", "REGISTRAR SERVICIO");
		
		model.addAttribute("listaTipoServicio", tService.listar());
		return "/Entidad/servicio";
	}
	@RequestMapping("/buscar")
	public String buscar(@ModelAttribute Servicio servicio, RedirectAttributes objRedir)
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
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Servicio objServicio, BindingResult binRes, Model model) throws ParseException
	{
		String mensaje = "Ocurrio un error";
		if (binRes.hasErrors()) {
			model.addAttribute("listaSucursales", suService.listar());
			model.addAttribute("listaTipoServicio", tService.listar());
			model.addAttribute("mensaje", mensaje);
		}
		else {
			boolean flag = sService.registrar(objServicio);
			if (flag) return "redirect:/panel/sucursal/servicios/";
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/servicio";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)throws ParseException
	{
		Optional<Servicio> objServicio = sService.buscarId(id);
		if (objServicio == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/servicios/"; 
		}
		else {
			model.addAttribute("listaSucursales", suService.listar());
			model.addAttribute("listaTipoServicio", tService.listar());
			model.addAttribute("titulo", "MODIFICAR SERVICIO");
			if (objServicio.isPresent()) objServicio.ifPresent(o -> model.addAttribute("servicio", o));
			return "/Entidad/servicio";
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
		return "redirect:/panel/sucursal/servicios/";
	}
}