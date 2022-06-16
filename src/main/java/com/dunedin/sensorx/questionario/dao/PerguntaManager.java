package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.model.Pergunta;

import java.util.List;

public interface PerguntaManager{

	public Pergunta retrieveProximaPergunta(int usuarioId, int ordemId);
	public List<Pergunta> findAll();
	public Pergunta findPerId(int perguntaId);
	public int addPergunta(Pergunta pergunta);
	public int delPergunta(int perguntaId);
	public int ordenar(String lista);

}
