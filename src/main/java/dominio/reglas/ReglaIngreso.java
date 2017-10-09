package dominio.reglas;

import dominio.SolicitudIngreso;

public interface ReglaIngreso {
	public void verificarPosibilidadIngreso(SolicitudIngreso solicitudIngreso);
}
