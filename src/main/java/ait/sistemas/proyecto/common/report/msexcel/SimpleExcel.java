package ait.sistemas.proyecto.common.report.msexcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ait.sistemas.proyecto.common.component.PathValues;

@SuppressWarnings("deprecation")
public class SimpleExcel {
	
	public final String SAVE_PATH = PathValues.PATH_REPORTS + String.valueOf(new java.util.Date().getTime()) + ".xlsx";
	XSSFWorkbook workbook = new XSSFWorkbook();
	
	public void save(String[][] data, List<String> columns, String titulo) {
		
		try {
			XSSFSheet spreadsheet = workbook.createSheet();
			XSSFRow row;
			writeTitle(spreadsheet, titulo, columns);
			Map<String, String[]> empinfo = new TreeMap<String, String[]>();
			XSSFRow row_title = spreadsheet.createRow((short) 0);
			row_title.setHeight((short) 800);
			XSSFCell celltitle = (XSSFCell) row_title.createCell((short) 0);
			celltitle.setCellValue(titulo);
			spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.size()));
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
			int rowid = 1;
			for (String key : keyid) {
				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = empinfo.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
					try {
						cell.setCellValue(Double.parseDouble(obj.toString()));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						continue;
					} catch (Exception ex) {
					}
					try {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						cell.setCellValue(formatter.parse(obj.toString()));
						CellStyle cellStyle = workbook.createCellStyle();
						CreationHelper createHelper = workbook.getCreationHelper();
						cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));
						cell.setCellStyle(cellStyle);
						continue;
					} catch (Exception ex) {
					}
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
	
	private void writeTitle(XSSFSheet spreadsheet, String titulo, List<String> columns) {
		XSSFRow row_title = spreadsheet.createRow((short) 0);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 18);
		CellStyle title_style = workbook.createCellStyle();
		title_style.setFont(font);
		row_title.setHeight((short) 400);
		XSSFCell celltitle = (XSSFCell) row_title.createCell((short) 0);
		celltitle.setCellStyle(title_style);
		celltitle.setCellValue(titulo);
		spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.size()));
	}
}
