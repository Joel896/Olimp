package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IEmpresaService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/afiliacion")
public class AfiliacionController {
	@Autowired
	private IEmpresaService eService;
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private IDistritoService dService;
	
	@RequestMapping("/")
	public String irAfiliacion(Model model) { 
		model.addAttribute("empresa", new Empresa());
		return "afiliacion";
	}
	@RequestMapping("/formulario/")
	public String irFormularioAfiliacion(Model model, @ModelAttribute Empresa empresa) {
		if(empresa.getRucEmpresa()==null) return "redirect:/afiliacion/";
		else {
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("sucursal", new Sucursal());
			model.addAttribute("empresa", empresa);
			model.addAttribute("listaDistritos", dService.listar());
			return "formulario";
		}
	}
	
	//Funciones

	@RequestMapping("/afiliarse")
	public String afiliarDuenioSucursal(@ModelAttribute("sucursal") Sucursal sucursal, @ModelAttribute("empresa") Empresa empresa, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaDistritos", dService.listar());
			return "afiliacion";
		}
		else {
			boolean flag = eService.registrar(empresa);
			if(flag) {
				sucursal.setEmpresa(empresa);
				flag = suService.registrar(sucursal);
				objRedir.addFlashAttribute("sucursal", sucursal);
				return "redirect:/registro/";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/afiliacion/";
			}
		}
	}
}
