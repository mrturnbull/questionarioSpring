package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.model.controls.Controle;

public interface ControleService{
	
	public Controle findPerId(int perguntaId, int alternativaId);
    public int getMaxId();    	
    public int delete(int perguntaId, int alternativaId);	
		
	
}