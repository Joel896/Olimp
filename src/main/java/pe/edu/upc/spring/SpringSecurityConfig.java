package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSuccessHandler;
import pe.edu.upc.spring.serviceImpl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JpaUserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		try {
			http
				.authorizeRequests()
					.antMatchers("/css/**", "/js/**", "/imagenes/**", "/login/", "/inicio/**", "/visualizar/**", "/contacto/**", "/afiliacion/**").permitAll()
					.antMatchers("/afiliacion/**", "/registro/**").anonymous()
					.antMatchers("/admin/**", "/distrito/**", "/estado/**", "/tiposervicio/**", "/tipovehiculo/**", "/empresa/modificar**", "/empresa/eliminar**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/panel/sucursal/**", "/imagen/**", "/tarifa/**", "/servicio/**", "/sucursal/**", "/visualizar/usuario/**").access("hasRole('ROLE_SUCURSAL')")
					.antMatchers("/panel/cliente/**", "/favorito/**", "/calificacion/**").access("hasRole('ROLE_CLIENTE')")
					.antMatchers("/solicitud/**").access("hasRole('ROLE_SUCURSAL') or hasRole('ROLE_CLIENTE')")
					.and()
				.formLogin()
					.successHandler(successHandler).loginPage("/login/").loginProcessingUrl("/login/").defaultSuccessUrl("/inicio/")
					.permitAll()
					.and()
				.logout()
					.logoutSuccessUrl("/login/")
					.permitAll()
					.and()
				.exceptionHandling()
					.accessDeniedPage("/error_403");
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
