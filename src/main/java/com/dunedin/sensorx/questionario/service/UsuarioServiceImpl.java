package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.dao.UsuarioManager;
import com.dunedin.sensorx.questionario.model.Usuario;
import com.dunedin.sensorx.questionario.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
    UsuarioManager dao;

    public long addUsuario(){
    	return dao.addUsuario();
    }

	public Usuario findById(long usuarioId){
		return dao.findById(usuarioId);
	}
	/*
	public Usuario findByEmail(String email){
		return dao.findByEmail(email);
	}
	*/
	public Usuario findByCPF(String cPF){
		return dao.findByCPF(cPF);
	}

}
