package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.model.controls.Controle;

public interface ControleManager{
	
	public Controle findPerId(int perguntaId, int alternativaId);
    public int getMaxId();    	
    public int delete(int perguntaId, int alternativaId);	
		
}