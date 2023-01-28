package tateti;

public abstract class Ficha {
	
	private int columna;
	private int fila;
	
	public Ficha(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	public int getColumna() {
		return columna;
	}
	
	public int getFila () {
		return fila;
	}
	
	public abstract char getTipo();
}
