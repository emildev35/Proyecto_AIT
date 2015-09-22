package ait.sistemas.proyecto.common.report;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PageSize {
	
    private static final float POINTS_PER_INCH = 72;
    
    public static final PDRectangle OFICIO = new PDRectangle(8.5f * POINTS_PER_INCH,
            13f * POINTS_PER_INCH);
}
