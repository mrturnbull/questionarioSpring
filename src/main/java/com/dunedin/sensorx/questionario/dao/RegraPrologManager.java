package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.model.RegraProlog;
import java.util.List;

public interface RegraPrologManager {
	
	public List<RegraProlog> retrieveAllRules();
	
}