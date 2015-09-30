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

@SuppressWarnings("deprecation")
public class PDFRevalorizacionGenerador {
	
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
	
	public int saltopag(int r, Integer rowsPerPage, Table table, PDPageContentStream contentStream, float tableTopY,
			PDDocument doc, String dependencia, int i) throws IOException {
		if (contentStream != null) {
			contentStream.close();
		}
		PDPage page;
		page = generatePage(doc, table);
		contentStream = generateContentStream(doc, page, table);
		r = table.getHeaderSize();
		tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight()
				- table.getMargin();
		tableTopY -= table.getRowHeight() * table.getHeaderSize();
		
		dependencia = table.getContent()[i][0];
		// drawTableGridDependencia(table, new String[] {
		// "Dependencia ", dependencia }, contentStream, tableTopY);
		writeHeader(contentStream, tableTopY, table);
		drawCurrentPageDependencia(table, new String[] { "Dependencia :", dependencia }, contentStream, tableTopY);
		drawCurrentPageOrden(table, new String[] { "(Orden:/Dependencia/Grupo/Auxiliar/Codigo)" }, contentStream, tableTopY);
		r++;
		tableTopY -= table.getRowHeight();
		
		drawTableGridGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
		drawCurrentPageHeaderGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
		tableTopY -= table.getRowHeight();
		
		drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
		drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
		// tableTopY -= table.getRowHeight();
		r += 2;
		return r;
	}
	
	public void drawTable(PDDocument doc, Table table) throws IOException {
		
		Integer rowsPerPage = new Double(Math.floor(table.getHeight() / table.getRowHeight())).intValue();
		
		int r = table.getHeaderSize();
		PDPage page;
		PDPageContentStream contentStream = null;
		float tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
				.getHeight() - table.getMargin();
		
		String dependencia = "";
		String grupo_contable = "";
		String auxiliar_contable = "";
		
		double sum_dependencia = 0;
		double sum_por_dependencia = 0;
		double sum_grupo_contable = 0;
		double sum_auxiliares_contables = 0;
		double sum_neto_dependencia = 0;
		double sum_neto_por_dependencia = 0;
		double sum_neto_grupo_contable = 0;
		double sum_neto_auxiliares_contables = 0;
		int can_dependencia = 0;
		int can_por_dependencia = 0;
		int can_grupo_contable = 0;
		int can_auxiliares_contables = 0;
		
		int numero_dependencias = 0;
		
		for (int i = 0; i < table.getContent().length; i++) {
			if (r >= rowsPerPage || r == table.getHeaderSize()) {
				// r += saltopag(r, rowsPerPage, table, contentStream,
				// tableTopY, doc, dependencia, i);
				if (contentStream != null) {
					contentStream.close();
				}
				page = generatePage(doc, table);
				contentStream = generateContentStream(doc, page, table);
				r = table.getHeaderSize();
				tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
						.getHeight() - table.getMargin();
				tableTopY -= table.getRowHeight() * table.getHeaderSize();
				
				dependencia = table.getContent()[i][0];
				// drawTableGridDependencia(table, new String[] {
				// "Dependencia ", dependencia }, contentStream, tableTopY);
				writeHeader(contentStream, tableTopY, table);
				drawCurrentPageDependencia(table, new String[] { "Dependencia :", dependencia }, contentStream, tableTopY);
				drawCurrentPageOrden(table, new String[] { "(Orden:/Dependencia/Grupo/Auxiliar/Codigo)" }, contentStream,
						tableTopY);
				r++;
				tableTopY -= table.getRowHeight();
				
				drawTableGridGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
				drawCurrentPageHeaderGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				
				drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
				
				tableTopY -= 3*table.getRowHeight();
				r += 5;
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
					
					double valors_auxiliar_contable = Double.parseDouble(String.valueOf(can_auxiliares_contables) == null ? "0"
							: String.valueOf(can_auxiliares_contables));
					DecimalFormat formate = new DecimalFormat("##,###,###,###");
					String str_aux_cont = formate.format(valors_auxiliar_contable);
					
					DecimalFormat formater = new DecimalFormat("##,###,###,###.00");
					
					double s_total = sum_auxiliares_contables;
					String str_s_total = formater.format(s_total);
					
					double s_total_neto = sum_neto_auxiliares_contables;
					String str_s_total_neto = formater.format(s_total_neto);
					contentStream.drawLine(table.getMargin() * 26, tableTopY, table.getMargin() * 31, tableTopY);
					
					drawCurrentPageCorte(table, new String[] { "Cantidad por Auxiliar Contable", str_aux_cont, "Total",
							str_s_total, str_s_total_neto }, contentStream, tableTopY);
					if (r >= rowsPerPage || r == table.getHeaderSize()) {
						if (contentStream != null) {
							contentStream.close();
						}
						page = generatePage(doc, table);
						contentStream = generateContentStream(doc, page, table);
						r = table.getHeaderSize();
						tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table
								.getPageSize().getHeight() - table.getMargin();
						tableTopY -= table.getHeaderSize() * table.getRowHeight();
						
						writeHeader(contentStream, tableTopY, table);
						tableTopY -= table.getRowHeight();
						
						r++;
					}
					// tableTopY -= table.getRowHeight();
					// r++;
				}
				can_auxiliares_contables = 0;
				sum_auxiliares_contables = 0;
				sum_neto_auxiliares_contables = 0;
				
				
				r++;
				if (r >= rowsPerPage || r == table.getHeaderSize()) {
					if (contentStream != null) {
						contentStream.close();
					}
					page = generatePage(doc, table);
					contentStream = generateContentStream(doc, page, table);
					r = table.getHeaderSize();
					tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
							.getHeight() - table.getMargin();
					tableTopY -= table.getRowHeight() * table.getHeaderSize();
					
					dependencia = table.getContent()[i][0];
					// drawTableGridDependencia(table, new String[] {
					// "Dependencia ", dependencia }, contentStream, tableTopY);
					writeHeader(contentStream, tableTopY, table);
					drawCurrentPageDependencia(table, new String[] { "Dependencia :", dependencia }, contentStream, tableTopY);
					drawCurrentPageOrden(table, new String[] { "(Orden:/Dependencia/Grupo/Auxiliar/Codigo)" }, contentStream,
							tableTopY);
					r++;
					tableTopY -= table.getRowHeight();
					
					if (!table.getContent()[i][0].equals(dependencia)) {
						/**
						 * Suma de Dependencias
						 */
						sum_dependencia += sum_grupo_contable;
						sum_neto_dependencia += sum_neto_auxiliares_contables;
						sum_por_dependencia += sum_grupo_contable;
						sum_grupo_contable = 0;
						sum_neto_auxiliares_contables = 0;
					}
					
					drawTableGridGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
					drawCurrentPageHeaderGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
					tableTopY -= table.getRowHeight();
					
					drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
					drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
					tableTopY -= 3*table.getRowHeight();
					r += 5;
					
				}
				// cambio grupo contable
				if (!table.getContent()[i][1].equals(grupo_contable)) {
					/**
					 * Suma de los Grupo Contable
					 */
					if (!grupo_contable.equals("")) {
						
						// drawTableGridContables(table, new String[] { "", ""
						// }, contentStream, tableTopY);
						double valors_grupo_contable = Double.parseDouble(String.valueOf(can_grupo_contable));
						
						DecimalFormat formate = new DecimalFormat("##,###,###,###");
						String str_grup_cont = formate.format(valors_grupo_contable);
						DecimalFormat formater = new DecimalFormat("##,###,###,###.00");
						
						double s_grp_total = Double.parseDouble(String.valueOf(sum_grupo_contable));
						String str_s_grup_total = formater.format(s_grp_total);
						
						double s_gruptotal_neto = Double.parseDouble(String.valueOf(sum_neto_grupo_contable));
						String str_s_grup_total_neto = formater.format(s_gruptotal_neto);
						tableTopY -= table.getRowHeight();
						contentStream.drawLine(table.getMargin() * 26, tableTopY, table.getMargin() * 31, tableTopY);
						drawCurrentPageCorte(table, new String[] { "Cantidad por Grupo Contable", str_grup_cont, "Total",
								str_s_grup_total, str_s_grup_total_neto }, contentStream, tableTopY);
						// tableTopY -= table.getRowHeight();
						r++;
						if (r >= rowsPerPage || r == table.getHeaderSize()) {
							if (contentStream != null) {
								contentStream.close();
							}
							page = generatePage(doc, table);
							contentStream = generateContentStream(doc, page, table);
							r = table.getHeaderSize();
							tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table
									.getPageSize().getHeight() - table.getMargin();
							tableTopY -= table.getHeaderSize() * table.getRowHeight();
							
							writeHeader(contentStream, tableTopY, table);
							tableTopY -= table.getRowHeight();
							writeHeader(contentStream, tableTopY, table);
							drawCurrentPageDependencia(table, new String[] { "Dependencia :", dependencia }, contentStream,
									tableTopY);
							drawCurrentPageOrden(table, new String[] { "(Orden:/Dependencia/Grupo/Auxiliar/Codigo)" },
									contentStream, tableTopY);
							tableTopY -= table.getRowHeight() * 1.1;
							// tableTopY -= table.getRowHeight();
							drawTableGridGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
							drawCurrentPageHeaderGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
							tableTopY -= table.getRowHeight();
							
							drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
							drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
							tableTopY -= 3*table.getRowHeight();
							r += 5;
							r += 2;
						}
					}
					can_dependencia += can_grupo_contable;
					sum_dependencia += sum_grupo_contable;
					sum_neto_dependencia += sum_neto_grupo_contable;
					can_por_dependencia += can_grupo_contable;
					sum_por_dependencia += sum_grupo_contable;
					sum_neto_por_dependencia += sum_neto_grupo_contable;
					can_grupo_contable = 0;
					sum_grupo_contable = 0;
					sum_neto_grupo_contable = 0;
				}
				// Cambio de Dependencia
				if (!table.getContent()[i][0].equals(dependencia)) {
					dependencia = table.getContent()[i][0];
					// drawTableGridDependencia(table, new String[] {
					// "Dependencia ", dependencia }, contentStream, tableTopY);
					
					// calculo suma por Dependencia
					
					DecimalFormat formate = new DecimalFormat("##,###,###,###");
					double valorf_dep_contable = Double.parseDouble(String.valueOf(can_por_dependencia));
					String str_f_dep_cont = formate.format(valorf_dep_contable);
					DecimalFormat formater = new DecimalFormat("##,###,###,###.00");
					
					double f_dep_total = Double.parseDouble(String.valueOf(sum_por_dependencia));
					String str_f_dep_total = formater.format(f_dep_total);
					
					double f_deptotal_neto = Double.parseDouble(String.valueOf(sum_neto_por_dependencia));
					String str_f_dep_total_neto = formater.format(f_deptotal_neto);
					tableTopY -= table.getRowHeight();
					contentStream.drawLine(table.getMargin() * 26, tableTopY, table.getMargin() * 31, tableTopY);
					
					drawCurrentPageCorte(table, new String[] { "Cantidad por Dependencia", str_f_dep_cont, "Total",
							str_f_dep_total, str_f_dep_total_neto }, contentStream, tableTopY);
					tableTopY -= table.getRowHeight();
					
					/**
					 * Suma de Dependencias
					 */
					if (contentStream != null) {
						contentStream.close();
					}
					page = generatePage(doc, table);
					contentStream = generateContentStream(doc, page, table);
					
					r = table.getHeaderSize();
					tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
							.getHeight() - table.getMargin();
					
					tableTopY -= table.getHeaderSize() * table.getRowHeight();
					writeHeader(contentStream, tableTopY, table);
					drawCurrentPageDependencia(table, new String[] { "Dependencia :", dependencia }, contentStream, tableTopY);
					drawCurrentPageOrden(table, new String[] { "(Orden:/Dependencia/Grupo/Auxiliar/Codigo)" }, contentStream,
							tableTopY);
					tableTopY -= table.getRowHeight() * 1.1;
					// tableTopY -= table.getRowHeight();
					drawTableGridGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
					drawCurrentPageHeaderGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
					tableTopY -= table.getRowHeight();
					
					drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
					drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
					// tableTopY -= table.getRowHeight();
					r += 2;
					
					r++;
					sum_dependencia += sum_grupo_contable;
					sum_neto_dependencia += sum_neto_auxiliares_contables;
					sum_por_dependencia = 0;
					sum_neto_por_dependencia = 0;
					sum_grupo_contable += sum_grupo_contable;
					sum_neto_auxiliares_contables = 0;
					can_por_dependencia = 0;
					
					numero_dependencias += 1;
				}
				grupo_contable = table.getContent()[i][1];
				auxiliar_contable = table.getContent()[i][2];
				
				// drawTableGridContables(table, new String[] {
				// "Grupo Contable", grupo_contable, "Auxiliar Contable",
				// auxiliar_contable }, contentStream, tableTopY);
				tableTopY -= table.getRowHeight();
				drawCurrentPageContable(table, new String[] { grupo_contable, auxiliar_contable }, contentStream, tableTopY);
				r++;
				if (r >= rowsPerPage || r == table.getHeaderSize()) {
					if (contentStream != null) {
						contentStream.close();
					}
					page = generatePage(doc, table);
					contentStream = generateContentStream(doc, page, table);
					r = table.getHeaderSize();
					tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
							.getHeight() - table.getMargin();
					tableTopY -= table.getRowHeight() * table.getHeaderSize();
					
					dependencia = table.getContent()[i][0];
					// drawTableGridDependencia(table, new String[] {
					// "Dependencia ", dependencia }, contentStream, tableTopY);
					writeHeader(contentStream, tableTopY, table);
					drawCurrentPageDependencia(table, new String[] { "Dependencia :", dependencia }, contentStream, tableTopY);
					drawCurrentPageOrden(table, new String[] { "(Orden:/Dependencia/Grupo/Auxiliar/Codigo)" }, contentStream,
							tableTopY);
					r++;
					tableTopY -= table.getRowHeight();
					
					if (!table.getContent()[i][0].equals(dependencia)) {
						/**
						 * Suma de Dependencias
						 */
						sum_dependencia += sum_grupo_contable;
						sum_neto_dependencia += sum_neto_auxiliares_contables;
						sum_por_dependencia += sum_grupo_contable;
						sum_grupo_contable = 0;
						sum_neto_auxiliares_contables = 0;
					}
					
					drawTableGridGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
					drawCurrentPageHeaderGA(table, table.getColumnsNamesAsArrayGA(), contentStream, tableTopY);
					tableTopY -= table.getRowHeight();
					
					drawTableGrid(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
					drawCurrentPageHeader(table, table.getColumnsNamesAsArray(), contentStream, tableTopY);
					tableTopY -= 3*table.getRowHeight();
					r += 5;
					
				}
			}
			
			String[] current = Arrays.copyOfRange(table.getContent()[i], 3, table.getContent()[i].length);
			/**
			 * Suma de los Auxiliares Contables
			 */
			
			sum_auxiliares_contables += Double.parseDouble(table.getContent()[i][6] == "null" ? "0" : table.getContent()[i][6]);
			sum_neto_auxiliares_contables += Double.parseDouble(table.getContent()[i][7] == "null" ? "0"
					: table.getContent()[i][7]);
			can_auxiliares_contables++;
			tableTopY -= table.getRowHeight();
			drawCurrentPage(table, current, contentStream, tableTopY);
			r++;
		}
		if (r >= rowsPerPage || r == table.getHeaderSize()) {
			if (contentStream != null) {
				contentStream.close();
			}
			page = generatePage(doc, table);
			contentStream = generateContentStream(doc, page, table);
			r = table.getHeaderSize();
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
					.getHeight() - table.getMargin();
			tableTopY -= table.getHeaderSize() * table.getRowHeight();
			
			writeHeader(contentStream, tableTopY, table);
			tableTopY -= table.getRowHeight();
			
			r++;
		}
		can_grupo_contable += can_auxiliares_contables;
		sum_grupo_contable += sum_auxiliares_contables;
		sum_neto_grupo_contable += sum_neto_auxiliares_contables;
		
		can_dependencia += can_grupo_contable;
		sum_dependencia += sum_grupo_contable;
		sum_neto_dependencia += sum_neto_grupo_contable;
		
		can_por_dependencia += can_grupo_contable;
		sum_por_dependencia += sum_grupo_contable;
		sum_neto_por_dependencia += sum_neto_grupo_contable;
		
		tableTopY -= table.getRowHeight();
		DecimalFormat formate = new DecimalFormat("##,###,###,###");
		
		double valorf_aux = Double.parseDouble(String.valueOf(can_auxiliares_contables));
		String str_f_aux = formate.format(valorf_aux);
		DecimalFormat formater = new DecimalFormat("##,###,###,###.00");
		
		double f_aux_total = Double.parseDouble(String.valueOf(sum_auxiliares_contables));
		String str_f_aux_total = formater.format(f_aux_total);
		
		double f_auxtotal_neto = Double.parseDouble(String.valueOf(sum_neto_auxiliares_contables));
		String str_f_aux_total_neto = formater.format(f_auxtotal_neto);
		contentStream.drawLine(table.getMargin() * 26, tableTopY, table.getMargin() * 31, tableTopY);
		drawCurrentPageCorte(table, new String[] { "Cantidad por Auxiliar Contable", str_f_aux, "Total", str_f_aux_total,
				str_f_aux_total_neto }, contentStream, tableTopY);
		tableTopY -= table.getRowHeight();
		
		double valorf_grupo_contable = Double.parseDouble(String.valueOf(can_grupo_contable));
		String str_f_grup_cont = formate.format(valorf_grupo_contable);
		
		double f_grp_total = Double.parseDouble(String.valueOf(sum_grupo_contable));
		String str_f_grup_total = formater.format(f_grp_total);
		
		double f__gruptotal_neto = Double.parseDouble(String.valueOf(sum_neto_grupo_contable));
		String str_f_grup_total_neto = formater.format(f__gruptotal_neto);
		contentStream.drawLine(table.getMargin() * 26, tableTopY, table.getMargin() * 31, tableTopY);
		drawCurrentPageCorte(table, new String[] { "Cantidad por Grupo Contable", str_f_grup_cont, "Total", str_f_grup_total,
				str_f_grup_total_neto }, contentStream, tableTopY);
		tableTopY -= table.getRowHeight();
		
		// calculo suma por Dependencia
		double valorf_dep_contable = Double.parseDouble(String.valueOf(can_por_dependencia));
		String str_f_dep_cont = formate.format(valorf_dep_contable);
		
		double f_dep_total = Double.parseDouble(String.valueOf(sum_por_dependencia));
		String str_f_dep_total = formater.format(f_dep_total);
		
		double f_deptotal_neto = Double.parseDouble(String.valueOf(sum_neto_por_dependencia));
		String str_f_dep_total_neto = formater.format(f_deptotal_neto);
		contentStream.drawLine(table.getMargin() * 26, tableTopY, table.getMargin() * 31, tableTopY);
		drawCurrentPageCorte(table, new String[] { "Cantidad por Dependencia", str_f_dep_cont, "Total", str_f_dep_total,
				str_f_dep_total_neto }, contentStream, tableTopY);
		tableTopY -= table.getRowHeight();
		
		if (numero_dependencias > 1) {
			// calculo suma de TODAS las Dependencias
			double valorf_sep_contable = Double.parseDouble(String.valueOf(can_dependencia));
			String str_f_sep_cont = formate.format(valorf_sep_contable);
			
			String str_f_sep_total = formater.format(sum_dependencia);
			
			String str_f_sep_total_neto = formater.format(sum_neto_dependencia);
			
			contentStream.drawLine(table.getMargin() * 26, tableTopY, table.getMargin() * 31, tableTopY);
			drawCurrentPageCorte(table, new String[] { "Cantidad todas las Dependencias", str_f_sep_cont, "Total",
					str_f_sep_total, str_f_sep_total_neto }, contentStream, tableTopY);
			tableTopY -= table.getRowHeight();
		}
		contentStream.close();
		
		drawFooter(doc, table);
		
	}
	
	private void drawCurrentPageHeaderGA(Table table, String[] columnsNamesAsArrayGA, PDPageContentStream contentStream,
			float tableTopY) throws IOException {
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() * 4 + table.getCellMargin();
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		writeContentLineHeaderGA(columnsNamesAsArrayGA, contentStream, nextTextX, nextTextY, table);
		
	}
	
	private void writeContentLineHeaderGA(String[] columnsNamesAsArrayGA, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		float copynextTextY = nextTextY;
		for (int i = 0; i < columnsNamesAsArrayGA.length; i++) {
			String text = columnsNamesAsArrayGA[i].replace("\n", "");
			copynextTextY = nextTextY;
			contentStream.beginText();
			long text_width = (long) ((long) ((table.getTextFont().getStringWidth(text) / 1000.0f) * table.getFontSize()));
			contentStream.moveTextPositionByAmount(nextTextX + table.getColumns().get(i).getWidth() / 2 - text_width / 2,
					copynextTextY);
			contentStream.showText(text);
			contentStream.endText();
			nextTextX += table.getColumnsGA().get(i).getWidth() * 0.73;
		}
		
	}
	
	@SuppressWarnings("static-access")
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
	
	private void drawCurrentPageOrden(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		
		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() * 24 + table.getCellMargin();
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		writeContentLineOrden(strings, contentStream, nextTextX, nextTextY, table);
	}
	
	private void drawCurrentPageContable(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		
		// Draws grid and borders
		// drawTableGrid(table, strings, contentStream, tableTopY);
		float nextTextX = table.getMargin() + table.getCellMargin();
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		writeContentLineContables(strings, contentStream, nextTextX, nextTextY, table);
		contentStream.drawLine(table.getMargin() * 11 / 3, tableTopY - 15, table.getMargin() * 5 + table.getWidth() / 6,
				tableTopY - 15);
		writeContentLineContables(strings, contentStream, nextTextX, nextTextY, table);
		contentStream.drawLine(table.getMargin() * 23, tableTopY - 15, table.getMargin() * 29 / 3 + table.getWidth() / 5,
				tableTopY - 15);
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
	
	private void drawTableGridGA(Table table, String[] strings, PDPageContentStream contentStream, float tableTopY)
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
			nextX += table.getColumnsGA().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
		
	}
	
	// Writes the content for one line
	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
			Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		
		for (int i = 0; i < lineContent.length; i++) {
			if (i >= 3) {
				String text = Util.numberFormat(lineContent[i]);
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
	
	private void writeContentLineHeader(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		float copynextTextY = nextTextY;
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i].replace("\n", "");
			// if (text_width > table.getColumns().get(i).getWidth()) {
			copynextTextY = nextTextY;
			StringTokenizer st = new StringTokenizer(text, " ");
			while (st.hasMoreTokens()) {
				contentStream.beginText();
				
				String part_text = st.nextToken();
				long text_width = (long) ((long) ((table.getTextFont().getStringWidth(part_text) / 1000.0f) * table.getFontSize()) * 1.20);
				contentStream.moveTextPositionByAmount(nextTextX + table.getColumns().get(i).getWidth() / 2 - text_width / 2,
						copynextTextY);
				// contentStream.moveTextPositionByAmount(nextTextX,
				// copynextTextY);
				try {
					contentStream.showText(part_text);
				} catch (Exception e) {
					System.out.print(lineContent[0] + "->" + text);
				}
				contentStream.endText();
				copynextTextY -= table.getRowHeight();
			}
			// }
			// contentStream.beginText();
			// contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			// try {
			// contentStream.showText(text != null ? text : "");
			// } catch (Exception e) {
			// System.out.print(lineContent[0] + "->" + text);
			// }
			// contentStream.endText();
			nextTextX += table.getColumns().get(i).getWidth();
		}
	}
	
	private void writeContentLineCorte(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
			Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		nextTextX = table.getColumns().get(2).getWidth() - 8 * table.getMargin();
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			if (i >= 2) {
				
				contentStream.moveTextPositionByAmount(
						Util.justificar(text, nextTextX + table.getColumns().get(4).getWidth(), table.getTextFont(),
								table.getFontSize()), nextTextY);
			} else {
				contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
				
			}
			
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			nextTextX += 2 * table.getColumns().get(4).getWidth();
			if (i == 3) {
				nextTextX = table.getMargin() + table.getWidth() - table.getColumns().get(4).getWidth();
			}
			if (i == 2) {
				nextTextX = table.getMargin() + table.getWidth() - 2 * table.getColumns().get(4).getWidth();
				
			}
		}
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
			nextTextX += table.getWidth() * 0.07;
		}
	}
	
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
	
	private void writeContentLineContables(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		
		nextTextX = (float) table.getColumns().get(2).getWidth() * 3;
		for (int i = 0; i < lineContent.length; i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			nextTextX += table.getColumns().get(2).getWidth() * 8;
			// contentStream.drawLine(nextTextX, nextTextY, nextTextX*5,
			// nextTextY);
			// contentStream.drawLine(table.getMargin() * 16, nextTextY,
			// table.getMargin() * 11 + table.getWidth()
			// / 6, nextTextY);
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
