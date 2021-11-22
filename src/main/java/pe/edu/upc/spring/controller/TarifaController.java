package pe.edu.upc.spring.controller;

import java.security.Principal;
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
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.service.ITarifaService;
import pe.edu.upc.spring.service.ITipoVehiculoService;
import pe.edu.upc.spring.service.IUsuarioService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;

@Controller
@RequestMapping("/tarifa")
public class TarifaController {
	@Autowired
	private IServicioService sService;
	@Autowired
	private ITipoVehiculoService tvService;
	@Autowired
	private ITarifaService tService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private IUsuarioService uService;

	@RequestMapping("/")
	public String irPaginaEntidad(Model model, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal aux = new Sucursal(); objUsuario.ifPresent(o->aux.setIdSucursal(o.getSucursal().getIdSucursal()));
		Optional<Sucursal> objSucursal = suService.buscarId(aux.getIdSucursal());

		objSucursal.ifPresent(o->model.addAttribute("listaServicios", sService.buscarSucursal(o.getIdSucursal())));
		model.addAttribute("tipoVehiculo", new TipoVehiculo());
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("tarifa", new Tarifa());
		
		model.addAttribute("titulo", "REGISTRAR TARIFA");
		model.addAttribute("listaTipoVehiculo", tvService.listar());
		return "/Entidad/tarifa";
	}
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Tarifa objTarifa, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaTipoVehiculo", tvService.listar());
			model.addAttribute("listaServicios", sService.listar());	
			model.addAttribute("mensaje", tService.listar());
		}
		else {
			boolean flag = tService.registrar(objTarifa);
			if (flag) return "redirect:/panel/sucursal/tarifario/";
			else model.addAttribute("mensaje", "Ocurrio un error");
		}
		return "/Entidad/tarifa";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, Principal logeado, RedirectAttributes objRedir)
	{
		Optional<Tarifa> objTarifa = tService.buscarId(id);
		if (objTarifa == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/tarifario/";
		}
		else {
			Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
			Sucursal aux = new Sucursal(); objUsuario.ifPresent(o->aux.setIdSucursal(o.getSucursal().getIdSucursal()));
			Optional<Sucursal> objSucursal = suService.buscarId(aux.getIdSucursal());

			objSucursal.ifPresent(o->model.addAttribute("listaServicios", sService.buscarSucursal(o.getIdSucursal())));
			model.addAttribute("listaTipoVehiculo", tvService.listar());
			model.addAttribute("titulo", "MODIFICAR TARIFA");
			if (objTarifa.isPresent()) objTarifa.ifPresent(o -> model.addAttribute("tarifa", o));
			return "/Entidad/tarifa";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) tService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
		}
		return "redirect:/panel/sucursal/tarifario/";
	}

}