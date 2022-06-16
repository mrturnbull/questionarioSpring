package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.dao.PerguntaManager;
import com.dunedin.sensorx.questionario.model.Pergunta;
import com.dunedin.sensorx.questionario.service.PerguntaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perguntaService")
@Transactional
public class PerguntaServiceImpl implements PerguntaService{

	@Autowired
    PerguntaManager dao;

    public Pergunta retrieveProximaPergunta(int usuarioId, int ordemId){
    	return dao.retrieveProximaPergunta(usuarioId, ordemId);
    }

	public List<Pergunta> findAll(){
		return dao.findAll();
	}

	public Pergunta findPerId(int perguntaId){
		return dao.findPerId(perguntaId);
	}

	public int addPergunta(Pergunta pergunta){
		return dao.addPergunta(pergunta);
	}

	public int delPergunta(int perguntaId){
		return dao.delPergunta(perguntaId);
	}

	public int ordenar(String lista){
		return dao.ordenar(lista);
	}

}
