package ariel.app.service;

import java.util.List;

import ariel.app.model.Pelicula;

public interface IPeliculasService {
	void insertar(Pelicula pelicula); 
	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int idPelicula);
}
