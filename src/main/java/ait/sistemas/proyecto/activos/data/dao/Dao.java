package ait.sistemas.proyecto.activos.data.dao;

import java.util.List;

/**
 * Interface Dao que proporciona los metodos CRUD para
 * tablas con Llaves primarias no compuestas
 * @author kimberly
 *
 * @param <T>	POJO Tabla
 */
public interface Dao<T> {
	public List<T> getall();
	public T getone(long id);
	public T add(T table);
	public int delete(T table);
	public T update(T table);
}