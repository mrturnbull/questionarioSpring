package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.dao.ScripteManager;
import com.dunedin.sensorx.questionario.model.Scripte;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryHint;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Repository;

@Repository
public class ScripteManagerBean implements ScripteManager {
	
	@PersistenceContext
	private EntityManager em;

	public ScripteManagerBean(){};	
	
	public String add(String funcaoNome, String body){

		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        String ret = "0";       

		try {
			
			/* Testando se a funcao nao contem erros de sintaxe */
			engine.eval(body);

			Scripte scripte = new Scripte();
			scripte.setNome(funcaoNome);
			scripte.setBody(body);

			em.persist(scripte);
			em.flush();

			ret = "1";

		}
    	catch(ScriptException se){
       		System.out.println(se.getMessage());
       		ret = se.getMessage();
       	}
	  
		return ret;
		
	}

	public Scripte find(int scripteId){
		
		Scripte u = null;
				
		try {
			u = (Scripte) em.createNamedQuery("Scripte.find").setParameter("scriptId", scripteId).getSingleResult();
		}
		catch(NoResultException nre){
			System.out.println(nre.getMessage());	
		}

		return u;

	}

	public String editar(int scripteId, String funcaoNome, String body){

		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        String ret = "0";       

		try {
			
			/* Testando se a funcao nao contem erros de sintaxe */
			engine.eval(body);

			Scripte scripte = find(scripteId);
			scripte.setNome(funcaoNome);
			scripte.setBody(body);

			em.persist(scripte);
			em.flush();

			ret = "1";

		}
    	catch(ScriptException se){
       		System.out.println(se.getMessage());
       		ret = se.getMessage();
       	}
	  
		return ret;
		
	}

	public List<Scripte> listar(){
		
		List u = null;
				
		try {
			u = (List<Scripte>) em.createNamedQuery("Scripte.list").getResultList();
		}
		catch(NoResultException nre){
			System.out.println(nre.getMessage());	
		}

		return u;

	}

	public int apagar(int scripteId){

		int ret = 0;
		
		try {
			em.createNamedQuery("Scripte.delete").setParameter("scriptId", scripteId).executeUpdate();
			ret = 1;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ret;
		
	}
		
}

