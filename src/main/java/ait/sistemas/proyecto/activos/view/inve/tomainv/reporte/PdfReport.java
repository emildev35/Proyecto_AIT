package ait.sistemas.proyecto.activos.view.inve.tomainv.reporte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.common.component.PathValues;
import ait.sistemas.proyecto.common.report.Column;
import ait.sistemas.proyecto.common.report.PDFTableGenerator;
import ait.sistemas.proyecto.common.report.Table;
import ait.sistemas.proyecto.common.report.TableBuilder;

public class PdfReport {
	 // Page configuration
    private static final PDRectangle PAGE_SIZE = PDRectangle.LEGAL;
    private static final float MARGIN = 40;
    private static final boolean IS_LANDSCAPE = true;

    // Font for textFont
    private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
    private static final float FONT_SIZE = 10;
    
    /**
     * Font for footer report
     */
    private static final PDFont FOOTER_FONT = PDType1Font.HELVETICA;
    private static final float FOOTER_FONT_SIZE = 8;
    
    /**
     * Font for footer report
     */
    private static final PDFont HEADER_FONT = PDType1Font.HELVETICA;
    private static final float HEADER_FONT_SIZE = 8;
    
    /**
     * Font for footer report
     */
    private static final PDFont TITLE_FONT = PDType1Font.HELVETICA_BOLD;
    private static final float TITLE_FONT_SIZE = 14;
    
    /**
     * Font for footer report
     */
    private static final PDFont SUBTITLE_FONT = PDType1Font.HELVETICA;
    private static final float SUBTITLE_FONT_SIZE = 12;
    
    private static final float ROW_HEIGHT = 14;
    private static final float CELL_MARGIN = 2;
    
    private static final int HEADER_SIZE = 3;
    
    public static final String SAVE_PATH = PathValues.PATH_REPORTS + String.valueOf(new Date().getTime()) + ".pdf";
    
    
    public boolean getPdf(String[][] data) throws IOException{
    	
        return new PDFTableGenerator().generatePDF(createContent(data), SAVE_PATH);
        
    }

    private static Table createContent(String[][] data) {
        List<Column> columns = new ArrayList<Column>();
        columns.add(new Column("Dep", 30));	
        columns.add(new Column("Codigo", 40));
        columns.add(new Column("Fecha", 60));
        columns.add(new Column("Codigo Act", 50));
        columns.add(new Column("Nombre del Activo", 400));
        columns.add(new Column("Servidor Publico Responsable", 200));	
        columns.add(new Column("Observaciones", 100));
        columns.add(new Column("SR", 20));
        columns.add(new Column("DR", 20));
        columns.add(new Column("MR", 20));
        
 
        String[][] content = data;

        float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);

        Table table = new TableBuilder()
            .setCellMargin(CELL_MARGIN)
            .setColumns(columns)
            .setContent(content)
            .setHeight(tableHeight)
            .setNumberOfRows(content.length)
            .setRowHeight(ROW_HEIGHT)
            .setMargin(MARGIN)
            .setPageSize(PAGE_SIZE)
            .setLandscape(IS_LANDSCAPE)
            .setTextFont(TEXT_FONT)
            .setFontSize(FONT_SIZE)
            .setHeaderFont(HEADER_FONT)
            .setFontSizeHeader(HEADER_FONT_SIZE)
            .setFooterFont(FOOTER_FONT)
            .setFontSizeFooter(FOOTER_FONT_SIZE)
            .setTitleFont(TITLE_FONT)
            .setFontSizeTitle(TITLE_FONT_SIZE)
            .setSubTitleFont(SUBTITLE_FONT)
            .setFontSizeSubTitle(SUBTITLE_FONT_SIZE)
            .setHeaderSize(HEADER_SIZE)
            .setUnidad("XXXXXX")
            .setDependencia("XXXXX")
            .setUsuario("XXXXXX")
            .setTitle("INMUEBLES")
            .setSubTitle("")
            .build();
        return table;
    }   
}
