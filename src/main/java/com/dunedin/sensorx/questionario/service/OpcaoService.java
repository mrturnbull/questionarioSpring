package com.dunedin.sensorx.questionario.service;

public interface OpcaoService{
	
	public int getMaxOpcaoId(int perguntaId, int alternativaId);
	public int deleteAll(int perguntaId, int alternativaId);

}