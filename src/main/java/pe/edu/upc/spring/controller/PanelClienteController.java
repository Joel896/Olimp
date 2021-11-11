package pe.edu.upc.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.ICalificacionService;
import pe.edu.upc.spring.service.IFavoritoService;
import pe.edu.upc.spring.service.ISolicitudService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/panel/cliente")
public class PanelClienteController {
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IFavoritoService fService;
	@Autowired
	private ISolicitudService sService;
	@Autowired
	private ICalificacionService cService;
	
	
	@RequestMapping("/")
	public String irPanelPerfil(Model model, RedirectAttributes objRedir) {
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
		return "PC_Perfil";
	}
	
	@RequestMapping("/favoritos/")
	public String irPanelFavoritos(Model model) {
		model.addAttribute("listaFavoritos", fService.listarServicios("00000000"));
		return "PC_Favorito";
	}
	
	@RequestMapping("/solicitudes/")
	public String irPanelSolicitudes(Model model, RedirectAttributes objRedir) {
		model.addAttribute("listaSolicitudes", sService.buscarUsuario("00000000"));
		return "PC_Solicitud";
	}
	
	@RequestMapping("/calificaciones/")
	public String irPanelCalificaciones(Model model) {
		model.addAttribute("listaCalificaciones", cService.buscarUsuario("00000000"));
		return "PC_Calificacion";
	}
}
