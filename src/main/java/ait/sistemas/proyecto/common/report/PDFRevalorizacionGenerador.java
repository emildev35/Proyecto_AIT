package ait.sistemas.proyecto.common.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.ui.UI;

@SuppressWarnings({ "deprecation", "unused" })
public class PDFRevalorizacionGenerador {

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
				drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				tableTopY -= 4 * table.getRowHeight();
				r +=7;

			}
			// Cambio de Dependencia
			if (!table.getContent()[i][0].equals(dependencia)) {
				dependencia = table.getContent()[i][0];
//				drawTableGridDependencia(table, new String[] { "Dependencia ", dependencia }, contentStream, tableTopY);
				drawCurrentPageDependencia(table, new String[] { "Dependencia :", dependencia }, contentStream,
						tableTopY);
				r++;
				tableTopY -= table.getRowHeight();
				/**
				 * Suma de Dependencias
				 */
				sum_dependencia += sum_grupo_contable;
				sum_neto_dependencia += sum_neto_auxiliares_contables;
				sum_grupo_contable = 0;
				sum_neto_auxiliares_contables = 0;
			}
			// Cambio de Auxliar
			if (!table.getContent()[i][2].equals(auxiliar_contable)) {
				sum_grupo_contable += sum_auxiliares_contables;
				sum_neto_grupo_contable += sum_neto_auxiliares_contables;

				can_grupo_contable += can_auxiliares_contables;
				if (!auxiliar_contable.equals("")) {
					tableTopY -= table.getRowHeight();
					// drawTableGridContables(table, new String[] { "", "" },
					// contentStream, tableTopY);
					
					double valors_auxiliar_contable = Double.parseDouble(String.valueOf(can_auxiliares_contables) == null ? "0" : String.valueOf(can_auxiliares_contables));
					DecimalFormat formater = new DecimalFormat("##,###,###,###.##");
					String str_aux_cont = formater.format(valors_auxiliar_contable);
					
					double s_total = Double.parseDouble(String.valueOf(sum_auxiliares_contables) == null ? "0" : String.valueOf(sum_auxiliares_contables));
					String str_s_total = formater.format(s_total);
					
					double s_total_neto = Double.parseDouble(String.valueOf(sum_neto_auxiliares_contables) == null ? "0" : String.valueOf(sum_neto_auxiliares_contables));
					String str_s_total_neto = formater.format(s_total_neto);
					
					drawCurrentPageCorte(
							table,
							new String[] { "Cantidad por Auxiliar Contable", str_aux_cont,			
									"Total", str_s_total, 
									"Total Neto", str_s_total_neto}, contentStream, tableTopY);
					tableTopY -= table.getRowHeight();
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

						// drawTableGridContables(table, new String[] { "", ""
						// }, contentStream, tableTopY);
						double valors_grupo_contable = Double.parseDouble(String.valueOf(can_grupo_contable));
						DecimalFormat formater = new DecimalFormat("##,###,###,###.##");
						String str_grup_cont = formater.format(valors_grupo_contable);
						
						double s_grp_total = Double.parseDouble(String.valueOf(sum_grupo_contable));
						String str_s_grup_total = formater.format(s_grp_total);
						
						double s_gruptotal_neto = Double.parseDouble(String.valueOf(sum_neto_grupo_contable));
						String str_s_grup_total_neto = formater.format(s_gruptotal_neto);
						
						drawCurrentPageCorte(
								table,
								new String[] { "Cantidad por Grupo Contable", str_grup_cont,
										"Total", str_s_grup_total, 
										"Total Neto", str_s_grup_total_neto }, contentStream, tableTopY);
						tableTopY -= table.getRowHeight();
						r++;
					}
					can_dependencia += can_grupo_contable;
					sum_dependencia += sum_grupo_contable;
					sum_neto_dependencia += sum_neto_dependencia;
					can_grupo_contable = 0;
					sum_grupo_contable = 0;
					sum_neto_grupo_contable = 0;
				}
				grupo_contable = table.getContent()[i][1];
				auxiliar_contable = table.getContent()[i][2];

				r++;
//				drawTableGridContables(table, new String[] { "Grupo Contable", grupo_contable, "Auxiliar Contable",
//						auxiliar_contable }, contentStream, tableTopY);
				drawCurrentPageContable(table, new String[] { "Grupo Contable:", grupo_contable, "Auxiliar Contable:",
						auxiliar_contable }, contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
			}

			String[] current = Arrays.copyOfRange(table.getContent()[i], 3, table.getContent()[i].length);
			/**
			 * Suma de los Auxiliares Contables
			 */
			sum_auxiliares_contables += Float.parseFloat(table.getContent()[i][6].replace(".", "").replace(",", ".") == "null" ? "0"
					: table.getContent()[i][6].replace(".", "").replace(",", "."));
			sum_neto_auxiliares_contables += Float.parseFloat(table.getContent()[i][7].replace(".", "").replace(",", ".") == "null" ? "0" : table
					.getContent()[i][7].replace(".", "").replace(",", "."));
			can_auxiliares_contables++;;

			drawCurrentPage(table, current, contentStream, tableTopY);
			tableTopY -= table.getRowHeight();
			r++;
		}
		if ((r + 6) >= rowsPerPage || r == 0) {
			if (contentStream != null) {
				contentStream.close();
			}
			page = generatePage(doc, table);
			contentStream = generateContentStream(doc, page, table);
			r = 0;
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
					.getHeight() - table.getMargin();
			tableTopY -= table.getHeaderSize() * table.getRowHeight();
			writeHeader(contentStream, tableTopY, table);
			tableTopY -= table.getRowHeight();

			r += 2;
		}
		can_grupo_contable += can_auxiliares_contables;
		sum_grupo_contable += sum_grupo_contable;
		sum_neto_grupo_contable += sum_neto_auxiliares_contables;

		can_dependencia += can_grupo_contable;
		sum_dependencia += sum_grupo_contable;
		sum_neto_dependencia += sum_neto_dependencia;

		double valorf_aux = Double.parseDouble(String.valueOf(can_auxiliares_contables));
		DecimalFormat formater = new DecimalFormat("##,###,###,###.##");
		String str_f_aux = formater.format(valorf_aux);
		
		double f_aux_total = Double.parseDouble(String.valueOf(sum_auxiliares_contables));
		String str_f_aux_total = formater.format(f_aux_total);
		
		double f_auxtotal_neto = Double.parseDouble(String.valueOf(sum_neto_auxiliares_contables));
		String str_f_aux_total_neto = formater.format(f_auxtotal_neto);
		
		drawCurrentPageCorte(
				table,
				new String[] { "Cantidad por Auxiliar Contable", str_f_aux, 
						"Total",str_f_aux_total, 
						"Total Neto",str_f_aux_total_neto }, contentStream, tableTopY);
		tableTopY -= table.getRowHeight();
		
		double valorf_grupo_contable = Double.parseDouble(String.valueOf(can_grupo_contable));
		String str_f_grup_cont = formater.format(valorf_grupo_contable);
		
		double f_grp_total = Double.parseDouble(String.valueOf(sum_grupo_contable));
		String str_f_grup_total = formater.format(f_grp_total);
		
		double f__gruptotal_neto = Double.parseDouble(String.valueOf(sum_neto_grupo_contable));
		String str_f_grup_total_neto = formater.format(f__gruptotal_neto);
		
		drawCurrentPageCorte(table, new String[] { "Cantidad por Grupo Contable", str_f_grup_cont,
				"Total", str_f_grup_total, 
				"Total Neto", str_f_grup_total_neto },
				contentStream, tableTopY);
		tableTopY -= table.getRowHeight();

		double valorf_sep_contable = Double.parseDouble(String.valueOf(can_dependencia));
		String str_f_sep_cont = formater.format(valorf_sep_contable);
		
		double f_sep_total = Double.parseDouble(String.valueOf(sum_dependencia));
		String str_f_sep_total = formater.format(f_sep_total);
		
		double f_septotal_neto = Double.parseDouble(String.valueOf(sum_neto_dependencia));
		String str_f_sep_total_neto = formater.format(f_septotal_neto);
		
		drawCurrentPageCorte(table, new String[] { "Cantidad por Dependencia", str_f_sep_cont,
				"Total", str_f_sep_total,
				"Total Neto", str_f_sep_total_neto },
				contentStream, tableTopY);
		tableTopY -= table.getRowHeight();

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

	private void drawCurrentPageHeader(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {

		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY
				- (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table
						.getFontSize()) / 4);
		writeContentLineHeader(strings, contentStream, nextTextX, nextTextY, table);
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
			nextY -= 4 * table.getRowHeight();
		}
		// Modificado solo pra el titulo para grilla modificar por
		// final float tableYLength = table.getRowHeight() +
		// (table.getRowHeight() * currentPageContent.length);
		// final float tableBottomY = tableTopY - tableYLength;
		final float tableBottomY = tableTopY - 4 * table.getRowHeight();

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
			
			lineContent[i] = lineContent[i] != null ? lineContent[i] : "";
			String text = lineContent[i].replace("\n", "");
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

	private void writeContentLineHeader(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {

		contentStream.setFont(table.getTextFont(), table.getFontSize());
		float copynextTextY = nextTextY;
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i].replace("\n", "");
//			if (text_width > table.getColumns().get(i).getWidth()) {
				copynextTextY = nextTextY;
				StringTokenizer st = new StringTokenizer(text, " ");
				while (st.hasMoreTokens()) {
					contentStream.beginText();
					
					String part_text = st.nextToken();
					long text_width = (long) ((long) ((table.getTextFont().getStringWidth(part_text) / 1000.0f) * table.getFontSize())*1.20);
					contentStream.moveTextPositionByAmount(nextTextX + table.getColumns().get(i).getWidth()/2 - text_width/2, copynextTextY);
//					contentStream.moveTextPositionByAmount(nextTextX, copynextTextY);
					try {
						contentStream.showText(part_text);
					} catch (Exception e) {
						System.out.print(lineContent[0] + "->" + text);
					}
					contentStream.endText();
					copynextTextY -= table.getRowHeight();
				}
//			}
//			contentStream.beginText();
//			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
//			try {
//				contentStream.showText(text != null ? text : "");
//			} catch (Exception e) {
//				System.out.print(lineContent[0] + "->" + text);
//			}
//			contentStream.endText();
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
			if (i == 0) {

				nextTextX += table.getWidth() / 4;
			} else {
				nextTextX += table.getWidth() / 6;
			}
		}
	}

	private void drawTableGridCorte(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		float nextY = tableTopY;

		// Modificado para solo el tititulo para grilla completa modificar por
		// for (int i = 0; i <= currentPageContent.length + 1; i++) {

		for (int i = 0; i <= 2; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(), nextY);
			nextY -= table.getRowHeight();
		}
		// Modificado solo pra el titulo para grilla modificar por
		// final float tableYLength = table.getRowHeight() +
		// (table.getRowHeight() * currentPageContent.length);
		// final float tableBottomY = tableTopY - tableYLength;
		final float tableBottomY = tableTopY - 2 * table.getRowHeight();

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
		nextTextY -= table.getRowHeight()*0.75;
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
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		contentStream.showText(usuario.getFull_name());
		contentStream.endText();

		contentStream.setFont(table.getTitleFont(), table.getFontSizetitle());
		nextTextY -= table.getRowHeight()*0.75;
		contentStream.beginText();
		long text_width = (long) ((table.getTitleFont().getStringWidth(table.getTitle()) / 1000.0f) * table
				.getFontSizetitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2) - (text_width / 2) + (table.getMargin() / 2),
				nextTextY);
		contentStream.showText(table.getTitle());
		contentStream.endText();

		contentStream.setFont(table.getSubtitleFont(), table.getFontSizesubtitle());
		nextTextY -= table.getRowHeight()*0.75;
		contentStream.beginText();
		contentStream.endText();

	}
}
