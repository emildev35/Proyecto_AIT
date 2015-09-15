package ait.sistemas.proyecto.common.report;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Clase de Soporte para la creacion de Reportes
 * @author Administrador
 *
 */
public class Util {

	/**
	 * Retorna un numero con formato ##.###.###,## 
	 * @param string
	 * @return
	 */
	public static String numberFormat(String string) {
		return null;
	}

	/**
	 * Retorna la Posicion justificada de un texto
	 * @param text
	 * @param nextX
	 * @param textFont
	 * @param fontSize
	 * @return
	 */
	public static float justificar(String text, float nextX, PDFont textFont, float fontSize) {
		try {
			long text_width = (long) ((textFont.getStringWidth(text) / 1000.0f) * fontSize);
			return nextX - text_width -5;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nextX;
	}

	/**
	 * Separa un String en  String con el numero de caracteres i
	 * @param text
	 * @param i
	 * @return
	 */
	public static String[] separeString(String text, int i) {
		return null;
	}
	

}
