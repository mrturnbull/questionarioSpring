package com.dunedin.sensorx.questionario.service;

import com.dunedin.sensorx.questionario.model.Usuario;

public interface UsuarioService{

	public long addUsuario();
	public Usuario findById(long usuarioId);
	//public Usuario findByEmail(String email);
	public Usuario findByCPF(String cPF);

}
