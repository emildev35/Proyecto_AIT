package ait.sistemas.proyecto.activos.view.mvac.asignacion.reporte;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import ait.sistemas.proyecto.common.report.pdf.movimiento.Acta;
import ait.sistemas.proyecto.common.report.pdf.movimiento.Firma;

@SuppressWarnings("deprecation")
public class PdfActaGenerator {
	
	private PDDocument doc;
	
	private int intNumberPages = 5;
	
	public boolean generatePDF(Acta acta, String savePath) throws IOException {
		boolean result = false;
		doc = null;
		try {
			doc = new PDDocument();
			drawacta(doc, acta);
			doc.save(savePath);
			result = true;
		} finally {
			if (doc != null) {
				doc.close();
			}
		}
		return result;
	}
	
	public void drawacta(PDDocument doc, Acta acta) throws IOException {
		
		Integer rowsPerPage = new Double(Math.floor(acta.getHeight() / acta.getTb_activos().getRowheigth())).intValue() - 1;
		Integer numberOfPages = new Double(Math.ceil((acta.getNumberOfRows().floatValue()) / rowsPerPage)).intValue();
		// numberOfPages = numberOfPages < 1 ? 0 : numberOfPages;
		this.intNumberPages = numberOfPages;
		for (int pageCount = 0; pageCount < numberOfPages; pageCount++) {
			PDPage page = new PDPage();
			page.setMediaBox(acta.getPageSize());
			this.doc.addPage(page);
			PDPageContentStream contentStream = generateContentStream(doc, page, acta);
			drawCurrentPage(acta, contentStream, pageCount, getContentForCurrentPage(acta, rowsPerPage, pageCount));
		}
		
	}
	
	private void drawCurrentPage(Acta table, PDPageContentStream contentStream, int pageCount, String[][] data)
			throws IOException {
		float tableTopY;
		
		tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight()
				- table.getMargin();
		
		float nextTextX = table.getMargin() + table.getCellMargin();
		
		if (pageCount == 0) {
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin()
					- (table.getRowHeight() * table.getHeaderSize()) : table.getHeight() - table.getMargin()
					- (table.getRowHeight() * table.getHeaderSize());
			writeHeader(contentStream, nextTextX, table);
		}
		float nextTextY = tableTopY;
		drawTableGrid(table, data, contentStream, tableTopY);
		// Calculate center alignment for text in cell considering font
		// height
		nextTextY -= (table.getRowHeight() - 5);
		writeContentLine(table.getColumnsNames(), contentStream, nextTextX + 2, nextTextY, table);
		nextTextY -= table.getTb_activos().getRowHeight();
		// Write content
		for (int i = 0; i < table.getTb_activos().getData().length; i++) {
			writeContentLine(table.getTb_activos().getData()[i], contentStream, nextTextX + 2, nextTextY, table);
			nextTextY -= table.getTb_activos().getRowHeight();
			nextTextX = table.getMargin() + table.getCellMargin();
		}
		if (pageCount == (intNumberPages - 1)) {
			dibujarfirmas(contentStream, nextTextX, nextTextY, table);
		}
		
		writeFooter(contentStream, nextTextX, 0, table, pageCount);
		contentStream.close();
	}
	
	private void dibujarfirmas(PDPageContentStream contentStream, float nextTextX, float nextTextY, Acta table)
			throws IOException {
		String footertitulotext = String.format("Responsabilidad por el manejo de bienes:");
		String footertext1 = String
				.format("Numero III art. 116 del DS 0181, todos los servidores publicos son responsables por el debido uso, custodia, preservacion y solicitud de servicios de mantenimiento"
						+ " de los bienes que les fueron asignados de acuerdo al regimen de Responsabilidad por la funcion");
		String footertext2 = String
				.format("Publica establecido en la Ley N° 1178 y sus reglamentos.Asimismo el art. 157 numerales I.Los servidores publicos quedan prohibidos de a) Usar los bienes para benficio "
						+ "particular o privado. b) Permitir el uso para beneficio particular o privado. c) Prestar o");
		String footertext3 = String
				.format("transferir el bien a otro empleado publico. d) Enajenar el bien por cuenta propia e) Dañar o alterar sus caracteristicas fisicas o tecnicas f) Poner en riesgo el bien."
						+ " g) Ingresar bienes particulares sin autorizacion de la Unidad Responsable de Activos Fijos. h) Sacar");
		String footertext4 = String
				.format("bienes  de la entidad sin autoizacion de la Unidad o Responsable de Activos Fijos y II.La no observacion a estas prohibiciones generara responsabilidades "
						+ "establecidas en la Ley 1178 y sus reglamentos.");
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(footertitulotext);
		contentStream.endText();
		
		nextTextY -= table.getRowHeight();
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(footertext1);
		contentStream.endText();
		
		nextTextY -= table.getRowHeight() * 0.5;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(footertext2);
		contentStream.endText();
		
		nextTextY -= table.getRowHeight() * 0.5;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(footertext3);
		contentStream.endText();
		
		nextTextY -= table.getRowHeight() * 0.5;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(footertext4);
		contentStream.endText();
		
		nextTextY -= (table.getRowHeight() + table.getMargin());
		// nextTextY -= table.getRowHeight();
		nextTextX += 20;
		for (Firma firma : table.getFirmas()) {
			contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
			contentStream.fillRect(nextTextX, nextTextY, (table.getWidth() - 2 * table.getMargin()) / 4 - 15,
					firma.getAlto() - 15);
			contentStream.setNonStrokingColor(Color.BLACK);
			
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY - 15);
			contentStream.showText("Firma");
			contentStream.endText();
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY - 25);
			nextTextX += (table.getWidth() - 2 * table.getMargin()) / 3;
			contentStream.showText(firma.getNombre_usuario());
			contentStream.endText();
		}
	}
	
	// Writes the content for one line
	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
			Acta table) throws IOException {
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			
			contentStream.showText(text != null ? text : "");
			
			contentStream.endText();
			nextTextX += table.getColumns().get(i).getWidth();
		}
	}
	
	/*
	 * // Writes the content for one line private void
	 * writeContentLine(PDPageContentStream contentStream, float nextTextX,
	 * float nextTextY, Kardex table) throws IOException {
	 * contentStream.setFont(table.getTextFont(), table.getFontSize()); for (int
	 * i = 0; i < 10; i++) { String text = "kim"; contentStream.beginText();
	 * contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
	 * contentStream.showText(text != null ? text : "");
	 * contentStream.endText(); nextTextX += 50; } }
	 */
	
	private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, Acta table) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, false, false);
		if (table.isLandscape()) {
			contentStream.concatenate2CTM(0, 1, -1, 0, table.getPageSize().getWidth(), 0);
			page.setMediaBox(table.getPageSize());
			page.setRotation(table.isLandscape() ? 90 : 0);
		}
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		return contentStream;
	}
	
	private void writeFooter(PDPageContentStream contentStream, float nextTextX, float nextTextY, Acta table, int pagecount)
			throws IOException {
		
		contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
		
		nextTextY = table.isLandscape() ? table.getMargin() : table.getMargin();
		nextTextY -= (table.getRowHeight() * 2.5);
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		
		nextTextY += table.getRowHeight() - table.getMargin();
		
		String footertext = String.format("Página %d de %d", (pagecount + 1), intNumberPages);
		contentStream.showText(footertext);
		contentStream.endText();
		
	}
	
	private String[][] getContentForCurrentPage(Acta table, Integer rowsPerPage, int pageCount) {
		int startRange = pageCount * rowsPerPage;
		
		int endRange = (pageCount * rowsPerPage) + rowsPerPage;
		
		if (pageCount == 0) {
			endRange = (pageCount * rowsPerPage) - 8 + rowsPerPage;
		}
		if (endRange > table.getNumberOfRowsTable()) {
			endRange = table.getNumberOfRowsTable();
		}
		return Arrays.copyOfRange(table.getTb_activos().getData(), startRange, endRange);
	}
	
	private void writeHeader(PDPageContentStream contentStream, float nextTextX, Acta table) throws IOException {
		
		contentStream.setFont(table.getHeaderFont(), table.getFontSizeheader());
		
		float nextTextY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
				.getHeight() - table.getMargin();
		
		contentStream.setFont(table.getTextFont(), 16);
		long text_width = (long) ((table.getTitleFont().getStringWidth(
				"Acta de Entrega de Activos N°" + table.getNro_acta_entrega()) / 1000.0f) * 16);
		nextTextX = table.getWidth() / 2 - text_width / 2;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		
		contentStream.showText("Acta de Entrega de Activos N°" + table.getNro_acta_entrega());
		contentStream.endText();
		
		nextTextY -= table.getTb_activos().getRowheigth();
		nextTextX = table.getWidth() - table.getMargin() - 40;
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Fecha: " + table.getFecha());
		contentStream.endText();
		
		nextTextY -= table.getTb_activos().getRowheigth();
		nextTextX = (table.getWidth() - table.getMargin() * 2) / 3;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Entregado por: ");
		contentStream.endText();
		contentStream.beginText();
		nextTextX += ((table.getWidth() - table.getMargin() * 2) / 3) + table.getMargin();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Recibido por: ");
		contentStream.endText();
		
		nextTextY -= table.getTb_activos().getRowHeight();
		nextTextX = table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Dependencia: ");
		contentStream.endText();
		
		nextTextX = (float) 2.6 * table.getMargin();
		drawGridCabezera(contentStream, nextTextX, nextTextY, table);
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getDependencia_origen());
		contentStream.endText();
		
		contentStream.beginText();
		nextTextX += 2 * ((table.getWidth() - table.getMargin() * 2) / 3) - (float) 1.8 * table.getMargin();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getDependencia_destino());
		contentStream.endText();
		
		nextTextY -= table.getTb_activos().getRowHeight();
		nextTextX = table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Unidad: ");
		contentStream.endText();
		
		nextTextX = (float) 2.6 * table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getUnidad_origen());
		contentStream.endText();
		
		contentStream.beginText();
		nextTextX += 2 * ((table.getWidth() - table.getMargin() * 2) / 3) - (float) 1.8 * table.getMargin();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getUnidad_destino());
		contentStream.endText();
		
		nextTextY -= table.getTb_activos().getRowHeight();
		nextTextX = table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Funcionario Responsable: ");
		contentStream.endText();
		
		nextTextX = (float) 2.6 * table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getUsuario_origen());
		contentStream.endText();
		
		contentStream.beginText();
		nextTextX += 2 * ((table.getWidth() - table.getMargin() * 2) / 3) - (float) 1.8 * table.getMargin();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getUsuario_destino());
		contentStream.endText();
		
		nextTextY -= 2 * table.getTb_activos().getRowHeight();
		nextTextX = table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream
				.showText("En la Ciudad de La Paz se procedio a la entrega de los bienes de uso verificando el estado de cada uno de los activos que se detallan a continuacion: ");
		contentStream.endText();
		nextTextY -= 2 * table.getTb_activos().getRowHeight();
	}
	
	private void drawGridCabezera(PDPageContentStream contentStream, float nextTextX, float nextTextY, Acta table) {
		float copY = nextTextY;
		try {
			for (int i = 1; i <= 4; i++) {
				contentStream.drawLine(nextTextX - 3, nextTextY + table.getTb_activos().getRowHeight() - 4,
						(float) (table.getWidth() - table.getMargin() / 2.5), nextTextY + table.getTb_activos().getRowHeight()
								- 4);
				nextTextY -= table.getTb_activos().getRowHeight();
			}
			nextTextX -= 3;
			nextTextY += 2 * table.getTb_activos().getRowHeight() - 4;
			contentStream.drawLine(nextTextX, copY + table.getTb_activos().getRowHeight() - 4, nextTextX, nextTextY);
			nextTextX += 2 * ((table.getWidth() - table.getMargin() * 2) / 3) - (float) 1.8 * table.getMargin();
			contentStream.drawLine(nextTextX, copY + table.getTb_activos().getRowHeight() - 4, nextTextX, nextTextY);
			contentStream.drawLine((float) (table.getWidth() - table.getMargin() / 2.5), copY
					+ table.getTb_activos().getRowHeight() - 4, (float) (table.getWidth() - table.getMargin() / 2.5), nextTextY);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void drawTableGrid(Acta table, String[][] currentPageContent, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		// Draw row lines
		float nextY = tableTopY;
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i <= currentPageContent.length + 1; i++) {
			if (i <= currentPageContent.length && i > 0) {
				if (!currentPageContent[i - 1][0].equals("")) {
					contentStream.drawLine(table.getMargin(), nextY, table.getColumnWidth() + table.getMargin(), nextY);
				}
			} else {
				contentStream.drawLine(table.getMargin(), nextY, table.getColumnWidth() + table.getMargin(), nextY);
			}
			
			nextY -= table.getRowHeight();
		}
		
		// Modificado solo pra el titulo para grilla modificar por
		final float tableYLength = table.getRowHeight() + (table.getRowHeight() * currentPageContent.length);
		final float tableBottomY = tableTopY - tableYLength;
		// final float tableBottomY = tableTopY - table.getRowHeight();
		
		float nextX = table.getMargin();
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < table.getColumns().size(); i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
			nextX += table.getColumns().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
	}
}
