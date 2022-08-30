package tateti;

public class Teteti {

	public static void main(String[] args) {
		java.util.Scanner lector = new java.util.Scanner(System.in);
		String[][] tablero = new String[3][3];  
		int jugada = 0;
		int jugador = 0;
		boolean hayGanador = false;
		limpiarTablero(tablero);
		imprimirBienvenida();
		while (hayGanador==false && jugada < 9) {
			jugador = obtenerNumeroDeJugador(jugada);
            int fila = pedirPosicionDeFicha(lector,tablero,"fila", jugador);
		    int columna = pedirPosicionDeFicha(lector,tablero,"columna",jugador);
		    jugada = ponerFichaEnCasillero(tablero,fila,columna,jugador,jugada);
		    hayGanador = verificarSiHayGanador(tablero,fila,columna);
		    imprimirTablero(tablero);
		}
		imprimirResultado(jugada, jugador);
		lector.close();
	}
	
	static void imprimirBienvenida() {
		System.out.println("Bienvenido al tateti v2");
	}
	
	static void limpiarTablero(String[][] tablero) {
		for (int fila = 0;fila < tablero.length;fila++) {
			for (int columna = 0;columna < tablero[fila].length;columna++) {
				tablero[fila][columna] = "-";
			}
		}	
	}
	static int obtenerNumeroDeJugador(int jugada) {
		return ((jugada % 2) +1);
	}
	
	static int pedirPosicionDeFicha(java.util.Scanner lector,String[][]tablero,String filaOColumna, int numeroDeJugador) {
		System.out.println("Jugador " + numeroDeJugador + " ingrese la "+ filaOColumna + " de la jugada");
		int columnaOFila = Integer.parseInt(lector.nextLine());
		return columnaOFila;
	}
	
	static int ponerFichaEnCasillero(String[][] tablero,int filaUsuario,int columnaUsuario,int jugador,int jugada) {
		if (tablero[filaUsuario][columnaUsuario].equals("-")) {
			tablero[filaUsuario][columnaUsuario] = (jugador == 1 ? "X" : "O");
			return ++jugada;
		} else {
			System.out.println("Este casillero esta ocupado");
			return jugada;
		}
	}
	
	static void imprimirTablero(String[][] tablero) {
	    for (int i = 0;i < tablero.length;i++) {
		    for (int j=0;j < tablero[i].length;j++) {
			    System.out.print("|" + tablero[i][j]);
		    }
		System.out.print("|");
		System.out.println();
	    }	
	}
	static boolean verificarSiHayGanador (String[][] tablero,int fila ,int columna) {
		if (hayGanadorEnFila(tablero,fila ) || hayGanadorEnColumna(tablero,columna ) || hayGanadorEnDiagonal(tablero)) {
			return true;
		} else {
			return false;
		}
	}
	
	static boolean hayGanadorEnFila(String[][] tablero,int fila ) {
		if(!tablero[fila][0].equals("-") && tablero[fila][0].equals(tablero[fila][1]) && tablero[fila][0].equals(tablero[fila][2])) {
			return true;
		} else {
			return false;
		}
	}
	
	static boolean hayGanadorEnColumna(String[][] tablero,int columna ) {
		if(!tablero[0][columna].equals("-") && tablero[0][columna].equals(tablero[1][columna]) && tablero[0][columna].equals(tablero[2][columna])) {
			return true;
		} else {
			return false;
		}
	}
	
	static boolean hayGanadorEnDiagonal(String[][] tablero) {
		if((!tablero[0][0].equals("-") && tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2])) || (!tablero[2][0].equals("-") && tablero[2][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[0][2]))) {
			return true;
		} else {
			return false;
		}
	}	
	
	static void imprimirResultado(int jugada, int jugador) {
		if (jugada ==9) {
			System.out.println("Empate");
		} else {
			System.out.println("Gana el jugador "+ jugador);
		}
	}
}	
