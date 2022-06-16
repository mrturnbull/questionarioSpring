package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class CPF extends Controle {

	public CPF(){
		this.tipoHTML = "CPF";
		this.maxLength = 14;//COM SEPARADOR, MAS GRAVA SEM
	}

}