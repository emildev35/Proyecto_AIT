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
	 * Mensaje utilzado para presentar errores relacionados con la deṕendecias
	 * de objetivos dentro de la base de datos
	 * 
	 * @param elemento
	 * @return
	 */
	public static final String FOREIGN_RELATION_ERROR(String elemento) {
		return String.format("Verifique las Dependecias del " + elemento);
	};
	
	/**
	 * Mensaje Validadcion de StringLength
	 * 
	 * @param min
	 * @param max
	 * @return String
	 */
	public static final String STRING_LENGTH_MESSAGE(int min, int max) {
		return String.format("La longitud de caracteres debe ser menor a %d y superior a %d", max, min);
	};
	
	/**
	 * Mensaje Confirmacion en el dialogo
	 * 
	 * @param value
	 * @return
	 */
	public static final String CONFIRM_DIALOG_DELETE_MESSAGE(String value) {
		return String.format("Esta Uds. seguro de eliminar el registro %s", value);
	};
	
	public static final String EMPTY_MESSAGE = "EL campo no debe estar Vacio";
	/**
	 * Este Mensaje se utilizara cuando el usuario desee eliminar un registro
	 */
	public static final String CONFIRM_DELETE_MESSAGE = "Esta Uds. seguro de eliminar el registro";
	/**
	 * Validacion de Contraseña
	 */
	public static final String PASSWORD_MESSAGE = "La constraseña debe contener como minimo un digito,"
			+ " un caracter en minuscula, uno en mayuscula,"
			+ " un caracter especial y contar con una longitud minima de 8 caracteres y una maxima de 15";
	/**
	 * Validacion de Contrasenas Iguales
	 */
	public static final String ERROR_EQUALS_PASSWORD = "Las contraseñas no son iguales";
	/**
	 * Mensaje de Login
	 */
	public static final String LOGIN_ERROR = "Login Incorrecto";
	/**
	 * Formato Numerico Incorrecto
	 */
	public static final String NUMBER_VALUE = "El formato no es correcto";
	
	public static final String PIN_MESSAGES = "* El PIN Generado es de uso exclusivo para Autorizar Documentos de Movimientos de Activos Fijos";
	public static final String PIN_MESSAGES_2 = "* El PIN Generado esta bajo su responsabilidad y no debe ser divulgado a terceros";
	/**
	 * Seleccione Tipo Movimiento
	 */
	public static final String SELECT_MOVIMIENTO = "Seleccione un Movimiento";
	/**
	 * Seleccione el Acta
	 */
	public static final String SELECT_ACTA = "Seleccione el Acta";
	/**
	 * Mensaje utilizado cuando no se encontro un tipo de cambio al momento de
	 * seleccionar un fecha
	 */
	public static final String EMPTY_TIPO_CAMBIO = "No se encontro el <strong>Tipo de Cambio</strong> favor introducirlo de manera manual";
	/**
	 * Mensaje utilizado en caso que no se Seleccione un elemento de un Grid
	 */
	public static final String EMPTY_GRID = "Debe seleccionar al menos un elemento del Grid";
	/**
	 * Mensaje para verificar que una fecha sea mayor a la ultima depreciacion
	 */
	public static final String INVALIT_DATE = "La fecha debe ser Mayor a la fecha de la ultima depreciacion";
	public static final String BAD_DATE= "Este Activo viene del Futuro?";
	/**
	 * Utilizado en casos donde el String que se desee utilizar tenga el valor
	 * de null
	 */
	public static final String NOT_FOUND_STRING = "No Disponible";
	/**
	 * Mesaje para la impresion del etiquetas de Activos
	 */
	public static final String CODE_BAR_INFO = "Debe seleccionar almenos un Activo del grid";
	/**
	 * Mensaje de error para el reporte varios
	 */
	public static final String REPORT_MESSAGE = "Dene introducir el nombre del Reporte y Seleccionar al menos una columna";
	/**
	 * Mensaje presionar tecla ENTER
	 */
	public static final String KEY_ENTER = "Para el registro debe presionar la tecla ENTER o INTRO";
	/***
	 * Mensaje de Campos requeridos
	 */
	public static final String REQUIED_FIELDS = "Los campos con (*) son requeridos";
	/**
	 * Mensaje de Seleccione Dependencia
	 */
	public static final String SELECT_DEPENDENCIA = "Seleccione una Dependencia";
	/**
	 * Todas las Dependencias
	 */
	public static final String ALL_DEP = "TODAS LAS DEPENDENCIAS";
	/**
	 * Expiracion de Session
	 */
	public static final String TIMEOUT_SESSION = "Session Expirada Presiones F5";
	/**
	 * Presione el Boton de Imprimir
	 */
	public static final String PRESS_PRINT_BTN = "Presione el boton imprimir para Visualizar el Reporte";
	public static final String PRESS_BUTTON_UPDATE = "Presione e boton de modificar para actualizar el Registro";
	public static final String PRESS_BUTTON_DELETE = "Presione e boton de Eliminar para actualizar el Registro";
	public static final String TIPO_CAMBIO = "Tipo de Cambio No Existe a ala Fecha";
	
	public static String FILL_FIRST(String caption) {
		return String.format("El campo de %s debe ser llenavo con anterioridad", caption);
	}
	
	public static String MANY_CONFIRM_DELETE_MESSAGE(int element, String caption) {
		return String.format("Esta Uds. seguro de eliminar los %d %s", element, caption);
	}
	/**
	 * llenar el campo nuevamente
	 */
	public static final String LLENAR_NUEVAMENTE = "Porfavor llene los campos nuevamente";
	/**
	 * los campos son distintos
	 */
	public static final String CAMPOS_DSITINTOS = "Los campos son disitntos";
	/**
	 * llenar la resolucion y la fecha
	 */
	public static final String LLENAR_CAMPOS_RES = "Debe llenar los campos No. Resolucion y Fecha";
	/**
	 * La resolucion no existe
	 */
	public static final String NO_EXISTE_RESOL = "La Resolucion no existe";
	/**
	 * La fecha de Incorporacion no puede ser menor a la Fecha de Compra
	 */
	public static final String BAD_FECHA_INCORPORACION = "La Fecha de Incorporacion no Puede ser menor a la Fecha de Compra";
	/**
	 * Activo no Seleccionado
	 */
	public static final String ACTIVO_NO_ENCONTRADO = "Activo no Seleccionado";
	/**
	 * Codigo de Activo no existente
	 */
	public static final String ACTIVO_NO_ENCONTRADO_DB = "CODIGO DE ACTIVO NO EXISTENTE, INTRODUZCA UN CODIGO DE ACTIVO VALIDO";
}
