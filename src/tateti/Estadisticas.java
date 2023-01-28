package tateti;

public class Estadisticas {
	
	private int jugada;
	private int turnoDeJugador;
	
	public Estadisticas() {
		this.jugada = 0;
		this.turnoDeJugador = 0;
	}
	
	public int getJugada() {
		return this.jugada;
	}
	
	public int getTurnoDeJugador() {
		calcularTurnoDeJugador();
		return this.turnoDeJugador;
	}
	
	
	public void aumentarJugada() {
		this.jugada++;
	}
	
	private void calcularTurnoDeJugador() {
		this.turnoDeJugador = (this.jugada % 2) +1;
	}
}
