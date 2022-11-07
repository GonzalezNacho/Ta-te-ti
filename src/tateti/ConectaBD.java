package tateti;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConectaBD {
	private String conexion;
	private String usuario;
	private String contraseña;
	private Connection miConexion;
	
	
	public ConectaBD(String conexion, String usuario, String contraseña) {
		this.conexion = conexion;
		this.usuario = usuario;
		this.contraseña = contraseña;
		try {
			this.miConexion = DriverManager.getConnection("jdbc:mysql://" + this.conexion,this.usuario,this.contraseña);
		}
		
		catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
	}
	
	public void imprimirTablaIdiomas() {
		System.out.println("Seleccione un idioma: ");
		try {
			//1- Crear conexion
			//Connection miConexion = DriverManager.getConnection("jdbc:mysql://" + this.conexion,this.usuario,this.contraseña);
			//Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tateti?serverTimezone=UTC","root","almitasol20");
			
			//2- Crear objeto STATEMENT
			Statement miStatement =this.miConexion.createStatement();
			
			//3- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery("select * from Idiomas");
			
			//4- Recorrer el ResultSet
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("codigo") + " "+ miResultSet.getString("descripcion"));
			}
			
			miResultSet.close();
		}
		
		catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
	}
	
	public String imprimirMensaje(int idioma, int mensaje) {
		String mensajeAImprimir ="";
		try {
			//1- Crear conexion
			//Connection miConexion = DriverManager.getConnection("jdbc:mysql://" + this.conexion,this.usuario,this.contraseña);
			//Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tateti?serverTimezone=UTC","root","almitasol20");
			
			//2- Crear objeto STATEMENT
			PreparedStatement miStatement = this.miConexion.prepareStatement("select * from Mensaje_por_idioma where cod_idioma =? and cod_mensaje =?");
			
			//3- Establecer los parametros
			miStatement.setInt(1, idioma);
			miStatement.setInt(2, mensaje);
			
			
			//3- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery();
			
			//4- Imprimir mensaje
			while (miResultSet.next()) {
				 mensajeAImprimir += miResultSet.getString("descripcion");
			}
			
			miResultSet.close();
		}
		
		catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
		
		return mensajeAImprimir;
	}
	
}
