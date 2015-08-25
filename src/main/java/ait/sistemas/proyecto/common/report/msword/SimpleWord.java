package ait.sistemas.proyecto.common.report.msword;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class SimpleWord {
	
	public final String SAVE_PATH = "C://" + String.valueOf(new java.util.Date().getTime()) + ".docx";
	private XWPFDocument word_document = new XWPFDocument();
	
	public void save(String[][] data,  List<String> columns, String titulo) {
		// Write the word_document in file system
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(SAVE_PATH));
			
			XWPFTable table = this.word_document.createTable();
			
			XWPFTableRow tableRowtitle = table.getRow(0);
			
			for (String string : columns) {
				tableRowtitle.addNewTableCell().setText(string);
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
