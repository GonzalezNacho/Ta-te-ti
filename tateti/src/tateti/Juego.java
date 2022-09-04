package tateti;

import java.util.Scanner;
import java.lang.Math;

public class Juego {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner lector = new Scanner(System.in);
		Tablero tablero = new Tablero();
		Estadisticas estadisticas = new Estadisticas();
		tablero.limpiar();
		imprimirBienvenida();
		tablero.imprimir();
		loopDeJuego(estadisticas, tablero, lector);
		imprimirResultado(estadisticas);
		lector.close();
	}
	
	private static void loopDeJuego(Estadisticas estadisticas, Tablero tab, Scanner lector) {
		boolean hayGanador = false;
		while (hayGanador == false && estadisticas.getJugada() < 9) {
			Ficha ficha = obtenerFicha(estadisticas, lector, tab);
			tab.colocarFicha(ficha);
			tab.imprimir();
			estadisticas.aumentarJugada();
			hayGanador = tab.verificarSiHayGanador(ficha.getFila(), ficha.getColumna());
		}
	}

	private static Ficha obtenerFicha(Estadisticas stats, Scanner lector,Tablero tab) {
		Ficha ficha;
		if (stats.getTurnoDeJugador() == 1) {
			ficha = pedirDatosDeFicha(lector,tab);
		} else {
			ficha = generarDatosDeFicha(tab);
			System.out.println("\nLa jugada de la computadora es: \n");
		}
		return ficha;
	}

	private static Ficha generarDatosDeFicha(Tablero tab) {
		int fila;
		int columna;
		do {
			fila = (int)(Math.random()*2)+1;
			columna = (int)(Math.random()*2)+1;
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
			System.out.println("\ningrese la fila de la jugada: ");
			fila = Integer.parseInt(lector.nextLine());
			System.out.println("ingrese la columna de la jugada: ");
			columna = Integer.parseInt(lector.nextLine());
			mostrarAviso = true;
		} while (!tab.verificarCasilleroVacio(fila, columna));
		Ficha ficha = new FichaX(fila,columna);
		return ficha;
	}

	private static void imprimirBienvenida() {
		System.out.println("Bienvenido al Ta-Te-Ti OOP\n");
	}
	
	private static void imprimirResultado(Estadisticas estadisticas) {
		if (estadisticas.getJugada() ==9) {
			System.out.println("Empate");
		} else {
			System.out.println("Gana el jugador "+ (estadisticas.getTurnoDeJugador() -1));
		}
	}

}
