package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class CNPJ extends Controle {

	public CNPJ(){
		this.tipoHTML = "CNPJ";
		this.maxLength = 19; //COM SEPARADOR, MAS GRAVA SEM
	}

}