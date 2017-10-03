package dominio.reglas;

import dominio.Recibo;

public interface ReglaIngreso {
	public Recibo verificarPosibilidadIngreso(Recibo reciboIngreso);
}
