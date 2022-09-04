package tateti;

public class FichaX extends Ficha{
	
	
	public FichaX(int fila, int columna) {
		super(fila, columna);
	}
	
	@Override
	public char getTipo() {
		return 'X';
	}
}
