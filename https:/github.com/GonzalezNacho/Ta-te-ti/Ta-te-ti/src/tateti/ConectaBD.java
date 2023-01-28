package tateti;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
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
			//1- Crear objeto STATEMENT
			Statement miStatement =this.miConexion.createStatement();
			
			//2- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery("select * from Idiomas");
			
			//3- Recorrer el ResultSet
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
			
			//1- Crear objeto STATEMENT
			PreparedStatement miStatement = this.miConexion.prepareStatement("select * from Mensaje_por_idioma where cod_idioma =? and cod_mensaje =?");
			
			//2- Establecer los parametros
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
	
	public void cargarResultados( LocalDateTime inicioPartida,  LocalDateTime finPartida, String nombre, String ganador) {
		try {
			
			//1- Crear objeto STATEMENT
			Statement miStatement = this.miConexion.createStatement();
			
			//2- instruccion SQL
			String sql = "insert into Resultados_partidas (final_partida, comienzo_partida, Jugador_NOMBRE, ganador) values ('" + finPartida + "','" + inicioPartida + "','" + nombre + "','"+ ganador +"')";
			
			
			//3- Ejecutar la instruccion SQL
			miStatement.executeUpdate(sql);
			
		}
		
		catch(Exception e) {
			System.out.println("No conecta, al cargar resultados");
			e.printStackTrace();
		}
	}
	
	public void imprimirTablaResultados(int idioma) {
		int mensajeTitulo = 12;
		System.out.println("\n\t\t"+ imprimirMensaje(idioma, mensajeTitulo) + " \n");
		try {
			//1- Crear objeto STATEMENT
			Statement miStatement =this.miConexion.createStatement();
			
			//2- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery("select * from Resultados_partidas");
			
			//3- Recorrer el ResultSet
			int mensaje = 13;
			System.out.println(imprimirMensaje(idioma, mensaje)+"\n");
			//System.out.println("nro. de partida\t nombre\t comienzo de partida\t\t final de partida\t\t ganador\n");
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("idresultado_partida") + "\t\t\t"+ miResultSet.getString("Jugador_NOMBRE") + "\t "+ miResultSet.getString("comienzo_partida") + "\t "+ miResultSet.getString("final_partida") + "\t "+ miResultSet.getString("ganador"));
			}
			
			miResultSet.close();
		}
		
		catch(Exception e) {
			System.out.println("No conecta imprimir tabla de resultados");
			e.printStackTrace();
		}
	}
	
	public void imprimirTablaResultadosJugador(int idioma, String jugador) {
		int mensajeTitulo = 12;
		System.out.println("\n\t\t"+ imprimirMensaje(idioma, mensajeTitulo) + " \n");
		try {
			//1- Crear objeto STATEMENT
			PreparedStatement miStatement = this.miConexion.prepareStatement("select * from Resultados_partidas where Jugador_NOMBRE =?");
			
			//2- Establecer los parametros
			miStatement.setString(1, jugador);
			
			//3- Ejecutar la instruccion SQL
			ResultSet miResultSet = miStatement.executeQuery();
			
			
			//3- Recorrer el ResultSet
			int mensaje = 13;
			System.out.println(imprimirMensaje(idioma, mensaje)+"\n");
			//System.out.println("nro. de partida\t nombre\t comienzo de partida\t\t final de partida\t\t ganador\n");
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("idresultado_partida") + "\t\t\t"+ miResultSet.getString("Jugador_NOMBRE") + "\t "+ miResultSet.getString("comienzo_partida") + "\t "+ miResultSet.getString("final_partida") + "\t "+ miResultSet.getString("ganador"));
			}
			
			miResultSet.close();
		}
		
		catch(Exception e) {
			System.out.println("No conecta imprimir tabla de resultados de jugador");
			e.printStackTrace();
		}
	}
	
}
