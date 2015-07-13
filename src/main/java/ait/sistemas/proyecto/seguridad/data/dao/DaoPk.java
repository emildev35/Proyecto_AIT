package ait.sistemas.proyecto.seguridad.data.dao;

import java.util.List;

/**
 * Interface Dao que proporciona los metodos CRUD para
 * tablas con Llaves primarias compuestas
 * @author franzemil
 *
 * @param <T>	Clase de la Entidad
 * @param <PK>	Clase de la LLave
 */
public interface DaoPk<T, PK>{

	public List<T> getall();
	public T getone(PK id);
	public int add(T table);
	public int delete(T table);
	public T update(T table);

}
