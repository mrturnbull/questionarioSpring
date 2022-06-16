package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.dao.RespostaManager;
import com.dunedin.sensorx.questionario.model.AuxResposta;
import com.dunedin.sensorx.questionario.model.Resposta;

import java.lang.NumberFormatException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import org.springframework.stereotype.Repository;

@Repository
public class RespostaManagerBean implements RespostaManager {

	@PersistenceContext
	private EntityManager em;

	public RespostaManagerBean(){};

	public int addResposta(int usuarioId, int perguntaId, String respId,  String resp){
		  
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String data = sdf.format(cal.getTime());

		String checkId[]	  = respId.split(",");
		String checkValores[] = resp.split(",");

		//Resposta resposta = null;

		try {

			List<Resposta> respostas = getRespostas(usuarioId, perguntaId);

			for (Resposta resposta : respostas){
				em.remove(resposta);
				em.flush();
			}

			for (int i=0; i < checkValores.length; i++){

				Resposta resposta = new Resposta();
			
				resposta.setUsuarioId(usuarioId);
				resposta.setPerguntaId(perguntaId);
				resposta.setRespostaId(Integer.parseInt(checkId[i]));
				resposta.setResposta(checkValores[i]);
				resposta.setDataInicio(data);

				em.persist(resposta);
				em.flush();

			}
			
		}
		catch(NumberFormatException nfe){
			nfe.printStackTrace();			
		}

		em.flush();
		
		return 1;
	
	}

	public Resposta getResposta(int usuarioId, int perguntaId, int respostaId){
		
		Resposta u = null;

		try {
			u = (Resposta) em.createNamedQuery("Resposta.findById")
							 .setParameter("usuarioId", usuarioId)
							 .setParameter("perguntaId", perguntaId)
							 .setParameter("respostaId", respostaId)
							 .getSingleResult();
		}
		catch(NoResultException nre){
			System.out.println(nre.getMessage());	
		}

		return u;

	}

	public List<Resposta> getRespostas(int usuarioId, int perguntaId){
		
		List<Resposta> u = null;

		try {
			u = (List<Resposta>) em.createNamedQuery("Resposta.findByPergunta")
							 .setParameter("usuarioId", usuarioId)
							 .setParameter("perguntaId", perguntaId)
							 .getResultList();
		}
		catch(NoResultException nre){
			System.out.println(nre.getMessage());	
		}

		return u;

	}

	public List<Resposta> getRespostas(int usuarioId){
		
		List u = null;

		String hql =  "";

		hql += " from Resposta";
		hql += " where usuarioId = :usuarioId";
		hql += " order by perguntaId";
				
		try {
			u = (List<Resposta>) em.createQuery(hql).setParameter("usuarioId", usuarioId).getResultList();
		}
		catch(NoResultException nre){
			System.out.println(nre.getMessage());	
		}

		return u;

	}

	public int getMaxPerguntaId(int usuarioId){
		
		int u;
		
		try {
			u = (int) em.createNamedQuery("Resposta.getMaxPerguntaId").setParameter("usuarioId", usuarioId).getSingleResult();
			return u;
		}
		catch(NoResultException nre){
			nre.printStackTrace();
			return 0;	
		}

	}

	public boolean darBaixa(int usuarioId){
		
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		String sql = "";
		boolean ret = false;
		
		sql += " UPDATE UsuarioResposta";
		sql += " SET DataFim = '" + sdf.format(cal.getTime()) + "'";
		sql += " WHERE UsuarioId = " + usuarioId;
		sql += "   AND DataFim IS NULL";
		
		try {
			em.createNativeQuery(sql).executeUpdate();
			ret = true;
		}
		catch(Exception e) {
			ret = false;
		}
		
		return ret;
		
	}




	public Object comparar2Respostas(int usuarioId, 
									 int perguntaId,
									 String respostas,
									 String nomeFuncao,
									 String corpoFuncao,
									 String respostaDeComparacao){

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");		     

		try {
			
			engine.eval(corpoFuncao);
			Invocable invocable = (Invocable) engine;
			return invocable.invokeFunction(nomeFuncao, respostaDeComparacao, respostas);

		}
		catch(ScriptException se){
			System.out.println(se.getMessage());
			return se.getMessage();
		}
		catch(NoSuchMethodException nsme){
			System.out.println(nsme.getMessage());
			return nsme.getMessage();
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////
	//
	// BACKOFFICE - 
	//
	//
	////////////////////////////////////////////////////////////////////////
	/*
	public List<AuxResposta> bkRetrieveRespostasPerEmail(String email){

	    String sql = "";
	
	    sql += " SELECT u.UsuarioId AS UsuarioId, u.Email, pe.Enunciado, ur.Resposta, ur.DataInicio AS DataInicio, ur.DataFim AS DataFim";
	    sql += " FROM UsuarioResposta ur";
	    sql += " INNER JOIN Usuario u ON ur.Usuarioid = u.Usuarioid";
	    sql += " INNER JOIN PerguntaEnunciado pe ON ur.Perguntaid = pe.PerguntaEnunciadoid";
	    //sql += " INNER JOIN PerguntaAlternativa pa ON ur.Perguntaid = pa.Perguntaid";
	    
	    if (!email.trim().equals("")) {
	    		sql += " WHERE u.Email = :email";
	    }
	    
	    sql += " ORDER BY ur.DataInicio, ur.PerguntaID";
	
	    try {
	    	
	    	List<AuxResposta> lista = null;
	    		
	    	
		    	if (!email.trim().equals("")) {
		    		
			    	lista = (List<AuxResposta>) em.createNativeQuery(sql).setParameter("email", email).getResultList();
		    }
		    	else {
			    
			    lista = (List<AuxResposta>) em.createNativeQuery(sql).getResultList();
		    	}
	    		
		    	

		    	return lista;

		}
		catch(NoResultException nre){
			return null;	
		}

	}
	*/

	/*
	public List<String> retrieveRespostasProlog(int usuarioId){

	    String sql = "";
	
	    sql += " SELECT pa.prolog";
	    sql += " FROM PerguntaEnunciado pe";
	    sql += " INNER JOIN PerguntaAlternativa pa ON pe.PerguntaEnunciadoID = pa.PerguntaID";
	    sql += " INNER JOIN UsuarioResposta ur ON pa.AlternativaPerguntaID = ur.RespostaID";
	    sql += " WHERE ur.UsuarioId = :usuarioId";
	    sql += " AND pa.AlternativaPerguntaId = (";
	    sql += "      SELECT ur2.RespostaID";
	    sql += " 	  FROM UsuarioResposta ur2, PerguntaEnunciado pe2";
	    sql += " 	  WHERE ur2.UsuarioId = :usuarioId AND ur2.PerguntaId = pe2.PerguntaEnunciadoID AND pe2.PerguntaEnunciadoID = pe.PerguntaEnunciadoID";
	    sql += " 	    AND ur2.DataFim IS NULL";
	    sql += "	  LIMIT 1";	
	    sql += " )";
	    sql += " GROUP BY pa.prolog";
	    sql += " HAVING pa.prolog IS NOT NULL";
	
	    try {
	    	
		    	@SuppressWarnings("unchecked")
		    	List<String> listaProlog = (List<String>) em.createNativeQuery(sql).setParameter("usuarioId", usuarioId).getResultList();

		    	return listaProlog;

		}
		catch(NoResultException nre){
			return null;	
		}

	}
	*/
}
