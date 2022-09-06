package tateti;

import java.util.Scanner;
import java.lang.Math;

public class Juego {

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		Tablero tablero = new Tablero();
		Estadisticas estadisticas = new Estadisticas();
		tablero.limpiar();
		imprimirBienvenida();
		tablero.imprimir();
		loopDeJuego(estadisticas, tablero, lector);
		imprimirResultado(estadisticas, tablero);
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

	private static void imprimirBienvenida() {
		System.out.println("Bienvenido al Ta-Te-Ti OOP\n");
	}
	
	private static void imprimirResultado(Estadisticas estadisticas , Tablero tablero) {
		if (tablero.getHayGanador()) {
			System.out.println("\nGana el jugador "+ (estadisticas.getTurnoDeJugador() -1));
		} else {
			System.out.println("\nEmpate");
		}
	}

}
