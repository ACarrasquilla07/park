package persistencia.entidad;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "Recibo")
@NamedQueries({
	@NamedQuery(name = "Recibo.findByPlaca", query = "SELECT recibo from Recibo recibo where recibo.vehiculoEntity.placa = :placa"),
	@NamedQuery(name = "Recibo.findAll", query = "SELECT recibo from Recibo recibo"),
	@NamedQuery(name="Recibo.contar", query = "SELECT COUNT(*) from  Recibo recibo WHERE recibo.vehiculoEntity.tipo = :tipo")
})
public class ReciboEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_VEHICULO", referencedColumnName = "id")
	private VehiculoEntity vehiculoEntity;

	@Column
	private Calendar horaIngreso;

	@Column
	private Calendar horaSalida;

	@Column
	private double precio;

	public void setHoraIngreso(Calendar horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	
	public void setHoraSalida(Calendar horaSalida) {
		this.horaSalida = horaSalida;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setVehiculoEntity(VehiculoEntity vehiculoEntity) {
		this.vehiculoEntity = vehiculoEntity;
	}
}
