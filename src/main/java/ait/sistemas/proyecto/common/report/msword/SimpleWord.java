package ait.sistemas.proyecto.common.report.msword;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class SimpleWord {
	
	public final String SAVE_PATH = "/tmp/" + String.valueOf(new java.util.Date().getTime()) + ".docx";
	private XWPFDocument word_document = new XWPFDocument();
	
	public void save(String[][] data, List<String> columns, String titulo) {
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(SAVE_PATH));
			 XWPFParagraph pr_title = word_document.createParagraph();
			XWPFRun rn_title = pr_title.createRun();
			rn_title.setBold(true);
			rn_title.setFontSize(18);
			rn_title.setText(titulo);
			
			XWPFTable table = this.word_document.createTable();
			
			
			XWPFTableRow tableRowtitle = table.getRow(0);
			int r = 0;
			
			for (String col : columns) {
				// XWPFParagraph pr_header = word_document.createParagraph();
				XWPFParagraph pr_header;
				if (r == 0) {
					pr_header = tableRowtitle.getCell(0).getParagraphs().get(0);
				} else {
					pr_header = tableRowtitle.addNewTableCell().getParagraphs().get(0);
				}
				XWPFRun rn_header = pr_header.createRun();
				rn_header.setBold(true);
				rn_header.setFontSize(16);
				rn_header.setText(col);
				r++;
			}
			drawTable(table, data);
			
			try {
				word_document.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("create_table.docx written successully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void drawTable(XWPFTable table, String[][] data) {
		for (int i = 0; i < data.length; i++) {
			XWPFTableRow tableRow = table.createRow();
			for (int j = 0; j < data[i].length; j++) {
				tableRow.getCell(j).setText(data[i][j]);
			}
			
		}
	}
	
}
