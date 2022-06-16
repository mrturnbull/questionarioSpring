package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.model.Resposta;
import java.util.List;

public interface RespostaService{

	public int addResposta(int usuarioId, int perguntaId, String respostaIds, String respostas);
	public boolean darBaixa(int usuarioId);
	public List<Resposta> getRespostas(int usuarioId);
	public Resposta getResposta(int usuarioId, int perguntaId, int respostaId);
	public Object comparar2Respostas(int usuarioId, int perguntaId, String respostas, String nomeFuncao, String corpoFuncao, String respostaDeComparacao);

}
