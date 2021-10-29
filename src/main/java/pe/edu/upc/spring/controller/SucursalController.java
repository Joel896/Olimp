package pe.edu.upc.spring.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IEmpresaService;
import pe.edu.upc.spring.service.ISucursalService;


@Controller
@RequestMapping("/sucursal")
public class SucursalController {
	@Autowired
	private IDistritoService dService;
	
	@Autowired
	private IEmpresaService eService;
	
	@Autowired
	private ISucursalService sService;

	//Páginas
	@RequestMapping("/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaSucursales", sService.listar());
		return "listSucursal"; //data
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("distrito", new Distrito());
		model.addAttribute("empresa", new Empresa());
		model.addAttribute("sucursal", new Sucursal());
		model.addAttribute("listaDistritos",  dService.listar());
		model.addAttribute("listaEmpresas", eService.listar());
		return "sucursal";
	}
	
	@RequestMapping("/irBusqueda")
	public String irPaginaBusqueda(Model model)
	{
		model.addAttribute("sucursal", new Sucursal());
		model.addAttribute("listaSucursales", sService.listar());
		return "busquedaSucursal";
	}
	
	public String irPaginaVisualizar(Model model) {
		return "visualizarSucursal";
	}

	//Funciones
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Sucursal objSucursal, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistritos", dService.listar());
			model.addAttribute("listaEmpresas", eService.listar());
			return "sucursal";
		}
		else {
			boolean flag = sService.registrar(objSucursal);
			if (flag)
				return "redirect:/usuario/irLogin";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/sucursal/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException
	{
		Optional<Sucursal> objSucursal = sService.buscarId(id);
		if (objSucursal == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/sucursal/"; //panel sucursal
		}
		else {
			model.addAttribute("listaDistritos",  dService.listar());
			model.addAttribute("listaEmpresas", eService.listar());
			
			if(objSucursal.isPresent())
				objSucursal.ifPresent(o->model.addAttribute("sucursal", o));
			
			return "sucursal";
		}
	}
	////////////////////////////////
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSucursales", sService.listar());
		return "listSucursal";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Sucursal sucursal)
	throws ParseException
	{
		sService.buscarId(sucursal.getIdSucursal());
		return "listSucursal";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Sucursal sucursal)
	throws ParseException
	{
		List<Sucursal> listaSucursales;
		sucursal.setDireccion(sucursal.getDireccion());
		listaSucursales= sService.buscarEmpresa(sucursal.getDireccion());

		if (listaSucursales.isEmpty()) {
			listaSucursales= sService.buscarDistrito(sucursal.getDireccion());	
		}
		if (listaSucursales.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");		
		}
		
		model.put("listaSucursales", listaSucursales);
		return "busquedaSucursal";
	}
}
