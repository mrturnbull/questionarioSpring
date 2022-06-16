package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.dao.ScripteManager;
import com.dunedin.sensorx.questionario.model.Scripte;
import com.dunedin.sensorx.questionario.service.ScripteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("scripteService")
@Transactional
public class ScripteServiceImpl implements ScripteService{

	@Autowired
    ScripteManager dao;

	public String add(String funcaoNome, String body){
		return dao.add(funcaoNome, body);
	}

	public Scripte find(int scriptId){
		return dao.find(scriptId);
	}
	
	public int apagar(int scriptId){
		return dao.apagar(scriptId);
	}
	
	public List<Scripte> listar(){
		return dao.listar();
	}
	
	public String editar(int scripteId, String funcaoNome, String funcaoCorpo){
		return dao.editar(scripteId, funcaoNome, funcaoCorpo);
	}

}