package ait.sistemas.proyecto.activos.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnecctionActivos {

	private final String str_conn = "jdbc:sqlserver://192.168.97.99;instanceName=ACTIVOS;databaseName=Activos;user=sa;password=sa";
	public int callproc(String str_proc) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(str_conn);
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
		conn.close();
		return 0;
	}
}
