package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class CEP extends Controle {
	
	public CEP(){
		this.tipoHTML = "CEP";
		this.maxLength = 9; //COM SEPARADOR, MAS GRAVA SEM
	}
	
}