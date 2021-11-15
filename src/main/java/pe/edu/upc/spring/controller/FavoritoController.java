package pe.edu.upc.spring.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Favorito;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IFavoritoService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/favorito")
public class FavoritoController {
	@Autowired
	private IFavoritoService fService;
	@Autowired
	private IServicioService sService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private IUsuarioService uService;
	private String url = "/admin/favoritos/";
	
	//CRUD
	@RequestMapping("/agregarServicio")
	public String agregarServicioFavorito(@RequestParam(value="id") Integer idServicio, Principal logeado, RedirectAttributes objRedir) throws ParseException
	{
		Favorito objFavorito = new Favorito();
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Optional<Servicio> objServicio = sService.buscarId(idServicio);
		objUsuario.ifPresent(o->objFavorito.setUsuario(o));
		objServicio.ifPresent(o->objFavorito.setServicio(o));
		objFavorito.setSucursal(null); 
		fService.registrar(objFavorito);
		return "redirect:/visualizar/servicio/"+idServicio;
	}
	@RequestMapping("/eliminarServicio")
	public String eliminarServicioFavorito(@RequestParam(value="id") Integer idServicio, Principal logeado, RedirectAttributes objRedir) throws ParseException
	{
		Favorito objFavorito = fService.buscarServicioUsuario(idServicio, logeado.getName());
		fService.eliminar(objFavorito.getIdFavorito());
		return "redirect:/visualizar/servicio/"+idServicio;
	}
	@RequestMapping("/agregarSucursal")
	public String agregarSucursalFavorito(@RequestParam(value="id") Integer idSucursal, Principal logeado, RedirectAttributes objRedir) throws ParseException
	{
		Favorito objFavorito = new Favorito();
		Optional<Usuario> objUsuario = uService.buscarId(logeado.getName());
		Optional<Sucursal> objSucursal = suService.buscarId(idSucursal);
		objUsuario.ifPresent(o->objFavorito.setUsuario(o));
		objSucursal.ifPresent(o->objFavorito.setSucursal(o));
		objFavorito.setServicio(null); 
		fService.registrar(objFavorito);
		return "redirect:/visualizar/sucursal/"+idSucursal;
	}
	@RequestMapping("/eliminarSucursal")
	public String eliminarSucursalFavorito(@RequestParam(value="id") Integer idSucursal, Principal logeado, RedirectAttributes objRedir) throws ParseException
	{
		Favorito objFavorito = fService.buscarSucursalUsuario(idSucursal, logeado.getName());
		fService.eliminar(objFavorito.getIdFavorito());
		return "redirect:/visualizar/sucursal/"+idSucursal;
	}

	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) fService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:"+url;
	}
}

