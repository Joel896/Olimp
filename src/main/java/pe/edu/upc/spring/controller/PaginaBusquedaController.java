package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;

@Controller
@RequestMapping("/busqueda")
public class PaginaBusquedaController {
	@Autowired
	private IServicioService sService;
	@Autowired
	private ISucursalService suService;
	
	//SUCURSAL
	@RequestMapping("/sucursal/")
	public String irBusquedaSucursal(Model model) {
		model.addAttribute("sucursal", new Sucursal());
		model.addAttribute("listaSucursales", suService.listar());
		return "busqueda";
	}
	@RequestMapping("/sucursal/resultados/")
	public String irResultadosBusquedaSucursal(Model model) {
		if(model.getAttribute("sucursal")== null)return "redirect:/busqueda/sucursal/";
		else return "busqueda";
	}
	//SERVICIO
	@RequestMapping("/servicio/")
	public String irBusquedaServicio(Model model) {
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("listaServicios", sService.listar());
		return "busqueda";
	}
	@RequestMapping("/servicio/resultados/")
	public String irResultadosBusquedaServicio(Model model) {
		if(model.getAttribute("servicio")== null)return "redirect:/busqueda/servicio/";
		else return "busqueda";
	}
}
