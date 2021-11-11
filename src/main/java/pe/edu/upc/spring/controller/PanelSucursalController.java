package pe.edu.upc.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IImagenService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISolicitudService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.ITarifaService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/panel/sucursal")
public class PanelSucursalController {
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private IServicioService sService;
	@Autowired
	private IImagenService iService;
	@Autowired
	private ITarifaService tService;
	@Autowired
	private ISolicitudService soService;
	
	
	@RequestMapping("/")
	public String irPanelSucursal(Model model, RedirectAttributes objRedir) {
		Optional<Sucursal> objSucursal = suService.buscarId(1);
		if (objSucursal == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/inicio/";
		}
		else {
			model.addAttribute("sucursal", objSucursal);
			if (objSucursal.isPresent())
				objSucursal.ifPresent(o -> model.addAttribute("sucursal", o));
			
		}
		return "PS_Sucursal";
	}
	
	@RequestMapping("/cuenta/")
	public String irPanelCuenta(Model model, RedirectAttributes objRedir) {
		Optional<Usuario> objUsuario = uService.buscarId("00000000");
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/inicio/";
		}
		else {
			model.addAttribute("usuario", objUsuario);
			if (objUsuario.isPresent())
				objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			
		}
		return "PS_Cuenta";
	}
	
	@RequestMapping("/servicios/")
	public String irPanelServicios(Model model) {
		model.addAttribute("listaServicios", sService.buscarSucursal(1));
		return "PS_Servicio";
	}
	
	@RequestMapping("/tarifario/")
	public String irPanelTarifario(Model model) {
		model.addAttribute("listaTarifas", tService.buscarSucursal(1));
		return "PS_Tarifario";
	}
	
	@RequestMapping("/galeria/")
	public String irPanelGaleria(Model model) {
		model.addAttribute("listaImagenes", iService.buscarSucursal(1));
		return "PS_Galeria";
	}
	
	@RequestMapping("/galeria/")
	public String irPanelSolicitudes(Model model) {
		model.addAttribute("listaSolicitudes", soService.buscarSucursal(1));
		return "PS_Solicitud";
	}
}
