package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fecha_Depreciacion implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int FDE_Id;

	private Date FDE_Fecha_Depreciacion;
	
	public Fecha_Depreciacion(){
		
	}

	public int getFDE_Id() {
		return FDE_Id;
	}

	public void setFDE_Id(int fDE_Id) {
		FDE_Id = fDE_Id;
	}

	public Date getFDE_Fecha_Depreciacion() {
		return FDE_Fecha_Depreciacion;
	}

	public void setFDE_Fecha_Depreciacion(Date fDE_Fecha_Depreciacion) {
		FDE_Fecha_Depreciacion = fDE_Fecha_Depreciacion;
	}
	
}
