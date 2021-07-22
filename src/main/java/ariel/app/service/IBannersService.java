package ariel.app.service;

import java.util.List;

import ariel.app.model.Banner;

public interface IBannersService {
	void insertar(Banner banner); 
	List<Banner> buscarTodos();
}
