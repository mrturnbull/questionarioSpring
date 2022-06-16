package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class Money extends Controle {

	public Money(){
		this.tipoHTML = "MONEY";
		this.maxLength = 9; //COM SEPARADOR, MAS GRAVA SEM
	}

}