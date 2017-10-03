package dominio.reglas;

import dominio.Recibo;
import dominio.Vehiculo;

public interface ReglaIngreso {
	public Recibo verificarPosibilidadIngreso(Recibo reciboIngreso);
}
