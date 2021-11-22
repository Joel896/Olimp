package pe.edu.upc.spring.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.ICalificacionService;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IFavoritoService;
import pe.edu.upc.spring.service.IImagenService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISolicitudService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.ITarifaService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/panel")
public class PanelUsuarioController {
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IFavoritoService fService;
	@Autowired
	private IServicioService sService;
	@Autowired
	private ICalificacionService cService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private IImagenService iService;
	@Autowired
	private ITarifaService tService;
	@Autowired
	private ISolicitudService soService;
	@Autowired
	private IDistritoService dService;
	
	@RequestMapping("/")
	public String irPanel(Principal logeado, RedirectAttributes objRedir) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Usuario aux = new Usuario();
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/inicio/";
		}
		else {
			if (objUsuario.isPresent()) objUsuario.ifPresent(o -> aux.setRoles(o.getRoles()));	
			if (aux.getRol().equals("ROLE_ADMIN")) return "redirect:/admin/";
			else if (aux.getRol().equals("ROLE_SUCURSAL"))return "redirect:/panel/sucursal/";
			else return "redirect:/panel/cliente/";
		}
	}
	//CLIENTE
	@RequestMapping("/cliente/")
	public String irPanelPerfil(Model model, RedirectAttributes objRedir, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/inicio/";
		}
		else {
			model.addAttribute("logeado", logeado.getName());
			if (objUsuario.isPresent()) objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
		}
		return "/Cliente/PC_Cuenta";
	}
	@RequestMapping("/cliente/favoritos/")
	public String irPanelFavoritos(Model model, Principal logeado) {
		model.addAttribute("listaSucursales", fService.listarSucursales(logeado.getName()));
		model.addAttribute("listaServicios", fService.listarServicios(logeado.getName()));
		model.addAttribute("logeado", logeado.getName());
		return "/Cliente/PC_Favorito";
	}
	@RequestMapping("/cliente/solicitudes/")
	public String irPanelSolicitudesRealizadas(Model model, Principal logeado) {
		model.addAttribute("listaSolicitudes", soService.buscarUsuario(logeado.getName()));
		model.addAttribute("logeado", logeado.getName());
		return "/Cliente/PC_Solicitud";
	}
	@RequestMapping("/cliente/calificaciones/")
	public String irPanelCalificaciones(Model model, Principal logeado) {
		model.addAttribute("listaCalificaciones", cService.buscarUsuario(logeado.getName()));
		model.addAttribute("logeado", logeado.getName());
		return "/Cliente/PC_Calificacion";
	}
	//SUCURSAL
	@RequestMapping("/sucursal/")
	public String irPanelSucursal(Model model, RedirectAttributes objRedir, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal aux = new Sucursal(); objUsuario.ifPresent(o->aux.setIdSucursal(o.getSucursal().getIdSucursal()));
		Optional<Sucursal> objSucursal = suService.buscarId(aux.getIdSucursal());
		if (objSucursal == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/cuenta/";
		}
		else {
			model.addAttribute("sucursal", objSucursal);
			model.addAttribute("listaDistritos", dService.listar());
			objSucursal.ifPresent(o -> model.addAttribute("sucursal", o));
			objSucursal.ifPresent(o -> model.addAttribute("ruc", o.getEmpresa().getRucEmpresa()));
			return "/Sucursal/PS_Sucursal";
		}
	}
	@RequestMapping("/sucursal/cuenta/")
	public String irPanelCuenta(Model model, RedirectAttributes objRedir, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal oSucursal = new Sucursal();
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/inicio/";
		}
		else {
			objUsuario.ifPresent(o->oSucursal.setEmpresa(o.getSucursal().getEmpresa()));
			objUsuario.ifPresent(o->model.addAttribute("encriptada", o.getContrasenia()));
			model.addAttribute("ruc", oSucursal.getEmpresa().getRucEmpresa());
			model.addAttribute("usuario", objUsuario);
			if (objUsuario.isPresent()) objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			return "/Sucursal/PS_Cuenta";
		}
	}
	@RequestMapping("/sucursal/servicios/")
	public String irPanelServicios(Model model, RedirectAttributes objRedir, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal oSucursal = new Sucursal(); 
		objUsuario.ifPresent(o->oSucursal.setIdSucursal(o.getSucursal().getIdSucursal()));
		objUsuario.ifPresent(o->oSucursal.setEmpresa(o.getSucursal().getEmpresa()));
		if (oSucursal.getIdSucursal() > 0) {
			model.addAttribute("listaServicios", sService.buscarSucursal(oSucursal.getIdSucursal()));
			model.addAttribute("ruc", oSucursal.getEmpresa().getRucEmpresa());
			return "/Sucursal/PS_Servicio";
		}
		else {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/cuenta/";
		}
	}
	@RequestMapping("/sucursal/tarifario/")
	public String irPanelTarifario(Model model, RedirectAttributes objRedir, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal oSucursal = new Sucursal(); 
		objUsuario.ifPresent(o->oSucursal.setIdSucursal(o.getSucursal().getIdSucursal()));
		objUsuario.ifPresent(o->oSucursal.setEmpresa(o.getSucursal().getEmpresa()));
		if (oSucursal.getIdSucursal() > 0) {
			model.addAttribute("listaTarifas", tService.buscarSucursal(oSucursal.getIdSucursal()));
			model.addAttribute("listaServicios", sService.buscarSucursal(oSucursal.getIdSucursal()));
			model.addAttribute("ruc", oSucursal.getEmpresa().getRucEmpresa());
			return "/Sucursal/PS_Tarifa";
		}
		else {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/cuenta/";
		}
	}
	@RequestMapping("/sucursal/galeria/")
	public String irPanelGaleria(Model model, RedirectAttributes objRedir, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal oSucursal = new Sucursal(); 
		objUsuario.ifPresent(o->oSucursal.setIdSucursal(o.getSucursal().getIdSucursal()));
		objUsuario.ifPresent(o->oSucursal.setEmpresa(o.getSucursal().getEmpresa()));
		if (oSucursal.getIdSucursal() > 0) {
			model.addAttribute("listaImagenes", iService.buscarSucursal(oSucursal.getIdSucursal()));
			model.addAttribute("listaServicios", sService.buscarSucursal(oSucursal.getIdSucursal()));
			model.addAttribute("ruc", oSucursal.getEmpresa().getRucEmpresa());
			return "/Sucursal/PS_Galeria";
		}
		else {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/cuenta/";
		}
	}
	@RequestMapping("/sucursal/solicitudes/")
	public String irPanelSolicitudes(Model model, RedirectAttributes objRedir, Principal logeado) {
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Sucursal oSucursal = new Sucursal(); 
		objUsuario.ifPresent(o->oSucursal.setIdSucursal(o.getSucursal().getIdSucursal()));
		objUsuario.ifPresent(o->oSucursal.setEmpresa(o.getSucursal().getEmpresa()));
		if (oSucursal.getIdSucursal() > 0) {
			model.addAttribute("listaSolicitudes", soService.buscarSucursal(oSucursal.getIdSucursal()));
			model.addAttribute("ruc", oSucursal.getEmpresa().getRucEmpresa());
			return "/Sucursal/PS_Solicitud";
		}
		else {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/cuenta/";
		}
	}
}
