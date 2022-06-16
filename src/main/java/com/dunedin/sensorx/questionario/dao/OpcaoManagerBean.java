package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.dao.OpcaoManager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OpcaoManagerBean implements OpcaoManager {
	
	@PersistenceContext
	private EntityManager em;

	public OpcaoManagerBean(){};

	public int deleteAll(int perguntaId, int alternativaId){
		  
		int ret = 0; //Nao apagou

		try {
			em.createNamedQuery("Opcao.deleteAll").setParameter("perguntaId", perguntaId).setParameter("alternativaId", alternativaId).executeUpdate();
			ret = 1;
		}
		catch(Exception e){
			e.printStackTrace();	
		}

		return ret;

	}

	public int getMaxOpcaoId(int perguntaId, int alternativaId) {
			
		int maxId = 0;
		try {
		
			Object obj = em.createNamedQuery("Opcao.findMaxOpcaoId").setParameter("perguntaId", perguntaId).setParameter("alternativaId", alternativaId).getSingleResult();
			if (obj != null)
				maxId = (int)(obj);
			
			return maxId;
		}	
		catch(Exception e){
			e.printStackTrace();
			return -1;
			
		}
		
	}
	
}