package com.alejandro.Controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
	List <Vegetal> listaCarrito=new ArrayList<>();
	@Autowired
	VegetalDao dao;

	@RequestMapping(value="/acceder")
	public ModelAndView accesoLogin() {
		ModelAndView modelo = new ModelAndView("login");
		return modelo;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView entrar(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session,
			ModelMap modelMap) 
	{
		ModelAndView modelo = null;
		if (dao.usuarioRegistrado("admin", password)){
			session.setAttribute("username", username);
			modelo=new ModelAndView("/index2");
			List<Vegetal> lista = dao.listarVegetales();
			modelo.addObject("lista",lista);
			modelo.addObject("username", username);
		}
		else{
			session.setAttribute("username", username);
			List <Slide> listaSlides=dao.listarSlides();
			List<Vegetal> listaTotal=dao.listarVegetales();
			modelo=new ModelAndView("/index"); 
			modelo.addObject("listaSlides",listaSlides);
			modelo.addObject("listaT",listaTotal);
		}

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
	public ModelAndView carrito(@PathVariable int id ) {
		//dao.aniadircarrito(id);
		Vegetal producto=dao.buscarporId(id);
		listaCarrito.add(producto);
		ModelAndView modelo=new ModelAndView("/carrito");
		modelo.addObject("listaC",listaCarrito);
		return modelo;
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
	
	
/*
 * gestión del back
 * */
	@RequestMapping("/aniadirVegetal")
	public ModelAndView MostrarFormulario() {
		return new ModelAndView("aniadirVegetal", "command", new Vegetal());
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute("veg") Vegetal veg) {
		dao.guardar(veg);
		return new ModelAndView("redirect:/listadoVegetal/0");
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
		dao.actualizar(veg);
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
