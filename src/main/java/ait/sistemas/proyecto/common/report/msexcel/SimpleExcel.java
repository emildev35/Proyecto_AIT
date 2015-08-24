package ait.sistemas.proyecto.common.report.msexcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SimpleExcel {
	public final String SAVE_PATH = "/tmp/" + String.valueOf(new java.util.Date().getTime()) + ".xlsx";
	
	@SuppressWarnings("resource")
	public void save(String[][] data, List<String> columns) {
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet = workbook.createSheet();
			XSSFRow row;
			Map<String, String[]> empinfo = new TreeMap<String, String[]>();
			
			int r = 0;
			String[] header = new String[columns.size()];
			for (String column : columns) {
				header[r] = column;
				r++;
			}
			r = 1;
			empinfo.put(String.valueOf(r), header);
			r++;
			for (String[] item : data) {
				
				empinfo.put(String.valueOf(r), item);
				r++;
			}
			
			Set<String> keyid = empinfo.keySet();
			int rowid = 0;
			for (String key : keyid) {
				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = empinfo.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
					cell.setCellValue((String) obj);
				}
			}
			
			FileOutputStream out;
			out = new FileOutputStream(new File(SAVE_PATH));
			workbook.write(out);
			out.close();
			System.out.println("createworkbook.xlsx written successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
