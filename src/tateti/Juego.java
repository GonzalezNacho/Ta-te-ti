package tateti;

import java.util.Scanner;
import java.lang.Math;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

public class Juego {

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		Tablero tablero = new Tablero();
		Estadisticas estadisticas = new Estadisticas();
		tablero.limpiar();
		LocalDateTime inicioPartida = LocalDateTime.now();
		LocalDateTime finPartida = null;
		ConectaBD bd = new ConectaBD("localhost:3306/Tateti","root","almitasol20");
		int idioma = seleccionarIdioma(bd, lector);
		imprimirBienvenida(bd,idioma);
		String nombreJugador = solicitarNombre(bd, idioma, lector);
		tablero.imprimir();
		loopDeJuego(estadisticas, tablero, lector, bd, idioma, inicioPartida);
		imprimirResultado(estadisticas, tablero, inicioPartida, finPartida, nombreJugador, bd);
		lector.close();
	}
	
	private static void loopDeJuego(Estadisticas estadisticas, Tablero tab, Scanner lector, ConectaBD bd, int idioma, LocalDateTime inicioPartida) {
		boolean hayGanador = false;
		while (hayGanador == false && estadisticas.getJugada() < 9) {
			Ficha ficha = obtenerFicha(estadisticas, lector, tab, bd, idioma);
			/*if (estadisticas.getJugada() == 0) {
				inicioPartida = LocalDateTime.now();
			}*/
			tab.colocarFicha(ficha);
			tab.imprimir();
			estadisticas.aumentarJugada();
			hayGanador = tab.verificarSiHayGanador(ficha.getFila(), ficha.getColumna());
		}
	}

	private static Ficha obtenerFicha(Estadisticas stats, Scanner lector,Tablero tab, ConectaBD bd, int idioma) {
		Ficha ficha;
		int mensaje = 2;
		if (stats.getTurnoDeJugador() == 1) {
			ficha = pedirDatosDeFicha(lector,tab);
		} else {
			ficha = generarDatosDeFicha(tab);
			System.out.println("\n"+ bd.imprimirMensaje(idioma, mensaje)+"\n");
		}
		return ficha;
	}

	private static Ficha generarDatosDeFicha(Tablero tab) {
		int fila;
		int columna;
		do {
			fila = (int)(Math.random()*3);
			columna = (int)(Math.random()*3);
		} while (!tab.verificarCasilleroVacio(fila, columna));
		Ficha ficha = new FichaO(fila,columna);
		return ficha;
	}

	private static Ficha pedirDatosDeFicha(Scanner lector,Tablero tab) {
		boolean mostrarAviso = false;
		int fila;
		int columna;
		do {
			if (mostrarAviso) {
				System.out.println("El casillero se encuentra ocupado intente nuevamente");
			}
			fila = ingresarFilaOColumna(lector, "fila");
			columna = ingresarFilaOColumna(lector, "columna");
			mostrarAviso = true;
		} while (!tab.verificarCasilleroVacio(fila, columna));
		Ficha ficha = new FichaX(fila,columna);
		return ficha;
	}
	
	private static int ingresarFilaOColumna (Scanner lector, String filaOColumna ) {
		int valor;
		boolean filasOColumnasFueraDeRango = false;
		do {
			if (filasOColumnasFueraDeRango) {
				System.out.println("El valor de la " + filaOColumna + " tiene que ser estrictamente un valor del 0 al 2 ");
			}
			System.out.println("\ningrese la " + filaOColumna + " de la jugada (del 0 al 2): ");
			try {
				valor = Integer.parseInt(lector.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("¡Cuidado! Solo puedes insertar números. ");
				valor = 3;
			}
			filasOColumnasFueraDeRango = true;
		} while (!(valor >= 0 && valor <= 2));
		return valor;
	}

	private static void imprimirBienvenida(ConectaBD bd, int idioma) {
		int mensaje = 1;
		System.out.println(bd.imprimirMensaje(idioma, mensaje));
	}
	
	private static String solicitarNombre(ConectaBD bd, int idioma, Scanner lector) {
		int mensaje = 11;
		System.out.println(bd.imprimirMensaje(idioma, mensaje));
		String nombre = lector.nextLine();
		return nombre;
	}
	
	private static void imprimirResultado(Estadisticas estadisticas , Tablero tablero,  LocalDateTime inicioPartida,  LocalDateTime finPartida, String nombre, ConectaBD bd ) {
		finPartida = LocalDateTime.now();
		if (tablero.getHayGanador()) {
			String ganador = "";
			int ganadorEntero = estadisticas.getTurnoDeJugador() -1;
			if (ganadorEntero == 1) {
				ganador = nombre;
			} else {
				ganador = "computadora";
			}
			bd.cargarResultados(inicioPartida, finPartida, nombre, ganador);
		} else {
			System.out.println("\nEmpate");
			bd.cargarResultados(inicioPartida, finPartida, nombre, "Empate");
		}
		bd.imprimirTablaResultados();
	}
	
	private static int seleccionarIdioma(ConectaBD bd, Scanner lector) {
		bd.imprimirTablaIdiomas();
		int valor = 0;
		do {
			System.out.println("\ningrese el numero correspodiente al idioma (del 1 al 4): ");
			valor = Integer.parseInt(lector.nextLine());
		} while (!(valor >= 1 && valor <= 4));
		return valor;
	}

}
