package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.dao.RegraPrologManager;
import com.dunedin.sensorx.questionario.model.RegraProlog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class RegraPrologManagerBean implements RegraPrologManager {

	@PersistenceContext
	private EntityManager em;
	
	public RegraPrologManagerBean(){};

	public List<RegraProlog> retrieveAllRules(){
		
		try {
			//@SuppressWarnings("unchecked")
			return em.createQuery("SELECT rp FROM RegraProlog rp").getResultList();
			
		}
		catch(NoResultException nre){
			return null;	
		}
	}

	

}

