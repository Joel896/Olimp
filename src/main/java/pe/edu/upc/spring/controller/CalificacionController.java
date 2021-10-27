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
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoCalificacion(Map<String, Object> model) {
		model.put("listaCalificacion", cService.listar());
		return "listTarifario";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("calificacion", new Calificacion());
		
		model.addAttribute("listaUsuario", uService.listar());	
		model.addAttribute("listaServicio", sService.listar());
		
		return "calificacion";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Calificacion objCalificacion, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaUsuario", uService.listar());
				model.addAttribute("listaServicio", sService.listar());			
				return "calificacion";
			}
		else {
			boolean flag = cService.registrar(objCalificacion);
			if (flag)
				return "redirect:/calificacion/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/calificacion/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Calificacion> objCalificacion = cService.buscarId(id);
		if (objCalificacion == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/calificacion/listar";
		}
		else {
			model.addAttribute("listaUsuario", uService.listar());
			model.addAttribute("listaServicio", sService.listar());			
					
			if (objCalificacion.isPresent())
				objCalificacion.ifPresent(o -> model.addAttribute("calificacion", o));
			
			return "calificacion";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				cService.eliminar(id);
				model.put("listaCalificacion", cService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaCalificacion", cService.listar());
			
		}
		return "listCalificacion";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCalificacion", cService.listar());
		return "listCalificacion";
	}		
	
	@RequestMapping("/buscarId")
	public String buscarId(Map<String, Object> model, @ModelAttribute Calificacion calificacion) 
	throws ParseException
	{
		cService.buscarId(calificacion.getIdCalificacion());
		return "listCalificacion";
	}	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("calificacion", new Calificacion());
		return "buscar";
	}
	
	//@RequestMapping("/buscarservicio")
	
	
	//@RequestMapping("/buscarusuario")
	
	
	//@RequestMapping("/buscarsucursal")
	
	
}
