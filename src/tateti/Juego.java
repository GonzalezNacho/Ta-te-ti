package tateti;

import java.util.Scanner;
import java.lang.Math;
import java.time.LocalDateTime;

public class Juego {

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		Tablero tablero = new Tablero();
		ConectaBD bd = new ConectaBD("localhost:3306/Tateti","root","almitasol20");
		int idioma = seleccionarIdioma(bd, lector);
		imprimirBienvenida(bd,idioma);
		String nombreJugador = solicitarNombre(bd, idioma, lector);
		int opcion = menu(bd, idioma, lector);
		while (opcion != 5) {
			switch (opcion) {
				case 1:
					idioma = seleccionarIdioma(bd, lector);
					break;
				case 2:
					tablero.limpiar();
					Estadisticas estadisticas = new Estadisticas();
					tablero.imprimir();
					LocalDateTime inicioPartida = LocalDateTime.now();
					LocalDateTime finPartida = null;
					loopDeJuego(estadisticas, tablero, lector, bd, idioma, inicioPartida);
					cargarResultadoPartida(estadisticas, tablero, inicioPartida, finPartida, nombreJugador, bd, idioma);
					break;
				case 3:
					bd.imprimirTablaResultados(idioma);
					break;
				case 4:
					bd.imprimirTablaResultadosJugador(idioma, nombreJugador);
					break;
				case 5:
					break;
			}
			opcion = menu(bd, idioma, lector);
		}
		lector.close();
	}
	
	private static void loopDeJuego(Estadisticas estadisticas, Tablero tab, Scanner lector, ConectaBD bd, int idioma, LocalDateTime inicioPartida) {
		boolean hayGanador = false;
		while (hayGanador == false && estadisticas.getJugada() < 9) {
			Ficha ficha = obtenerFicha(estadisticas, lector, tab, bd, idioma);
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
			ficha = pedirDatosDeFicha(lector,tab, bd, idioma);
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

	private static Ficha pedirDatosDeFicha(Scanner lector,Tablero tab, ConectaBD bd, int idioma) {
		boolean mostrarAviso = false;
		int fila;
		int columna;
		do {
			if (mostrarAviso) {
				int mensaje = 3;
				System.out.println(bd.imprimirMensaje(idioma, mensaje));
			}
			fila = ingresarFilaOColumna(lector, 4, 6, bd, idioma);
			columna = ingresarFilaOColumna(lector, 5, 7, bd, idioma);
			mostrarAviso = true;
		} while (!tab.verificarCasilleroVacio(fila, columna));
		Ficha ficha = new FichaX(fila,columna);
		return ficha;
	}
	
	private static int ingresarFilaOColumna (Scanner lector, int mensaje1, int mensaje2, ConectaBD bd, int idioma) {
		int valor;
		boolean filasOColumnasFueraDeRango = false;
		do {
			if (filasOColumnasFueraDeRango) {
				System.out.println(bd.imprimirMensaje(idioma, mensaje1));
			}
			System.out.println(bd.imprimirMensaje(idioma, mensaje2));
			try {
				valor = Integer.parseInt(lector.nextLine());
			} catch (NumberFormatException e) {
				int mensaje = 8;
				System.out.println(bd.imprimirMensaje(idioma, mensaje));
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
	
	private static void cargarResultadoPartida(Estadisticas estadisticas , Tablero tablero,  LocalDateTime inicioPartida,  LocalDateTime finPartida, String nombre, ConectaBD bd, int idioma ) {
		finPartida = LocalDateTime.now();
		if (tablero.getHayGanador()) {
			int mensaje = 9;
			String ganador = "";
			int ganadorEntero = estadisticas.getTurnoDeJugador() -1;
			if (ganadorEntero == 1) {
				ganador = nombre;
			} else {
				ganador = "Matrix";
			}
			System.out.println(bd.imprimirMensaje(idioma, mensaje) + " " + ganador);
			bd.cargarResultados(inicioPartida, finPartida, nombre, ganador);
		} else {
			int mensaje = 10;
			System.out.println(bd.imprimirMensaje(idioma, mensaje));
			bd.cargarResultados(inicioPartida, finPartida, nombre, "Empate");
		}
	}
	
	private static int seleccionarIdioma(ConectaBD bd, Scanner lector) {
		bd.imprimirTablaIdiomas();
		int valor = 0;
		int mensaje = 14;
		do {
			System.out.println();
			for(int i = 1; i <=4; i++) {
				System.out.println(i+ "- " +bd.imprimirMensaje(i, mensaje));
			}
			valor = Integer.parseInt(lector.nextLine());
		} while (!(valor >= 1 && valor <= 4));
		return valor;
	}
	
	private static int menu(ConectaBD bd, int idioma, Scanner lector) {
		int opcion = 0;
		boolean opcionErronea = false;
		do {
			if (opcionErronea) {
				int mensaje = 15;
				System.out.println(bd.imprimirMensaje(idioma, mensaje));
			} else {
				imprimirMenu(bd, idioma);
			}
			opcion = Integer.parseInt(lector.nextLine());
			opcionErronea = true;
		} while (!(opcion >= 1 && opcion <= 5));
		return opcion;
	}
	
	private static void imprimirMenu(ConectaBD bd, int idioma) {
		int mensaje = 15;
		System.out.println();
		for (int i =1; i <=5; i++) {
			System.out.println(i + "- " + bd.imprimirMensaje(idioma, mensaje +i));
		}
	}
}
