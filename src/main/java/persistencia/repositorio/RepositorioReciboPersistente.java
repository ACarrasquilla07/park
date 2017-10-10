package persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import dominio.Recibo;
import dominio.Vehiculo;
import dominio.repositorio.RepositorioRecibo;
import persistencia.builder.ReciboBuilder;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.ReciboEntity;
import persistencia.entidad.VehiculoEntity;

@Repository
public class RepositorioReciboPersistente implements RepositorioRecibo {
	private static final String RECIBO_FIND_ACTIVO_BY_PLACA = "Recibo.findActivoByPlaca";
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
	
	@SuppressWarnings("rawtypes")
	private ReciboEntity findReciboActivoByPlaca(String placa) {
		Query query = entityManager.createNamedQuery(RECIBO_FIND_ACTIVO_BY_PLACA);
		query.setParameter("placa", placa);
		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (ReciboEntity) resultList.get(0) : null;
	}
	
	@Override
	public Recibo obtenerReciboActivoPorPlaca(String placa) {
		ReciboEntity reciboEntity = findReciboActivoByPlaca(placa);
		return ReciboBuilder.convertirARecibo(reciboEntity != null ? reciboEntity : null);
	}

	@Override
	public void actualizarReciboParaSalida(Recibo recibo) {
		ReciboEntity reciboEntity = findReciboActivoByPlaca(recibo.getVehiculo().getPlaca());
		if(reciboEntity != null) {
			reciboEntity.setHoraSalida(recibo.getHoraSalida());
			reciboEntity.setPrecio(recibo.getPrecio());
		}
	}
	

	@Override
	public List<Vehiculo> listarVehiculosEnParqueadero() {
		List<VehiculoEntity> listaVehiculosEntityEnPark = listarVehiculosEntityEnParqueadero();
		List<Vehiculo> listaVehiculosEnParqueadero = new ArrayList<>();
		for (VehiculoEntity vehiculoEntity : listaVehiculosEntityEnPark) {
			Vehiculo vehiculo = VehiculoBuilder.convertirAVehiculo(vehiculoEntity);
			listaVehiculosEnParqueadero.add(vehiculo);
			
		}
		return listaVehiculosEntityEnPark != null ? listaVehiculosEnParqueadero : null;
	}
	
	private List<VehiculoEntity> listarVehiculosEntityEnParqueadero(){
		Query query = entityManager.createNamedQuery("Recibo.findVehiculosEnParqueadero");
		List<VehiculoEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList : null;
	}
}
