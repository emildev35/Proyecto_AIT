package ait.sistemas.proyecto.common.component;


public class Messages {
	/**
	 * Este Mensaje se utilizara cuando un accion de registro en base de datos
	 * sea exitosa
	 */
	public static final String SUCCESS_MESSAGE = "Operacion Exitosa";
	/**
	 * Este Mensaje se utilizara cuando un accion de registro en base de datos
	 * retorne una Exepcion
	 */
	public static final String NOT_SUCCESS_MESSAGE = "Operacion sin Exito";
	
	/**
	 * Mensaje utilzado para presentar errores relacionados con la deṕendecias de objetivos
	 * dentro de la base de datos
	 * @param elemento
	 * @return
	 */
	public static final String FOREIGN_RELATION_ERROR(String elemento)
	{return String.format("Verifique las Dependecias del " + elemento );};
	
	
	public static final String STRING_LENGTH_MESSAGE(int min, int max)
	{return String.format("La longitud de caracteres debe ser menor a %d y superior a %d", min, max);};
	
	public static final String CONFIRM_DIALOG_DELETE_MESSAGE(String value)
	{return String.format("Esta Uds. seguro de eliminar el registro %s", value);};
	
	public static final String EMPTY_MESSAGE = "EL campo no debe estar Vacio";
	/**
	 * Este Mensaje se utilizara cuando el usuario desee eliminar un registro
	 */
	public static final String CONFIRM_DELETE_MESSAGE = "Esta Uds. seguro de eliminar el registro";

	public static String FILL_FIRST(String caption) {
		return String.format("El campo de %s debe ser llenavo con anterioridad", caption);
	}
}
