package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.model.Scripte;

import java.util.List;

public interface ScripteManager{

	public Scripte find(int scriptId);
	public String add(String funcaoNome, String body);
	public int apagar(int scriptId);
	public List<Scripte> listar();
	public String editar(int scripteId, String funcaoNome, String funcaoCorpo);
	
}