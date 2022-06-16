package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.dao.RegraPrologManager;
import com.dunedin.sensorx.questionario.model.RegraProlog;
import com.dunedin.sensorx.questionario.service.RegraPrologService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("regraPrologService")
@Transactional
public class RegraPrologServiceImpl implements RegraPrologService{

	@Autowired
    RegraPrologManager dao;

    public List<RegraProlog> retrieveAllRules(){
    	return dao.retrieveAllRules();
    }

}
