package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.model.Alternativa;
import com.dunedin.sensorx.questionario.model.controls.Controle;
import java.util.List;

public interface AlternativaService{

	public List<Alternativa> findAllPerPerguntaId(int perguntaId);
	public Alternativa findAlternativaPerId(int perguntaId, int alternativaId);
	public String addAlternativa(Controle controle);
	public int delAlternativa(int perguntaId, int alternativaId);
	public int getMaxAlternativaId(int perguntaId);
	public int saveRegraComparacao(int perguntaId, int scriptId, int perguntaIDdeComparacao);

}

