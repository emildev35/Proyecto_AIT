package ait.sistemas.proyecto.activos.view.inve.tomainv.reporte;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import ait.sistemas.proyecto.common.report.Table;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.ui.UI;

/**
 * Clase encargada de generar el Pdf del Reporte de Toma de Inventario Fisico
 * 
 * @author franzemil
 *
 */
public class TomaInvFisGenerator {
	
	private PDDocument doc;
	
	public boolean generatePDF(Table table, String savePath) throws IOException {
		boolean result = false;
		doc = null;
		try {
			doc = new PDDocument();
			drawTable(doc, table);
			doc.save(savePath);
			result = true;
		} finally {
			if (doc != null) {
				doc.close();
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public void drawTable(PDDocument doc, Table table) throws IOException {
		Integer rowsPerPage = new Double(Math.floor(table.getHeight() / table.getRowHeight())).intValue() - 1;
		PDPage page;
		PDPageContentStream contentStream = null;
		float tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
				.getHeight() - table.getMargin();
		
		String dependencia = "";
		String nro_resolucion = "";
		int r = table.getHeaderSize();
		
		for (int i = 0; i < table.getContent().length; i++) {
			
			if (!table.getContent()[i][0].equals(dependencia) && contentStream != null) {
				tableTopY -= table.getRowHeight() * 1.15;
				r++;
				contentStream.drawLine(table.getMargin(), tableTopY, table.getWidth() + table.getMargin(), tableTopY);
			}
			
			if (!table.getContent()[i][0].equals(dependencia)) {
				
				if (contentStream != null) {
					contentStream.close();
				}
				page = generatePage(doc, table);
				contentStream = generateContentStream(doc, page, table);
				tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
						.getHeight() - table.getMargin();
				tableTopY -= table.getRowHeight() * table.getHeaderSize();
				
				dependencia = table.getContent()[i][0];
				nro_resolucion = table.getContent()[i][1];
				r = table.getHeaderSize();
				writeHeader(contentStream, tableTopY, table);
				drawCurrentPageDependencia(table, new String[] { "Dependencia : " + dependencia,
						"Nro. Resolucion : " + nro_resolucion }, contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				r++;
				drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				r++;
				drawTableGridObservacion(table, new String[] { "Observaciones" }, contentStream, tableTopY);
				drawCurrentPageHeaderObservacion(table, new String[] { "Observaciones" }, contentStream, tableTopY);
				
			}
			if (!table.getContent()[i][1].equals(nro_resolucion)) {
				
				if (contentStream != null) {
					contentStream.close();
				}
				page = generatePage(doc, table);
				contentStream = generateContentStream(doc, page, table);
				tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
						.getHeight() - table.getMargin();
				tableTopY -= table.getRowHeight() * table.getHeaderSize();
				nro_resolucion = table.getContent()[i][1];
				r = table.getHeaderSize();
				writeHeader(contentStream, tableTopY, table);
				drawCurrentPageDependencia(table, new String[] { "Dependencia : " + dependencia,
						"Nro. Resolucion : " + nro_resolucion }, contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				r++;
				drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				
			}
			/**
			 * Dibujado de los Datos
			 */
			String[] current = Arrays.copyOfRange(table.getContent()[i], 2, table.getContent()[i].length);
			tableTopY -= table.getRowHeight();
			r++;
			drawCurrentPage(table, current, contentStream, tableTopY);
			if (r >= rowsPerPage) {
				if (contentStream != null) {
					contentStream.close();
				}
				page = generatePage(doc, table);
				contentStream = generateContentStream(doc, page, table);
				tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
						.getHeight() - table.getMargin();
				tableTopY -= table.getRowHeight() * table.getHeaderSize();
				
				dependencia = table.getContent()[i][0];
				nro_resolucion = table.getContent()[i][1];
				r = table.getHeaderSize();
				writeHeader(contentStream, tableTopY, table);
				drawCurrentPageDependencia(table, new String[] { "Dependencia : " + dependencia,
						"Nro. Resolucion : " + nro_resolucion }, contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				r++;
				drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				r++;
				drawTableGridObservacion(table, new String[] { "Observaciones" }, contentStream, tableTopY);
				drawCurrentPageHeaderObservacion(table, new String[] { "Observaciones" }, contentStream, tableTopY);
			}
			if (!table.getContent()[i][7].equals("Ninguna") && table.getContent()[i][7] != null) {
				tableTopY -= table.getRowHeight();
				drawCurrentPage(table, new String[] { "", "", "", table.getContent()[i][7] }, contentStream, tableTopY);
				r++;
				if (r >= rowsPerPage) {
					if (contentStream != null) {
						contentStream.close();
					}
					page = generatePage(doc, table);
					contentStream = generateContentStream(doc, page, table);
					tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
							.getHeight() - table.getMargin();
					tableTopY -= table.getRowHeight() * table.getHeaderSize();
					
					dependencia = table.getContent()[i][0];
					nro_resolucion = table.getContent()[i][1];
					r = table.getHeaderSize();
					writeHeader(contentStream, tableTopY, table);
					drawCurrentPageDependencia(table, new String[] { "Dependencia : " + dependencia,
							"Nro. Resolucion : " + nro_resolucion }, contentStream, tableTopY);
					tableTopY -= table.getRowHeight();
					r++;
					drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
					drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
					tableTopY -= table.getRowHeight();
					r++;
					drawTableGridObservacion(table, new String[] { "Observaciones" }, contentStream, tableTopY);
					drawCurrentPageHeaderObservacion(table, new String[] { "Observaciones" }, contentStream, tableTopY);
				}
			}
			
		}
		
		contentStream.close();
		drawFooter(doc, table);
		
	}
	
	@SuppressWarnings("deprecation")
	private void drawCurrentPageHeaderObservacion(Table table, String[] lineContent, PDPageContentStream contentStream,
			float nextTextY) throws IOException {
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		float copynextTextY = nextTextY;
		
		float finalnextX = table.getMargin() + table.getColumns().get(0).getWidth() + table.getColumns().get(1).getWidth()
				+ table.getColumns().get(2).getWidth() + table.getColumns().get(3).getWidth()
				+ table.getColumns().get(4).getWidth();
		
		String text = lineContent[0].replace("\n", "");
		copynextTextY -= table.getRowHeight() - table.getCellMargin();
		contentStream.beginText();
		long text_width = (long) ((long) ((table.getTextFont().getStringWidth(text) / 1000.0f) * table.getFontSize()));
		contentStream.moveTextPositionByAmount(finalnextX / 2 - text_width / 2, copynextTextY);
		contentStream.showText(text);
		contentStream.endText();
		
	}
	
	@SuppressWarnings("deprecation")
	private void drawTableGridObservacion(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		float nextY = tableTopY;
		float nextX = table.getMargin() + table.getColumns().get(0).getWidth() + table.getColumns().get(1).getWidth()
				+ table.getColumns().get(2).getWidth();
		
		float finalnextX = table.getMargin() + table.getColumns().get(0).getWidth() + table.getColumns().get(1).getWidth()
				+ table.getColumns().get(2).getWidth() + table.getColumns().get(3).getWidth()
				+ table.getColumns().get(4).getWidth();
		
		for (int i = 0; i <= 1; i++) {
			contentStream.drawLine(nextX, nextY, finalnextX, nextY);
			nextY -= table.getRowHeight();
		}
		final float tableBottomY = tableTopY - table.getRowHeight();
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
		contentStream.drawLine(finalnextX, tableTopY, finalnextX, tableBottomY);
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	private void drawFooter(PDDocument doc, Table table) {
		int numofPages = doc.getPages().getCount();
		int actual_row = 1;
		for (PDPage page : doc.getPages()) {
			try {
				PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);
				contentStream.drawLine(table.getMargin(), table.getMargin(), table.getWidth() + table.getMargin(),
						table.getMargin());
				contentStream.beginText();
				contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
				contentStream.moveTextPositionByAmount(table.getMargin(), table.getMargin() - 8);
				contentStream.showText(new String().format("Página %d de %d", actual_row, numofPages));
				contentStream.endMarkedContent();
				contentStream.endText();
				contentStream.beginText();
				contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
				contentStream.moveTextPositionByAmount(table.getWidth() - 3 * table.getMargin(), table.getMargin() - 8);
				contentStream.showText("S/R: Sin Registro Previo");
				contentStream.endText();
				contentStream.beginText();
				contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
				contentStream.moveTextPositionByAmount(table.getWidth() - 3 * table.getMargin(), table.getMargin() - 16);
				contentStream.showText("D/R: Diferencias en el Servidor Responsable");
				contentStream.endText();
				contentStream.beginText();
				contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
				contentStream.moveTextPositionByAmount(table.getWidth() - 3 * table.getMargin(), table.getMargin() - 24);
				contentStream.showText("A/D: Activo duplicado en el Inventario");
				contentStream.endText();
				contentStream.close();
				
			} catch (IOException e) {
			}
			actual_row++;
		}
	}
	
	private void drawCurrentPage(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		
		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		writeContentLine(strings, contentStream, nextTextX, nextTextY, table);
	}
	
	private void drawCurrentPageHeader(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		
		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		writeContentLineHeader(strings, contentStream, nextTextX, nextTextY, table);
	}
	
	private void drawCurrentPageDependencia(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		
		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		writeContentLineDependencia(strings, contentStream, nextTextX, nextTextY, table);
	}
	
	@SuppressWarnings("deprecation")
	private void drawTableGrid(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		float nextY = tableTopY;
		
		for (int i = 0; i <= 1; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(), nextY);
			nextY -= table.getRowHeight();
		}
		final float tableBottomY = tableTopY - table.getRowHeight();
		
		float nextX = table.getMargin();
		
		for (int i = 0; i < strings.length; i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
			nextX += table.getColumns().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
	}
	
	@SuppressWarnings("deprecation")
	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
			Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		
		for (int i = 0; i < lineContent.length; i++) {
			if (i == 5)
				continue;
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			nextTextX += table.getColumns().get(i > 5 ? i - 1 : i).getWidth();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void writeContentLineHeader(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		float copynextTextY = nextTextY;
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i].replace("\n", "");
			copynextTextY = nextTextY;
			contentStream.beginText();
			long text_width = (long) ((long) ((table.getTextFont().getStringWidth(text) / 1000.0f) * table.getFontSize()));
			contentStream.moveTextPositionByAmount(nextTextX + table.getColumns().get(i).getWidth() / 2 - text_width / 2,
					copynextTextY);
			contentStream.showText(text);
			contentStream.endText();
			nextTextX += table.getColumns().get(i).getWidth();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void writeContentLineDependencia(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			nextTextX += table.getWidth() / 6;
		}
	}
	
	private PDPage generatePage(PDDocument doc, Table table) {
		PDPage page = new PDPage();
		page.setMediaBox(table.getPageSize());
		page.setRotation(table.isLandscape() ? 90 : 0);
		doc.addPage(page);
		return page;
	}
	
	@SuppressWarnings("deprecation")
	private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, Table table) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, false, false);
		if (table.isLandscape()) {
			contentStream.concatenate2CTM(0, 1, -1, 0, table.getPageSize().getWidth(), 0);
		}
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		return contentStream;
	}
	
	@SuppressWarnings("deprecation")
	private void writeHeader(PDPageContentStream contentStream, float nextTextY, Table table) throws IOException {
		
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		contentStream.setFont(table.getHeaderFont(), table.getFontSizeheader());
		
		float nextTextX = table.getMargin();
		float nextTextXCopy = nextTextX;
		nextTextY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight()
				- table.getMargin();
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		
		contentStream.showText(usuario.getDependecia());
		
		contentStream.endText();
		
		long size_header = table.isLandscape() ? 800 : 450;
		Date date = new Date();
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = fechaHora.format(date);
		
		nextTextX += size_header;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Fecha : " + fecha);
		contentStream.endText();
		
		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight() * 0.5;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(usuario.getUnidad());
		contentStream.endText();
		
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		String strhora = hora.format(date);
		
		nextTextX += size_header;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Hora : " + strhora);
		contentStream.endText();
		
		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight() * 0.5;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		
		contentStream.showText(usuario.getFull_name());
		contentStream.endText();
		
		contentStream.setFont(table.getTitleFont(), table.getFontSizetitle());
		
		nextTextY -= table.getRowHeight();
		
		contentStream.beginText();
		long text_width = (long) ((table.getTitleFont().getStringWidth(table.getTitle()) / 1000.0f) * table.getFontSizetitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2) - (text_width / 2) + (table.getMargin() / 2), nextTextY);
		contentStream.showText(table.getTitle());
		contentStream.endText();
		
		nextTextY -= table.getRowHeight();
		contentStream.setFont(table.getSubtitleFont(), table.getFontSizesubtitle());
		
		contentStream.beginText();
		text_width = (long) ((table.getSubtitleFont().getStringWidth(table.getSubtitle()) / 1000.0f) * table
				.getFontSizesubtitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2) - (text_width / 2) + (table.getMargin() / 2), nextTextY);
		contentStream.showText(table.getSubtitle());
		contentStream.endText();
		
	}
}
