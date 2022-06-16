package com.dunedin.sensorx.questionario.dao;

public interface OpcaoManager{
	
	public int getMaxOpcaoId(int perguntaId, int alternativaId);
	public int deleteAll(int perguntaId, int alternativaId);

}