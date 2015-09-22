package ait.sistemas.proyecto.common.report;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 * sacar el tamaño para imprimir una hoja oficio
 * @author Kimberly
 *
 */
public class PageSize {
	
    private static final float POINTS_PER_INCH = 72;
    /**
     * tamaño de pagina oficio 8.5 pulgadas x 13 pulgadas
     */
    public static final PDRectangle OFICIO = new PDRectangle(8.5f * POINTS_PER_INCH,
            13f * POINTS_PER_INCH);
}
