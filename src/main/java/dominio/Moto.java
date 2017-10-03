package dominio;

public class Moto extends Vehiculo{
	private int cilindraje;
	
	
	
	public Moto() {
	}

	public Moto(String placa) {
		super(placa);
	}

	public Moto(String placa, int cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}

	public int getCilindraje() {
		return cilindraje;
	}
}
