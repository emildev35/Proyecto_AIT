package ait.sistemas.proyecto.activos.component.etiqueta;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import ait.sistemas.proyecto.common.component.CodeBar;

/**
 * Clase encargada de la generacion del documentos que contiene todos los
 * codigos de barras de los activos listos para su impresion
 * 
 * @author franzemil
 *
 */
public class PdfEtiqueta {
	
	private PDDocument doc;
	/**
	 * Retorna true el caso que el documento se haya generado con exito y false 
	 * si ocurrio un inconveniente
	 * @param etiqueta
	 * @param savePath
	 * @return
	 * @throws IOException
	 */
	public boolean generatePDF(Etiqueta etiqueta, String savePath) throws IOException {
		boolean result = false;
		doc = null;
		try {
			doc = new PDDocument();
			drawDocument(doc, etiqueta);
			doc.save(savePath);
			result = true;
		} finally {
			if (doc != null) {
				doc.close();
			}
		}
		return result;
	}
	/**
	 * Genera las paginas y se encarga de llenarlas con los datos espesificados de la 
	 * Etiqueta que se envie
	 * @param doc
	 * @param etiqueta
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void drawDocument(PDDocument doc, Etiqueta etiqueta) throws IOException {
		float nextY = (float) (etiqueta.getPageSize().getHeight() - etiqueta.getMargin()*0.75);
		float nextX = (float) (etiqueta.getMargin()*0.60);
		PDPage page = generatePage(doc, etiqueta);
		PDPageContentStream contentStream = generateContentStream(doc, page, etiqueta);
		for (CodeBar codigo : etiqueta.getCodigos()) {
			float copynextY = nextY;
			if (codigo.getNombre().length() > 40) {
				for (String parte_nombre : separeString(codigo.getNombre(), 40)) {
					contentStream.beginText();
					contentStream.moveTextPositionByAmount(nextX, nextY);
					contentStream.showText(parte_nombre);
					contentStream.endText();
					nextY -= 6;
				}
				nextY -= 65;
			} else {
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(nextX, nextY);
				contentStream.showText(codigo.getNombre());
				contentStream.endText();
				nextY -= 72;
			}
			PDImageXObject pdImage = PDImageXObject.createFromFile(codigo.getPathCode(), doc);
			contentStream.drawImage(pdImage, nextX, nextY, etiqueta.getWidthCode(), etiqueta.getHeigthCode());
			nextX += 197;
			nextY = copynextY;
			if ((nextX + 197) >= (etiqueta.getPageSize().getWidth() + 40)) {
				nextX = (float) (etiqueta.getMargin()*0.60);
				nextY -= 102;
				contentStream.close();
				if ((nextY - 90) <= 0) {
					page = generatePage(doc, etiqueta);
					contentStream = generateContentStream(doc, page, etiqueta);
					nextY = (float) (etiqueta.getPageSize().getHeight() - etiqueta.getMargin()*0.75);
					nextX = (float) (etiqueta.getMargin()*0.60);
				}
			}
		}
		contentStream.close();
		
	}
	
	/**
	 * Serpara un string dentro de un array cada string que sea generado contara
	 * con una longitud de 40 caracteres
	 * 
	 * @param data
	 * @return
	 */
	public String[] separeString(String data, int number) {
		int intpart = (int) Math.ceil((double) data.length() / (double) number);
		String[] row = new String[intpart];
		for (int i = 0; i < intpart; i++) {
			if ((i + 1) * number > data.length()) {
				row[i] = data.substring(i * number, data.length());
			} else {
				row[i] = data.substring(i * number, number * (i + 1));
			}
			
		}
		return row;
	}
	/**
	 * Crea una nueva pagina para el documento
	 * @param doc
	 * @param table
	 * @return
	 */
	private PDPage generatePage(PDDocument doc, Etiqueta table) {
		PDPage page = new PDPage();
		page.setMediaBox(table.getPageSize());
		doc.addPage(page);
		return page;
	}
	/**
	 * Genera un nuevo contentStream para una pagina
	 * @param doc
	 * @param page
	 * @param table
	 * @return
	 * @throws IOException
	 */
	private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, Etiqueta table) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, false, false);
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		return contentStream;
	}
	
	
	
}
