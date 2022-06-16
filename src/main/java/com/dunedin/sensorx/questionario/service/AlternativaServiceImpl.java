package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.dao.AlternativaManager;
import com.dunedin.sensorx.questionario.model.Alternativa;
import com.dunedin.sensorx.questionario.service.AlternativaService;
import com.dunedin.sensorx.questionario.model.controls.Controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("alternativaService")
@Transactional
public class AlternativaServiceImpl implements AlternativaService{

	@Autowired
    AlternativaManager dao;

    public List<Alternativa> findAllPerPerguntaId(int perguntaId){
    	return dao.findAllPerPerguntaId(perguntaId);
    }

	public Alternativa findAlternativaPerId(int perguntaId, int alternativaId){
		return dao.findAlternativaPerId(perguntaId, alternativaId);
	}

	public String addAlternativa(Controle controle){
		return dao.addAlternativa(controle);
	}

	public int delAlternativa(int perguntaId, int alternativaId){
		return dao.delAlternativa(perguntaId, alternativaId);
	}

	public int getMaxAlternativaId(int perguntaId){
		return dao.getMaxAlternativaId(perguntaId);
	}	

	public int saveRegraComparacao(int perguntaId, int scriptId, int perguntaIDdeComparacao){
		return dao.saveRegraComparacao(perguntaId, scriptId, perguntaIDdeComparacao);
	}

}