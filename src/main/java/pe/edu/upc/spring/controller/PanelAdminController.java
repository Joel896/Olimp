package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.service.IDistritoService;
import pe.edu.upc.spring.service.IEmpresaService;
import pe.edu.upc.spring.service.IEstadoSolicitudService;
import pe.edu.upc.spring.service.IFavoritoService;
import pe.edu.upc.spring.service.IImagenService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.ISolicitudService;
import pe.edu.upc.spring.service.ISucursalService;
import pe.edu.upc.spring.service.ITarifaService;
import pe.edu.upc.spring.service.ITipoServicioService;
import pe.edu.upc.spring.service.ITipoVehiculoService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class PanelAdminController {
	@Autowired
	private IDistritoService dService;
	@Autowired
	private IEmpresaService eService;
	@Autowired
	private IEstadoSolicitudService esService;
	@Autowired
	private IFavoritoService fService;
	@Autowired
	private IImagenService iService;
	@Autowired
	private IServicioService sService;
	@Autowired
	private ISolicitudService soService;
	@Autowired
	private ISucursalService suService;
	@Autowired
	private ITarifaService tService;
	@Autowired
	private ITipoServicioService tsService;
	@Autowired
	private ITipoVehiculoService tvService;
	@Autowired
	private IUsuarioService uService;
	
	///Panel oficial
	@RequestMapping("/tipovehiculo/")
	public String irPaginaTiposVehiculo(Map<String, Object> model) {
		model.put("listaTipoVehiculo", tvService.listar());
		return "listTipoVehiculo";
	}
	@RequestMapping("/tiposervicio/")
	public String irPaginaTiposServicio(Map<String, Object> model) {
		model.put("listaTipoServicio", tsService.listar());
		return "listTipoServicio";
	}
	@RequestMapping("/distrito/")
	public String irPaginaDistritos(Map<String, Object> model) {
		model.put("listaDistritos", dService.listar());
		return "listTipoServicio";
	}
	@RequestMapping("/")
	public String irPaginaEstadosSolicitud(Map<String, Object> model) {
		model.put("listaEstados", esService.listar());
		return "listEstadoSolicitud"; //data
	}
	@RequestMapping("/usuario/")
	public String irPaginaUsuarios(Map<String, Object> model) {
		model.put("listaUsuarios", uService.listar());
		return "listUsuario";
	}
	///
	@RequestMapping("/empresa/")
	public String irPaginaEmpresas(Map<String, Object> model) {
		model.put("listaEmpresas", eService.listar());
		return "listEmpresa";
	}
	@RequestMapping("/favorito/")
	public String irPaginaFavoritos(Map<String, Object> model) {
		model.put("listaFavoritos", fService.listar());
		return "listFavorito";
	}
	@RequestMapping("/imagen/")
	public String irPaginaImagenes(Map<String, Object> model) {
		model.put("listaImagenes", iService.listar());
		return "listImagen";
	}
	@RequestMapping("/servicio/")
	public String irPaginaServicios(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "listServicio";
	}
	@RequestMapping("/solicitud/")
	public String irPaginaSolicitudes(Map<String, Object> model) {
		model.put("listaSolicitudes", soService.listar());
		return "listSolicitud";
	}
	@RequestMapping("/sucursal/")
	public String irPaginaSucursales(Map<String, Object> model) {
		model.put("listaSucursales", suService.listar());
		return "listSucursal";
	}
	@RequestMapping("/tarifa/")
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaTarifas", tService.listar());
		return "listTarifa";
	}
}
