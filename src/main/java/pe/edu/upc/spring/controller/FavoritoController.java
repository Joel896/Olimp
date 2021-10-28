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

import pe.edu.upc.spring.model.Favorito;
import pe.edu.upc.spring.service.IFavoritoService;

@Controller
@RequestMapping("/favorito")
public class FavoritoController {
	
	@Autowired
	private IFavoritoService fService;
	
	//Páginas
	@RequestMapping("/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaFavoritos", fService.listar());
		return "listFavorito"; //panel usuario
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("favorito", new Favorito());
		return "favorito"; 
	}

	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Favorito objFavorito, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "favorito";
		else {
			boolean flag = fService.registrar(objFavorito);
			if (flag)
				return "redirect:/favorito/"; //panel usuario
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/favorito/irRegistrar";
			}
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				fService.eliminar(id);
				model.put("listaFavoritos", fService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaFavoritos", fService.listar());
		}
		return "listFavorito"; //panel usuario
	}

	/////////////////////////////////////
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaFavoritos", fService.listar());
		return "listFavorito";
	}
}

