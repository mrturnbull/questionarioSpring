package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.dao.UsuarioManager;
import com.dunedin.sensorx.questionario.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioManagerBean implements UsuarioManager {

	@PersistenceContext
	private EntityManager em;
	
	public UsuarioManagerBean(){};

	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long addUsuario(){

		long ret = 0; //Significa que o usu�rio n�o foi adicionado
			
		Usuario usuario = new Usuario();
		
		em.persist(usuario);
		em.flush(); 				//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<????????

		ret = usuario.getId();
		
		return ret;

	}

	public Usuario findByCPF(String cPF){
		
		Usuario u;
		
		try {
			u = (Usuario) em.createNamedQuery("Usuario.findByCPF").setParameter("cPF", cPF).getSingleResult();
			return u;
		}
		catch(NoResultException nre){
			return null;	
		}
	}

	public Usuario findById(long usuarioId){
		
		Usuario u;
		
		try {
			u = (Usuario) em.createNamedQuery("Usuario.findById").setParameter("id", usuarioId).getSingleResult();
			return u;
		}
		catch(NoResultException nre){
			return null;	
		}
	}

}
