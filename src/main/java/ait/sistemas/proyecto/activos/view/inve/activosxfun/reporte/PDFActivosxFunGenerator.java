package ait.sistemas.proyecto.activos.view.inve.activosxfun.reporte;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import ait.sistemas.proyecto.common.report.pdf.movimiento.Acta;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.ui.UI;

@SuppressWarnings("deprecation")
public class PDFActivosxFunGenerator {

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
		PDPage page = new PDPage();
		page.setMediaBox(acta.getPageSize());
		this.doc.addPage(page);
		PDPageContentStream contentStream = generateContentStream(doc, page, acta);
		drawCurrentPage(acta, contentStream, 0, acta.getTb_activos().getData());

	}

	private void drawCurrentPage(Acta table, PDPageContentStream contentStream, int pageCount, String[][] data)
			throws IOException {
		float tableTopY;
		PDPage page;
		Integer rowsPerPage = new Double(Math.floor(table.getHeight() / table.getRowHeight())).intValue();
		tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
				.getHeight() - table.getMargin();

		float nextTextX = table.getMargin() + table.getCellMargin();

		if (pageCount == 0) {
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin()
					- (table.getRowHeight() * table.getHeaderSize()) : table.getHeight() - table.getMargin()
					- (table.getRowHeight() * table.getHeaderSize());
			// cabecera comun
			writeHeader(contentStream, tableTopY, table);
			writeHeader2(contentStream, nextTextX, table);
		}

		float nextTextY = tableTopY;
		drawTableGrid(table, data, contentStream, tableTopY);

		// Calculate center alignment for text in cell considering font
		// height
		nextTextY -= (table.getRowHeight() - 5);
		writeContentLine(table.getColumnsNames(), contentStream, nextTextX + 2, nextTextY, table);
		nextTextY -= table.getTb_activos().getRowHeight();
		// Write content
		int r = table.getHeaderSize();
		for (int i = 0; i < table.getTb_activos().getData().length; i++) {
			writeContentLine(table.getTb_activos().getData()[i], contentStream, nextTextX + 2, nextTextY, table);
			// nextTextY -= table.getTb_activos().getRowHeight();
			r++;
			if (!table.getTb_activos().getData()[i][4].equals("") && table.getTb_activos().getData()[i][4] != null
					&& !table.getTb_activos().getData()[i][4].equals("Sin Componentes")) {
				// nextTextY += table.getRowHeight();
				nextTextY -= table.getTb_activos().getRowHeight();
				r++;
			} else {
				r--;
			}
//		if(table.getTb_activos().getData()[i][1].equals("null") || table.getTb_activos().getData()[i][1].equals("") || table.getTb_activos().getData()[i][1] == null){
//			table.getTb_activos().getData()[i][1]="entr";
//			}
			if (r >= rowsPerPage) {
				if (contentStream != null) {
					contentStream.close();
				}
				page = generatePage(doc, table);
				contentStream = generateContentStream(doc, page, table);
				nextTextY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table
						.getPageSize().getHeight() - table.getMargin();
				nextTextY -= table.getRowHeight() * table.getHeaderSize();
				nextTextX = table.getMargin() + table.getCellMargin();
				r = table.getHeaderSize();
				writeHeader(contentStream, nextTextY, table);
				writeHeader2(contentStream, nextTextX, table);
				drawTableGrid(table, data, contentStream, tableTopY);

				// Calculate center alignment for text in cell considering font
				// height
				nextTextY -= (table.getRowHeight() - 5);
				writeContentLine(table.getColumnsNames(), contentStream, nextTextX + 2, nextTextY, table);
				nextTextY -= table.getRowHeight();
				r++;
			}

		}
		// if (pageCount == (intNumberPages - 1)) {
		// dibujarfirmas(contentStream, nextTextX, nextTextY, table);
		// }

		contentStream.close();
		drawFooter(doc, table);
	}

	private PDPage generatePage(PDDocument doc2, Acta table) {
		PDPage page = new PDPage();
		page.setMediaBox(table.getPageSize());
		page.setRotation(table.isLandscape() ? 90 : 0);
		doc.addPage(page);
		return page;
	}

	// private void dibujarfirmas(PDPageContentStream contentStream, float
	// nextTextX, float nextTextY, Acta table)
	// throws IOException {
	// String footertitulotext =
	// String.format("Responsabilidad por el manejo de bienes:");
	// String footertext1 = String
	// .format("Numero III art. 116 del DS 0181, todos los servidores publicos son responsables por el debido uso, custodia, preservacion y solicitud de servicios de mantenimiento"
	// +
	// " de los bienes que les fueron asignados de acuerdo al regimen de Responsabilidad por la funcion");
	// String footertext2 = String
	// .format("Publica establecido en la Ley N° 1178 y sus reglamentos.Asimismo el art. 157 numerales I.Los servidores publicos quedan prohibidos de a) Usar los bienes para benficio "
	// +
	// "particular o privado. b) Permitir el uso para beneficio particular o privado. c) Prestar o");
	// String footertext3 = String
	// .format("transferir el bien a otro empleado publico. d) Enajenar el bien por cuenta propia e) Dañar o alterar sus caracteristicas fisicas o tecnicas f) Poner en riesgo el bien."
	// +
	// " g) Ingresar bienes particulares sin autorizacion de la Unidad Responsable de Activos Fijos. h) Sacar");
	// String footertext4 = String
	// .format("bienes  de la entidad sin autoizacion de la Unidad o Responsable de Activos Fijos y II.La no observacion a estas prohibiciones generara responsabilidades "
	// + "establecidas en la Ley 1178 y sus reglamentos.");
	//
	// contentStream.beginText();
	// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
	// contentStream.showText(footertitulotext);
	// contentStream.endText();
	//
	// nextTextY -= table.getRowHeight();
	//
	// contentStream.beginText();
	// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
	// contentStream.showText(footertext1);
	// contentStream.endText();
	//
	// nextTextY -= table.getRowHeight() * 0.5;
	//
	// contentStream.beginText();
	// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
	// contentStream.showText(footertext2);
	// contentStream.endText();
	//
	// nextTextY -= table.getRowHeight() * 0.5;
	//
	// contentStream.beginText();
	// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
	// contentStream.showText(footertext3);
	// contentStream.endText();
	//
	// nextTextY -= table.getRowHeight() * 0.5;
	//
	// contentStream.beginText();
	// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
	// contentStream.showText(footertext4);
	// contentStream.endText();
	//
	// nextTextY -= (table.getRowHeight() + table.getMargin());
	// // nextTextY -= table.getRowHeight();
	// nextTextX += 20;
	// for (Firma firma : table.getFirmas()) {
	// contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
	// contentStream.fillRect(nextTextX, nextTextY, (table.getWidth() - 2 *
	// table.getMargin()) / 4 - 15,
	// firma.getAlto() - 15);
	// contentStream.setNonStrokingColor(Color.BLACK);
	//
	// contentStream.beginText();
	// contentStream.moveTextPositionByAmount(nextTextX, nextTextY - 15);
	// contentStream.showText("Firma");
	// contentStream.endText();
	// contentStream.beginText();
	// contentStream.moveTextPositionByAmount(nextTextX, nextTextY - 25);
	// nextTextX += (table.getWidth() - 2 * table.getMargin()) / 3;
	// contentStream.showText(firma.getNombre_usuario());
	// contentStream.endText();
	// }
	// }
	@SuppressWarnings("static-access")
	private void drawFooter(PDDocument doc, Acta table) {
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

	// Writes the content for one line
	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Acta table) throws IOException {
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

			contentStream.showText(text != null ? text : "");
		
			contentStream.endText();
			// if (text == null || text.toString().equals("") && i>0){
			// nextTextY += table.getRowHeight();
			// System.out.print("entra para cortar");
			// }
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
//
//	private void writeFooter(PDPageContentStream contentStream, float nextTextX, float nextTextY, Acta table,
//			int pagecount) throws IOException {
//
//		contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
//
//		nextTextY = table.isLandscape() ? table.getMargin() : table.getMargin();
//		nextTextY -= (table.getRowHeight() * 2.5);
//
//		contentStream.beginText();
//		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
//
//		nextTextY += table.getRowHeight() - table.getMargin();
//
//		String footertext = String.format("Página %d de %d", (pagecount + 1), intNumberPages);
//		contentStream.showText(footertext);
//		contentStream.endText();
//
//	}

//	private String[][] getContentForCurrentPage(Acta table, Integer rowsPerPage, int pageCount) {
//		int startRange = pageCount * rowsPerPage;
//
//		int endRange = (pageCount * rowsPerPage) + rowsPerPage;
//
//		if (pageCount == 0) {
//			endRange = (pageCount * rowsPerPage) - 8 + rowsPerPage;
//		}
//		if (endRange > table.getNumberOfRowsTable()) {
//			endRange = table.getNumberOfRowsTable();
//		}
//		return Arrays.copyOfRange(table.getTb_activos().getData(), startRange, endRange);
//	}

	private void writeHeader(PDPageContentStream contentStream, float nextTextY, Acta table) throws IOException {

		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		contentStream.setFont(table.getHeaderFont(), table.getFontSizeheader());

		float nextTextX = table.getMargin();
		float nextTextXCopy = nextTextX;
		nextTextY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
				.getHeight() - table.getMargin();

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		contentStream.showText(usuario.getDependecia());

		contentStream.endText();

		long size_header = table.isLandscape() ? 800 : 400;
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

		nextTextY -= table.getRowHeight() * 0.75;

		contentStream.beginText();
		long text_width = (long) ((table.getTitleFont().getStringWidth(table.getTitle()) / 1000.0f) * table
				.getFontSizetitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2) - (text_width / 2) + (table.getMargin() / 2),
				nextTextY);
		contentStream.showText(table.getTitle());
		contentStream.endText();

		nextTextY -= table.getRowHeight();
		contentStream.setFont(table.getSubtitleFont(), table.getFontSizesubtitle());

		contentStream.beginText();
		text_width = (long) ((table.getSubtitleFont().getStringWidth(table.getSubtitle()) / 1000.0f) * table
				.getFontSizesubtitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2) - (text_width / 2) + (table.getMargin() / 2),
				nextTextY);
		contentStream.showText(table.getSubtitle());
		contentStream.endText();

	}

	private void writeHeader2(PDPageContentStream contentStream, float nextTextX, Acta table) throws IOException {

		contentStream.setFont(table.getHeaderFont(), table.getFontSizeheader());

		float nextTextY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table
				.getPageSize().getHeight() - table.getMargin();

		// contentStream.setFont(table.getTextFont(), 16);
		// long text_width = (long)
		// ((table.getTitleFont().getStringWidth("Activos por Funcionario") /
		// 1000.0f) * 16);
		// nextTextX = table.getWidth() / 2 - text_width / 2;
		// contentStream.beginText();
		// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		// contentStream.showText("Activos por Funcionario");
		// contentStream.endText();
		//
		// nextTextY -= table.getTb_activos().getRowheigth();
		// nextTextX = table.getWidth() - table.getMargin() - 40;
		//
		// Date date = new Date();
		// DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
		// String fecha = fechaHora.format(date);
		//
		// contentStream.setFont(table.getTextFont(), table.getFontSize());
		// contentStream.beginText();
		// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		// contentStream.showText("Fecha: " + fecha);
		// contentStream.endText();
		//
		// nextTextY -= table.getTb_activos().getRowheigth();
		// nextTextX = (table.getWidth() - table.getMargin() * 2) / 3;
		// contentStream.beginText();
		// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		// contentStream.showText("Entregado por: ");
		// contentStream.endText();
		// contentStream.beginText();
		// nextTextX += ((table.getWidth() - table.getMargin() * 2) / 3) +
		// table.getMargin();
		// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		// contentStream.showText("Recibido por: ");
		// contentStream.endText();
		nextTextY -= table.getRowHeight() * 3;

		nextTextY -= table.getTb_activos().getRowHeight();
		nextTextX = table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Dependencia: ");
		contentStream.endText();

		nextTextX = (float) 4.5 * table.getMargin();
		drawGridCabezera(contentStream, nextTextX, nextTextY, table);
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getDependencia_origen());
		contentStream.endText();

		// contentStream.beginText();
		// nextTextX += 2 * ((table.getWidth() - table.getMargin() * 2) / 3) -
		// (float) 1.8 * table.getMargin();
		// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		// contentStream.showText(table.getDependencia_destino());
		// contentStream.endText();

		nextTextY -= table.getTb_activos().getRowHeight();
		nextTextX = table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Unidad: ");
		contentStream.endText();

		nextTextX = (float) 4.5 * table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getUnidad_origen());
		contentStream.endText();

		// contentStream.beginText();
		// nextTextX += 2 * ((table.getWidth() - table.getMargin() * 2) / 3) -
		// (float) 1.8 * table.getMargin();
		// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		// contentStream.showText(table.getUnidad_destino());
		// contentStream.endText();

		nextTextY -= table.getTb_activos().getRowHeight();
		nextTextX = table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Funcionario Responsable: ");
		contentStream.endText();

		nextTextX = (float) 4.5 * table.getMargin();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(table.getCi() + "  " + table.getUsuario_origen());
		contentStream.endText();

		// contentStream.beginText();
		// nextTextX += 2 * ((table.getWidth() - table.getMargin() * 2) / 3) -
		// (float) 1.8 * table.getMargin();
		// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		// contentStream.showText(table.getUsuario_destino());
		// contentStream.endText();

		// nextTextY -= 2 * table.getTb_activos().getRowHeight();
		// nextTextX = table.getMargin();
		// contentStream.beginText();
		// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		// contentStream
		// .showText("En la Ciudad de La Paz se procedio a la entrega de los bienes de uso verificando el estado de cada uno de los activos que se detallan a continuacion: ");
		// contentStream.endText();
		// nextTextY -= 2 * table.getTb_activos().getRowHeight();
	}

	private void drawGridCabezera(PDPageContentStream contentStream, float nextTextX, float nextTextY, Acta table) {
		float copY = nextTextY;
		try {
			for (int i = 1; i <= 4; i++) {
				contentStream.drawLine(nextTextX - 3, nextTextY + table.getTb_activos().getRowHeight() - 4,
						(float) (table.getWidth() - table.getMargin() / 2.5), nextTextY
								+ table.getTb_activos().getRowHeight() - 4);
				nextTextY -= table.getTb_activos().getRowHeight();
			}
			nextTextX -= 3;
			nextTextY += 2 * table.getTb_activos().getRowHeight() - 4;
			contentStream.drawLine(nextTextX, copY + table.getTb_activos().getRowHeight() - 4, nextTextX, nextTextY);
			nextTextX += 2 * ((table.getWidth() - table.getMargin() * 2) / 3) - (float) 1.8 * table.getMargin();
			// contentStream.drawLine(nextTextX, copY +
			// table.getTb_activos().getRowHeight() - 4, nextTextX, nextTextY);
			contentStream.drawLine((float) (table.getWidth() - table.getMargin() / 2.5), copY
					+ table.getTb_activos().getRowHeight() - 4, (float) (table.getWidth() - table.getMargin() / 2.5),
					nextTextY);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawTableGrid(Acta table, String[][] currentPageContent, PDPageContentStream contentStream,
			float tableTopY) throws IOException {
		// Draw row lines
		float nextY = tableTopY;

		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < currentPageContent.length + 1; i++) {
			if (i <= currentPageContent.length && i > 0) {
//				if (!currentPageContent[i - 1][0].equals("")) {
					// contentStream.drawLine(table.getMargin(), nextY,
					// table.getColumnWidth() + table.getMargin(), nextY);
//				}
			} else {
				contentStream.drawLine(table.getMargin(), nextY, table.getColumnWidth() + table.getMargin(), nextY);
				contentStream.drawLine(table.getMargin(), nextY - table.getRowHeight(),
						table.getColumnWidth() + table.getMargin(), nextY - table.getRowHeight());
			}

			nextY -= table.getRowHeight();
		}

		// Modificado solo pra el titulo para grilla modificar por
		// final float tableYLength = table.getRowHeight() +
		// (table.getRowHeight() * currentPageContent.length);
		// final float tableBottomY = tableTopY - tableYLength;
		final float tableBottomY = tableTopY - table.getRowHeight();

		float nextX = table.getMargin();

		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < table.getColumns().size(); i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
			nextX += table.getColumns().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
	}
}
