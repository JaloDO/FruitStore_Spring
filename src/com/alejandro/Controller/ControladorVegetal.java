package com.alejandro.Controller;

import java.util.ArrayList;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	JavaMailSender emailSender;

	int contador = 1;
	/*
	 * GESTI�N DEL SESION
	 */
	@RequestMapping(value="/signIn")
	public ModelAndView accesoLogin() {
		ModelAndView modelo = new ModelAndView("login");
		return modelo;
	}
	
	
	/*
	 * REVISAR BIEN ESTE M�TODO
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView entrar( @RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session,
			ModelMap modelMap) 
	{
		ModelAndView modelo = null;
		try {
		
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
		}
		catch(Exception e) {
			return new ModelAndView("redirect:/signIn");
		}
		return modelo;
		
	}
	
	@RequestMapping(value="/signUp", method = RequestMethod.GET)
	public ModelAndView registrarUsuario() {
		ModelAndView modelo = new ModelAndView("acceso", "command", new Usuario());
		return modelo;
	}
	
	@RequestMapping(value="/registrar",method=RequestMethod.POST)
	public String registrar(@Valid @ModelAttribute("user") Usuario user,BindingResult result, HttpSession session) {

		if(result.hasErrors()){  //si hay error en alg�n campo, se retorna de nuevo al formulario con avisos en rojo
			return "redirect:/signUp";
		}
		else {
			int p = dao.crearUsuario(user);
			System.out.println("registrar: "+p);
			session.setAttribute("user", user);
			try {
				mensajeInformativo(session);
				session.removeAttribute("user");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/signIn";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("listaCarrito");
		session.removeAttribute("contador");
		return "login";
	}
	
	/*
	 * GESTION DEL CARRITO
	 */
	@RequestMapping(value="/carrito", method = RequestMethod.GET)
	public ModelAndView irCarrito(HttpSession session) {
		ModelAndView modelo=new ModelAndView("carrito");
		return modelo;
	}
	
	@RequestMapping(value="/carrito/{id}")
	public String carrito(@PathVariable int id, HttpSession session ) {
		
		Vegetal producto=dao.buscarporId(id);
		System.out.println(producto.toString());
		List<Vegetal> listaCarrito = null;
		if(session.getAttribute("listaCarrito")!=null) {
			listaCarrito = (List<Vegetal>) session.getAttribute("listaCarrito");
			listaCarrito.add(producto);
			session.setAttribute("listaCarrito", listaCarrito);
			session.setAttribute("contador", ++contador);
		}else {
			contador=1;
			listaCarrito = new ArrayList<Vegetal>();
			listaCarrito.add(producto);
			session.setAttribute("listaCarrito", listaCarrito);
			session.setAttribute("contador", contador);
		}
		System.out.println("Size carrito: "+listaCarrito.size());
		return "redirect:/listadoVegetales2/0";
	}
	
	@RequestMapping(value="/eliminarTodo", method = RequestMethod.GET)
	public String borrarCarrito(HttpSession session) {
		session.removeAttribute("listaCarrito");
		session.removeAttribute("contador");
		return "redirect:/carrito";
	}
	
	
	//METODO PAGAR
	@RequestMapping(value="/comprar", method = RequestMethod.POST)
	public String pagar(HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("user");
		if(u==null) {
			return "redirect:/signIn";
		}
		System.out.println("no entra 3");
		List<Vegetal> listaCompra = (List<Vegetal>) session.getAttribute("listaCarrito");
		session.removeAttribute("listaCarrito");
		session.removeAttribute("contador");
		int articulos = listaCompra.size();
		float importe = 0;
		for(Vegetal v:listaCompra) {
			importe += v.getPrecio();
		}
		//mas o menos ya tenemos los datos necesarios para la factura
		session.setAttribute("comprado", true);
		try {
			mensajeFactura(u, articulos, importe, listaCompra);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/carrito";
	}
	

	/*
	 * GESTI�N DEL FRONT
	 */


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
	
	
	

	@RequestMapping(value="listadoVegetales2/{pageId}")
	public ModelAndView listadoVegetales2(@PathVariable int pageId, HttpSession session) {
		session.setAttribute("comprado", false);
		int total=6;
		pageId=(pageId)*total+1;
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
	public String modificarUsuario(@Valid @ModelAttribute("user") Usuario user,BindingResult result, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("user");
		System.out.println("User sesion: "+u.toString());
		System.out.println("User modificado: "+user.toString());
		if(result.hasErrors()){  //si hay error en alg�n campo, se retorna de nuevo al formulario con avisos en rojo
			return "redirect:/datos";
		}
		else {
			if(user.getAge()!=u.getAge() 
					|| !user.getEmail().equals(u.getEmail()) 
					|| !user.getName().equals(u.getName()) 
					|| !user.getPassword().equals(u.getPassword())) {
				//se ha modificado alg�n dato
				int p = dao.modificarUsuario(user);
				//actualizar el usuario en la sesion
				session.setAttribute("user", user);
				//enviar mensaje informativo
				try {
					mensajeInformativo(session);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				return "redirect:/datos";
			}
			return "redirect:/listadoVegetales2/0";
		}
	}

	
	/*
 * gesti�n del back
 * */
	@RequestMapping("/aniadirVegetal")
	public ModelAndView MostrarFormulario() {
		return new ModelAndView("aniadirVegetal", "command", new Vegetal());
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute("veg") Vegetal veg, BindingResult result) {
		if(result.hasErrors()){  //si hay error en alg�n campo, se retorna de nuevo al formulario con avisos en rojo
			return "redirect:/aniadirVegetal";
		}
		else {
			dao.guardar(veg);
			return "redirect:/listadoVegetal/0";
		}
	}

	@RequestMapping(value = "/editarVegetal/{id}")
	public ModelAndView editarVegetal(@PathVariable int id) {
		
		Vegetal veg = dao.buscarporId(id);
		return new ModelAndView("editarVegetal", "command", veg);
	}

	@RequestMapping(value = "/editarGuardar", method = RequestMethod.POST)
	public ModelAndView editarGuardar(@Valid @ModelAttribute("veg") Vegetal veg, BindingResult result) {
		if(result.hasErrors()){
			return new ModelAndView("redirect:editarVegetal/"+veg.getId());
		}
		else {
			System.out.println(dao.actualizar(veg));
			return new ModelAndView("redirect:listadoVegetal/0");
		}
		
	}

	@RequestMapping(value = "/eliminarVegetal/{id}", method = RequestMethod.GET)
	public ModelAndView eliminarEmpleado(@PathVariable int id) {
		dao.eliminar(id);
		return new ModelAndView("redirect:../listadoVegetal/0");
	}
	
	@RequestMapping(value="/eliminarV/{id}",method=RequestMethod.GET)
	public ModelAndView eliminiarV(@PathVariable int id, HttpSession session) {
		System.out.println("Eliminar vegetal id: "+id);
		List<Vegetal> listaCarrito = null;
		try {
			session.removeAttribute("ruta");
		if(session.getAttribute("listaCarrito")!=null) 
		{
			listaCarrito = (List<Vegetal>) session.getAttribute("listaCarrito");
			int cont=0;
			for(Vegetal v: listaCarrito){
				if(v.getId()==id){
					System.out.println("eliminado: "+listaCarrito.get(cont).toString());
					listaCarrito.remove(cont);
					session.setAttribute("contador", --contador);
				}
				cont++;
			}
			if(listaCarrito.size()!=0) {
				session.setAttribute("listaCarrito", listaCarrito);
			}
		}
		
		}catch(Exception e) {
			String ruta="https://www.meme-arsenal.com/memes/32e6ea8ef744f2354bacb4510e033a8d.jpg";
			session.setAttribute("ruta", ruta);
			return new ModelAndView("redirect:/carrito");
		}
		
		return new ModelAndView("redirect:/carrito");
	}
	
	
	/*
	 *  MENSAJES
	 */
	
	private void mensajeInformativo(HttpSession session) throws MessagingException {
		Usuario destino = (Usuario) session.getAttribute("user");
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setFrom("adofruteria@gmail.com");
		helper.setTo(destino.getEmail());
		helper.setSubject("FRUTERIA ADO - TU INFORMACION");
		String contenido = 
				"<h2>Datos de tu cuenta</h2>"
				+"<div>"
				+"<ul>"
				+ "<li>Usuario, '"+destino.getUsername()+"'.</li>"
				+ "<li>Contrase&ntilde;a, '"+destino.getPassword()+"'.</li>"
				+ "<li>Email, '"+destino.getEmail()+"'.</li>"
				+ "<li>Nombre, '"+destino.getName()+"'.</li>"
				+ "<li>Edad, '"+destino.getAge()+"'.</li></ul>"
				+"</div>";
		helper.setText(contenido, true);
		emailSender.send(mimeMessage);
	}
	
	private void mensajeFactura(Usuario u, int articulos, float importe, List<Vegetal>listaCompra) throws MessagingException {
		System.out.println("Entra en factura");
		String refs="";
		try {
			System.out.println("Entra al try");
		for (Vegetal v: listaCompra) {
			refs += "#"+v.getId()+" ";
		}
		
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setFrom("adofruteria@gmail.com");
		helper.setTo(u.getEmail());
		helper.setSubject("FRUTERIA ADO - TU COMPRA");
		String contenido = 
				"<h2>Has realizado una compra en nuestra tienda</h2>"
				+"<div>"
				+"<table><thead>"
				+ "<tr><th>Ref. Art�culos</th>"
				+"<th>N� Art�culos</th>"
				+"<th>Importe total</th></tr></thead>"
				+"<tbody><tr><td>"+refs+"</td>"
				+ "<td>"+articulos+"</td>"
				+"<td><b>"+importe+"</b></td></tr>"
				+"</tbody></table>"
				+"</div>";
		helper.setText(contenido, true);
		emailSender.send(mimeMessage);
		}catch(Exception e) {
			
		}
	}


}
