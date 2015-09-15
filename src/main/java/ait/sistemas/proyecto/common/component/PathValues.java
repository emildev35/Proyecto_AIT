package ait.sistemas.proyecto.common.component;

public class PathValues {
	
//	public static final String PATH_REPORTS = "C:\\Reportes\\";
	public static final String PATH_REPORTS = System.getProperty("os.name").startsWith("Windows")?"C:\\Reportes\\":"/tmp/";
	
//	public static final String CODE_PATH = "C:\\Reportes\\Codigos\\";
	public static final String CODE_PATH = System.getProperty("os.name").startsWith("Windows")?"C:\\Reportes\\Codigos\\":"/tmp/";
}
