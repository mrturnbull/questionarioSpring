package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.model.Scripte;

import java.util.List;

public interface ScripteService{
	
	public Scripte find(int scriptId);
	public String add(String funcaoNome, String body);
	public int apagar(int scriptId);
	public List<Scripte> listar();
	public String editar(int scripteId, String funcaoNome, String funcaoCorpo);

}