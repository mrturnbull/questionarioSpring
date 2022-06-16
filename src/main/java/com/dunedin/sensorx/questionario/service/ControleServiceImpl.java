package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.dao.ControleManager;
import com.dunedin.sensorx.questionario.model.controls.Controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("controleService")
@Transactional
public class ControleServiceImpl implements ControleService{

	@Autowired
    ControleManager dao;

    public Controle findPerId(int perguntaId, int alternativaId){
    	return dao.findPerId(perguntaId, alternativaId);
    }
    public int getMaxId(){
    	return dao.getMaxId();
    }
    public int delete(int perguntaId, int alternativaId){
    	return dao.delete(perguntaId, alternativaId);
    }	

}