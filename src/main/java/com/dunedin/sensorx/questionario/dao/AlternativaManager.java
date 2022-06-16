package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.model.Alternativa;
import com.dunedin.sensorx.questionario.model.controls.Controle;
import java.util.List;

public interface AlternativaManager{

	public List<Alternativa> findAllPerPerguntaId(int perguntaId);
	public Alternativa findAlternativaPerId(int perguntaId, int alternativaId);
	public String addAlternativa(Controle controle);
	public int delAlternativa(int perguntaId, int alternativaId);
	public int getMaxAlternativaId(int perguntaId);
	public int saveRegraComparacao(int perguntaId, int scriptId, int perguntaIDdeComparacao);
	
}

