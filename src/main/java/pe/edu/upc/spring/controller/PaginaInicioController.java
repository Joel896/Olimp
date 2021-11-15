package pe.edu.upc.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.service.IImagenService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISucursalService;

@Controller
@RequestMapping("/inicio")
public class PaginaInicioController {
	@Autowired
	private IServicioService sService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private IImagenService iService;
	
	@RequestMapping("/")
	public String irInicio(Model model){
		//servicios populares
		Optional<Servicio> objSer1 = sService.buscarSolicitado(1);
		Optional<Servicio> objSer2 = sService.buscarSolicitado(2);
		Optional<Servicio> objSer3 = sService.buscarSolicitado(3);
		if (objSer1.isPresent()) {
			objSer1.ifPresent(o -> model.addAttribute("serpop1", o));
			objSer1.ifPresent(o -> model.addAttribute("serimg1", iService.portadaServicio(o.getIdServicio())));
		}
		if (objSer2.isPresent()) {
			objSer2.ifPresent(o -> model.addAttribute("serpop2", o));
			objSer2.ifPresent(o -> model.addAttribute("serimg2", iService.portadaServicio(o.getIdServicio())));
		}
		if (objSer3.isPresent()) {
			objSer3.ifPresent(o -> model.addAttribute("serpop3", o));
			objSer3.ifPresent(o -> model.addAttribute("serimg3", iService.portadaServicio(o.getIdServicio())));
		}

		//sucursales populares
		Optional<Sucursal> objSuc1 = suService.buscarSolicitado(1);
		Optional<Sucursal> objSuc2 = suService.buscarSolicitado(2);
		Optional<Sucursal> objSuc3 = suService.buscarSolicitado(3);
		if (objSuc1.isPresent())objSuc1.ifPresent(o -> model.addAttribute("sucpop1", o));
		if (objSuc2.isPresent())objSuc2.ifPresent(o -> model.addAttribute("sucpop2", o));
		if (objSuc3.isPresent())objSuc3.ifPresent(o -> model.addAttribute("sucpop3", o));
		return "inicio";
	}
}
