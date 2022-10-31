package tateti;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConectaBD {
	private String conexion;
	private String usuario;
	private String contraseña;
	
	public ConectaBD(String conexion, String usuario, String contraseña) {
		this.conexion = conexion;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	public void imprimirTablaIdiomas() {
		System.out.println("Seleccione un idioma: ");
		try {
			//1- Crear conexion
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://" + this.conexion,this.usuario,this.contraseña);
			//Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tateti?serverTimezone=UTC","root","almitasol20");
			
			//2- Crear objeto STATEMENT
			Statement miStatement =miConexion.createStatement();
			//3- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery("select * from Idiomas");
			
			//4- Recorrer el ResultSet
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("codigo") + " "+ miResultSet.getString("descripcion"));
			}
		}
		
		catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
	}
	
}
