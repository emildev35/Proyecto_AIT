package ait.sistemas.proyecto.common.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.ui.UI;

@SuppressWarnings("deprecation")
public class PDFMultiInventarioGenerator {

	private PDDocument doc;

	private int intactualpage = 0;
	private int intNumberoftotalPages;

	// Generates document from Table object
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

	// Configures basic setup for the table and draws it page by page
	public void drawTable(PDDocument doc, Table table) throws IOException {

		Integer rowsPerPage = new Double(Math.floor(table.getHeight() / table.getRowHeight())).intValue() - 1;

		int r = 0;
		PDPage page;
		PDPageContentStream contentStream = null;
		float tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table
				.getPageSize().getHeight() - table.getMargin();

		String dependencia = "";
		String grupo_contable = "";
		String auxiliar_contable = "";

		float sum_dependencia = 0;
		float sum_grupo_contable = 0;
		float sum_auxiliares_contables = 0;
		float sum_neto_dependencia = 0;
		float sum_neto_grupo_contable = 0;
		float sum_neto_auxiliares_contables = 0;
		int can_dependencia = 0;
		int can_grupo_contable = 0;
		int can_auxiliares_contables = 0;
		
		for (int i = 0; i < table.getContent().length; i++) {

			if (r >= rowsPerPage || r == 0) {
				if (contentStream != null) {
					contentStream.close();
				}
				page = generatePage(doc, table);
				contentStream = generateContentStream(doc, page, table);
				r = 0;
				tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table
						.getPageSize().getHeight() - table.getMargin();

				tableTopY -= table.getHeaderSize() * table.getRowHeight();
				writeHeader(contentStream, tableTopY, table);
				tableTopY -= table.getRowHeight();
				drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				drawCurrentPage(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				r += 4;

			}
			// Cambio de Dependencia
			if (!table.getContent()[i][0].equals(dependencia)) {
				dependencia = table.getContent()[i][0];
				/**
				 * Suma de Dependencias
				 */
				sum_dependencia += sum_grupo_contable;
				sum_neto_dependencia += sum_neto_auxiliares_contables;
				sum_grupo_contable = 0;
				sum_neto_auxiliares_contables = 0;
			}
			// Cambio de Auxliar
			if (!table.getContent()[i][2].equals(auxiliar_contable) ) {
				sum_grupo_contable += sum_auxiliares_contables;
				sum_neto_grupo_contable += sum_neto_auxiliares_contables;

				can_grupo_contable += can_auxiliares_contables;
				if (!auxiliar_contable.equals("")) {
					tableTopY -= table.getRowHeight();
//					drawTableGridContables(table, new String[] { "", "" }, contentStream, tableTopY);
					drawCurrentPageCorte(
							table,
							new String[] { "Cantidad por Auxiliar Contable", String.valueOf(can_auxiliares_contables),
									"Total", String.valueOf(sum_auxiliares_contables), "Total Neto",
									String.valueOf(sum_neto_auxiliares_contables) }, contentStream, tableTopY);
					tableTopY -=  table.getRowHeight();
					r += 2;
				}
				can_auxiliares_contables = 0;
				sum_auxiliares_contables = 0;
				sum_neto_auxiliares_contables = 0;

				String[] mgru = { grupo_contable, auxiliar_contable };

				r++;
				if (!table.getContent()[i][1].equals(grupo_contable)) {
					/**
					 * Suma de los Grupo Contable
					 */
					if (!grupo_contable.equals("")) {

//						drawTableGridContables(table, new String[] { "", "" }, contentStream, tableTopY);
						drawCurrentPageCorte(
								table,
								new String[] { "Cantidad por Grupo Contable", String.valueOf(can_grupo_contable),
										"Total", String.valueOf(sum_grupo_contable), "Total Neto",
										String.valueOf(sum_neto_grupo_contable) }, contentStream, tableTopY);
						tableTopY -= table.getRowHeight();
						r++;
					}
					can_dependencia+=can_grupo_contable;
					sum_dependencia+=sum_grupo_contable;
					sum_neto_dependencia+=sum_neto_dependencia;
					can_grupo_contable = 0;
					sum_grupo_contable = 0;
					sum_neto_grupo_contable = 0;
				}
				grupo_contable = table.getContent()[i][1];
				auxiliar_contable = table.getContent()[i][2];

				r++;
				drawTableGridContables(table, new String[] { "Grupo Contable", grupo_contable, "Auxiliar Contable",
						auxiliar_contable }, contentStream, tableTopY);
				drawCurrentPageContable(table, new String[] { "Grupo Contable", grupo_contable, "Auxiliar Contable",
						auxiliar_contable }, contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
			}

			String[] current = {table.getContent()[i][0] ,table.getContent()[i][3] ,table.getContent()[i][4] ,table.getContent()[i][5] ,table.getContent()[i][6] ,table.getContent()[i][7] };
			/**
			 * Suma de los Auxiliares Contables
			 */
			sum_auxiliares_contables += Float.parseFloat(table.getContent()[i][6] == "null" ? "0"
					: table.getContent()[i][6]);
			sum_neto_auxiliares_contables += Float.parseFloat(table.getContent()[i][7] == "null" ? "0" : table
					.getContent()[i][7]);
			can_auxiliares_contables++;

			drawCurrentPage(table, current, contentStream, tableTopY);
			tableTopY -= table.getRowHeight();
			r++;
		}
		if ((r+6) >= rowsPerPage || r == 0) {
			if (contentStream != null) {
				contentStream.close();
			}
			page = generatePage(doc, table);
			contentStream = generateContentStream(doc, page, table);
			r = 0;
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table
					.getPageSize().getHeight() - table.getMargin();
			tableTopY -= table.getHeaderSize() * table.getRowHeight();
			writeHeader(contentStream, tableTopY, table);
			tableTopY -= table.getRowHeight();
		
			r += 2;
		}
		can_grupo_contable+=can_auxiliares_contables;
		sum_grupo_contable+=sum_grupo_contable;
		sum_neto_grupo_contable += sum_neto_auxiliares_contables;
		
		can_dependencia+=can_grupo_contable;
		sum_dependencia+=sum_grupo_contable;
		sum_neto_dependencia+=sum_neto_dependencia;
		
		drawCurrentPageCorte(
				table,
				new String[] { "Cantidad por Auxiliar Contable", String.valueOf(can_auxiliares_contables),
						"Total", String.valueOf(sum_auxiliares_contables), "Total Neto",
						String.valueOf(sum_neto_auxiliares_contables) }, contentStream, tableTopY);
		tableTopY -=  table.getRowHeight();
	
		drawCurrentPageCorte(
				table,
				new String[] { "Cantidad por Grupo Contable", String.valueOf(can_grupo_contable),
						"Total", String.valueOf(sum_grupo_contable), "Total Neto",
						String.valueOf(sum_neto_grupo_contable) }, contentStream, tableTopY);
		tableTopY -=  table.getRowHeight();
	
		drawCurrentPageCorte(
				table,
				new String[] { "Cantidad por Dependencia", String.valueOf(can_dependencia),
						"Total", String.valueOf(sum_dependencia), "Total Neto",
						String.valueOf(sum_neto_dependencia) }, contentStream, tableTopY);
		tableTopY -=  table.getRowHeight();
		
		contentStream.close();
		
		
	}

	private void drawCurrentPage(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {

		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY
				- (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table
						.getFontSize()) / 4);
		writeContentLine(strings, contentStream, nextTextX, nextTextY, table);
	}

	private void drawCurrentPageDependencia(Table table, String[] strings, PDPageContentStream contentStream,
			float tableTopY) throws IOException {

		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY
				- (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table
						.getFontSize()) / 4);
		writeContentLineDependencia(strings, contentStream, nextTextX, nextTextY, table);
	}

	private void drawCurrentPageContable(Table table, String[] strings, PDPageContentStream contentStream,
			float tableTopY) throws IOException {

		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY
				- (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table
						.getFontSize()) / 4);
		writeContentLineContables(strings, contentStream, nextTextX, nextTextY, table);
	}

	private void drawCurrentPageCorte(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {

		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY
				- (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table
						.getFontSize()) / 4);
		writeContentLineCorte(strings, contentStream, nextTextX, nextTextY, table);
	}

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

	private void drawTableGridDependencia(Table table, String[] strings, PDPageContentStream contentStream,
			float tableTopY) throws IOException {
		float nextY = tableTopY;

		// Modificado para solo el tititulo para grilla completa modificar por
		// for (int i = 0; i <= currentPageContent.length + 1; i++) {
		for (int i = 0; i <= 1; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth() / 3, nextY);
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
			nextX += table.getWidth() / 6;
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);

	}

	private void drawTableGridContables(Table table, String[] strings, PDPageContentStream contentStream,
			float tableTopY) throws IOException {
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
			nextX += table.getWidth() / 4;
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);

	}

	// Writes the content for one line
	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {

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

	private void writeContentLineCorte(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {

		contentStream.setFont(table.getTextFont(), table.getFontSize());

		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			if(i==0){
				
				nextTextX += table.getWidth() / 4;
			}else{
				nextTextX += table.getWidth() / 6;
			}
		}
	}

	private void drawTableGridCorte(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
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
			nextX += table.getWidth() / 4;
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);

	}

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

	private void writeContentLineContables(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {

		contentStream.setFont(table.getTextFont(), table.getFontSize());

		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			nextTextX += table.getWidth() / 4;
		}
	}

	private PDPage generatePage(PDDocument doc, Table table) {
		PDPage page = new PDPage();
		page.setMediaBox(table.getPageSize());
		page.setRotation(table.isLandscape() ? 90 : 0);
		doc.addPage(page);
		return page;
	}

	private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, Table table) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, false, false);
		if (table.isLandscape()) {
			contentStream.concatenate2CTM(0, 1, -1, 0, table.getPageSize().getWidth(), 0);
		}
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		return contentStream;
	}

	private void writeFooter(PDPageContentStream contentStream, float nextTextX, float nextTextY, Table table,
			int pagecount) throws IOException {

		contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());

		nextTextY = table.isLandscape() ? table.getMargin() : table.getMargin();
		nextTextY -= (table.getRowHeight() * 2.5);
		contentStream.drawLine(nextTextX, (nextTextY + table.getRowHeight()), nextTextX * table.getNumberOfColumns(),
				(nextTextY + table.getRowHeight()));

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		nextTextY += table.getRowHeight() - table.getMargin();
		nextTextX += table.getWidth();
		String footertext = String.format("Página %d de %d", this.intactualpage, this.intNumberoftotalPages);
		contentStream.showText(footertext);
		contentStream.endText();

	}

	private void writeHeader(PDPageContentStream contentStream, float nextTextY, Table table) throws IOException {

		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		contentStream.setFont(table.getHeaderFont(), table.getFontSizeheader());

		float nextTextX = table.getMargin();
		float nextTextXCopy = nextTextX;
		nextTextY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
				.getHeight() - table.getMargin();

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		contentStream.showText("Dependencia : " + usuario.getDependecia());

		contentStream.endText();

		Date date = new Date();
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = fechaHora.format(date);

		nextTextX += 400;

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Fecha : " + fecha);
		contentStream.endText();

		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Unidad : " + usuario.getUnidad());
		contentStream.endText();

		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		String strhora = hora.format(date);

		nextTextX += 400;

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Hora : " + strhora);
		contentStream.endText();

		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		contentStream.showText("Usuario : " + usuario.getFull_name());
		contentStream.endText();

		contentStream.setFont(table.getTitleFont(), table.getFontSizetitle());
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		long text_width = (long) ((table.getTitleFont().getStringWidth(table.getTitle()) / 1000.0f) * table
				.getFontSizetitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2) - (text_width / 2) + (table.getMargin() / 2),
				nextTextY);
		contentStream.showText(table.getTitle());
		contentStream.endText();

		contentStream.setFont(table.getSubtitleFont(), table.getFontSizesubtitle());
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		contentStream.endText();

	}
}
