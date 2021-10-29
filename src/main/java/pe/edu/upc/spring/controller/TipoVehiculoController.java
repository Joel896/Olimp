package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.TipoVehiculo;
import pe.edu.upc.spring.service.ITipoVehiculoService;

@Controller
@RequestMapping("/tipovehiculo")
public class TipoVehiculoController {
	@Autowired
	private ITipoVehiculoService tService;
	
	//Páginas
	@RequestMapping("/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaTipoVehiculo", tService.listar());
		return "listTipoVehiculo"; //data
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("tipoVehiculo", new TipoVehiculo());
		model.addAttribute("listaTipoVehiculo", tService.listar());
		return "dataTV";
	}
	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute TipoVehiculo objTipo, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaTipoVehiculo", tService.listar());
			return "dataTV";
		}
		else {
			boolean flag = tService.registrar(objTipo);
			if(flag) return "redirect:/tipovehiculo/";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tipovehiculo/irRegistrar";
			}
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				tService.eliminar(id);
				model.put("listaTipoVehiculo", tService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaTipoVehiculo", tService.listar());
		}
		return "redirect:/tipovehiculo/"; 
	}
}
