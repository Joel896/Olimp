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

import pe.edu.upc.spring.model.TipoVehiculo;
import pe.edu.upc.spring.service.ITipoVehiculoService;

@Controller
@RequestMapping("/tipovehiculo")
public class TipoVehiculoController {
	/*
	@Autowired
	private ITipoVehiculoService tvService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoTipoVehiculo(Map<String, Object> model) {
		model.put("listaTipoVehiculo", tvService.listar());
		return "listTipoVehiculo";
	}
	
	@RequestMapping("/irregistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("tipoVehiculo", new TipoVehiculo());
		return "tipoVehiculo";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute TipoVehiculo objTipoVehiculo, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "tipoVehiculo";
		else {
			boolean flag = tvService.registrar(objTipoVehiculo);
			if (flag)
				return "redirect:/tipovehiculo/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tipovehiculo/irregistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<TipoVehiculo> objTipoVehiculo = tvService.listarId(id);
		if (objTipoVehiculo == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/tipovehiculo/listar";
		}
		else {
			model.addAttribute("tipoVehiculo", objTipoVehiculo);
			return "tipoVehiculo";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				tvService.eliminar(id);
				model.put("listaTipoVehiculo", tvService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaTipoVehiculo", tvService.listar());
			
		}
		return "listTipoVehiculo";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaTipoVehiculo", tvService.listar());
		return "listTipoVehiculo";
	}		
	*/
}
