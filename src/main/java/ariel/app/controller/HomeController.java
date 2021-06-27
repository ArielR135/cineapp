package ariel.app.controller;

// import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
// import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ariel.app.model.Pelicula;
import ariel.app.service.IPeliculasService;
import ariel.app.util.Utileria;

@Controller
public class HomeController {
	
	@Autowired
	private IPeliculasService servicePeliculas;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha") String fecha, Model model){
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> peliculas = servicePeliculas.buscarTodas();

		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("peliculas", peliculas);
		
		System.out.println("Buscando todas las peliculas en exhibicion para la fecha: " + fecha);
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		
		List<String> listaFechas = Utileria.getNextDays(4);

		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
		// peliculas.add("Rapido y furioso");
		// peliculas.add("El aro 2");
		// peliculas.add("Aliens");
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("peliculas", peliculas);

		return "home";
	}

	// @RequestMapping(value = "/detail/{id}/{fecha}", method = RequestMethod.GET)
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha) {
		System.out.println("Buscando horarios para la película: " + idPelicula);
		System.out.println("Para la fecha: " + fecha);
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		/*
		 * String tituloPelicula = "Rapidos y furiosos"; int duracion = 136;
		 * double precioEntrada = 50;
		 */
		/*
		 * model.addAttribute("titulo", "Rapidos y furiosos");
		 * model.addAttribute("duracion", 136); model.addAttribute("precio",
		 * 50);
		 */

		// ToDo - Buscar en la base de datos los horarios.

		return "detalle";
	}

}