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


import pe.edu.upc.spring.model.Tarifa;
import pe.edu.upc.spring.model.TipoVehiculo;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.service.ITarifaService;
import pe.edu.upc.spring.service.ITipoVehiculoService;
import pe.edu.upc.spring.service.IServicioService;

@Controller
@RequestMapping("/tarifa")
public class TarifaController {
	/*

	@Autowired
	private IServicioService sService;
	
	@Autowired
	private ITipoVehiculoService tvService;
	
	@Autowired
	private ITarifarioService tService;	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoMascotas(Map<String, Object> model) {
		model.put("listaMascotas", tService.Listar());
		return "listTarifario";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("tipoVehiculo", new TipoVehiculo());
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("tarifario", new Tarifario());
		model.addAttribute("listaTipoVehiculo", tvService.listar());
		model.addAttribute("listaServicio", sService.listar());		
		
		return "tarifario";
	}
	
	@RequestMapping("/registrar")
	public String Registrar(@ModelAttribute Tarifario objTarifario, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaTipoVehiculo", tvService.listar());
				model.addAttribute("listaServicio", sService.listar());			
				return "tarifario";
			}
		else {
			boolean flag = tService.Registrar(objTarifario);
			if (flag)
				return "redirect:/tarifario/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tarifario/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String Modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Tarifario> objTarifario = tService.BuscarId(id);
		if (objTarifario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/tarifario/listar";
		}
		else {
			model.addAttribute("listaTipoVehiculo", tvService.listar());
			model.addAttribute("listaServicio", sService.listar());			
					
			if (objTarifario.isPresent())
				objTarifario.ifPresent(o -> model.addAttribute("tarifario", o));
			
			return "tarifario";
		}
	}
	
	@RequestMapping("/eliminar")
	public String Eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				tService.Eliminar(id);
				model.put("listaTarifario", tService.Listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaTarifario", tService.Listar());
			
		}
		return "listTarifario";
	}
	
	@RequestMapping("/listar")
	public String Listar(Map<String, Object> model) {
		model.put("listaTarifario", tService.Listar());
		return "listTarifario";
	}		
	
	@RequestMapping("/listarId")
	public String ListarId(Map<String, Object> model, @ModelAttribute Tarifario tarifario) 
	throws ParseException
	{
		tService.ListarId(tarifario.getIdTarifario());
		return "listTarifario";
	}	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("tarifario", new Tarifario());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Tarifario tarifario)
			throws ParseException
	{
		List<Tarifario> listaTarifario;
		if (listaTarifario.isEmpty()) {
			listaTarifario = tService.ListarPorNombreDeServicio(tarifario.getServicio().getNombreServicio());
		}
		if (listaTarifario.isEmpty()) {
			listaTarifario = tService.ListarPorIdDeServicio(tarifario.getServicio().getIdServicio());
		}
	}
		model.put("listaTarifario", listaTarifario);
		return "buscar";
	}
	*/
}