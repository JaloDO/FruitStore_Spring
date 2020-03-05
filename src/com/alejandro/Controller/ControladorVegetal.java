package com.alejandro.Controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alejandro.Dao.VegetalDao;
import com.alejandro.Model.*;

@Controller
public class ControladorVegetal {
	//List <Vegetal> listaCarrito=new ArrayList<>();
	@Autowired
	VegetalDao dao;
	//sin fichero adjunto
	@Autowired
	MailSender mailSender;

	/*
	 * GESTIÓN DEL SESION
	 */
	@RequestMapping(value="/signIn")
	public ModelAndView accesoLogin() {
		ModelAndView modelo = new ModelAndView("login");
		return modelo;
	}
	
	
	/*
	 * REVISAR BIEN ESTE MÉTODO
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView entrar(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session,
			ModelMap modelMap) 
	{
		ModelAndView modelo = null;
		List<Usuario>usuarios = dao.usuarioRegistrado(username, password);
		Usuario u = null;
		u = usuarios.get(0);
		System.out.println(u);
		//devolver objeto entero y ver si es distinto de null
		if ( u.getName() != null){
			
			//le pasamos los atributos que queramos para obtenerlos con el sessionScope
			session.setAttribute("user", u);
			if(u.getUsername().equals("admin")) {
				modelo=new ModelAndView("redirect:/listadoVegetal/0");
				List<Vegetal> lista = dao.listarVegetales();
				modelo.addObject("lista",lista);
			}
			else{
				List <Slide> listaSlides=dao.listarSlides();
				List<Vegetal> listaTotal=dao.listarVegetales();
				modelo=new ModelAndView("redirect:/listadoVegetales2/0");
				modelo.addObject("listaSlides",listaSlides);
				modelo.addObject("listaT",listaTotal);
			}
		}
		else {
			modelo = new ModelAndView("redirect:/signIn");
		}
		

		return modelo;
	}
	
	@RequestMapping(value="/signUp", method = RequestMethod.GET)
	public ModelAndView registrarUsuario() {
		ModelAndView modelo = new ModelAndView("acceso", "command", new Usuario());
		return modelo;
	}
	
	@RequestMapping(value="/registrar",method=RequestMethod.POST)
	public String registrar(@Valid @ModelAttribute("user") Usuario user,BindingResult result) {

		if(result.hasErrors()){  //si hay error en algún campo, se retorna de nuevo al formulario con avisos en rojo
			return "redirect:/signUp";
		}
		else {
			int p = dao.crearUsuario(user);
			System.out.println("registrar: "+p);
			return "redirect:/signIn";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	/*
	 * GESTIÓN DEL FRONT
	 */
	@RequestMapping(value="/carrito", method = RequestMethod.GET)
	public ModelAndView irCarrito(HttpSession session) {
		ModelAndView modelo=new ModelAndView("carrito");
		//modelo.addObject("listaC",listaCarrito);
		return modelo;
	}

	@RequestMapping(value="/listarVegetal")
	public ModelAndView ListarVegetal() {
		List <Vegetal> listaVegetal=dao.listarVegetales();
		List <Slide> listaSlides=dao.listarSlides();
		
		ModelAndView modelo=new ModelAndView("index");
		modelo.addObject("listaF",listaVegetal);
		modelo.addObject("listaSlides",listaSlides);
		return modelo;
	}

	@RequestMapping(value="/listadoVegetal/{pageId}")
	public ModelAndView listadoVegetal(@PathVariable int pageId) {
		int total=5;
		pageId=(pageId)*total+1;
		List<Vegetal> lista = dao.listarVegetalesPorPaginas(pageId, total);
		List <Vegetal> listaVegetal=dao.listarVegetales();
		ModelAndView modelo=new ModelAndView("index2");
		modelo.addObject("lista",lista);
		modelo.addObject("listaVegetal",listaVegetal);
		return modelo;
	}
	
	@RequestMapping(value="/buscarCategoria/{categoria}")
	public ModelAndView buscarCategoria(@PathVariable String categoria) {
		List <Vegetal> listaVegetal=dao.listarVegetalesCategoria(categoria);
		List <Slide> listaSlides=dao.listarSlides();
		List<Vegetal> listaTotal=dao.listarVegetales();
		System.out.println(categoria);
		ModelAndView modelo=new ModelAndView("/index"); 
		modelo.addObject("listaF",listaVegetal);
		modelo.addObject("listaSlides",listaSlides);
		modelo.addObject("listaT",listaTotal);
		return modelo;
	}
	
	@RequestMapping(value="/carrito/{id}")
	public String carrito(@PathVariable int id, HttpSession session ) {
		
		Vegetal producto=dao.buscarporId(id);
		List<Vegetal> listaCarrito;
		if(session.getAttribute("listaCarrito")!=null) {
			listaCarrito = (List<Vegetal>) session.getAttribute("listaCarrito");
			listaCarrito.add(producto);
			session.setAttribute("listaCarrito", listaCarrito);
		}else {
			listaCarrito = new ArrayList<Vegetal>();
			listaCarrito.add(producto);
		}
		System.out.println("Size carrito: "+listaCarrito.size());
		return "redirect:/listadoVegetales2/0";
	}

	@RequestMapping(value="listadoVegetales2/{pageId}")
	public ModelAndView listadoVegetales2(@PathVariable int pageId) {
		int total=6;
		pageId=(pageId)*total+1;
		System.out.println("comienza en: "+pageId);
		List <Vegetal> listaFiltro = dao.listarVegetalesPorPaginas(pageId, total);
		List <Vegetal> listaTotal=dao.listarVegetales();
		List <Slide> listaSlides=dao.listarSlides();
		ModelAndView modelo=new ModelAndView("index");
		modelo.addObject("listaF",listaFiltro);
		modelo.addObject("listaT",listaTotal);
		modelo.addObject("listaSlides",listaSlides);
		return modelo;
	}
	
	//modificar datos
	@RequestMapping(value="/datos", method = RequestMethod.GET)
	public ModelAndView irDatos(HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("user");
		return new ModelAndView("modificar", "command", u);
	}
	
	@RequestMapping(value="/modificarUsuario", method = RequestMethod.POST)
	public String modificarUsuario(@Valid @ModelAttribute("user") Usuario user,BindingResult result) {

		if(result.hasErrors()){  //si hay error en algún campo, se retorna de nuevo al formulario con avisos en rojo
			return "redirect:/datos";
		}
		else {
			int p = dao.modificarUsuario(user);
			System.out.println("Modificar: "+p);
			return "redirect:/listadoVegetales2/0";
		}
		
	}
	
	
/*
 * gestión del back
 * */
	@RequestMapping("/aniadirVegetal")
	public ModelAndView MostrarFormulario() {
		return new ModelAndView("aniadirVegetal", "command", new Vegetal());
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute("veg") Vegetal veg, BindingResult result) {
		if(result.hasErrors()){  //si hay error en algún campo, se retorna de nuevo al formulario con avisos en rojo
			return "redirect:/aniadirVegetal";
		}
		else {
			dao.guardar(veg);
			return "redirect:/listadoVegetal/0";
		}
	}

	@RequestMapping("/listadoVeg")
	public ModelAndView listadoEmpleados() {
		List<Vegetal> lista = dao.listarVegetales();
		return new ModelAndView("listadoVegetales", "listV", lista);
	}

	@RequestMapping(value = "/editarVegetal/{id}")
	public ModelAndView editarVegetal(@PathVariable int id) {
		Vegetal veg = dao.buscarporId(id);
		return new ModelAndView("editarVegetal", "command", veg);
	}

	@RequestMapping(value = "/editarGuardar", method = RequestMethod.POST)
	public ModelAndView editarGuardar(@ModelAttribute("vegetal") Vegetal veg) {
		System.out.println(dao.actualizar(veg));
		return new ModelAndView("redirect:listadoVegetal/0");
	}

	@RequestMapping(value = "/eliminarVegetal/{id}", method = RequestMethod.GET)
	public ModelAndView eliminarEmpleado(@PathVariable int id) {
		dao.eliminar(id);
		return new ModelAndView("redirect:../listadoVegetal/0");
	}
	
	@RequestMapping(value="/eliminarV/{id}",method=RequestMethod.GET)
	public ModelAndView eliminiarV(@PathVariable int id) {
		for(int i=0;i<listaCarrito.size();i++) {
			if (listaCarrito.get(i).getId()==id) {
				listaCarrito.remove(i);
			}
		}
		ModelAndView modelo=new ModelAndView("/carrito");
		modelo.addObject("listaC",listaCarrito);
		return modelo;
	}
}
