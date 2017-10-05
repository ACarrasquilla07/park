package persistencia.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import dominio.Recibo;
import dominio.repositorio.RepositorioRecibo;
import persistencia.entidad.ReciboEntity;
import persistencia.entidad.VehiculoEntity;

@Repository
public class RepositorioReciboPersistente implements RepositorioRecibo {
	EntityManager entityManager;

	public RepositorioReciboPersistente(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Recibo ingresarRecibo(Recibo reciboIngreso) {
		entityManager.persist(convertirAEntity(reciboIngreso));
		return reciboIngreso;
	}

	public ReciboEntity convertirAEntity(Recibo recibo) {
		RepositorioVehiculoPersistente repositorioVehiculoPersistente = new RepositorioVehiculoPersistente(
				entityManager);
		VehiculoEntity vehiculoEntity = repositorioVehiculoPersistente
				.findVehiculoEtityByPlaca(recibo.getVehiculo().getPlaca());
		ReciboEntity reciboEntity = new ReciboEntity();
		reciboEntity.setVehiculoEntity(vehiculoEntity);
		reciboEntity.setHoraIngreso(recibo.getHoraIngreso());
		reciboEntity.setHoraSalida(recibo.getHoraSalida());
		reciboEntity.setPrecio(recibo.getPrecio());
		return reciboEntity;
	}

	@Override
	public Long numeroRecibos(String tipoVehiculo) {
		Query query = entityManager.createNamedQuery("Recibo.contar");
		query.setParameter("tipo", tipoVehiculo);
		return (Long) query.getSingleResult();
	}

}
