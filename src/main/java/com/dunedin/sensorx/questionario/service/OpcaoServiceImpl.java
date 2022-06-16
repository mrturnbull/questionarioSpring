package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.dao.OpcaoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("opcaoService")
@Transactional
public class OpcaoServiceImpl implements OpcaoService{

	@Autowired
    OpcaoManager dao;

	public int getMaxOpcaoId(int perguntaId, int alternativaId){
		return dao.getMaxOpcaoId(perguntaId, alternativaId);
	}

	public int deleteAll(int perguntaId, int alternativaId){
		return dao.deleteAll(perguntaId, alternativaId);
	}

}