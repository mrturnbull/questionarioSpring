package com.dunedin.sensorx.questionario.dao;

import com.dunedin.sensorx.questionario.model.Usuario;

public interface UsuarioManager{

	public long    addUsuario();
	public Usuario findById(long usuarioId);
	public Usuario findByCPF(String cPF);
	//public Usuario findByEmail(String email);

}
