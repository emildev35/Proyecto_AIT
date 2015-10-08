package ait.sistemas.proyecto.common.report;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Clase de Soporte para la creacion de Reportes
 * 
 * @author Administrador
 *
 */
public class Util {
	
	/**
	 * Retorna un numero con formato ##.###.###,00
	 * 
	 * @param string
	 * @return
	 */
	public static String numberFormat(String string) {
		try {
			double d_valor = Double.parseDouble(string);
			DecimalFormat formater = new DecimalFormat("##,###,##0.00");
			String valor_str = formater.format(d_valor);
			return valor_str;
		} catch (NumberFormatException ex) {
			return string;
		}
	}
	
	/**
	 * Retorna un numero con formato ##.###.##
	 * 
	 * @param string
	 * @return
	 */
	public static String numberIntFormat(String string) {
		try {
			double d_valor = Double.parseDouble(string);
			DecimalFormat formater = new DecimalFormat("###.###.###.###");
			String valor_str = formater.format(d_valor);
			return valor_str;
		} catch (NumberFormatException ex) {
			return "0";
		}
	}
	
	/**
	 * Retorna un numero con formato ##.###.###,######
	 * 
	 * @param string
	 * @return
	 */
	public static String numberfloatFormat(String string) {
		try {
			double d_valor = Double.parseDouble(string);
			DecimalFormat formater = new DecimalFormat("##,###,###,###.######");
			String valor_str = formater.format(d_valor);
			return valor_str;
		} catch (NumberFormatException ex) {
			return "0";
		}
	}
	
	/**
	 * Retorna la Posicion justificada de un texto
	 * 
	 * @param text
	 * @param nextX
	 * @param textFont
	 * @param fontSize
	 * @return
	 */
	public static float justificar(String text, float nextX, PDFont textFont, float fontSize) {
		try {
			
			long text_width = (long) ((textFont.getStringWidth(text == null ? "" : text) / 1000.0f) * fontSize);
			return nextX - text_width - 5;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nextX;
	}
	
	/**
	 * Separa un String en String con el numero de caracteres i
	 * 
	 * @param text
	 * @param number
	 * @return
	 */
	public static String[] separeString(String data, int number) {
		int intpart = (int) Math.ceil((double) data.length() / (double) number);
		String[] row = new String[intpart];
		for (int i = 0; i < intpart; i++) {
			if ((i + 1) * number > data.length()) {
				row[i] = data.substring(i * number, data.length());
			} else {
				row[i] = data.substring(i * number, number * (i + 1));
			}
			
		}
		return row;
	}
	
	/**
	 * Retorna true en Caso que el el String tenga formato numerico
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isNumber(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (Exception ex) {
			
		}
		return false;
	}
	/**
	 * Retorna true en Caso que el el String tenga formato numerico
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isInt(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (Exception ex) {
			
		}
		return false;
	}
	/**
	 * Retorna true en caso que el String tenga formato de Fecha
	 * 
	 * @param text
	 * @param match
	 * @return
	 */
	public static boolean isDate(String text, String match) {
		SimpleDateFormat format = new SimpleDateFormat(match);
		try {
			format.parse(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean isFloat(String text) {
		try {
			Float.parseFloat(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
