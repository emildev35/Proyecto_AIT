package ait.sistemas.proyecto.activos.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conecction {
	private final String str_conn = "jdbc:sqlserver://192.168.97.99;instanceName=ACTIVOS;databaseName=Activos;user=sa;password=sa";
	
	public int callproc(String str_proc) throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(str_conn);
			System.out.println("Coneccion Realizada con Exito");
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(str_proc);
			rs.next();
			int resultado = rs.getInt("res");
			if (resultado > 0)
				return 1;
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
