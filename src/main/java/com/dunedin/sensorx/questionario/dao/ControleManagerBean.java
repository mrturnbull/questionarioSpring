package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.dao.ControleManager;
import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ControleManagerBean implements ControleManager {
	
	@PersistenceContext
	private EntityManager em;

	public ControleManagerBean(){};

	public Controle findPerId(int perguntaId, int alternativaId){
		
		Controle u;
		
		try {
			u = (Controle) em.createNamedQuery("Controle.findAlternativaPerId").setParameter("perguntaId", perguntaId).setParameter("alternativaId", alternativaId).getSingleResult();
			return u;
		}
		catch(NoResultException nre){
			return null;	
		}
	}
	
	public int getMaxId() {
		
		int maxId = 0;
		try {
		
			Object obj = em.createNamedQuery("Controle.findMaxId").getSingleResult();
			if (obj != null)
				maxId = (int)(obj);
			
			return maxId;
		}	
		catch(Exception e){
			e.printStackTrace();
			return -1;
			
		}
		
	}
		
	public int delete(int perguntaId, int alternativaId){

		int ret = 0; //Nao apagou

		try {
			em.createNamedQuery("Controle.delAlternativa").setParameter("perguntaId", perguntaId).setParameter("alternativaId", alternativaId).executeUpdate();
			ret = 1;
		}
		catch(Exception e){
			e.printStackTrace();	
		}

		return ret;

	}	
	
}