package ait.sistemas.proyecto.activos.view.inve.invxgrupo.reporte;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import ait.sistemas.proyecto.common.report.Table;
import ait.sistemas.proyecto.common.report.Util;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.ui.UI;

public class InventarioGrupoGenerator {
	
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
		
		PDPage page;
		PDPageContentStream contentStream = null;
		float tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
				.getHeight() - table.getMargin();
		
		String dependencia = "";
		
		int can_dependencia = 0;
		double sum_valorCompra = 0;
		double sum_valorNeto = 0;
		int num_dep = 0;
		for (int i = 0; i < table.getContent().length; i++) {
			
			if (!table.getContent()[i][0].equals(dependencia) && contentStream != null) {
				
				tableTopY -= table.getRowHeight() * 1.15;
				contentStream.drawLine(table.getMargin(), tableTopY, table.getWidth() + table.getMargin(), tableTopY);
				
				DecimalFormat formater = new DecimalFormat("##,###,###,###.00");
				DecimalFormat formaterint = new DecimalFormat("##,###,###,###");
				drawCurrentPageCorte(table,
						new String[] { num_dep > 1 ? "Total General" : "Total Dependencia", formaterint.format(can_dependencia),
								formater.format(sum_valorCompra), formater.format(sum_valorNeto) }, contentStream, tableTopY);
				num_dep++;
			}
			
			if (!table.getContent()[i][0].equals(dependencia)) {
				
				if (contentStream != null) {
					contentStream.close();
				}
				page = generatePage(doc, table);
				contentStream = generateContentStream(doc, page, table);
				
				dependencia = table.getContent()[i][0];
				tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
						.getHeight() - table.getMargin();
				
				tableTopY -= table.getRowHeight() * table.getHeaderSize();
				writeHeader(contentStream, tableTopY, table);
				
				drawCurrentPageDependencia(table, new String[] { "Dependencia : " + dependencia }, contentStream, tableTopY);
				drawCurrentPageOrden(table, new String[] { "(Orden:/Dependencia/Grupo)" }, contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				
				drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				
				can_dependencia = 0;
				sum_valorCompra = 0;
				sum_valorNeto = 0;
				
			}
			
			String[] current = Arrays.copyOfRange(table.getContent()[i], 1, table.getContent()[i].length);
			
			tableTopY -= table.getRowHeight();
			drawCurrentPage(table, current, contentStream, tableTopY);
			
			can_dependencia += Integer.parseInt(table.getContent()[i][2] == "null" ? "0" : table.getContent()[i][2]);
			sum_valorCompra += Double.parseDouble(table.getContent()[i][3] == "null" ? "0" : table.getContent()[i][3]);
			sum_valorNeto += Double.parseDouble(table.getContent()[i][4] == "null" ? "0" : table.getContent()[i][4]);
			
		}
		
		tableTopY -= table.getRowHeight() * 1.15;
		contentStream.drawLine(table.getMargin(), tableTopY, table.getWidth() + table.getMargin(), tableTopY);
		
		DecimalFormat formater = new DecimalFormat("##,###,###,###.00");
		DecimalFormat formaterint = new DecimalFormat("##,###,###,###");
		drawCurrentPageCorte(
				table,
				new String[] { num_dep > 1 ? "Total General" : "Total Dependencia", formaterint.format(can_dependencia),
						formater.format(sum_valorCompra), formater.format(sum_valorNeto) }, contentStream, tableTopY);
		
		contentStream.close();
		drawFooter(doc, table);
		
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	private void drawFooter(PDDocument doc, Table table) {
		int numofPages = doc.getPages().getCount();
		int actual_row = 1;
		for (PDPage page : doc.getPages()) {
			try {
				PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);
				contentStream.drawLine(table.getMargin(), table.getMargin(), table.getWidth(), table.getMargin());
				contentStream.beginText();
				contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
				contentStream.moveTextPositionByAmount(table.getMargin(), table.getMargin() - 7);
				contentStream.showText(new String().format("Página %d de %d", actual_row, numofPages));
				contentStream.endMarkedContent();
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
	
	private void drawCurrentPageCorte(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		
		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		writeContentLineCorte(strings, contentStream, nextTextX, nextTextY, table);
	}
	
	@SuppressWarnings("deprecation")
	private void drawTableGrid(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		float nextY = tableTopY;
		
		// Modificado para solo el tititulo para grilla completa modificar por
		// for (int i = 0; i <= currentPageContent.length + 1; i++) {
		for (int i = 0; i <= 1; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(), nextY);
			nextY -= table.getRowHeight();
		}
		// Modificado solo pra el titulo para grilla modificar por
		// final float tableYLength = table.getRowHeight() +
		// (table.getRowHeight() * currentPageContent.length);
		// final float tableBottomY = tableTopY - tableYLength;
		final float tableBottomY = tableTopY - table.getRowHeight();
		
		float nextX = table.getMargin();
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < strings.length; i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
			nextX += table.getColumns().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
		
	}
	
	// Writes the content for one line
	@SuppressWarnings("deprecation")
	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
			Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		
		for (int i = 0; i < lineContent.length; i++) {
			if (i >= 1) {
				String text = lineContent[i];
				if (i != 1) {
					text = Util.numberFormat(lineContent[i]);
				}
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(
						Util.justificar(text, nextTextX + table.getColumns().get(i).getWidth(), table.getTextFont(),
								table.getFontSize()), nextTextY);
				contentStream.showText(text != null ? text : "");
				contentStream.endText();
				nextTextX += table.getColumns().get(i).getWidth();
				continue;
			}
			lineContent[i] = lineContent[i] != null ? lineContent[i] : "";
			String text = lineContent[i].replace("\n", "");
			if (text.length() > 100) {
				text = Util.separeString(text, 100)[0];
			}
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			try {
				contentStream.showText(text != null ? text : "");
			} catch (Exception e) {
				System.out.print(lineContent[0] + "->" + text);
			}
			contentStream.endText();
			nextTextX += table.getColumns().get(i).getWidth();
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
	
	private void drawCurrentPageOrden(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = (float) (table.getWidth() - 2.5 * table.getMargin());
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		writeContentLineOrden(strings, contentStream, nextTextX, nextTextY, table);
	}
	
	@SuppressWarnings("deprecation")
	private void writeContentLineOrden(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
			Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			nextTextX += table.getWidth();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void writeContentLineCorte(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
			Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			if (i >= 1) {
				contentStream.moveTextPositionByAmount(
						Util.justificar(text, nextTextX + table.getColumns().get(3).getWidth(), table.getTextFont(),
								table.getFontSize()), nextTextY);
			} else {
				contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			}
			contentStream.showText(text != null ? text : "");
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
