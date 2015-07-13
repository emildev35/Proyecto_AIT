package ait.sistemas.proyecto.seguridad.view.estr.reporte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.seguridad.component.report.Column;
import ait.sistemas.proyecto.seguridad.component.report.PDFTableGenerator;
import ait.sistemas.proyecto.seguridad.component.report.Table;
import ait.sistemas.proyecto.seguridad.component.report.TableBuilder;

public class ReportPdf {
	 // Page configuration
    private static final PDRectangle PAGE_SIZE = PDRectangle.LETTER;
    private static final float MARGIN = 20;
    private static final boolean IS_LANDSCAPE = false;

    // Font configuration
    private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
    private static final float FONT_SIZE = 11;

    // Table configuration
    private static final float ROW_HEIGHT = 15;
    private static final float CELL_MARGIN = 2;
    
    public void getPdf(String[][] data) throws IOException{
        PDDocument report = new  PDFTableGenerator().generatePDF(createContent(data));
        report.save("Ejemplo-de-Tabla.pdf");
    }

    private static Table createContent(String[][] data) {
        List<Column> columns = new ArrayList<Column>();
        columns.add(new Column("ID", 40));
        columns.add(new Column("COD", 40));
        columns.add(new Column("NOMBRE DEL MENU", 230));
        columns.add(new Column("NIVEL", 40));
        columns.add(new Column("PROGRAMA", 230));
 
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
            .build();
        return table;
    }   
}
