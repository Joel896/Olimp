package pe.edu.upc.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.service.IImagenService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;

@Controller
@RequestMapping("/principal")
public class PrincipalController {
	@Autowired
	private IServicioService sService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private IImagenService iService;
	
	//Inicio
	@RequestMapping("/inicio")
	public String irInicio(Model model) throws ParseException{
		
		//servicios populares
		Optional<Servicio> objSer1 = sService.buscarSolicitado(1);
		Optional<Servicio> objSer2 = sService.buscarSolicitado(2);
		Optional<Servicio> objSer3 = sService.buscarSolicitado(3);
		if (objSer1.isPresent()) {
			System.out.println("aaaaaaaaaaaaaa");
			objSer1.ifPresent(o -> model.addAttribute("serpop1", o));
			objSer1.ifPresent(o -> model.addAttribute("serimg1", iService.portadaServicio(o.getIdServicio())));
		}
		if (objSer2.isPresent()) {
			objSer2.ifPresent(o -> model.addAttribute("serpop2", o));
			objSer2.ifPresent(o -> model.addAttribute("serimg2", iService.portadaServicio(o.getIdServicio())));
		}
		if (objSer3.isPresent()) {
			objSer3.ifPresent(o -> model.addAttribute("serpop3", o));
			objSer3.ifPresent(o -> model.addAttribute("serimg3", iService.portadaServicio(o.getIdServicio())));
		}

		//sucursales populares
		Optional<Sucursal> objSuc1 = suService.buscarSolicitado(1);
		Optional<Sucursal> objSuc2 = suService.buscarSolicitado(2);
		Optional<Sucursal> objSuc3 = suService.buscarSolicitado(3);
		if (objSuc1.isPresent())objSuc1.ifPresent(o -> model.addAttribute("sucpop1", o));
		if (objSuc2.isPresent())objSuc2.ifPresent(o -> model.addAttribute("sucpop2", o));
		if (objSuc3.isPresent())objSuc3.ifPresent(o -> model.addAttribute("sucpop3", o));
		return "inicio";
	}
	
	//Busqueda
	@RequestMapping("/busqueda/sucursal")
	public String irBusquedaSucursal(Model model) {
		model.addAttribute("mostrarSucursal", 1);
		model.addAttribute("sucursal", new Sucursal());
		model.addAttribute("listaSucursales", suService.listar());
		return "busqueda";
	}
	@RequestMapping("/busqueda/sucursal/resultados")
	public String irBuscarSucursal(Model model) {
		model.addAttribute("mostrarSucursal", 1);
		if(model.getAttribute("sucursal")== null)return "redirect:/principal/busqueda/sucursal";
		else return "busqueda";
	}
	@RequestMapping("/busqueda/servicio")
	public String irBusquedaServicio(Model model) {
		model.addAttribute("mostrarSucursal", null);
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("listaServicios", sService.listar());
		return "busqueda";
	}
	@RequestMapping("/busqueda/servicio/resultados")
	public String irBuscarServicio(Model model) {
		model.addAttribute("mostrarSucursal", null);
		if(model.getAttribute("servicio")== null)return "redirect:/principal/busqueda/servicio";
		else return "busqueda";
	}
	
	//Visualizar
	@RequestMapping("/busqueda/visualizar/sucursal/{id}")
	public String irVisualizarSucursal(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Sucursal> objSucursal = suService.buscarId(id);
		if (objSucursal == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/busqueda/sucursal/";
		}
		else {
			model.addAttribute("listaTipoVehiculo", tvService.listar());
			model.addAttribute("listaServicios", sService.listar());			
					
			if (objSucursal.isPresent())
				objSucursal.ifPresent(o -> model.addAttribute("sucursal", o));
			
			return "visualizar";
		}
	}
	@RequestMapping("/busqueda/visualizar/servicio/{id}")
	public String irVisualizarServicio(@PathVariable int id, Model model) throws ParseException {
		return "verSucursal";
	}
	
	
	//Contacto
	@RequestMapping("/contacto")
	public String irContacto() { return "contacto";}
	
	//Afiliacion
	@RequestMapping("/afiliacion")
	public String irAfiliacion(Model model) { 
		model.addAttribute("empresa", new Empresa());
		return "afiliacion";
	}
}
