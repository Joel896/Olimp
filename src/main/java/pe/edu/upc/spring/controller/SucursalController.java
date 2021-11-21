package pe.edu.upc.spring.controller;


import java.security.Principal;
import java.util.List;
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
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("distrito", new Distrito());
		model.addAttribute("empresa", new Empresa());
		model.addAttribute("sucursal", new Sucursal());
		model.addAttribute("listaDistritos",  dService.listar());
		model.addAttribute("listaEmpresas", eService.listar());
		return "/Entidad/sucursal";
	}
	@RequestMapping("/buscar")
	public String buscar(@ModelAttribute Sucursal sucursal, RedirectAttributes objRedir)
	{
		if (sucursal.getDireccion().length()==0) return "redirect:/busqueda/sucursal/";
		else {
			sucursal.setDireccion(sucursal.getDireccion());
			List<Sucursal> listaSucursales; listaSucursales= sService.buscarEmpresa(sucursal.getDireccion());
			if (listaSucursales.isEmpty()) listaSucursales= sService.buscarDistrito(sucursal.getDireccion());	
			if (listaSucursales.isEmpty()) objRedir.addFlashAttribute("mensaje", "No existen coincidencias");		
			objRedir.addFlashAttribute("sucursal", sucursal);
			objRedir.addFlashAttribute("listaSucursales", listaSucursales);
			return "redirect:/busqueda/sucursal/resultados/";
		}
	}
	//CRUD
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Sucursal objSucursal, @ModelAttribute Empresa empresa, BindingResult binRes, Model model, Principal logeado) throws ParseException
	{
		String mensaje = "Ocurrio un error";
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistritos", dService.listar());
			model.addAttribute("listaEmpresas", eService.listar());
			model.addAttribute("mensaje", mensaje);
		}
		else {
			objSucursal.setEmpresa(empresa);
			boolean flag = sService.registrar(objSucursal);
			if (flag) {
				if(logeado==null) return "redirect:/login/";
				else return "redirect:/panel/sucursal/";
			}
			else model.addAttribute("mensaje", mensaje);
		}
		return "/Entidad/sucursal";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)	{
		Optional<Sucursal> objSucursal = sService.buscarId(id);
		if (objSucursal == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/panel/sucursal/";
		}
		else {
			model.addAttribute("listaDistritos",  dService.listar());
			model.addAttribute("listaEmpresas", eService.listar());
			if(objSucursal.isPresent()) objSucursal.ifPresent(o->model.addAttribute("sucursal", o));
			return "/Entidad/sucursal";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(RedirectAttributes objRedir, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) sService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/sucursales/";
	}
}
