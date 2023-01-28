package tateti;

public class Tablero {
	
	private char [][] tabla;
	private boolean hayGanador;
	
	public Tablero() {
		this.tabla = new char [3][3];
		this.setHayGanador(false);
	}
	
	public void limpiar() {
		for (int fila = 0;fila < this.tabla.length;fila++) {
			for (int columna = 0;columna < this.tabla[fila].length;columna++) {
				this.tabla[fila][columna] = '-';
			}
		}
	}
	
	public void imprimir() {
		System.out.println();
		for (int i = 0;i < this.tabla.length;i++) {
		    for (int j=0;j < this.tabla[i].length;j++) {
			    System.out.print("|" + this.tabla[i][j]);
		    }
		System.out.print("|");
		System.out.println();
	    }
		System.out.println();
	}
	
	public boolean verificarCasilleroVacio (int fila, int columna) {
		return this.tabla[fila][columna]=='-';
	}
	
	public void colocarFicha(Ficha ficha) {
		this.tabla[ficha.getFila()][ficha.getColumna()] = ficha.getTipo();
	}
	
	public boolean verificarSiHayGanador (int fila ,int columna) {
		if (hayGanadorEnFila(fila) || hayGanadorEnColumna(columna) || hayGanadorEnDiagonal()) {
			this.setHayGanador(true);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hayGanadorEnFila(int fila ) {
		if(this.tabla[fila][0]!= '-' && this.tabla[fila][0] == this.tabla[fila][1] && this.tabla[fila][0] == this.tabla[fila][2]) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hayGanadorEnColumna(int columna ) {
		if(this.tabla[0][columna]!= '-' && this.tabla[0][columna]== this.tabla[1][columna] && this.tabla[0][columna]== this.tabla[2][columna]) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hayGanadorEnDiagonal() {
		if((this.tabla[0][0] !='-' && this.tabla[0][0]== this.tabla[1][1] && this.tabla[1][1]== this.tabla[2][2]) || (this.tabla[2][0] != '-' && this.tabla[2][0]== this.tabla[1][1] && this.tabla[1][1]== this.tabla[0][2])) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getHayGanador() {
		return hayGanador;
	}

	private void setHayGanador(boolean hayGanador) {
		this.hayGanador = hayGanador;
	}
}
