package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class TelefoneFixo extends Controle {
	
	public TelefoneFixo(){
		this.tipoHTML = "TELEFONEFIXO";
		this.maxLength = 14; //COM SEPARADOR, MAS GRAVA SEM
	}
	
}