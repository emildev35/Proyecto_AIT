package ait.sistemas.proyecto.common.component;

/**
 * Clase donde se almacenan las rutas donde se almacenar las rutas para los
 * @author franzemil
 *
 */
public class PathValues {
	
//	public static final String PATH_REPORTS = "C:\\Reportes\\";
	public static final String PATH_REPORTS = System.getProperty("os.name").startsWith("Windows")?"C:\\Reportes\\":"/tmp/";
	
//	public static final String CODE_PATH = "C:\\Reportes\\Codigos\\";
	public static final String CODE_PATH = System.getProperty("os.name").startsWith("Windows")?"C:\\Reportes\\Codigos\\":"/tmp/";

	public static final String IMAGE_UPLOAD =  System.getProperty("os.name").startsWith("Windows")?"C:\\Reportes\\imgActivos\\":"/tmp/";

	public static final String DOCUMENT_PATH = System.getProperty("os.name").startsWith("Windows")?"C:\\Reportes\\docActivos\\":"/tmp/";
}
