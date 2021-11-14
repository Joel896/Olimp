package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.service.ICalificacionService;
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
	private ICalificacionService cService;
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
	
	@RequestMapping("/calificaciones/")
	public String irPaginaCalificaciones(Map<String, Object> model) {
		model.put("listaCalificaciones", cService.listar());
		return "/Admin/listCalificacion";
	}
	@RequestMapping("/distritos/")
	public String irPaginaDistritos(Map<String, Object> model) {
		model.put("listaDistritos", dService.listar());
		return "/Admin/listDistrito";
	}
	@RequestMapping("/empresas/")
	public String irPaginaEmpresas(Map<String, Object> model) {
		model.put("listaEmpresas", eService.listar());
		return "/Admin/listEmpresa";
	}
	@RequestMapping("/estados/")
	public String irPaginaEstadosSolicitud(Map<String, Object> model) {
		model.put("listaEstados", esService.listar());
		return "/Admin/listEstadoSolicitud";
	}
	@RequestMapping("/favoritos/")
	public String irPaginaFavoritos(Map<String, Object> model) {
		model.put("listaFavoritos", fService.listar());
		return "/Admin/listFavorito";
	}
	@RequestMapping("/imagenes/")
	public String irPaginaImagenes(Map<String, Object> model) {
		model.put("listaImagenes", iService.listar());
		return "/Admin/listImagen";
	}
	@RequestMapping("/servicios/")
	public String irPaginaServicios(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "/Admin/listServicio";
	}
	@RequestMapping("/solicitudes/")
	public String irPaginaSolicitudes(Map<String, Object> model) {
		model.put("listaSolicitudes", soService.listar());
		return "/Admin/listSolicitud";
	}
	@RequestMapping("/sucursales/")
	public String irPaginaSucursales(Map<String, Object> model) {
		model.put("listaSucursales", suService.listar());
		return "/Admin/listSucursal";
	}
	@RequestMapping("/tarifas/")
	public String irPaginaTarifas(Map<String, Object> model) {
		model.put("listaTarifas", tService.listar());
		return "/Admin/listTarifa";
	}
	@RequestMapping("/tiposervicio/")
	public String irPaginaTiposServicio(Map<String, Object> model) {
		model.put("listaTipoServicio", tsService.listar());
		return "/Admin/listTipoServicio";
	}
	@RequestMapping("/tipovehiculo/")
	public String irPaginaTiposVehiculo(Map<String, Object> model) {
		model.put("listaTipoVehiculo", tvService.listar());
		return "/Admin/listTipoVehiculo";
	}
	@RequestMapping("/usuarios/")
	public String irPaginaUsuarios(Map<String, Object> model) {
		model.put("listaUsuarios", uService.listar());
		return "/Admin/listUsuario";
	}	
}
