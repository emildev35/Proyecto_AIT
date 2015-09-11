package ait.sistemas.proyecto.common.component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * Clase encargada de generar el Codigo de Barras como una imagen
 * 
 * @author franzemil
 *
 */
public class CodeBar {
	
	private String codigo;
	private String grupo_contable;
	private String auxiliar_contable;
	private String ruta_imagen;
	private String nombre;
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CodeBar() {
	}
	
	public CodeBar(String codigo, String grupo_contable, String auxiliar_contable) {
		this.codigo = codigo;
		this.grupo_contable = grupo_contable;
		this.auxiliar_contable = auxiliar_contable;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getGrupo_contable() {
		return grupo_contable;
	}
	
	public void setGrupo_contable(String grupo_contable) {
		this.grupo_contable = grupo_contable;
	}
	
	public String getAuxiliar_contable() {
		return auxiliar_contable;
	}
	
	public void setAuxiliar_contable(String auxiliar_contable) {
		this.auxiliar_contable = auxiliar_contable;
	}
	
	public String getRuta_imagen() {
		return ruta_imagen;
	}
	
	public void generate() throws FileNotFoundException {
		Code39Bean bean = new Code39Bean();
		
		final int dpi = 320;
		
		bean.setModuleWidth(UnitConv.in2mm(1.f / dpi));
		
		bean.setWideFactor(5);
		bean.doQuietZone(false);
		
		File outputFile = new File(PathValues.CODE_PATH + codigo + ".png");
		OutputStream out = new FileOutputStream(outputFile);
		try {
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			if (grupo_contable.length() == 1) {
				grupo_contable = "0" + grupo_contable;
			}
			if (auxiliar_contable.length() == 1) {
				auxiliar_contable = "00" + auxiliar_contable;
			}
			if (auxiliar_contable.length() == 2) {
				auxiliar_contable = "0" + auxiliar_contable;
			}
			for (int i = 1; i <= 5; i++) {
				if (codigo.length() < 5) {
					codigo = "0" + codigo;
				}
			}
			if (auxiliar_contable.length() == 1) {
				auxiliar_contable = "0" + auxiliar_contable;
			}
			String codigo_barras = String.format("%s-%s-%s", grupo_contable, auxiliar_contable, codigo);
			bean.generateBarcode(canvas, codigo_barras);
			canvas.finish();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getPathCode() {
		ruta_imagen = PathValues.CODE_PATH + codigo + ".png";
		File f = new File(ruta_imagen);
		if (!f.exists() && !f.isDirectory()) {
			try {
				generate();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return ruta_imagen;
	}
}
