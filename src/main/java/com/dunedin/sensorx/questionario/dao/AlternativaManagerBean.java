package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.dao.AlternativaManager;
import com.dunedin.sensorx.questionario.model.Alternativa;
import com.dunedin.sensorx.questionario.model.controls.Controle;
import com.dunedin.sensorx.questionario.model.controls.Opcao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryHint;
import org.springframework.stereotype.Repository;

@Repository
public class AlternativaManagerBean implements AlternativaManager {
	
	@PersistenceContext
	private EntityManager em;

	public AlternativaManagerBean(){};
	
	public List<Alternativa> findAllPerPerguntaId(int perguntaId){
		
		List<Alternativa> u = null;
		
		try {
			u = (List<Alternativa>) em.createNamedQuery("Alternativa.findAllPerPerguntaId").setParameter("perguntaId", perguntaId).getResultList();
			//u = (List<Alternativa>) em.createNamedQuery("Alternativa.findAllPerPerguntaId").getResultList();
		}
		catch(NoResultException nre){
				
		}

		return u;

	}
	
	public Alternativa findAlternativaPerId(int perguntaId, int alternativaId){
		
		Alternativa u;
		
		try {
			u = (Alternativa) em.createNamedQuery("Alternativa.findAlternativaPerId").setParameter("perguntaId", perguntaId).setParameter("alternativaId", alternativaId).getSingleResult();
			//u = (List<Alternativa>) em.createNamedQuery("Alternativa.findAllPerPerguntaId").getResultList();
			return u;
		}
		catch(NoResultException nre){
			return null;	
		}
	}
	
	public int getMaxAlternativaId(int perguntaId) {
		
		int maxId = 0;
		try {
		
			Object obj = em.createNamedQuery("Alternativa.findMaxId").setParameter("perguntaId", perguntaId).getSingleResult();
			if (obj != null)
				maxId = (int)(obj);
			
			return maxId;
		}	
		catch(Exception e){
			e.printStackTrace();
			return -1;
			
		}
		
	}
	
	public String addAlternativa(Controle controle){

		Alternativa alternativa = new Alternativa();

		int perguntaId = controle.getPerguntaId();
		int alternativaId = getMaxAlternativaId(controle.getPerguntaId()) + 1;

		controle.setAlternativaId(alternativaId);

		alternativa.setPerguntaId(perguntaId);
		alternativa.setId(alternativaId);
		alternativa.setDescricao(controle.getDescricao());
		alternativa.setControle(controle);
		alternativa.setVisivel(1);

		em.persist(alternativa);
		em.flush();
	  
		return perguntaId + "," + alternativaId;
		
	}
	
	public int delAlternativa(int perguntaId, int alternativaId){

		int ret = 0; //Nao apagou

		try {
			em.createNamedQuery("Alternativa.delAlternativa").setParameter("perguntaId", perguntaId).setParameter("alternativaId", alternativaId).executeUpdate();
			ret = 1;
		}
		catch(Exception e){
			e.printStackTrace();	
		}

		return ret;

	}

	public int saveRegraComparacao(int perguntaId, int scriptId, int perguntaIDdeComparacao){
		
		/*
		TEM QUE SER BIDIRECAO
		*/

		int ret = 0;

		try {

			/* Num sentido */
			em.createNamedQuery("Alternativa.saveRegraComparacao")
			  .setParameter("perguntaIDdeComparacao", perguntaIDdeComparacao)
			  .setParameter("scriptIDdeComparacao", scriptId)
			  .setParameter("perguntaId", perguntaId)
			  .executeUpdate();

			/* No outro sentido */  
			em.createNamedQuery("Alternativa.saveRegraComparacao")
			  .setParameter("perguntaIDdeComparacao", perguntaId)
			  .setParameter("scriptIDdeComparacao", scriptId)
			  .setParameter("perguntaId", perguntaIDdeComparacao)
			  .executeUpdate();

			ret = 1;
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ret;
		
	}

}

