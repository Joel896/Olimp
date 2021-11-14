package pe.edu.upc.spring.controller;

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
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("favorito", new Favorito());
		model.addAttribute("sucursal", new Sucursal());
		
		model.addAttribute("listaUsuarios", uService.listar());	
		model.addAttribute("listaServicios", sService.listar());
		model.addAttribute("listaSucursales", suService.listar());
		return "/Entidad/favorito"; 
	}
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Favorito objFavorito, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaServicios", sService.listar());
			model.addAttribute("listaSucursales", suService.listar());
			model.addAttribute("mensaje", "Ocurrio un error");
			return "/Entidad/favorito";
		}
		else {
			boolean flag = fService.registrar(objFavorito);
			if (flag) return "redirect:"+url;
			else {
				objRedir.addFlashAttribute("mensaje","Ocurrio un error");
				return "redirect:/favorito/";
			}
		}
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

