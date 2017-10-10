package persistencia.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dominio.Vehiculo;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.VehiculoEntity;

@Repository
public class RepositorioVehiculoPersistente implements RepositorioVehiculo {
	private static final String VEHICULO_FIND_BY_PLACA = "Vehiculo.findByPlaca";
	EntityManager entityManager;

	public RepositorioVehiculoPersistente(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public VehiculoEntity findVehiculoEtityByPlaca(String placa) {
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_BY_PLACA);
		query.setParameter("placa", placa);
		VehiculoEntity vehiculoEntity = (VehiculoEntity) query.getSingleResult();
		return vehiculoEntity != null ? vehiculoEntity : null;
	}

	@Override
	public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = VehiculoBuilder.convertirAVehiculoEntity(vehiculo);
		entityManager.persist(vehiculoEntity);
		return vehiculo;
	}

	@Override
	public Vehiculo encontrarVehiculoPorPlaca(String placa) {
		VehiculoEntity vehiculoEntity = findVehiculoEtityByPlaca(placa);
		return vehiculoEntity != null ? VehiculoBuilder.convertirAVehiculo(vehiculoEntity) : null;
	}
}
