package ariel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ariel.app.model.Noticia;
import ariel.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private INoticiasService serviceNoticias;

	@GetMapping(value = "/create")
	public String crear() {
		return "noticias/formNoticia";
	}

	@PostMapping(value = "/save")
	public String guardar(Noticia noticia) {

		// Pendiente: Guardar el objeto noticia en la BD

		serviceNoticias.guardar(noticia);

		return "noticias/formNoticia";
	}

}
