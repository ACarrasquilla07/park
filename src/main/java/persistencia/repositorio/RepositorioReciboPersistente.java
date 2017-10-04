package persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dominio.Recibo;
import dominio.Vehiculo;
import dominio.repositorio.RepositorioRecibo;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.ReciboEntity;
import persistencia.entidad.VehiculoEntity;

@Repository
public class RepositorioReciboPersistente implements RepositorioRecibo{
	private static final String RECIBO_FIND_ALL = "Recibo.findAll";
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
		RepositorioVehiculoPersistente repositorioVehiculoPersistente = new RepositorioVehiculoPersistente(entityManager);		
		VehiculoEntity vehiculoEntity = repositorioVehiculoPersistente.findVehiculoEtityByPlaca(recibo.getVehiculo().getPlaca());		
		ReciboEntity reciboEntity = new ReciboEntity();		
		reciboEntity.setVehiculoEntity(vehiculoEntity);
		reciboEntity.setHoraIngreso(recibo.getHoraIngreso());
		reciboEntity.setHoraSalida(recibo.getHoraSalida());
		reciboEntity.setPrecio(recibo.getPrecio());
		return reciboEntity;
	}
	
	private List<ReciboEntity> listarVehiculos(String tipo){
		Query query = entityManager.createNamedQuery(RECIBO_FIND_ALL);
		//query.setParameter("tipo", tipo);
		return query.getResultList();
	}

	@Override
	public List<Recibo> listarRecibosCarros() {
		List<ReciboEntity> listaRecibosEntity = listarVehiculos("Carro");
		List<Recibo> listaRecibos = new ArrayList<>();
		
		for (ReciboEntity reciboEntity : listaRecibosEntity) {
			Vehiculo vehiculo = VehiculoBuilder.convertirAVehiculo(reciboEntity.getVehiculoEntity());
			listaRecibos.add(new Recibo(vehiculo,reciboEntity.getHoraIngreso()));			
		}
		return listaRecibos;
	}
}
