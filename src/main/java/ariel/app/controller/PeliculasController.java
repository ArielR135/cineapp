package ariel.app.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ariel.app.model.Pelicula;
import ariel.app.service.IPeliculasService;
import ariel.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

	@Autowired
	private IPeliculasService servicePeliculas;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Pelicula> lista = servicePeliculas.buscarTodas();
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}

	@GetMapping("/create")
	public String crear() {
		return "peliculas/formPelicula";
	}

	@PostMapping("/save")
	public String guardar(Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {

		/*
		 * if (result.hasErrors()) { System.out.println("Existieron errores");
		 * return "peliculas/formPelicula"; }
		 */

		for (ObjectError error : result.getAllErrors()) {
			System.out.println(error.getDefaultMessage());
		}

		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			pelicula.setImagen(nombreImagen);
		}

		System.out.println("Recibiendo objeto pelicula: " + pelicula);
		System.out.println("Elementos en la lista antes de la insersion: " + servicePeliculas.buscarTodas().size());

		servicePeliculas.insertar(pelicula);

		System.out.println("Elementos en la lista despues de la insersion: " + servicePeliculas.buscarTodas().size());

		attributes.addFlashAttribute("mensaje", "El registro fue guardado");

		// return "peliculas/formPelicula";
		return "redirect:/peliculas/index";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
