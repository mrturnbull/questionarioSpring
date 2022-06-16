package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.dao.RespostaManager;
import com.dunedin.sensorx.questionario.model.Resposta;
import com.dunedin.sensorx.questionario.service.RespostaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("respostaService")
@Transactional
public class RespostaServiceImpl implements RespostaService{

	@Autowired
    RespostaManager dao;

    public int addResposta(int usuarioId, int perguntaId, String respostaIds, String respostas){
    	return dao.addResposta(usuarioId, perguntaId, respostaIds, respostas);
    }
    
	public List<Resposta> getRespostas(int usuarioId){
		return dao.getRespostas(usuarioId);
	}

	public boolean darBaixa(int usuarioId){
		return dao.darBaixa(usuarioId);
	}

	public Resposta getResposta(int usuarioId, int perguntaId, int respostaId){
		return dao.getResposta(usuarioId, perguntaId, respostaId);
	}

	public Object comparar2Respostas(int usuarioId, int perguntaId, String respostas, String nomeFuncao, String corpoFuncao, String respostaDeComparacao){
		return dao.comparar2Respostas(usuarioId, perguntaId, respostas, nomeFuncao, corpoFuncao, respostaDeComparacao);
	}

}
