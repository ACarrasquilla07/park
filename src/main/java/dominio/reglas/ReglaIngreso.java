package dominio.reglas;

import dominio.Recibo;
import dominio.SolicitudIngreso;

public interface ReglaIngreso {
	public Recibo verificarPosibilidadIngreso(SolicitudIngreso solicitudIngreso);
}
