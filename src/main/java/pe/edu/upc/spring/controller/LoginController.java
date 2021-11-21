package pe.edu.upc.spring.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class LoginController {
	@GetMapping(value = { "/login/", "/" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal logeado,
			RedirectAttributes objRedir, HttpServletRequest request, HttpServletResponse response) {
		
		if (logout != null) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
	        if (auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);
			objRedir.addFlashAttribute("mensaje", "Ha cerrado sesión con éxito!");
			return "redirect:/login/";
		}
		else {
			if (logeado != null) return "redirect:/inicio/";
			if (error != null) model.addAttribute("mensaje", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
		return "login";
	}
	
}
