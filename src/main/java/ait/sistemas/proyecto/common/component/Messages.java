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
	
	/**
	 * Mensaje Validadcion de StringLength	
	 * @param min
	 * @param max
	 * @return	String
	 */
	public static final String STRING_LENGTH_MESSAGE(int min, int max)
	{return String.format("La longitud de caracteres debe ser menor a %d y superior a %d", min, max);};
	/**
	 * Mensaje Confirmacion en el dialogo
	 * @param value
	 * @return
	 */
	public static final String CONFIRM_DIALOG_DELETE_MESSAGE(String value)
	{return String.format("Esta Uds. seguro de eliminar el registro %s", value);};
	
	public static final String EMPTY_MESSAGE = "EL campo no debe estar Vacio";
	/**
	 * Este Mensaje se utilizara cuando el usuario desee eliminar un registro
	 */
	public static final String CONFIRM_DELETE_MESSAGE = "Esta Uds. seguro de eliminar el registro";
	public static final String PASSWORD_MESSAGE = "La constraseña debe contener como minimo un digito,"
			+ " un caracter en minuscula, uno en mayuscula,"
			+ " un caracter especial y contar con una longitud minima de 8 caracteres y una maxima de 15";
	public static final String ERROR_EQUALS_PASSWORD = "Las contraseñas no son iguales";
	public static final String LOGIN_ERROR = "Login Incorrecto";
	public static final String NUMBER_VALUE = "El formato no es correcto";
	public static final String PIN_MESSAGES = "* El PIN Generado es de uso exclusivo para Autorizar Documentos de Movimientos de Activos Fijos";
	public static final String PIN_MESSAGES_2 = "* El PIN Generado esta bajo su responsabilidad y no debe ser divulgado a terceros";

	public static String FILL_FIRST(String caption) {
		return String.format("El campo de %s debe ser llenavo con anterioridad", caption);
	}
	public static String MANY_CONFIRM_DELETE_MESSAGE(int element, String caption) {
		return String.format("Esta Uds. seguro de eliminar los %d %s", element, caption);
	}
}
